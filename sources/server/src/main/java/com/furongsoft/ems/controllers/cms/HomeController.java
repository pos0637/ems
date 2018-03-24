package com.furongsoft.ems.controllers.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页控制器
 *
 * @author Alex
 */
@Controller
@RequestMapping("/cms/home")
public class HomeController {
    @RequestMapping("/index")
    public String index() {
        return "cms/templates/template1/views/homepage/index.html";
    }
}
