package com.furongsoft.ems.controllers.api.v1.cms;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.furongsoft.base.entities.PageRequest;
import com.furongsoft.base.entities.PageResponse;
import com.furongsoft.base.exceptions.BaseException;
import com.furongsoft.base.restful.entities.RestResponse;
import com.furongsoft.ems.entities.cms.Company;
import com.furongsoft.ems.entities.cms.Profile;
import com.furongsoft.ems.services.cms.CompanyService;
import com.furongsoft.ems.services.cms.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * 公司控制器
 *
 * @author Alex
 */
@RestController
@RequestMapping("/api/v1/cms/company")
public class CompanyController {
    private final CompanyService companyService;
    private final ProfileService profileService;

    @Autowired
    public CompanyController(CompanyService companyService, ProfileService profileService) {
        this.companyService = companyService;
        this.profileService = profileService;
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

    /**
     * 获取全部公司描述
     *
     * @param pageRequest 页面
     * @param name        名称
     * @param sortField   排序字段
     * @param sortType    排序方式
     * @return 全部公司描述
     */
    @GetMapping("/profiles")
    public PageResponse getProfiles(
            PageRequest pageRequest,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) String sortType) {
        Page<Profile> page = profileService.getProfiles(pageRequest.getPage(), name, sortField, sortType);
        return new PageResponse<>(HttpStatus.OK, page);
    }

    /**
     * 获取公司描述
     *
     * @param id 公司描述索引
     * @return 响应内容
     */
    @GetMapping("/profiles/{id}")
    public RestResponse getProfile(@NonNull @PathVariable String id) {
        return new RestResponse(HttpStatus.OK, null, profileService.get(id));
    }

    /**
     * 添加公司描述
     *
     * @param profile 公司描述
     * @return 响应内容
     */
    @PostMapping("/profiles")
    public RestResponse addProfile(@NonNull Profile profile) {
        profileService.add(profile);
        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 更新公司描述
     *
     * @param id      公司描述索引
     * @param profile 公司描述
     * @return 响应内容
     */
    @PutMapping("/profiles/{id}")
    public RestResponse editProfile(@NonNull @PathVariable String id, @NonNull Profile profile) {
        profile.setId(id);
        profileService.edit(profile);
        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 删除公司描述
     *
     * @param id 公司描述索引
     * @return 响应内容
     */
    @DeleteMapping("/profiles/{id}")
    public RestResponse delProfile(@NonNull @PathVariable String id) {
        profileService.del(id);
        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 批量删除公司描述
     *
     * @param delete 公司描述索引列表
     * @return 响应内容
     */
    @PostMapping("/profiles/batch")
    public RestResponse delProfiles(@NonNull @RequestParam String delete) {
        try {
            delete = URLDecoder.decode(delete, "UTF-8");
            List<Serializable> ids = JSON.parseArray(delete, Serializable.class);
            profileService.delProfiles(ids);
        } catch (UnsupportedEncodingException e) {
            throw new BaseException.IllegalArgumentException();
        }

        return new RestResponse(HttpStatus.OK);
    }
}
