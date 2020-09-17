package cn.java.finalwork.service;

import cn.java.finalwork.entity.Admin;

/**
 * @author tanchenxi
 * @create 2020-06-25 21:42
 */
public interface AdminService {

    /**
     * 管理员登录
     *
     * @param admin 封住管理员用户名和密码的bean
     * @return 登录的管理员信息
     */
    Admin login(Admin admin);

    /**
     * 添加新用户
     *
     * @param admin 封装注册用户信息的bean
     * @return 用户是否注册成功
     */
    boolean register(Admin admin);

    /**
     * 更新用户信息
     *
     * @param admin 封装修改后用户信息的bean
     */
    void updateAdmin(Admin admin);
}
