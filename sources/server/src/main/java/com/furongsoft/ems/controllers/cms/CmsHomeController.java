package com.furongsoft.ems.controllers.cms;

import com.furongsoft.ems.services.cms.CompanyService;
import com.furongsoft.ems.services.cms.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

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

    @Autowired
    public CmsHomeController(CompanyService companyService, ProfileService profileService) {
        this.companyService = companyService;
        this.profileService = profileService;
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("company", companyService.get(null));
        model.addAttribute("profiles", profileService.getProfiles());
        return "resources/cms/templates/template1/views/homepage/index.html";
    }
}
