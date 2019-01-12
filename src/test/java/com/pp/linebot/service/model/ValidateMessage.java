package com.pp.linebot.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidateMessage {
    private String message;
    private String expectResult;
}
