package com.southwind.springboottest.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 通过@Entity将实体类和数据库的表book进行映射绑定;
 * 通过@Data是lombok的注解，自动生成各种各样的get方法;
 * 通过@Id是给属性加主键绑定;
 * 通过 @GeneratedValue给数据库自增的id属性进行映射，否则报错
 */
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String sex;
    private String birthday;
}
