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
        double totalWages = oldWages.getBaseSalary() + oldWages.getPerformanceSalary();
        wages.setPensionInsurance(calculationPensionInsurance(totalWages));
        wages.setMedicalInsurance(calculationMedicalInsurance(totalWages));
        wages.setUnemploymentInsurance(calculationUnemploymentInsurance(totalWages));
        wages.setInjuryInsurance(calculationInjuryInsurance(totalWages));
        wages.setFertilityInsurance(calculationFertilityInsurance(totalWages));
        wages.setHousingProvidentFund(calculationHousingProvidentFund(totalWages));
        //时间
        wages.setWagesDate(oldWages.getWagesDate());
        wages.setPersonalIncomeTax(calculationPersonalIncomeTax(wages));
        wages.setPaidWages(calculationPaidWages(wages));
        Long isSuc = updateById(wages);
        if (isSuc != 1) {
            return new Result(0, "核对提交的工资信息不存在");
        }
        return new Result(1, "核对成功");
    }


    /**
     * 核算养老保险
     *
     * @param totalWages
     * @return
     */
    private double calculationPensionInsurance(double totalWages) {
        return totalWages * 0.08;
    }

    /**
     * 核算医疗保险
     *
     * @param totalWages
     * @return
     */
    private double calculationMedicalInsurance(double totalWages) {
        return totalWages * 0.02 + 3;
    }

    /**
     * 核算失业保险
     *
     * @param totalWages
     * @return
     */
    private double calculationUnemploymentInsurance(double totalWages) {
        return totalWages * 0.002;
    }

    /**
     * 核算工伤
     *
     * @param totalWages
     * @return
     */
    private double calculationInjuryInsurance(double totalWages) {
        return 0;
    }

    /**
     * 核算生育
     *
     * @param totalWages
     * @return
     */
    private double calculationFertilityInsurance(double totalWages) {
        return 0;
    }

    /**
     * 核算公积金
     *
     * @param totalWages
     * @return
     */
    private double calculationHousingProvidentFund(double totalWages) {
        return totalWages * 0.12;
    }

    /**
     * 核算个人所得税
     * 应纳税额=应纳税所得额×适用税率-速算扣除数
     * 北京的起征点是5000
     *
     * @param wages
     * @return
     */
    private double calculationPersonalIncomeTax(Wages wages) {
        double totalWages = wages.getBaseSalary() + wages.getPerformanceSalary();
        //如果扣完五险一金后小于5000就不用扣个人所得税
        if (totalWages <= 5000) {
            return 0;
        }
        //五险一金总数
        double fiveRisksAndOneGold = wages.getFertilityInsurance() + wages.getPensionInsurance() +
                wages.getMedicalInsurance() + wages.getUnemploymentInsurance() + wages.getInjuryInsurance() +
                wages.getHousingProvidentFund();
        return CalcRate.getPersonalIncomeTax(totalWages - fiveRisksAndOneGold, wages.getWagesDate().getMonthValue(), 0);
    }

    /**
     * 核算实际工资
     *
     * @param wages
     * @return
     */
    private double calculationPaidWages(Wages wages) {
        double totalWages = wages.getBaseSalary() + wages.getPerformanceSalary();
        double fiveRisksAndOneGold = wages.getFertilityInsurance() + wages.getPensionInsurance() +
                wages.getMedicalInsurance() + wages.getUnemploymentInsurance() + wages.getInjuryInsurance() +
                wages.getHousingProvidentFund();

        return totalWages - fiveRisksAndOneGold - wages.getPersonalIncomeTax();
    }

}
