package com.ithaima.test;/*
@outhor shkstart
@date 2019/12/11-14:23
*/


import com.ithaima.dao.IUserDao;
import com.ithaima.dao.impl.IUserDaoIpml;
import com.ithaima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {
   /* public static void main(String[] args) throws  Exception {

    }*/
   private InputStream in;

   private IUserDao userDao;
   private SqlSessionFactory factory;
   @Before//用于在测试方法执行之前执行
   public void init() throws Exception {
       //1.读取配置文件
       in = Resources.getResourceAsStream("SqlMapConfig.xml");
       //2.创建SqlSessionFactory工厂
        factory=new SqlSessionFactoryBuilder().build(in);
       //3. 使用工厂对象创建dao对象
        userDao =  new IUserDaoIpml(factory);
   }
   @After//用于在测试方法执行之后执行
   public void destroy() throws Exception {
       //提交事务

       //6.释放资源

       in.close();
   }
   /**
    * 查询所有用户信息
    * */
    @Test
    public void testFindAll()  {
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

    }
    /**
     * 测试保存所有操作
     * */
    @Test
     public void testSave()  {
         User user = new User();
         user.setUsername("mybatis dao ipml user  ");
         user.setAddress("郑州八戒工场");
         user.setSex("男");
         user.setBirthday(new Date());
        System.out.println("保存所有操作之前"+user);
         //5.使用代理对象执行保存方法
          userDao.saveUser(user);
        System.out.println("保存所有操作之后"+user);
     }
    /**
     * 测试更新 操作
     * */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(51);
        user.setUsername("mybatis userdaoimpl Update user");
        user.setAddress("郑州八戒工场");
        user.setSex("男");
        user.setBirthday(new Date());

        //5.使用代理对象执行保存方法
        userDao.updateUser(user);
    }
    /**
     * 测试删除操作
     * */
    @Test
    public void testDelete() {

        //5.使用代理对象执行删除方法
        userDao.deleteUser(53);
    }
    /**
     * 测试查询一个操作
     * */
    @Test
    public void testFindOne() {

        //5.使用代理对象执行查询一个方法
        User user = userDao.findById(51);
        System.out.println(user);
    }
    /**
     * 测试模糊查询操作
     * */
    @Test
    public void testFindByName() {

        //5.使用代理对象执行查询一个方法
        List<User> users = userDao.findByName("%王%");
       /* List<User> users = userDao.findByName("王");*/
        for (User user : users) {
            System.out.println(user);
        }
    }
    /**
     * 测试查询总记录条数
     * */
    @Test
    public void testFindTotal(){

        //5.使用代理对象执行查询总记录条数方法
         int count=userDao.findTotal();
        System.out.println(count);
    }

}
