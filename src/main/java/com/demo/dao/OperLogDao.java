package com.demo.dao;
/*
 * @Author: CaseyL
 * @Description: com.demo.dao
 * @Date: 2023/2/16 16:21
 * */

import com.demo.entity.OperateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperLogDao extends JpaRepository<OperateLog, Integer> {
    @Override
    List<OperateLog> findAll();

    List<OperateLog> findAllByOperName(String name);

}
