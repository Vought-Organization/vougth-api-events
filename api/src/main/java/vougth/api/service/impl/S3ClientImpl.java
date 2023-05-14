package vougth.api.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import vougth.api.service.S3ClientService;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3ClientImpl implements S3ClientService {

    private final AmazonS3Client amazonS3Client;

    @Value("${application.bucket.name}")
    private String bucketName;

    @Override
    public String uploadFile(MultipartFile file) {
        validationFile(Objects.requireNonNull(file.getOriginalFilename()));
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String key = UUID.randomUUID() + "." + extension;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            amazonS3Client.putObject(bucketName, key, file.getInputStream(), metadata);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error upload file to S3 bucket AWS");
        }
        amazonS3Client.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
        return amazonS3Client.getResourceUrl(bucketName, key);
    }

    private void validationFile(String file) {
        if (!file.endsWith(".jpg") && !file.endsWith(".png") && !file.endsWith(".gif")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Incorrect file type, JPG or PNG or GIF required."
            );
        }
    }
}
