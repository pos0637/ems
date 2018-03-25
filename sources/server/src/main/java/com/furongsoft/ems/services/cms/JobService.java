package com.furongsoft.ems.services.cms;

import com.baomidou.mybatisplus.plugins.Page;
import com.furongsoft.base.services.BaseService;
import com.furongsoft.ems.entities.cms.Job;
import com.furongsoft.ems.mappers.cms.JobDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 工作服务
 *
 * @author Alex
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class JobService extends BaseService<Job> {
    private final JobDao jobDao;

    @Autowired
    public JobService(JobDao jobDao) {
        super(jobDao);
        this.jobDao = jobDao;
    }

    /**
     * 获取所有工作
     *
     * @param page      页面
     * @param name      公司描述名称
     * @param sortField 排序字段
     * @param sortType  排序类型
     * @return 工作列表
     */
    public Page<Job> getJobs(Page<Job> page, String name, String sortField, String sortType) {
        return page.setRecords(jobDao.selectJobListWithParams(page, name, sortField, sortType));
    }

    /**
     * 获取所有工作
     *
     * @return 工作列表
     */
    public List<Job> getJobs() {
        return jobDao.selectJobList();
    }

    /**
     * 根据索引列表批量删除工作
     *
     * @param ids 索引列表
     */
    public void delJobs(List<Serializable> ids) {
        jobDao.deleteBatchIds(ids);
    }
}
