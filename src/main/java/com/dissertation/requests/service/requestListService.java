package com.dissertation.requests.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * The interface Request list service.
 */
public interface requestListService {
    /**
     * Gets request list.
     *
     * @param id the id
     * @return the request list
     * @throws IOException the io exception
     */
    public ResponseEntity<byte[]> getRequestList(String id) throws IOException;
    public void uploadRequestList(MultipartFile file, String filename) throws IOException;
    void json() throws JsonProcessingException;
}
