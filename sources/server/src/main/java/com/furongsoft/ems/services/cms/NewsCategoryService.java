package com.furongsoft.ems.services.cms;

import com.furongsoft.base.entities.TreeNode;
import com.furongsoft.base.exceptions.BaseException;
import com.furongsoft.base.services.BaseService;
import com.furongsoft.ems.entities.cms.NewsCategory;
import com.furongsoft.ems.mappers.cms.NewsCategoryDao;
import com.furongsoft.ems.mappers.cms.NewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * 新闻分类服务
 *
 * @author Alex
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class NewsCategoryService extends BaseService<NewsCategory> {
    private final NewsCategoryDao newsCategoryDao;
    private final NewsDao newsDao;

    @Autowired
    public NewsCategoryService(NewsCategoryDao newsCategoryDao, NewsDao newsDao) {
        super(newsCategoryDao);
        this.newsCategoryDao = newsCategoryDao;
        this.newsDao = newsDao;
    }

    /**
     * 获取所有新闻分类
     *
     * @return 权限列表
     */
    public List<NewsCategory> getNewsCategories() {
        return newsCategoryDao.selectNewsCategoryList();
    }

    @Override
    public void del(Serializable id) throws BaseException {
        newsCategoryDao.deleteNewsCategory(id);
        newsDao.deleteNewsByCategoryId(id);
    }

    /**
     * 获取新闻分类树形列表
     *
     * @param newsCategories 新闻分类列表
     * @return 新闻分类树形列表
     */
    public TreeNode<NewsCategory> getNewsCategoriesTree(final List<NewsCategory> newsCategories) {
        if (newsCategories == null) {
            return new TreeNode<>();
        }

        TreeNode<NewsCategory> root = new TreeNode<>();
        HashMap<Serializable, TreeNode<NewsCategory>> map = new HashMap<>();
        for (NewsCategory newsCategory : newsCategories) {
            map.put(newsCategory.getId(), new TreeNode<>(newsCategory));
        }

        for (NewsCategory newsCategory : newsCategories) {
            TreeNode<NewsCategory> parent = map.get(newsCategory.getParentId());
            TreeNode<NewsCategory> node = map.get(newsCategory.getId());
            if (parent != null) {
                parent.children.add(node);
            } else {
                root.children.add(node);
            }
        }

        return root;
    }
}
