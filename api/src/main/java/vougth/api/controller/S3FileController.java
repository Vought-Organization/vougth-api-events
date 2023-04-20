package vougth.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vougth.api.service.S3Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class S3FileController {

    @Autowired
    private S3Service s3Service;

    @PostMapping()
    @RequestMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> upload(@RequestPart("file") MultipartFile file) {
        String publicUrl = s3Service.saveFile(file);
        Map<String, String> response = new HashMap<>();
        response.put("publicURL", publicUrl);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable("filename") String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", MediaType.ALL_VALUE);
        headers.add("Content-Disposition", "attachment; filename=" + filename);
        byte[] bytes = s3Service.downloadFile(filename);
        return ResponseEntity.status(200).headers(headers).body(bytes);
    }

    @DeleteMapping("/{filename}")
    public ResponseEntity<Void> deleteFile(@PathVariable("filename") String filename) {
        s3Service.deleteFile(filename);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/all-files")
    public ResponseEntity<List<String>> getAllFiles() {
        List<String> files = s3Service.listAllFiles();
        return ResponseEntity.status(200).body(files);
    }
}
