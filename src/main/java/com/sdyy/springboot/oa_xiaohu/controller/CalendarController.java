package com.sdyy.springboot.oa_xiaohu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xiaohu
 * @createDate 2018-08-27 15:28
 */
@Controller
public class CalendarController {

    @GetMapping("/calendar")
    public String calendar(){
        return "calendar";
    }

}
