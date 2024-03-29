package vougth.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileServiceImpl {
    String saveFile(MultipartFile file) throws IOException;

    byte[] downloadFile(String filename);

    String deleteFile(String filename);

    List<String> listAllFiles();

}
