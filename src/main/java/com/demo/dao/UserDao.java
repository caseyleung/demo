package com.demo.dao;
/*
 * @Author: CaseyL
 * @Description: com.demo.dao
 * @Date: 2023/2/16 12:44
 * */

import com.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    Optional<User> findById(Integer integer);

}
