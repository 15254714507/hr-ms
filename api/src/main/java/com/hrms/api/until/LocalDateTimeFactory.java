package com.hrms.api.until;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 生成时间的工厂类
 *
 * @author 孔超
 * @date 2020/4/13 23:07
 */
public class LocalDateTimeFactory {
    /**
     * 返回当天日期
     *
     * @return
     */
    public static LocalDate getLocalDate() {
        return LocalDate.now();
    }

    /**
     * 返回时间，准确的秒
     *
     * @return
     */
    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

}
