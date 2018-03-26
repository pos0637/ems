package com.furongsoft.ems.controllers.api.v1.cms;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.furongsoft.base.entities.PageRequest;
import com.furongsoft.base.entities.PageResponse;
import com.furongsoft.base.entities.TreeNode;
import com.furongsoft.base.exceptions.BaseException;
import com.furongsoft.base.restful.entities.RestResponse;
import com.furongsoft.ems.entities.cms.Product;
import com.furongsoft.ems.entities.cms.ProductCategory;
import com.furongsoft.ems.services.cms.ProductCategoryService;
import com.furongsoft.ems.services.cms.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * 产品控制器
 *
 * @author Alex
 */
@RestController
@RequestMapping("/api/v1/cms/product")
public class ProductController {
    private final ProductCategoryService productCategoryService;
    private final ProductService productService;

    @Autowired
    public ProductController(ProductCategoryService productCategoryService, ProductService productService) {
        this.productCategoryService = productCategoryService;
        this.productService = productService;
    }

    /**
     * 获取全部产品
     *
     * @param pageRequest 页面
     * @param categoryId  分类索引
     * @param name        名称
     * @param sortField   排序字段
     * @param sortType    排序方式
     * @return 全部产品
     */
    @GetMapping("/products")
    public PageResponse getProducts(
            PageRequest pageRequest,
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) String sortType) {
        Page<Product> page = productService.getProducts(pageRequest.getPage(), categoryId, name, sortField, sortType);
        return new PageResponse<>(HttpStatus.OK, page);
    }

    /**
     * 获取产品
     *
     * @param id 产品索引
     * @return 响应内容
     */
    @GetMapping("/news/{id}")
    public RestResponse getNews(@NonNull @PathVariable String id) {
        return new RestResponse(HttpStatus.OK, null, productService.get(id));
    }

    /**
     * 获取产品
     *
     * @param id 产品索引
     * @return 响应内容
     */
    @GetMapping("/products/{id}")
    public RestResponse getProduct(@NonNull @PathVariable String id) {
        return new RestResponse(HttpStatus.OK, null, productService.get(id));
    }

    /**
     * 添加产品
     *
     * @param product 产品
     * @return 响应内容
     */
    @PostMapping("/products")
    public RestResponse addProduct(@NonNull Product product) {
        productService.add(product);
        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 更新产品
     *
     * @param id      产品索引
     * @param product 产品
     * @return 响应内容
     */
    @PutMapping("/products/{id}")
    public RestResponse editProduct(@NonNull @PathVariable String id, @NonNull Product product) {
        product.setId(id);
        productService.edit(product);
        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 删除产品
     *
     * @param id 产品索引
     * @return 响应内容
     */
    @DeleteMapping("/products/{id}")
    public RestResponse delProduct(@NonNull @PathVariable String id) {
        productService.del(id);
        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 批量删除产品
     *
     * @param delete 产品索引列表
     * @return 响应内容
     */
    @PostMapping("/products/batch")
    public RestResponse delProducts(@NonNull @RequestParam String delete) {
        try {
            delete = URLDecoder.decode(delete, "UTF-8");
            List<Serializable> ids = JSON.parseArray(delete, Serializable.class);
            productService.delProducts(ids);
        } catch (UnsupportedEncodingException e) {
            throw new BaseException.IllegalArgumentException();
        }

        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 获取产品分类列表
     *
     * @return 产品分类列表
     */
    @GetMapping("/productCategories")
    public RestResponse getProductCategories() {
        TreeNode<ProductCategory> root = productCategoryService.getProductCategoriesTree(productCategoryService.getProductCategories());
        return new RestResponse(HttpStatus.OK, null, JSON.toJSONString(root.children));
    }

    /**
     * 获取产品分类
     *
     * @param id 产品分类索引
     * @return 响应内容
     */
    @GetMapping("/productCategories/{id}")
    public RestResponse getProductCategory(@NonNull @PathVariable String id) {
        return new RestResponse(HttpStatus.OK, null, productCategoryService.get(id));
    }

    /**
     * 添加产品分类
     *
     * @param productCategory 产品分类
     * @return 响应内容
     */
    @PostMapping("/productCategories")
    public RestResponse addProductCategory(@NonNull ProductCategory productCategory) {
        productCategoryService.add(productCategory);
        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 更新产品分类
     *
     * @param id              产品分类索引
     * @param productCategory 产品分类
     * @return 响应内容
     */
    @PutMapping("/productCategories/{id}")
    public RestResponse editProductCategory(@NonNull @PathVariable String id, @NonNull ProductCategory productCategory) {
        productCategory.setId(id);
        productCategoryService.edit(productCategory);
        return new RestResponse(HttpStatus.OK);
    }

    /**
     * 删除产品分类
     *
     * @param id 产品分类索引
     * @return 响应内容
     */
    @DeleteMapping("/productCategories/{id}")
    public RestResponse delProductCategory(@NonNull @PathVariable String id) {
        productCategoryService.del(id);
        return new RestResponse(HttpStatus.OK);
    }
}
