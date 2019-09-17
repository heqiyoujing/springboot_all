package com.example.mapper.mybatisMap.controller;

import com.example.mapper.mybatisMap.SpringBootAll;
import com.example.mapper.mybatisMap.constant.ZKConst;
import com.example.mapper.mybatisMap.dao.UserDao;
import com.example.mapper.mybatisMap.dao.UserDaoIml;
import com.example.mapper.mybatisMap.entity.OOO;
import com.example.mapper.mybatisMap.entity.Student;
import com.example.mapper.mybatisMap.entity.User;
import com.example.mapper.mybatisMap.entity.User_c;
import com.example.mapper.mybatisMap.service.UserService;
import com.example.mapper.mybatisMap.tool.LocalLock;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.mapper.mybatisMap.tool.CommonObject.*;

/**
 * @author yiqq
 * @date @date 2018/07/20 14:10
 */
@RestController
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    UserDao userDao = new UserDaoIml(SpringBootAll.sqlSessionFactory);

    /**
     * hello world 第一个程序
     * @return
     */
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    /**
     * 直接返回页面
     */
    @RequestMapping(value = "/random", method = RequestMethod.GET)
    @ResponseBody
    public User_c returnUser() {
        User_c user = new User_c();
        //随机年龄
        Random rand = new Random();
        int id = rand.nextInt(100) + 10000;
        user.setId(id);
        int age = rand.nextInt(20) + 20;
        user.setAge(age);
        user.setName(getChineseName());
        user.setRole(getRoad());
        user.setEmail(getEmail(6,9));
        user.setPhone(getTel());
        user.setUpdateTime(getDate());
        return user;
    }

    /**
     * 分页插件,加锁
     */
    @RequestMapping(value = "/selectallpage",produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public List<User> SelectAllPage(HttpServletRequest request
            , HttpServletResponse response
            ,@RequestParam(value = "start",defaultValue = "1") int start
            , @RequestParam(value = "end",defaultValue = "8") int end) {
        // 获取session，同一次运行的session都是一样的
        String sessionId = request.getSession().getId();
        logger.debug("hhhhhhhhhhhh");
        logger.info("1111111111");
        // 获取锁
        LocalLock lock = new LocalLock(sessionId, String.format(ZKConst.SCORE_LOCK_NODE, 102, 123, 234));
        // 加锁
        if(!lock.writeLock(ZKConst.LOCK_EXPIRE_TIME)){
            //log.warn("加锁失败！");
            return null;
        }
        PageHelper.startPage(start, end);
        List<User> userList = userDao.selectall();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        // 释放锁
        lock.unWriteLock();
        return pageInfo.getList();
    }

    /**
     * 返回对象
     */
    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<User> GetGoodList(@RequestParam("name") String name, @RequestParam("age") int age) throws Exception{

        ArrayList<User> user = userDao.findInfoList(name, age);
        return user ;
    }

    /**
     * 返回对象，可不传参数，使用默认参数
     */
    @RequestMapping(value = "/goodsCount", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<User> GetGoodCount(@RequestParam(value = "name",defaultValue = "易强强") String name
            , @RequestParam(value = "age", defaultValue = "27") int age) throws Exception{

        ArrayList<User> user = userDao.findInfoList(name, age);
        return user ;
    }

    /**
     * 查询所有User
     * @return
     */
    @RequestMapping(value = "/selectall", method = RequestMethod.GET)
    @ResponseBody
    public List<User> SelectAll() {
        return userDao.selectall();
    }
    /**
     * 查询所有OOO
     * @return
     */
    @RequestMapping(value = "/selectallname", method = RequestMethod.GET)
    @ResponseBody
    public List<OOO> SelectAllName() {
        return userDao.selectallname();
    }
    /**
     * 查询所有Student
     * @return
     */
    @RequestMapping(value = "/selectallt", method = RequestMethod.GET)
    @ResponseBody
    public List<Student> SelectAllt() {
        return userDao.selectuser_t();
    }

    /**
     * mapper方式实现mybatis查询数据库
     * sqlSession.selectOne方式
     */
    @RequestMapping(value = "/selectone", method = RequestMethod.GET)
    @ResponseBody
    public String GetSelectOne(@RequestParam(value = "name", defaultValue = "文彪承") String name
            , @RequestParam(value = "age", defaultValue = "35") int age) throws Exception{
        User user = new User();
        user.setAge(age);
        user.setName(name);
        String names = userDao.findName(user);
        return names ;
    }

    /**
     * insert主键自增长,时间
     * 写入数据库
     */
    @RequestMapping(value = "/insertT", method = RequestMethod.POST)
    @ResponseBody
    public String insertTime() {
        User_c user = new User_c();
        //随机年龄
        Random rand = new Random();
        int age = rand.nextInt(20) + 20;
        user.setAge(age);
        user.setName(getChineseName());
        user.setRole(getRoad());
        user.setEmail(getEmail(6,9));
        user.setPhone(getTel());
        user.setUpdateTime(getDate());
        boolean res = userDao.insertTime(user);
        if(res == true) {
            return "主键自增长插入成功";
        }else {
            return "主键自增长插入失败";
        }
    }

    /**
     * insert主键自增长
     * 写入数据库
     */
    @RequestMapping(value = "/insertC", method = RequestMethod.POST)
    @ResponseBody
    public String insert() {
        User user = new User();
        //随机年龄
        Random rand = new Random();
        int age = rand.nextInt(20) + 20;
        user.setAge(age);
        user.setName(getChineseName());
        user.setRole(getRoad());
        user.setEmail(getEmail(6,9));
        user.setPhone(getTel());
        boolean res = userDao.insert(user);
        if(res == true) {
            return "主键自增长插入成功";
        }else {
            return "主键自增长插入失败";
        }
    }

}
