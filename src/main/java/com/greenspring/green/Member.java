package com.greenspring.green;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private int id;
    private String uesrname;
    private String password;
    private String email;
}
