package cn.wys.live.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wys
 * @date 2019/12/26
 */
@Controller
public class RegisterController {

    @RequestMapping("/register")
    public String register(){
        return "register";
    }
}
