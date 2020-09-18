package cn.java.finalwork.controller;

import cn.java.finalwork.entity.Admin;
import cn.java.finalwork.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tanchenxi
 * @create 2020-09-17 21:47
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl service;

    @PostMapping("/login")
    public String login(@RequestBody Admin admin) {
        Admin admin1 = service.login(admin);
        if (admin1 != null) {
            return "success";
        } else {
            return "error";
        }
    }

    @PostMapping("/register")
    public boolean register(@RequestBody Admin admin) {
        return service.register(admin);
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody Admin admin) {
        service.updateAdmin(admin);
        return "success";
    }
}
