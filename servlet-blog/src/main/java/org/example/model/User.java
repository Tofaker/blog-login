package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2020-12-06;   Time: 23:24
 */
@Getter
@Setter
public class User {//user类
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private Date birthday;
    private String phoneNumber;
    private String email;
    private String head;
}
