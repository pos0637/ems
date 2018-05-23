package com.furongsoft.cms.addons.companyWebsite.controllers;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.furongsoft.base.entities.PageRequest;
import com.furongsoft.base.entities.PageResponse;
import com.furongsoft.base.entities.TreeNode;
import com.furongsoft.base.exceptions.BaseException;
import com.furongsoft.base.restful.entities.RestResponse;
import com.furongsoft.cms.addons.companyWebsite.entities.News;
import com.furongsoft.cms.addons.companyWebsite.entities.NewsCategory;
import com.furongsoft.cms.addons.companyWebsite.services.NewsCategoryService;
import com.furongsoft.cms.addons.companyWebsite.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * 新闻控制器
 *
 * @author Alex
 */
@RestController
@RequestMapping("/api/v1/companyWebsite/news")
public class NewsController {
    private final NewsCategoryService newsCategoryService;
    private final NewsService newsService;

    @Autowired
    public NewsController(NewsCategoryService newsCategoryService, NewsService newsService) {
        this.newsCategoryService = newsCategoryService;
        this.newsService = newsService;
    }

    /**
     * 获取全部新闻
     *
     * @param pageRequest 页面
     * @param categoryId  分类索引
     * @param name        名称
     * @param sortField   排序字段
     * @param sortType    排序方式
     * @return 全部新闻
     */
    @GetMapping("/news")
    public PageResponse getNews(
            PageRequest pageRequest,
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) String sortType) {
        Page<News> page = newsService.getNews(pageRequest.getPage(), categoryId, name, sortField, sortType);
        return new PageResponse<>(HttpStatus.OK, page);
    }

    /**
     * 获取新闻
     *
     * @param id 新闻索引
     * @return 响应内容
     */
    @GetMapping("/news/{id}")
    public RestResponse getNews(@NonNull @PathVariable String id) {
        return new RestResponse(HttpStatus.OK, null, newsService.get(id));
    }

    /**
     * 添加新闻
     *
     * @param news 新闻
     * @return 响应内容
     */
    @PostMapping("/news")
    public RestResponse addNews(@NonNull News news) {
        newsService.add(news);
        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 更新新闻
     *
     * @param id   新闻索引
     * @param news 新闻
     * @return 响应内容
     */
    @PutMapping("/news/{id}")
    public RestResponse editNews(@NonNull @PathVariable String id, @NonNull News news) {
        news.setId(id);
        newsService.edit(news);
        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 删除新闻
     *
     * @param id 新闻索引
     * @return 响应内容
     */
    @DeleteMapping("/news/{id}")
    public RestResponse delNews(@NonNull @PathVariable String id) {
        newsService.del(id);
        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 批量删除新闻
     *
     * @param delete 新闻索引列表
     * @return 响应内容
     */
    @PostMapping("/news/batch")
    public RestResponse delNewsBatch(@NonNull @RequestParam String delete) {
        try {
            delete = URLDecoder.decode(delete, "UTF-8");
            List<Serializable> ids = JSON.parseArray(delete, Serializable.class);
            newsService.delNews(ids);
        } catch (UnsupportedEncodingException e) {
            throw new BaseException.IllegalArgumentException();
        }

        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 获取新闻分类列表
     *
     * @return 新闻分类列表
     */
    @GetMapping("/newsCategories")
    public RestResponse getNewsCategories() {
        TreeNode<NewsCategory> root = newsCategoryService.getNewsCategoriesTree(newsCategoryService.getNewsCategories());
        return new RestResponse(HttpStatus.OK, null, JSON.toJSONString(root.children));
    }

    /**
     * 获取新闻分类
     *
     * @param id 新闻分类索引
     * @return 响应内容
     */
    @GetMapping("/newsCategories/{id}")
    public RestResponse getNewsCategory(@NonNull @PathVariable String id) {
        return new RestResponse(HttpStatus.OK, null, newsCategoryService.get(id));
    }

    /**
     * 添加新闻分类
     *
     * @param newsCategory 新闻分类
     * @return 响应内容
     */
    @PostMapping("/newsCategories")
    public RestResponse addNewsCategory(@NonNull NewsCategory newsCategory) {
        newsCategoryService.add(newsCategory);
        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 更新新闻分类
     *
     * @param id           新闻分类索引
     * @param newsCategory 新闻分类
     * @return 响应内容
     */
    @PutMapping("/newsCategories/{id}")
    public RestResponse editNewsCategory(@NonNull @PathVariable String id, @NonNull NewsCategory newsCategory) {
        newsCategory.setId(id);
        newsCategoryService.edit(newsCategory);
        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 删除新闻分类
     *
     * @param id 新闻分类索引
     * @return 响应内容
     */
    @DeleteMapping("/newsCategories/{id}")
    public RestResponse delNewsCategory(@NonNull @PathVariable String id) {
        newsCategoryService.del(id);
        return new RestResponse(HttpStatus.OK);
    }
}
