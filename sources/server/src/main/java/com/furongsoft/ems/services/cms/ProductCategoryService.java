package com.furongsoft.ems.services.cms;

import com.furongsoft.base.entities.TreeNode;
import com.furongsoft.base.exceptions.BaseException;
import com.furongsoft.base.file.StorageService;
import com.furongsoft.base.services.BaseService;
import com.furongsoft.ems.entities.cms.ProductCategory;
import com.furongsoft.ems.mappers.cms.ProductCategoryDao;
import com.furongsoft.ems.mappers.cms.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * 产品分类服务
 *
 * @author Alex
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class ProductCategoryService extends BaseService<ProductCategory> {
    private final ProductCategoryDao productCategoryDao;
    private final ProductDao productDao;
    private final StorageService storageService;

    @Autowired
    public ProductCategoryService(ProductCategoryDao productCategoryDao, ProductDao productDao, StorageService storageService) {
        super(productCategoryDao);
        this.productCategoryDao = productCategoryDao;
        this.productDao = productDao;
        this.storageService = storageService;
    }

    /**
     * 获取所有产品分类
     *
     * @return 权限列表
     */
    public List<ProductCategory> getProductCategories() {
        return productCategoryDao.selectProductCategoryList();
    }

    @Override
    public ProductCategory get(Serializable id) throws BaseException {
        return productCategoryDao.selectProductCategory(id);
    }

    @Override
    public void add(ProductCategory productCategory) throws BaseException {
        Serializable id = storageService.getFileId(productCategory.getIconPath());
        if (id != null) {
            productCategory.setIcon((String) id);
        }

        productCategoryDao.insert(productCategory);
    }

    @Override
    public void edit(ProductCategory productCategory) throws BaseException {
        productCategory.setIcon((String) storageService.getFileId(productCategory.getIconPath()));
        productCategoryDao.updateById(productCategory);
    }

    @Override
    public void del(Serializable id) throws BaseException {
        productCategoryDao.deleteProductCategory(id);
        productDao.deleteProductByCategoryId(id);
    }

    /**
     * 获取产品分类树形列表
     *
     * @param productCategories 产品分类列表
     * @return 产品分类树形列表
     */
    public TreeNode<ProductCategory> getProductCategoriesTree(final List<ProductCategory> productCategories) {
        if (productCategories == null) {
            return new TreeNode<>();
        }

        TreeNode<ProductCategory> root = new TreeNode<>();
        HashMap<Serializable, TreeNode<ProductCategory>> map = new HashMap<>(productCategories.size());
        for (ProductCategory productCategory : productCategories) {
            map.put(productCategory.getId(), new TreeNode<>(productCategory));
        }

        for (ProductCategory productCategory : productCategories) {
            TreeNode<ProductCategory> parent = map.get(productCategory.getParentId());
            TreeNode<ProductCategory> node = map.get(productCategory.getId());
            if (parent != null) {
                parent.children.add(node);
            } else {
                root.children.add(node);
            }
        }

        return root;
    }
}
