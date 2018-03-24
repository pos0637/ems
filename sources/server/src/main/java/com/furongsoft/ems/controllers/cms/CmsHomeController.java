package com.furongsoft.ems.controllers.cms;

import com.furongsoft.ems.services.cms.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private final CompanyInfoService companyInfoService;

    @Autowired
    public CmsHomeController(CompanyInfoService companyInfoService) {
        this.companyInfoService = companyInfoService;
    }

    @RequestMapping("/index")
    public String index(Map<String, Object> map) {
        map.put("companyInfo", companyInfoService.get(null).getInformation());
        return "resources/cms/templates/template1/views/homepage/index.html";
    }
}
