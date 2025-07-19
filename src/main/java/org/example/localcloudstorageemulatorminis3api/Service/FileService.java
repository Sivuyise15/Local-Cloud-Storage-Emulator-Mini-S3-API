package org.example.localcloudstorageemulatorminis3api.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileService {

    Stream<Path> listFiles(String path);
    void deleteFile(String filename) throws IOException;
    void downloadFile(String filename) throws IOException;
    Path getFilePath(String filename);
}
