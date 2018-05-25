package com.furongsoft.cms.addons.schoolWebsite.controllers;

import com.furongsoft.base.restful.entities.RestResponse;
import com.furongsoft.cms.addons.schoolWebsite.entities.School;
import com.furongsoft.cms.addons.schoolWebsite.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学校控制器
 *
 * @author Alex
 */
@RestController
@RequestMapping("/api/v1/schoolWebsite/school")
public class SchoolController {
    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    /**
     * 获取学校信息
     *
     * @return 学校信息
     */
    @GetMapping("/school")
    public RestResponse getSchool() {
        return new RestResponse(HttpStatus.OK, null, schoolService.get(null));
    }

    /**
     * 更新学校信息
     *
     * @param school 学校信息
     * @return 响应内容
     */
    @PutMapping("/school")
    public RestResponse editSchool(@NonNull School school) {
        schoolService.edit(school);
        return new RestResponse(HttpStatus.OK);
    }
}
