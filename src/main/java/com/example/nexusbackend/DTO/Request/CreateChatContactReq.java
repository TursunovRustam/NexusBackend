package com.example.nexusbackend.DTO.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateChatContactReq {
    @NotNull
    private Long phone;
    @NotNull
    private String chatName;
}
