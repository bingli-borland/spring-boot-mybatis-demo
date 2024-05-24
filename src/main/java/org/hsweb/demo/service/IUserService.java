package org.hsweb.demo.service;

import org.hsweb.demo.po.User;

import java.util.List;

public interface IUserService {
    List<User> selectAll(int size);
}
