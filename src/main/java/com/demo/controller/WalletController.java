package com.demo.controller;
/*
 * @Author: CaseyL
 * @Description: com.demo.controller
 * @Date: 2023/2/16 13:36
 * */

import com.demo.dao.OperLogDao;
import com.demo.entity.OperateLog;
import com.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class WalletController {
    private final UserService userService;

    private final OperLogDao operLogDao;

    @GetMapping("api/queryMoney")
    public String queryMoney() {
        int uid = 1;
        double money = userService.queryUserMoney(uid);
        return String.valueOf(money);
    }

    @GetMapping("api/details")
    public String queryDetails() {
        List<OperateLog> list = operLogDao.findAllByOperName("jack");
        return list.toString();
    }

    @PostMapping("api/cost/{cost}")
    public String costMoney(@PathVariable("cost") double cost) {
        double money = userService.costMoney(1, cost);
        return "剩余余额： " + money;
    }

    @PostMapping("api/refund/{cost}")
    public String refund(@PathVariable("cost") double cost) {
        double money = userService.refund(1, cost);
        return "剩余余额： " + money;
    }



}
