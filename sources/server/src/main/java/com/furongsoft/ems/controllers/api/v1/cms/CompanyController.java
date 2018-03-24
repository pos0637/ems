package com.furongsoft.ems.controllers.api.v1.cms;

import com.furongsoft.base.restful.entities.RestResponse;
import com.furongsoft.ems.entities.cms.CompanyInfo;
import com.furongsoft.ems.services.cms.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

/**
 * 公司信息控制器
 *
 * @author Alex
 */
@RestController
@RequestMapping("/api/v1/cms/company")
public class CompanyController {
    private final CompanyInfoService companyInfoService;

    @Autowired
    public CompanyController(CompanyInfoService companyInfoService) {
        this.companyInfoService = companyInfoService;
    }

    /**
     * 获取公司信息
     *
     * @return 公司信息
     */
    @GetMapping("/companyInfo")
    public RestResponse getCompanyInfo() {
        return new RestResponse(HttpStatus.OK, null, companyInfoService.get(null));
    }

    /**
     * 更新产品
     *
     * @param companyInfo 公司信息
     * @return 响应内容
     */
    @PutMapping("/companyInfo")
    public RestResponse editProduct(@NonNull CompanyInfo companyInfo) {
        companyInfoService.edit(companyInfo);
        return new RestResponse(HttpStatus.OK);
    }
}
