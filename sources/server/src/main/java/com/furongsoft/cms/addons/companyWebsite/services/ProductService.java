package com.furongsoft.cms.addons.companyWebsite.services;

import com.baomidou.mybatisplus.plugins.Page;
import com.furongsoft.base.exceptions.BaseException;
import com.furongsoft.base.file.StorageService;
import com.furongsoft.base.services.BaseService;
import com.furongsoft.cms.addons.companyWebsite.entities.Product;
import com.furongsoft.cms.addons.companyWebsite.mappers.ProductDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 产品服务
 *
 * @author Alex
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class ProductService extends BaseService<Product> {
    private final ProductDao productDao;
    private final StorageService storageService;

    public ProductService(ProductDao productDao, StorageService storageService) {
        super(productDao);
        this.productDao = productDao;
        this.storageService = storageService;
    }

    /**
     * 获取产品列表
     *
     * @param page       页面
     * @param categoryId 分类索引
     * @param name       产品名称
     * @param sortField  排序字段
     * @param sortType   排序类型
     * @return 产品列表
     */
    public Page<Product> getProducts(Page<Product> page, Serializable categoryId, String name, String sortField, String sortType) {
        return page.setRecords(productDao.selectProductListWithParams(page, categoryId, name, sortField, sortType));
    }

    /**
     * 获取产品列表
     *
     * @param categoryId 分类索引
     * @return 产品列表
     */
    public List<Product> getProducts(Serializable categoryId) {
        return productDao.selectProductList(categoryId, null, null, null);
    }

    /**
     * 获取推荐产品列表
     *
     * @param size 产品数量
     * @return 产品列表
     */
    public List<Product> getRecommendProducts(int size) {
        return productDao.selectRecommendProductList(new Page<>(0, size));
    }

    /**
     * 根据索引列表批量删除产品
     *
     * @param ids 索引列表
     */
    public void delProducts(List<Serializable> ids) {
        productDao.deleteBatchIds(ids);
    }

    /**
     * 获取产品
     *
     * @param id 产品索引
     * @throws BaseException 异常
     */
    @Override
    public Product get(Serializable id) throws BaseException {
        return productDao.selectProduct(id);
    }

    /**
     * 获取产品，上一条与下一条产品
     *
     * @param id 产品索引
     * @throws BaseException 异常
     */
    public Product[] getPrevAndNext(Serializable id) throws BaseException {
        Product[] products = new Product[3];
        Product product = productDao.selectProduct(id);
        Long rank = productDao.selectProductRank(product.getCategoryId(), product.getId());
        products[0] = productDao.selectProductByRank(product.getCategoryId(), rank - 1);
        products[1] = product;
        products[2] = productDao.selectProductByRank(product.getCategoryId(), rank + 1);

        return products;
    }

    /**
     * 新增产品
     *
     * @param product 产品
     * @throws BaseException 异常
     */
    @Override
    public void add(Product product) throws BaseException {
        Serializable id = storageService.getFileId(product.getIconPath());
        if (id != null) {
            product.setIcon((String) id);
        }

        productDao.insert(product);
    }

    /**
     * 更新产品
     *
     * @param product 产品
     * @throws BaseException 异常
     */
    @Override
    public void edit(Product product) throws BaseException {
        product.setIcon((String) storageService.getFileId(product.getIconPath()));
        productDao.updateById(product);
    }
}
