package org.wys.live.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wys.live.server.service.UserService;
import org.wys.live.web.response.RetResponse;
import org.wys.live.web.response.RetResult;
import org.wys.live.domain.po.User;
import org.wys.live.server.dao.UserMapper;

import javax.servlet.http.HttpSession;

/**
 * @author wys
 * @date 2019/12/26
 */
@Controller
@AllArgsConstructor
@Slf4j
public class LoginController {

    private final UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public RetResult<Boolean> Login(String username, String password, HttpSession session){
        if(username.equals("admin") &&password.equals("admin")){
            session.setAttribute("name","admin");
            return RetResponse.makeRsp(-1,"管理员登录");
        }
        System.out.println(username+" :"+password);
        String msg = "";
        User user = userService.selectUserByUserName(username);
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
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("name");
        session.removeAttribute("id");
        return "login";
    }
}
