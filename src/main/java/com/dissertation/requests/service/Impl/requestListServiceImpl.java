package com.dissertation.requests.service.Impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.dissertation.requests.config.AwsS3Config;
import com.dissertation.requests.pojo.requestList;
import com.dissertation.requests.service.requestListService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

/**
 * The type Request list service.
 */
@Service
@Slf4j
public class requestListServiceImpl implements requestListService {
    private AwsS3Config s3Config;
    private MongoTemplate mongoTemplate;

    @Autowired
    public void setAmazonS3Client(AwsS3Config s3Config){
        this.s3Config = s3Config;
    }

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Value("${aws.s3.bucket}")
    private String bucketName;
    @Override
    public ResponseEntity<byte[]> getRequestList(String id) throws IOException {
        AmazonS3 s3client = s3Config.getS3Client();
        //Get it from mongodb
        String key = "7bd32ec5-afc4-4886-b558-29f7294b2b3a.pdf";
        S3Object s3Object = s3client.getObject(new GetObjectRequest(bucketName, id));
        byte[] content = IOUtils.toByteArray(s3Object.getObjectContent());
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(content.length);
        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }

    @Override
    public void uploadRequestList(MultipartFile file, String fileName) throws IOException {
        AmazonS3 s3client = s3Config.getS3Client();
        File convertedFile = convertMultipartFileToFile(file);
        s3client.putObject(new PutObjectRequest(bucketName, fileName, convertedFile));
        requestList requestList = new requestList(file.getOriginalFilename(), fileName);
        mongoTemplate.insert(requestList);
        deleteTempFile(convertedFile.toPath());
    }

    private String generateFileName(String originalFileName) {
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        return UUID.randomUUID().toString() + fileExtension;
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile("temp", null);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        }
        return tempFile;
    }

    private void deleteTempFile(Path path) throws NoSuchFieldError, IOException {
        Files.delete(path);
    }

    @Override
    public void json() throws JsonProcessingException {
        requestList requestlist = new requestList("id", "key");
        String result = new ObjectMapper().writeValueAsString(requestlist);
        log.warn(result);
    }

}
