package com.furongsoft.ems.controllers.cms;

import com.furongsoft.ems.services.cms.CompanyService;
import com.furongsoft.ems.services.cms.NewsCategoryService;
import com.furongsoft.ems.services.cms.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页控制器
 *
 * @author Alex
 */
@Controller
@RequestMapping("/cms/home")
public class CmsHomeController {
    private final CompanyService companyService;
    private final ProfileService profileService;
    private final NewsCategoryService newsCategoryService;

    @Autowired
    public CmsHomeController(CompanyService companyService, ProfileService profileService, NewsCategoryService newsCategoryService) {
        this.companyService = companyService;
        this.profileService = profileService;
        this.newsCategoryService = newsCategoryService;
    }

    /**
     * 首页
     *
     * @param model 模型
     * @return 地址
     */
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("company", companyService.get(null));
        model.addAttribute("profiles", profileService.getProfiles());
        return "resources/cms/templates/template1/views/homepage/index.html";
    }

    /**
     * 新闻中心
     *
     * @param model 模型
     * @return 地址
     */
    @RequestMapping("/news")
    public String news(Model model) {
        model.addAttribute("company", companyService.get(null));
        model.addAttribute("newsCategories", newsCategoryService.getNewsCategories());
        return "resources/cms/templates/template1/views/news/index.html";
    }
}
