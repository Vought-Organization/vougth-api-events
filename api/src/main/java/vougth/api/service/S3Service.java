package vougth.api.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class S3Service implements FileServiceImpl {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3Client amazonS3Client;

    //    @Override
//    public String saveFile(MultipartFile file) {
//        String originalFileName = file.getOriginalFilename();
//        try {
//            File file1 = convertMultiPartToFile(file);
//            PutObjectResult putObjectResult = s3.putObject(bucketName, originalFileName, file1);
//            return putObjectResult.getContentMd5();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


    @Override
    public String saveFile(MultipartFile file) {
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String key = UUID.randomUUID() + "." + extension;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            amazonS3Client.putObject(bucketName, key, file.getInputStream(), metadata);
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error occured while uploading the file"
            );
        }
        amazonS3Client.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
        return amazonS3Client.getResourceUrl(bucketName, key);
    }

    @Override
    public byte[] downloadFile(String filename) {
        S3Object object = amazonS3Client.getObject(bucketName, filename);
        S3ObjectInputStream objectContent = object.getObjectContent();
        try {
            return IOUtils.toByteArray(objectContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteFile(String filename) {
        amazonS3Client.deleteObject(bucketName, filename);
        return "File deleted";
    }

    @Override
    public List<String> listAllFiles() {
        ListObjectsV2Result listObjectsV2Result = amazonS3Client.listObjectsV2(bucketName);
        return listObjectsV2Result.getObjectSummaries().stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
