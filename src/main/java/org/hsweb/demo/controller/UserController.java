package org.hsweb.demo.controller;

import org.hsweb.demo.po.User;
import org.hsweb.demo.service.IUserService;
import org.hsweb.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {
    @Resource
    UserService userService;

    @Resource
    IUserService myUserService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.selectAll();
    }

    @RequestMapping(value = "/users/test", method = RequestMethod.GET)
    public List<User> test(@RequestParam Integer size) {
        return myUserService.selectAll(size);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getById(@PathVariable("id") String id) {
        return userService.selectById(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User add(@RequestBody User user) {
        userService.insert(user);
        return user;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public User update(@PathVariable String id, @RequestBody User user) {
        user.setId(id);
        userService.update(user);
        return user;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String id) {
        return userService.delete(id);
    }
}
