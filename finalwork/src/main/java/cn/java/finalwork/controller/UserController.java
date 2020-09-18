package cn.java.finalwork.controller;

import cn.java.finalwork.dao.UserDao;
import cn.java.finalwork.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hui
 * @create 2020-09-16 8:33
 */
@RestController
@RequestMapping("/user")
/*@Api(value="cms页面管理接口",description = "cms页面管理接口，提供页面的增、删、改、查")*/
public class UserController {

    @Autowired
    private UserDao dao;

/*
    //页面查询
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })*/
    @GetMapping("/findByPage/{page}/{size}")
    public Page<User> findByPage(@PathVariable("page") Integer page,
                                 @PathVariable("size") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return dao.findAll(pageable);
    }

    @GetMapping("/findAll")
    public List<User> findByPage(){
        List<User> users = dao.findAll();
        return  users;
    }


    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        User result = dao.save(user);
        if (result != null) {
            return "success";
        } else {
            return "error";
        }
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestBody User user) {
        User user1 = dao.saveAndFlush(user);
        if (user1 != null) {
            return "success";
        } else {
            return "error";
        }
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestBody User user) {
        dao.deleteById(user.getId());
        return "success";
    }

    @PostMapping("/deleteByIds")
    public String deleteAll(@RequestBody Integer[] arr) {
        for (Integer integer : arr) {
            dao.deleteById(integer);
        }
        return "success";
    }
}
