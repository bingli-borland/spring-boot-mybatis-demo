package org.hsweb.demo.service.impl;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.hsweb.demo.dao.UserDao;
import org.hsweb.demo.po.User;
import org.hsweb.demo.service.IUserService;
import org.hsweb.demo.service.UserService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("myUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<User> selectAll(int size) {
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
        UserDao userMapper = sqlSession.getMapper(UserDao.class);
        List<String> list = new ArrayList<>();
        for (int i =0; i<size;i++) {
            list.add(i+"");
        }
        List<User> rs = Collections.EMPTY_LIST;
        try {
            rs = list.parallelStream().map(item -> convert(item, userMapper)).collect(Collectors.toList());
            sqlSession.commit();
            sqlSession.clearCache();
            return rs;
        }catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return rs;
    }

    private User convert(String i, UserDao mapper){
        return mapper.selectByUserName(i);
    }
}
