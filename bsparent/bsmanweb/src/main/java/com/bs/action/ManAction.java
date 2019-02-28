package com.bs.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tangfan
 * @Date:2019/2/26 17:35
 * @Description:
 */
@Controller
public class ManAction {
    //页面跳转，通过rest风格得到请求的地址
    @RequestMapping("/{page}")
    public String goToPage(@PathVariable String page){
        return page;
    }
}
