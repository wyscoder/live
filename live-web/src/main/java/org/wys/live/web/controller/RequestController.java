package org.wys.live.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wys
 * @date 2019/10/31
 * <p>
 * 这个就是控制网页请求的控制器
 */
@Controller
public class RequestController {

    @RequestMapping("/index")
    public String my_index() {
        return "index";
    }

    @RequestMapping("/")
    public String index() {
        return "login";
    }
}
