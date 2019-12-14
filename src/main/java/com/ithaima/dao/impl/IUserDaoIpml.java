package com.ithaima.dao.impl;/*
@outhor shkstart
@date 2019/12/12-20:09
*/

import com.ithaima.dao.IUserDao;
import com.ithaima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class IUserDaoIpml implements IUserDao {
    private SqlSessionFactory factory;

    public IUserDaoIpml() {
    }

    public IUserDaoIpml(SqlSessionFactory factory) {
        this.factory = factory;
    }

    public List<User> findAll() {
        SqlSession session = factory.openSession();
        List<User> users = session.selectList("com.ithaima.dao.IUserDao.findAll");
        session.close();
        return users;
    }

    public void saveUser(User user) {
        SqlSession session = factory.openSession();
        session.insert("com.ithaima.dao.IUserDao.saveUser",user);
        session.commit();
        session.close();
    }

    public void updateUser(User user) {
        SqlSession session = factory.openSession();
        session.update("com.ithaima.dao.IUserDao.updateUser",user);
        session.commit();
        session.close();
    }

    public void deleteUser(Integer userId) {
        SqlSession session = factory.openSession();
        session.delete("com.ithaima.dao.IUserDao.deleteUser",userId);
        session.commit();
        session.close();
    }

    public User findById(Integer userId) {
        SqlSession session = factory.openSession();
        User user = session.selectOne( "com.ithaima.dao.IUserDao.findById",userId);
        session.close();
        return user;
    }

    public List<User> findByName(String username) {
        SqlSession session = factory.openSession();
        List<User> users = session.selectList("com.ithaima.dao.IUserDao.findByName",username);
        session.close();
        return users;
    }

    public int findTotal() {
        SqlSession session = factory.openSession();
        Integer count = session.selectOne( "com.ithaima.dao.IUserDao.findTotal");
        session.close();
        return count;
    }
}
