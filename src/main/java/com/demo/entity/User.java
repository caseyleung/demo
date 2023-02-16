package com.demo.entity;
/*
 * @Author: CaseyL
 * @Description: com.demo.entity
 * @Date: 2023/2/16 12:40
 * */


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private double money;


}
