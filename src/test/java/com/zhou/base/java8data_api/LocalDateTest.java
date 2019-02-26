package com.zhou.base.java8data_api;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * Created by zhoumb on 2019/2/26
 */
public class LocalDateTest {

    /**
     * 构造函数都是私有的
     */
    @Test
    public void localDate() {
        //获取当前时间
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println(localDateTime);
    }

    @Test
    public void getPointDay() {
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
                .append(DateTimeFormatter.ISO_LOCAL_DATE)
                .appendLiteral(' ')
                .append(DateTimeFormatter.ISO_LOCAL_TIME)
                .toFormatter();
        //获取指定日期
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(dateTimeFormatter.format(localDateTime));

        LocalDateTime localDateTime1 = localDateTime.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(localDateTime1);
        LocalDateTime localDateTime2 = localDateTime.withHour(20).withMinute(50);
        System.out.println(localDateTime2);
        LocalDateTime localDateTime3 = localDateTime.plusDays(3);
        System.out.println(localDateTime3);

        //判断闰年
        LocalDate localDate = LocalDate.now();
        boolean leapYear = localDate.isLeapYear();
        System.out.println(leapYear);

        //比较两个日期之间的时间差
        LocalDate of = LocalDate.of(2020, 10, 2);
        Period period = Period.between(localDate, of);
        System.out.println(period.getDays());
        System.out.println(period.getMonths());
        System.out.println(period.getYears());
        System.out.println(period.getUnits());
        System.out.println(of.until(localDate,ChronoUnit.DAYS));
        System.out.println(of.until(localDate,ChronoUnit.MONTHS));
        System.out.println(of.until(localDate,ChronoUnit.YEARS));
    }
}
