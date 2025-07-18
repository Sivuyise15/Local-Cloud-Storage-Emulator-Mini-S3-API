package org.example.localcloudstorageemulatorminis3api.Service;
import java.io.*;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileService {

    Stream<Path> listFiles();
    void deleteFile(String filename) throws IOException;
    Path getFilePath(String filename);
}
