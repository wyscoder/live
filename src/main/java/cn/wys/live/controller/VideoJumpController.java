package cn.wys.live.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wys
 * @date 2019/11/9
 * 这个就是视图映射器
 */
@RequestMapping("/video")
@Controller
public class VideoJumpController {

    @RequestMapping("/detail")
    public String detail(@RequestParam("videoid") String videoId, Model model) {
        System.out.println(videoId);

        model.addAttribute("videoName","亮剑");
        return "detail";
    }

}
