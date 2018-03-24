package com.furongsoft.ems.controllers.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 首页控制器
 *
 * @author Alex
 */
@Controller
@RequestMapping("/cms/home")
public class CmsHomeController {
    @RequestMapping("/index")
    public String index(Map<String, Object> map) {
        map.put("hello", "from TemplateController.helloHtml");
        return "resources/cms/templates/template1/views/homepage/index.html";
    }
}
