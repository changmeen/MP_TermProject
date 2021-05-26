package com.changmeen.firebase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class contents {
    private int type;
    private Object object;

    public contents(int type) {
        this.type=type;
    }
}
