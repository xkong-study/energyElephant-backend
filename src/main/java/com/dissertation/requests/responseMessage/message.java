package com.dissertation.requests.responseMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class message {
    private String message;
    private Integer Code;
}
