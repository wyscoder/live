package cn.wys.live.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wys
 * @date 2019/10/31
 *
 * 这个就是控制网页请求的控制器
 */
@Controller
public class RequestController {

    @RequestMapping("/{url}")
    public String allRequest(@PathVariable(value = "url") String myUrl) {
        return myUrl;
    }

    @RequestMapping("/")
    public String index(){
        return "/login";
    }
}
