package org.example.localcloudstorageemulatorminis3api.Controller;

import org.example.localcloudstorageemulatorminis3api.Service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    /* Listing the files **/
    @GetMapping
    public ResponseEntity<List<String>> listFiles(@RequestParam(value = "path", required = false) String subDirectory) {
        try (Stream<Path> fileStream = fileService.listFiles(subDirectory)) {
            List<String> files = fileStream
                    .map(Path::toString) // map(Path -> Path.toString()), Practising shorthands
                    .collect(Collectors.toList());
            return ResponseEntity.ok(files);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /* Deleting a specified file **/
    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<String> deleteFile(@PathVariable String filename) {
        try {
            fileService.deleteFile(filename);
            return ResponseEntity.ok("File deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting file: " + e.getMessage());
        }
    }

    /* Downloading the file **/
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam(value = "filename", required = true) String filename) throws IOException {
        Resource resource = fileService.loadFileAsResource(filename);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
