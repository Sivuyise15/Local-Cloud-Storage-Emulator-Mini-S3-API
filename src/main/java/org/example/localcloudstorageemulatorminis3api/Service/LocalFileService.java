package org.example.localcloudstorageemulatorminis3api.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class LocalFileService implements FileService {

    private final Path rootDirectory;

    public LocalFileService(@Value("${storage.root.path}") String rootPath) {
        this.rootDirectory = Paths.get(rootPath);
        try {
            Files.createDirectories(rootDirectory); // ensure the root dir exists
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage root", e);
        }
    }

    @Override
    public Stream<Path> listFiles(String subDirectory) {
        try {

            Path actualPath = (subDirectory == null || subDirectory.isBlank())
                    ? rootDirectory
                    : rootDirectory.resolve(subDirectory);

            System.out.println(actualPath);
            return Files.walk(actualPath, 1)
                    .filter(path -> !path.equals(actualPath))
                    .map(actualPath::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Failed to list files", e);
        }
    }

    @Override
    public void deleteFile(String filename){
        try {
            Path filePath = getFilePath(filename);
            Files.deleteIfExists(filePath);
            System.out.println("File deleted: " + filePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not delete file: " + filename, e);
        }
    }

    @Override
    public void downloadFile(String filename) throws IOException {
        Path filePath = rootDirectory.resolve(filename);
    }

    @Override
    public Path getFilePath(String filename){
        return rootDirectory.resolve(filename).normalize();
    }
}
