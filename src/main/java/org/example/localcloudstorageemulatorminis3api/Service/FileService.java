package org.example.localcloudstorageemulatorminis3api.Service;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileService {

    Stream<Path> listFiles(String path);
    void deleteFile(String filename) throws IOException;
    Resource loadFileAsResource(String filename) throws MalformedURLException, FileNotFoundException;
    Path getFilePath(String filename);
}
