package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.condition.DimissionUserCondition;
import com.hrms.api.domain.condition.UserJobCondition;
import com.hrms.api.domain.dto.Employees;
import com.hrms.api.domain.entity.DimissionUser;
import com.hrms.api.domain.entity.User;
import com.hrms.api.domain.entity.UserJob;
import com.hrms.api.exception.DaoException;
import com.hrms.api.exception.ServiceException;
import com.hrms.api.service.DimissionUserService;
import com.hrms.api.service.UserJobService;
import com.hrms.api.service.UserService;
import com.hrms.api.until.LocalDateTimeFactory;
import com.hrms.api.until.Result;
import com.hrms.support.manager.DimissionUserManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/4/27 18:40
 */
@Slf4j
@Service(interfaceClass = DimissionUserService.class)
public class DimissionUserServiceImpl implements DimissionUserService {
    @Resource
    DimissionUserManager dimissionUserManager;
    @Resource
    UserService userService;
    @Resource
    UserJobService userJobService;

    @Override
    public DimissionUser getById(Long id) throws DaoException {
        return dimissionUserManager.getById(id);
    }

    @Override
    public DimissionUser getDimissionUserMsgByUsername(String username) {
        DimissionUserCondition dimissionUserCondition = new DimissionUserCondition();
        dimissionUserCondition.setUsername(username);
        List<DimissionUser> dimissionUserList = dimissionUserManager.list(dimissionUserCondition);
        if (dimissionUserList == null || dimissionUserList.size() < 1) {
            return assemblyDimissionUser(username);
        }
        return dimissionUserList.get(0);
    }

    @Override
    public Result insert(DimissionUser dimissionUser) {
        Result result = fillDimissionUser(dimissionUser);
        if (result != null) {
            return result;
        }
        Long isSuc = dimissionUserManager.insert(dimissionUser);
        if (isSuc != 1) {
            return new Result(0, "已申请，请勿重复申请离职");
        }
        return new Result(1, "申请成功");
    }

    @Override
    public Long updateById(DimissionUser dimissionUser) throws DaoException {
        return dimissionUserManager.updateById(dimissionUser);
    }

    @Override
    public Result deleteById(Long id) throws DaoException {
        DimissionUser dimissionUser = dimissionUserManager.getById(id);
        if (dimissionUser == null) {
            return new Result(0, "要删除的离职申请不存在");
        }
        if (dimissionUser.getSteps() != 1) {
            return new Result(0, "要删除的离职申请，已过审核,不允许再删除");
        }
        Long isSuc = dimissionUserManager.deleteById(id);
        if (isSuc != 1) {
            return new Result(0, "要删除的离职申请不存在");
        }
        //这里应该对申请者有个通知
        return new Result(1, "删除成功");
    }

    @Override
    public List<DimissionUser> list(DimissionUserCondition dimissionUserCondition) throws DaoException {
        return dimissionUserManager.list(dimissionUserCondition);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteUser(Long dimissionUserId) throws ServiceException {
        try {
            DimissionUser dimissionUser = dimissionUserManager.getById(dimissionUserId);
            //如果为空或者离职步骤不为3则说明要不不存在，要不就是已经过了删除账号或者没到删除账号的步骤
            Result result = validateDimissionUser(dimissionUser);
            if (result != null) {
                return result;
            }
            User user = userService.getByUsername(dimissionUser.getUsername());
            result = validateUser(user);
            if (result != null) {
                return result;
            }
            dimissionUser.setSteps(4);
            dimissionUserManager.updateById(dimissionUser);
            userService.deleteById(user.getId());
        } catch (Exception e) {
            log.warn("在修改离职信息为已完成并删除账号信息时出现系统异常 dimissionUserId{}", dimissionUserId, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return new Result(1, "删除账号成功，半小时后生效");
    }

    /**
     * 验证离职用户的信息
     *
     * @param dimissionUser
     * @return
     */
    private Result validateDimissionUser(DimissionUser dimissionUser) {
        if (dimissionUser == null) {
            return new Result(0, "没有此人的离职信息");
        }
        if (dimissionUser.getSteps() != 3) {
            return new Result(0, "离职步骤还没到删除账号信息这一步");
        }
        return null;
    }

    /**
     * 验证用户信息
     *
     * @param user
     * @return
     */
    private Result validateUser(User user) {
        if (user == null) {
            return new Result(0, "没有此账号信息，请联系运营人员");
        }
        if (user.getDelete()) {
            return new Result(0, "此账号已被删除，请勿重复操作");
        }
        return null;
    }

    /**
     * 添加离职表前把缺少的信息填充完整
     *
     * @param dimissionUser
     * @return
     */
    private Result fillDimissionUser(DimissionUser dimissionUser) {
        User user = userService.getByUsername(dimissionUser.getUsername());
        if (user == null) {
            return new Result(0, "没有此用户,请刷新后重试");
        }
        UserJobCondition userJobCondition = new UserJobCondition();
        userJobCondition.setUserId(user.getId());
        List<UserJob> userJobList = userJobService.list(userJobCondition);
        if (userJobList == null || userJobList.size() < 1) {
            return new Result(0, "没有此用户的岗位，请联系运营人员");
        }
        dimissionUser.setTypesOfEmployees(userJobList.get(0).getTypesOfEmployees());
        dimissionUser.setIdentityCard(user.getIdentityCard());
        dimissionUser.setDateOfEntry(user.getEmploymentDate());
        dimissionUser.setDateOfSeparation(LocalDateTimeFactory.getLocalDate());
        return null;
    }

    /**
     * 在离职表里没有查到此账号的信息说明此账号还没有申请离职，所以拼装一个离职信息返回
     *
     * @param username
     * @return
     */
    private DimissionUser assemblyDimissionUser(String username) {
        User user = userService.getByUsername(username);
        //说明没有此账号，就返回空
        if (user == null) {
            return null;
        }
        Employees employees = userJobService.getEmployees(user.getId());
        DimissionUser dimissionUser = transEmployeesToDimissionUser(employees);
        dimissionUser.setUsername(user.getUsername());
        dimissionUser.setName(user.getName());
        if (user.getGender() == 0) {
            dimissionUser.setGender("男");
        } else {
            dimissionUser.setGender("女");
        }
        dimissionUser.setIdentityCard(user.getIdentityCard());
        return dimissionUser;
    }

    /**
     * employees转换成DimissionUser
     *
     * @param employees
     * @return
     */
    private DimissionUser transEmployeesToDimissionUser(Employees employees) {
        DimissionUser dimissionUser = new DimissionUser();
        dimissionUser.setDepartmentName(employees.getDepartmentName());
        dimissionUser.setJobName(employees.getJobName());
        //离职流程第一步申请为0
        dimissionUser.setSteps(0);
        return dimissionUser;
    }


}
