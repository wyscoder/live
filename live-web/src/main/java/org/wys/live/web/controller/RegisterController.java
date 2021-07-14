package org.wys.live.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wys.live.web.response.RetResponse;
import org.wys.live.web.response.RetResult;
import org.wys.live.domain.po.User;
import org.wys.live.server.service.UserService;

/**
 * @author wys
 * @date 2019/12/26
 */
@Controller
@Slf4j
@AllArgsConstructor
public class RegisterController {

    private final UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public RetResult<Boolean> Register(String username, String mail, String password, String name) {

        String msg = "";
        User uu = userService.selectUserByUserName(username);
        if (uu != null) {
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
