package cn.java.finalwork.service;

import cn.java.finalwork.entity.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author tanchenxi
 * @create 2020-06-26 7:18
 */
@SpringBootTest
public class AdminServiceTest {

    @Autowired
    private AdminService service;

    @Test
    public void test1() {
        Admin admin = new Admin();
        admin.setUsername("zhangsan");
        admin.setPassword("111");
        Admin admin1 = service.login(admin);
        System.out.println(admin1);
    }

    @Test
    public void test2() {
        String username = "tcx";
        String password = "111";
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        System.out.println(service.register(admin));
    }

    @Test
    public void test3() {
        String username = "tcx";
        String password = "222";
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        service.updateAdmin(admin);
    }
}
