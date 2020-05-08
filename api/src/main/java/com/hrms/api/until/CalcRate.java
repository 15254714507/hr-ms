package com.hrms.api.until;

/**
 * 计算个人所得税的工具类
 *
 * @author 孔超
 * @date 2020/5/8 3:10
 */
public class CalcRate {
    /**
     * 纳税基准
     */
    public static final double BASE = 5000.00;
    /**
     * 税率
     */
    static double[] rate;
    /**
     * 税率对应表
     */
    static double[][] rates = {{0, 0, 0}, {36000, 0.03, 0},
            {144000, 0.1, 2520}, {3000000, 0.2, 16920},
            {420000, 0.25, 31920}, {660000, 0.3, 52920},
            {960000, 0.35, 85920}, {999999999, 0.45, 181920}};

    public static double[] getRate(double total) {
        double[] rate = {36000, 0.03, 0};
        for (int i = 1; i < rates.length; i++) {
            if (total <= rates[0][0]) {
                rate = rates[0];
            } else if (total > rates[i - 1][0] && total <= rates[i][0]) {
                rate = rates[i];
                break;
            }

        }
        return rate;
    }

    /**
     * 获得个人所得税交的钱
     *
     * @param beforeAmount 扣完五险一金后的准备扣个人所得税的工资
     * @param month        几月份
     * @param p            专项扣除数
     * @return
     */
    public static double getPersonalIncomeTax(double beforeAmount, int month, double p) {
        double rateAmount = 0;
        double tolerate = 0;
        for (int i = 1; i <= month; i++) {
            double amount = (beforeAmount - p - BASE) * i;
            rate = getRate(amount);
            rateAmount = Math.round((amount * rate[1]) * 100) / 100 - rate[2] - tolerate;
//            System.out.println("第" + i + "月:应纳税金额：" + amount + ",税率：" + rate[1] + ",速算扣除金额："
//                    + rate[2] + ",往月纳税金额合计：" + tolerate + ",本月应缴税额：" + rateAmount);
            tolerate = tolerate + rateAmount;
        }
        return rateAmount;
    }

//    public static void main(String[] args) {
//        double i = getPersonalIncomeTax(15000, 12);
//        System.out.println(i);
//    }
}
