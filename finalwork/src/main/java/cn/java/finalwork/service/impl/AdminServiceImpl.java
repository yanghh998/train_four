package cn.java.finalwork.service.impl;

import cn.java.finalwork.dao.AdminDao;
import cn.java.finalwork.entity.Admin;
import cn.java.finalwork.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tanchenxi
 * @create 2020-06-25 21:44
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao dao;

    @Override
    public Admin login(Admin admin) {
        return dao.findAdminByUsernameAndPassword(admin);
    }

    @Override
    public boolean register(Admin admin) {
        boolean flag = false;
        if (dao.findByUsername(admin.getUsername()) == null) {
            dao.addAdmin(admin);
            flag = true;
        }
        return flag;
    }

    @Override
    public void updateAdmin(Admin admin) {
        dao.updateAdmin(admin);
    }
}
