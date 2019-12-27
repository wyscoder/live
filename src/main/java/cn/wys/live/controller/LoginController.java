package cn.wys.live.controller;

import cn.wys.live.beans.RetResponse;
import cn.wys.live.beans.RetResult;
import cn.wys.live.beans.User;
import cn.wys.live.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public RetResult Login(String username, String password, HttpSession session){
        System.out.println(username+" :"+password);
        String msg = "";
        User user = userMapper.selectUserByUserName(username);
        if(user == null){
            msg = "用户不存在！";
        }else{
            if(user.getPassword().equals(password)){
                session.setAttribute("name",user.getName());
                session.setAttribute("id",user.getId());
                return RetResponse.makeOKRsp();
            }else if(!user.getPassword().equals(password)){
                msg = "密码错误！";
            }
        }
        return RetResponse.makeErrRsp(msg);
    }
}
