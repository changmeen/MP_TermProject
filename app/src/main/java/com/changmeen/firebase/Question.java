package com.changmeen.firebase;
import lombok.Data;

@Data
public class Question {
    private String title;
    private String content;
    private String Token;
    private String username;
    private String DBKey;
}
