package com.zhou.hd;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhoumb on 2019/3/27
 */
public class RandomTest {

    @Test
    public void test() {
        Random random = new Random();
        System.out.println(random.nextInt(10));
        System.out.println(random.nextInt(12));
    }

    @Test
    public void enumTest() {
        Zz zzz = Zz.valueOf("1");
        System.out.println(zzz);
    }

    enum Zz {
        ZZZ(1),;

        private int value;

        Zz(int value) {
            this.value = value;
        }
    }

    public static final String ptag = "?";
    public static final String sparrtag = "&";
    public static final String sptag = "=";

    @Test
    public void map() {
        String content = "https://wx.vibeac.com/bizcardBackend/qrcode/login/scan.do?unionId=fa02a392874c4b93b1d3f50a84d95441&type=import";
        String paramsStr = StringUtils.indexOf(content, ptag) == -1 ? null : StringUtils.split(content, ptag)[1];
        Map<String, String> result = new HashMap<>();
        if (StringUtils.isNotBlank(paramsStr)) {
            if (paramsStr.indexOf(sparrtag) != -1) {
                String[] params = StringUtils.split(paramsStr, sparrtag);
                for (int i = 0; i < params.length; i++) {
                    String paramStr = params[i];
                    String[] param = StringUtils.split(paramStr, sptag);
                    if (param.length == 2) {
                        result.put(param[0], param[1]);
                    }
                }
            } else {
                String[] param = StringUtils.split(paramsStr, sptag);
                if (param.length == 2) {
                    result.put(param[0], param[1]);
                }
            }
        }
        for (Map.Entry<String, String> entry : result.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    @Test
    public void listClone() {
        List<String> ids = new ArrayList<>();
        ids.add("1");
        ids.add("2");
        ids.add("3");
        List<String> clones = (List<String>) ((ArrayList<String>) ids).clone();
        clones.add("5");
        for (String id : ids) {
            System.out.println(id);
        }
        System.out.println("-------------------------");

        List<String> addAll = new ArrayList<>();
        addAll.add("13");
        addAll.add("15");
        clones.addAll(addAll);
        for (String clone : clones) {
            System.out.println(clone);
        }
        System.out.println("----------------------------");
        addAll.add("23");
        for (String s : addAll) {
            System.out.println(s);
        }

    }

    @Test
    public void stringTokenizer() {
        String str = "fsdafd*7dfhsjkf* 7fsdyafsd& fsaf8f9f9fsdfdsaf((F*DF(ud";
        StringTokenizer stringTokenizer = new StringTokenizer(str, "*");
        while (stringTokenizer.hasMoreElements()) {
            System.out.println(stringTokenizer.nextElement());
        }
    }

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Test
    public void time() {
        Date now = getSpeficyBeginDay(new Date());//当前时间,起始时间
        Date weekStart = new Date(now.getTime() - 3600 * 24 * 7 * 1000);
        Date weekEnd = getSpeficyDayEnd(getNextDay(now, -1));
        /*String date = DateUtils.format(weekEnd, "yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int year = calendar.get(Calendar.YEAR);*/
        Date starTime = getSpeficyBeginDay(weekStart);
        Date endTime = getSpeficyDayEnd(weekEnd);
        System.out.println(simpleDateFormat.format(now) + " : " + simpleDateFormat.format(weekStart) + " : " + simpleDateFormat.format(weekEnd));
        System.out.println(simpleDateFormat.format(starTime) + " : " + simpleDateFormat.format(endTime));
    }

    public Date getSpeficyBeginDay(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public Date getSpeficyDayEnd(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static Date getNextDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
        return cal.getTime();
    }
}
