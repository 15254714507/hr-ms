package com.hrms.support.manager.impl;

import com.alibaba.fastjson.JSON;
import com.hrms.api.domain.condition.JobCondition;
import com.hrms.api.domain.entity.Job;
import com.hrms.api.exception.DaoException;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.support.dao.JobDao;
import com.hrms.support.manager.JobManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/13 23:17
 */
@Slf4j
@Component("jobManager")
public class JobManagerImpl implements JobManager {
    @Resource
    JobDao jobDao;

    @Override
    public Job getById(Long id) throws DaoException {
        try {
            return jobDao.getById(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(Job job) throws DaoException {
        JobCondition jobCondition = getJobCondition(job);
        List<Job> jobList = list(jobCondition);
        if (jobList != null && jobList.size() > 0) {
            log.warn("新添加的岗位已存在 job:{}", JSON.toJSONString(job));
            return 0L;
        }
        job.setCreateTime(LocalDateTimeFactory.getLocalDateTime());
        job.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return jobDao.insert(job);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long updateById(Job job) throws DaoException {
        if (getById(job.getId()) == null) {
            log.warn("要修改的岗位不存在 job：{}", JSON.toJSONString(job));
            return 0L;
        }
        JobCondition jobCondition = getJobCondition(job);
        List<Job> jobList = list(jobCondition);
        if (jobList != null && jobList.size() > 0) {
            log.warn("要修改后的岗位已存在，job：{}", JSON.toJSONString(job));
            return 0L;
        }
        job.setUpdateTime(LocalDateTimeFactory.getLocalDateTime());
        try {
            return jobDao.updateById(job);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteById(Long id) throws DaoException {
        if (getById(id) == null) {
            log.warn("要删除的岗位不存在 id{}", id);
            return 0L;
        }
        try {
            return jobDao.deleteById(id);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new DaoException(e);
        }
    }

    @Override
    public List<Job> list(JobCondition jobCondition) throws DaoException {
        try {
            return jobDao.list(jobCondition);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    /**
     * job转换成JobCondition
     *
     * @param job
     * @return
     */
    private JobCondition getJobCondition(Job job) {
        JobCondition jobCondition = new JobCondition();
        jobCondition.setDepartmentId(job.getDepartmentId());
        jobCondition.setJobName(job.getJobName());
        return jobCondition;
    }
}
