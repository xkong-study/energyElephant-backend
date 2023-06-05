package com.dissertation.requests.controller;

import com.amazonaws.Response;
import com.dissertation.requests.responseMessage.message;
import com.dissertation.requests.service.requestListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * The type Request list controller.
 */
@RestController
@RequestMapping("/api")
public class requestListController {
    private requestListService requestListService;

    @Autowired
    public void setRequestListService(requestListService requestListService) {
        this.requestListService = requestListService;
    }

    @GetMapping("/requests/download/{filename}")
    public ResponseEntity<byte[]> getRequestList(@PathVariable String filename) throws IOException {
        return requestListService.getRequestList(filename);
    }

    @PostMapping("/requests/upload")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file, @RequestPart("filename") String filename) {
        try {
            requestListService.uploadRequestList(file, filename);
            return ResponseEntity.status(HttpStatus.CREATED).body("File uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        }
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.status(200).body("success");
    }

}
