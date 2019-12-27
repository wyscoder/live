package cn.wys.live.controller;

import cn.wys.live.beans.User;
import cn.wys.live.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * @author wys
 * @date 2019/12/26
 */
@Controller
public class LoginController {

    @Autowired
    private UserMapper userMapper;
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String Login(String username, String password, HttpSession session){

        User user = userMapper.selectUserByUserName(username);
        System.out.println(user);
        if(user.getPassword().equals(password)){
            session.setAttribute("name",user.getName());
            session.setAttribute("id",user.getId());
            return "index";
        }
        return "login";
    }
}
