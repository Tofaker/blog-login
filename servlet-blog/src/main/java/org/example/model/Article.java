package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2020-12-06;   Time: 14:49
 */
@Getter
@Setter
@ToString
public class Article {//文章类
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private Date createTime;
    private Integer viewCount; 
}






