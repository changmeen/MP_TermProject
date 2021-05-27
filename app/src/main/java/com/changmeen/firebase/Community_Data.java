package com.changmeen.firebase;

import java.io.Serializable;
import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Community_Data implements Serializable {
    private String title;
    private String token;
    private String username;
    private String content;
    private String DBKEy;
    private Reply reply;
}
