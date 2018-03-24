package com.furongsoft.ems.controllers.api.v1.cms;

import com.furongsoft.base.restful.entities.RestResponse;
import com.furongsoft.ems.entities.cms.Company;
import com.furongsoft.ems.services.cms.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公司控制器
 *
 * @author Alex
 */
@RestController
@RequestMapping("/api/v1/cms/company")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * 获取公司信息
     *
     * @return 公司信息
     */
    @GetMapping("/company")
    public RestResponse getCompany() {
        return new RestResponse(HttpStatus.OK, null, companyService.get(null));
    }

    /**
     * 更新公司信息
     *
     * @param company 公司信息
     * @return 响应内容
     */
    @PutMapping("/company")
    public RestResponse editCompany(@NonNull Company company) {
        companyService.edit(company);
        return new RestResponse(HttpStatus.OK);
    }
}
