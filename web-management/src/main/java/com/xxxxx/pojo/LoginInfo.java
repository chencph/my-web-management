package com.xxxxx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//登入結果
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {

    private Integer id;
    private String username;
    private String name;
    private String token;

}
