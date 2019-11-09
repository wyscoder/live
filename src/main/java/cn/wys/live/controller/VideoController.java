package cn.wys.live.controller;

import cn.wys.live.beans.RetResponse;
import cn.wys.live.beans.RetResult;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author wys
 * @date 2019/11/9
 * 影视网站控制层
 */
@RestController
@RequestMapping("/video")
public class VideoController {

    @RequestMapping("/{name}")
    public RetResult jumpMovie(@PathVariable(value = "name") String name) {

        System.out.println(name);

        return RetResponse.makeOKRsp(name);
    }

}
