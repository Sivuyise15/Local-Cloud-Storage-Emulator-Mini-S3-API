package org.example.localcloudstorageemulatorminis3api.Service;
import java.io.*;
import java.nio.file.Path;
import java.util.List;

public interface FileService {

    boolean deleteFile(String filename) throws IOException;
    Path getFilePath(String filename);
}
