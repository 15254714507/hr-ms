package com.hrms.support.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hrms.api.domain.condition.WagesCondition;
import com.hrms.api.domain.entity.Wages;
import com.hrms.api.exception.DaoException;
import com.hrms.api.service.WagesService;
import com.hrms.api.until.CalcRate;
import com.hrms.api.until.Result;
import com.hrms.support.manager.WagesManager;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 孔超
 * @date 2020/5/7 16:45
 */
@Slf4j
@Service(interfaceClass = WagesService.class)
public class WagesServiceImpl implements WagesService {
    @Resource
    WagesManager wagesManager;

    @Override
    public Wages getById(Long id) throws DaoException {
        return wagesManager.getById(id);
    }

    @Override
    public Long insert(Wages wages) throws DaoException {
        return wagesManager.insert(wages);
    }

    @Override
    public Long updateById(Wages wages) throws DaoException {
        return wagesManager.updateById(wages);
    }

    @Override
    public List<Wages> listByYearMonth(WagesCondition wagesCondition) throws DaoException {
        return wagesManager.listByYearMonth(wagesCondition);
    }

    @Override
    public List<Wages> list(WagesCondition wagesCondition) throws DaoException {
        return wagesManager.list(wagesCondition);
    }

    @Override
    public List<Wages> listCheckWages(WagesCondition wagesCondition) throws DaoException {
        //初始的工资信息
        wagesCondition.setStatus(0);
        List<Wages> wagesList = wagesManager.listByYearMonth(wagesCondition);
        //工资信息异常的
        wagesCondition.setStatus(2);
        wagesList.addAll(wagesManager.listByYearMonth(wagesCondition));
        return wagesList;
    }

    @Override
    public Result submitCheckWages(Wages wages) {
        Wages oldWages = wagesManager.getById(wages.getId());
        wages.setBaseSalary(oldWages.getBaseSalary());
        wages.setPerformanceSalary(oldWages.getPerformanceSalary());
        wages.setWagesDate(oldWages.getWagesDate());
        double totalWages = oldWages.getBaseSalary() + oldWages.getPerformanceSalary();
        double vacationWages = calculationVacation(wages, totalWages);
        fillWages(totalWages - vacationWages, wages);
        Long isSuc = updateById(wages);
        if (isSuc != 1) {
            return new Result(0, "核对提交的工资信息不存在");
        }
        return new Result(1, "核对成功");

    }

    @Override
    public Result reedCheckWages(Wages wages) {
        Wages oldWages = wagesManager.getById(wages.getId());
        wages.setWagesDate(oldWages.getWagesDate());
        double totalWages = wages.getBaseSalary() + wages.getPerformanceSalary();
        double vacationWages = calculationVacation(wages, totalWages);
        fillWages(totalWages - vacationWages, wages);
        Long isSuc = updateById(wages);
        if (isSuc != 1) {
            return new Result(0, "重新编辑提交的工资信息不存在");
        }
        return new Result(1, "修改成功");
    }

    /**
     * 填充工资信息
     *
     * @param beforeAmount
     * @param wages
     */
    private void fillWages(double beforeAmount, Wages wages) {
        wages.setPensionInsurance(calculationPensionInsurance(beforeAmount));
        wages.setMedicalInsurance(calculationMedicalInsurance(beforeAmount));
        wages.setUnemploymentInsurance(calculationUnemploymentInsurance(beforeAmount));
        wages.setInjuryInsurance(calculationInjuryInsurance(beforeAmount));
        wages.setFertilityInsurance(calculationFertilityInsurance(beforeAmount));
        wages.setHousingProvidentFund(calculationHousingProvidentFund(beforeAmount));
        wages.setPersonalIncomeTax(calculationPersonalIncomeTax(wages, beforeAmount));
        wages.setPaidWages(calculationPaidWages(wages, beforeAmount));

    }

    /**
     * 核算假期扣的钱  21.75是平均月上班时间
     *
     * @param wages
     * @return
     */
    private double calculationVacation(Wages wages, double totalWages) {
        double dayWages = totalWages / 21.75;
        double sumVacationWages = 0;
        if (wages.getDaysOfAbsenteeism() > 0) {
            sumVacationWages = dayWages * wages.getDaysOfAbsenteeism();
        }
        if (wages.getDaysOfLeave() > 0) {
            sumVacationWages += dayWages * wages.getDaysOfLeave();
        }
        if (wages.getDaysOfSickLeave() > 0) {
            //病假是当前月工资的70% ，那扣的工资是30%
            sumVacationWages += dayWages * wages.getDaysOfSickLeave() * 0.3;
        }
        return sumVacationWages;
    }

    /**
     * 核算养老保险
     *
     * @param beforeAmount 征五险一金和个人所得税前实得的工资
     * @return
     */
    private double calculationPensionInsurance(double beforeAmount) {
        return beforeAmount * 0.08;
    }

    /**
     * 核算医疗保险
     *
     * @param beforeAmount 征五险一金和个人所得税前实得的工资
     * @return
     */
    private double calculationMedicalInsurance(double beforeAmount) {
        return beforeAmount * 0.02 + 3;
    }

    /**
     * 核算失业保险
     *
     * @param beforeAmount 征五险一金和个人所得税前实得的工资
     * @return
     */
    private double calculationUnemploymentInsurance(double beforeAmount) {
        return beforeAmount * 0.002;
    }

    /**
     * 核算工伤
     *
     * @param beforeAmount 征五险一金和个人所得税前实得的工资
     * @return
     */
    private double calculationInjuryInsurance(double beforeAmount) {
        return 0;
    }

    /**
     * 核算生育
     *
     * @param beforeAmount 征五险一金和个人所得税前实得的工资
     * @return
     */
    private double calculationFertilityInsurance(double beforeAmount) {
        return 0;
    }

    /**
     * 核算公积金
     *
     * @param beforeAmount 征五险一金和个人所得税前实得的工资
     * @return
     */
    private double calculationHousingProvidentFund(double beforeAmount) {
        return beforeAmount * 0.12;
    }

    /**
     * 核算个人所得税
     * 应纳税额=应纳税所得额×适用税率-速算扣除数
     * 北京的起征点是5000
     *
     * @param wages
     * @param beforeAmount 征五险一金和个人所得税前实得的工资
     * @return
     */
    private double calculationPersonalIncomeTax(Wages wages, double beforeAmount) {
        //如果扣完五险一金后小于5000就不用扣个人所得税
        if (beforeAmount <= 5000) {
            return 0;
        }
        //五险一金总金额
        double fiveRisksAndOneGold = wages.getFertilityInsurance() + wages.getPensionInsurance() +
                wages.getMedicalInsurance() + wages.getUnemploymentInsurance() + wages.getInjuryInsurance() +
                wages.getHousingProvidentFund();
        return CalcRate.getPersonalIncomeTax(beforeAmount - fiveRisksAndOneGold, wages.getWagesDate().getMonthValue(), 0);
    }

    /**
     * 核算实际工资
     *
     * @param wages
     * @param beforeAmount 征五险一金和个人所得税前实得的工资
     * @return
     */
    private double calculationPaidWages(Wages wages, double beforeAmount) {
        double fiveRisksAndOneGold = wages.getFertilityInsurance() + wages.getPensionInsurance() +
                wages.getMedicalInsurance() + wages.getUnemploymentInsurance() + wages.getInjuryInsurance() +
                wages.getHousingProvidentFund();
        return beforeAmount - fiveRisksAndOneGold - wages.getPersonalIncomeTax();
    }

}
