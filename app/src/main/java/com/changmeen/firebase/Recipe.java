package com.changmeen.firebase;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recipe implements Serializable {
    private String name;
    private String image;
    private String ingredient;
    private String recipe;
    private String recUrl;

}
