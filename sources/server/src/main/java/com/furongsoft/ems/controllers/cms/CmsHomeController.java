package com.furongsoft.ems.controllers.cms;

import com.furongsoft.base.entities.TreeNode;
import com.furongsoft.ems.entities.cms.ProductCategory;
import com.furongsoft.ems.services.cms.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 首页控制器
 *
 * @author Alex
 */
@Controller
@RequestMapping("/cms/home")
public class CmsHomeController {
    private final CompanyService companyService;
    private final ProductCategoryService productCategoryService;
    private final ProductService productService;
    private final ProfileService profileService;
    private final NewsCategoryService newsCategoryService;
    private final NewsService newsService;
    private final JobService jobService;

    @Autowired
    public CmsHomeController(CompanyService companyService, ProductService productService, ProductCategoryService productCategoryService, ProductService productService1, ProfileService profileService, NewsCategoryService newsCategoryService, NewsService newsService, JobService jobService) {
        this.companyService = companyService;
        this.productCategoryService = productCategoryService;
        this.productService = productService1;
        this.profileService = profileService;
        this.newsCategoryService = newsCategoryService;
        this.newsService = newsService;
        this.jobService = jobService;
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

        TreeNode<ProductCategory> tree = productCategoryService.getProductCategoriesTree(productCategoryService.getProductCategories());
        List<TreeNode<ProductCategory>> productCategories = tree.getChildren().size() > 4 ? tree.getChildren().subList(0, 4) : tree.getChildren();
        model.addAttribute("productCategories", productCategories);

        return "resources/cms/templates/template1/views/home/index.html";
    }

    /**
     * 产品中心
     *
     * @param model 模型
     * @return 地址
     */
    @RequestMapping("/product")
    public String product(Model model) {
        model.addAttribute("company", companyService.get(null));
        model.addAttribute("productCategories", productCategoryService.getProductCategoriesTree(productCategoryService.getProductCategories()));
        return "resources/cms/templates/template1/views/product/index.html";
    }

    /**
     * 产品详情
     *
     * @param model 模型
     * @return 地址
     */
    @RequestMapping("/product/detail")
    public String productDetail(@NonNull @RequestParam String id, Model model) {
        model.addAttribute("company", companyService.get(null));
        model.addAttribute("product", productService.get(id));
        return "resources/cms/templates/template1/views/product/detail.html";
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

    /**
     * 新闻详情
     *
     * @param model 模型
     * @return 地址
     */
    @RequestMapping("/news/detail")
    public String newsDetail(@NonNull @RequestParam String id, Model model) {
        model.addAttribute("company", companyService.get(null));
        model.addAttribute("news", newsService.get(id));
        return "resources/cms/templates/template1/views/news/detail.html";
    }

    /**
     * 企业招聘
     *
     * @param model 模型
     * @return 地址
     */
    @RequestMapping("/job")
    public String job(Model model) {
        model.addAttribute("company", companyService.get(null));
        model.addAttribute("jobs", jobService.getJobs());
        return "resources/cms/templates/template1/views/job/index.html";
    }

    /**
     * 联系我们
     *
     * @param model 模型
     * @return 地址
     */
    @RequestMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("company", companyService.get(null));
        return "resources/cms/templates/template1/views/contact/index.html";
    }

    /**
     * 关于我们
     *
     * @param model 模型
     * @return 地址
     */
    @RequestMapping("/about")
    public String about(Model model) {
        model.addAttribute("company", companyService.get(null));
        return "resources/cms/templates/template1/views/about/index.html";
    }
}
