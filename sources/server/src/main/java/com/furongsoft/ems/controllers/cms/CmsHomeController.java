package com.furongsoft.ems.controllers.cms;

import com.furongsoft.ems.services.cms.CompanyService;
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

    @Autowired
    public CmsHomeController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("company", companyService.get(null));
        return "resources/cms/templates/template1/views/homepage/index.html";
    }
}
