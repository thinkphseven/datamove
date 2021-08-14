package com.longtu.datamove.controller;

import com.longtu.datamove.entity.User;
import com.longtu.datamove.repositiory.UserRepositiory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: IndexController
 * @description:
 * @author: hk
 * @date: 2021-04-28 16:03
 **/
@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepositiory userRepositiory;

    @RequestMapping("login")
    public String tologin(){
        logger.info("定向登陆页");
        return "login";
    }

    @RequestMapping("home")
    public String home(){
        logger.info("定向主页");
        return "home";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:login";
    }
    @RequestMapping("user/login")
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, Map<String,Object> map, HttpSession session) {
        Map<String, Object> data = new HashMap();
        String uname = request.getParameter("username");
        String pwd = request.getParameter("password");
        List<User> list = userRepositiory.getUserByCodeAndPwd(uname, pwd);
        if (list == null || list.size() == 0) {
            data.put("code", 0);
            data.put("message", "用户名密码错误");
            return data;
        }
        session.setAttribute("user", "admin");
        data.put("code", 1);
        data.put("url", "/home");
        return data;
    }

    @RequestMapping("user/setPwd")
    @ResponseBody
    public Map<String,Object> setPwd(String pwd, String isPwd) {
        Map<String, Object> data = new HashMap();
        if(!pwd.equals(isPwd)){
            data.put("code",0);
            data.put("message","两次输入的密码不一致!");
            logger.error("两次输入的密码不一致!");
            return data;
        }
        int result = userRepositiory.updatePwd(pwd,"admin");
        if(result == 0){
            data.put("code",0);
            data.put("msg","修改密码失败！");
            logger.error("用户修改密码失败！");
            return data;
        }
        data.put("code",1);
        data.put("msg","修改密码成功！");
        logger.info("用户修改密码成功！");
        return data;
    }
}
