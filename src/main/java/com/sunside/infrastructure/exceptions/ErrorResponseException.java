package com.sunside.infrastructure.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ErrorResponseException {
    private int status;
    private String error;
    private List<String> messages;
    private LocalDateTime date;

    public ErrorResponseException(int status, String error, List<String> messageList){
        this.status = status;
        this.error = error;
        this.messages = messageList;
        this.date = LocalDateTime.now();
    }
}
