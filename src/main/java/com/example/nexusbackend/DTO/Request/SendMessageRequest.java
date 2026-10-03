package com.example.nexusbackend.DTO.Request;

import lombok.Data;

import java.util.UUID;

@Data
public class SendMessageRequest {
    private UUID chatId;
    private String content;
}
