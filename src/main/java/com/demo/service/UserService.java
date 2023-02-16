package com.demo.service;
/*
 * @Author: CaseyL
 * @Description: com.demo.service
 * @Date: 2023/2/16 12:49
 * */


import com.demo.anno.OperateRecord;
import com.demo.dao.UserDao;
import com.demo.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserDao userDao;

    public User loginUser(int uid) {
        return userDao.findById(uid).orElseThrow(
                () -> new RuntimeException("该用户不存在!")
        );
    }

    public double queryUserMoney(int uid) {
        return loginUser(uid).getMoney();
    }

    @OperateRecord(desc = "消费")
    public double costMoney(int uid, double cost) {
        // 操作类型 操作人 操作时间 余额

        double money = loginUser(uid).getMoney();
        if (money >= 0 && cost >=0 && money >= cost) {
            loginUser(uid).setMoney(money-cost);
            userDao.save(loginUser(uid));
        } else {
            System.out.println("操作失败!");
        }
        return loginUser(uid).getMoney();
    }
    @OperateRecord(desc = "退款")
    public double refund(int uid, double fee) {
        double money = loginUser(uid).getMoney();
        loginUser(uid).setMoney(money+fee);
        userDao.save(loginUser(uid));
        return loginUser(uid).getMoney();
    }
}
