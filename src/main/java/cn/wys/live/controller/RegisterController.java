package cn.wys.live.controller;

import cn.wys.live.beans.RetResponse;
import cn.wys.live.beans.RetResult;
import cn.wys.live.beans.User;
import cn.wys.live.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wys
 * @date 2019/12/26
 */
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public RetResult Register(String username, String mail, String password, String comfirmPassword, String name){

        String msg = "";
        User uu = userService.selectUserByUserName(username);
        if(uu!=null){
            msg = "用户已存在！";
            return RetResponse.makeErrRsp(msg);
        }
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setMail(mail);
        user.setPassword(password);
        userService.insertUser(user);


        System.out.println(username);

        return RetResponse.makeOKRsp();

    }
}
