package cn.java.finalwork.dao;

import cn.java.finalwork.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tanchenxi
 * @create 2020-06-26 8:25
 */
public interface UserDao extends JpaRepository<User, Integer> {

}
