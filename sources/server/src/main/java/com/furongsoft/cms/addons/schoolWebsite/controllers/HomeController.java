package com.furongsoft.cms.addons.schoolWebsite.controllers;

import com.furongsoft.cms.addons.schoolWebsite.services.SchoolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页控制器
 *
 * @author Alex
 */
@Controller("SchoolWebsiteHomeController")
@RequestMapping("/cms/schoolWebsite")
public class HomeController {
    private final SchoolService schoolService;

    public HomeController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    /**
     * 首页
     *
     * @param model 模型
     * @return 地址
     */
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("school", schoolService.get(null));
        return "resources/cms/templates/schoolWebsite/views/home/index.html";
    }

    /**
     * 机构设置
     *
     * @param model 模型
     * @return 地址
     */
    @RequestMapping("/structure")
    public String index1(Model model) {
        model.addAttribute("school", schoolService.get(null));
        return "resources/cms/templates/schoolWebsite/views/home/structure.html";
    }
}
