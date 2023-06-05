package com.dissertation.requests.pojo;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class requestList {
    @Id
    private String id;
    private String key;

    @JsonGetter("id")
    public String getId(){
        return id;
    }
}
