package cn.java.finalwork.dao;

import cn.java.finalwork.entity.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author tanchenxi
 * @create 2020-06-25 21:34
 */
public interface AdminDao {

    /**
     * 根据用户名和密码查询管理员信息
     *
     * @param admin 封装了管理员用户名和密码的bean
     * @return 查询到的管理员信息
     */
    @Select("select * from admin where username=#{username} " +
            "and password=#{password}")
    Admin findAdminByUsernameAndPassword(Admin admin);

    /**
     * 添加新用户
     *
     * @param admin 封装注册信息的bean
     */
    @Insert("insert into admin values(null,#{username},#{password})")
    void addAdmin(Admin admin);

    /**
     * 根据用户名查询数据库中是否存在当前用户
     *
     * @param username 用户名
     * @return 查询到的用户信息
     */
    @Select("select * from admin where username = #{username}")
    Admin findByUsername(String username);


    /**
     * 更新用户信息
     *
     * @param admin 封装修改后用户信息的bean
     */
    @Update("update admin set password=#{password} where username=#{username}")
    void updateAdmin(Admin admin);
}
