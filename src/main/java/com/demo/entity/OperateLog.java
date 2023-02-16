package com.demo.entity;
/*
 * @Author: CaseyL
 * @Description: com.demo.entity
 * @Date: 2023/2/16 15:59
 * */

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "oper_log")
public class OperateLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String operName;
    private String operType;
    private Date operTime;
    private String result;
}
