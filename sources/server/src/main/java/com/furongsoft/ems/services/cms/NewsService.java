package com.furongsoft.ems.services.cms;

import com.baomidou.mybatisplus.plugins.Page;
import com.furongsoft.base.exceptions.BaseException;
import com.furongsoft.base.file.StorageService;
import com.furongsoft.base.services.BaseService;
import com.furongsoft.ems.entities.cms.News;
import com.furongsoft.ems.mappers.cms.NewsDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 新闻服务
 *
 * @author Alex
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class NewsService extends BaseService<News> {
    private final NewsDao newsDao;
    private final StorageService storageService;

    public NewsService(NewsDao newsDao, StorageService storageService) {
        super(newsDao);
        this.newsDao = newsDao;
        this.storageService = storageService;
    }

    /**
     * 获取新闻
     *
     * @param page       页面
     * @param categoryId 分类索引
     * @param name       新闻名称
     * @param sortField  排序字段
     * @param sortType   排序类型
     * @return 新闻
     */
    public Page<News> getNews(Page<News> page, String categoryId, String name, String sortField, String sortType) {
        return page.setRecords(newsDao.selectNewsListWithParams(page, categoryId, name, sortField, sortType));
    }

    /**
     * 获取新闻
     *
     * @param categoryId 分类索引
     * @return 新闻
     */
    public List<News> getNews(String categoryId) {
        return newsDao.selectNewsList(categoryId, null, null, null);
    }

    /**
     * 根据索引列表批量删除新闻
     *
     * @param ids 索引列表
     */
    public void delNews(List<Serializable> ids) {
        newsDao.deleteBatchIds(ids);
    }

    /**
     * 获取新闻
     *
     * @param id 新闻索引
     * @throws BaseException 异常
     */
    @Override
    public News get(Serializable id) throws BaseException {
        return newsDao.selectNews(id);
    }

    /**
     * 新增新闻
     *
     * @param news 新闻
     * @throws BaseException 异常
     */
    @Override
    public void add(News news) throws BaseException {
        Serializable id = storageService.getFileId(news.getIconPath());
        if (id != null) {
            news.setIcon((String) id);
        }

        newsDao.insert(news);
    }

    /**
     * 更新新闻
     *
     * @param news 新闻
     * @throws BaseException 异常
     */
    @Override
    public void edit(News news) throws BaseException {
        news.setIcon((String) storageService.getFileId(news.getIconPath()));
        newsDao.updateById(news);
    }
}
