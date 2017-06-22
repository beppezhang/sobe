package com.kpuswebup.comom.util;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author zhuhuipei
 */
public class DateUtil {

    public static final String formatStr1 = "yyyy-MM-dd HH:mm:ss";
    public static final String formatStr2 = "yyyy-MM-dd";
    public static final String formatStr3 = "yyyyMMdd";
    public static final String formatStr4 = "yyyy-MM";
    public static final String formatStr5 = "yyyyMMddHHmmss";
    public static final String formatStr6 = "yyyy-MM-dd HH:mm:ss.S";
    public static final String formatUTC = "yyyy-MM-dd'T'HH:mm:ss.S'Z'";
    
    public static final String getDateTimeDir(Date date) {
        return DateUtil.getDateFormat(date, "yyyy") + File.separator + DateUtil.getDateFormat(date, "MM")
               + File.separator + DateUtil.getDateFormat(date, "dd");
    }
    /**
     * 返回日期字符串："yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" 格式。
     * 
     * @param date
     * @return
     */
    public static final String getDateTimeUTC(Date date) {
        if (date == null) return "";
        DateFormat ymdhmsFormat = new SimpleDateFormat(formatUTC);
        return ymdhmsFormat.format(date);
    }
    
    /**
     * 返回日期字符串："yyyy-MM-dd HH:mm:ss" 格式。
     * 
     * @param date
     * @return
     */
    public static final String getDateTime(Date date) {
        if (date == null) return "";
        DateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return ymdhmsFormat.format(date);
    }

    /**
     * 两个时间之间相差距离多少天
     * 
     * @param one 时间参数 1：
     * @param two 时间参数 2：
     * @return 相差天数
     */
    public static long getDistanceDays(String str1, String str2) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date one;
        Date two;
        long days = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 距离今天相差多少天
     * 
     * @2012-7-26
     * @author zhuhuipei
     * @param str
     * @return
     * @throws Exception
     */
    public static long getDistanceDaysTONow(String str) throws Exception {
        Date now = new Date();
        String nowstr = getDateFormat(now, formatStr2);
        return getDistanceDays(str, nowstr);
    }

    public static final String getDateDay(Date date) {
        if (date == null) {
            return "";
        }
        DateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return ymdhmsFormat.format(date);
    }

    /**
     * 根据指定的格式，返回指定日期的对应格式
     * 
     * @param date
     * @param formatStr 可以是"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm","yyyy-MM-dd HH","yyyy-MM-dd"等等
     * @return
     */
    public static String getDateFormat(Date date, String formatStr) {
        if (date == null || formatStr == null) return "";
        DateFormat ymdFormat = new SimpleDateFormat(formatStr);
        String tmpStr = ymdFormat.format(date).toString();
        if (tmpStr == null) return "";
        return tmpStr;
    }

    /**
     * 时间比较
     * 
     * @param args
     * @throws ParseException
     */

    public static boolean isDateBefore(String date1, String date2) {
        try {
            DateFormat df = DateFormat.getDateTimeInstance();
            return df.parse(date1).before(df.parse(date2));
        } catch (ParseException e) {
            System.out.print("[SYS] " + e.getMessage());
            return false;
        }
    }

    /**
     * 字符传化成时间类型
     * 
     * @2012-7-19
     * @author zhuhuipei
     * @param ds
     * @return
     * @throws ParseException
     */
    public static Date strintToDatetime(String ds) throws ParseException {
        if (ds == null) {
            return null;
        }
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = sdf.parse(ds);
        return d;
    }

    /**
     * 字符传化成时间类型
     * 
     * @2012-7-19
     * @author zhuhuipei
     * @param ds
     * @return
     * @throws ParseException
     */
    public static Date strintToDatetime(String ds, String DateFormat) throws ParseException {
        if (StringUtil.isEmpty(ds)) {
            return null;
        }
        SimpleDateFormat sdf = new java.text.SimpleDateFormat(DateFormat);
        Date d = sdf.parse(ds);
        return d;
    }

    /**
     * 时间向前推
     * 
     * @param days
     * @return
     */
    public static String getDateAgo(int days) {
        SimpleDateFormat sd = new SimpleDateFormat(DateUtil.formatStr2);
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.DAY_OF_YEAR);
        i = i - days;
        calendar.set(Calendar.DAY_OF_YEAR, i);
        return sd.format(calendar.getTime());
    }

    /**
     * 时间向前推
     * 
     * @param days
     * @return
     */
    public static Date getDateAgoDate(int days, String format) {
        SimpleDateFormat sd = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.DAY_OF_YEAR);
        i = i - days;
        calendar.set(Calendar.DAY_OF_YEAR, i);
        Date date = null;
        try {
            date = strintToDatetime(sd.format(calendar.getTime()), format);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 时间向前推
     * 
     * @param days
     * @return
     */
    public static String getDateAgo(int days, String format) {
        SimpleDateFormat sd = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.DAY_OF_YEAR);
        i = i - days;
        calendar.set(Calendar.DAY_OF_YEAR, i);
        return sd.format(calendar.getTime());
    }

    /**
     * 时间推移
     * 
     * @param days
     * @return
     */
    public static String getHourLater(int hour, String format) {
        SimpleDateFormat sd = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.add(java.util.Calendar.HOUR_OF_DAY, hour);
        return sd.format(cal.getTime());
    }

    /**
     * 时间推移
     * 
     * @param days
     * @return
     */
    public static Date getHourLaterDate(int hour) {
        Calendar cal = Calendar.getInstance();
        cal.add(java.util.Calendar.HOUR_OF_DAY, hour);
        return cal.getTime();
    }

    /**
     * 获取当前周 周一日期 按周一至周日 是一个周期计算。 例如：2013-11-18至2013-11-24
     * 
     * @autor lijn 2013-11-22
     * @return
     */
    public static Date getMonDayCurrentWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    public static Date getMonDayLastWeek() {
        return addToDay(getMonDayCurrentWeek(), -7);
    }

    /**
     * 获取当前周 周日日期 按周一至周日 是一个周期计算。例如：2013-11-18至2013-11-24
     * 
     * @autor lijn 2013-11-22
     * @return
     */
    public static Date getSunDayCurrentWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.add(Calendar.DAY_OF_MONTH, 6);
        return cal.getTime();
    }

    public static Date getSunDayLastWeek() {
        return addToDay(getSunDayCurrentWeek(), -7);
    }

    /**
     * 获得当前日期 + N个天 之后的日期
     * 
     * @autor lijn 2013-11-22
     * @param oldDate
     * @param n
     * @return
     */
    public static Date addToDay(Date oldDate, int n) {
        Date newDate = null;
        Calendar calOld = Calendar.getInstance();
        calOld.setTime(oldDate);
        int day = calOld.get(Calendar.DAY_OF_MONTH);
        Calendar calNew = Calendar.getInstance();
        calNew.setTime(oldDate);
        calNew.set(Calendar.DAY_OF_MONTH, n + day);
        newDate = calNew.getTime();
        return newDate;
    }

    /**
     * 计算两个日期之间相差的天数
     * 
     * @autor lijn 2013-11-25
     * @param date1 较小的时间
     * @param date2 较大的时间
     * @return 相差天数
     */
    public static int getBetweenDays(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date1 = sdf.parse(sdf.format(date1));
            date2 = sdf.parse(sdf.format(date2));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();

        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return new BigDecimal(String.valueOf(between_days)).abs().intValue();
    }

    /**
     * 计算日期间相差的秒数
     * 
     * @author shihl 2014-3-5
     * @param startDate 起始时间
     * @param endDate 结束时间
     * @return
     */
    public static int getBetweenSeconds(Date startDate, Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        long startTime = cal.getTimeInMillis();
        cal.setTime(endDate);
        long endTime = cal.getTimeInMillis();

        long betweenMillionSeconds = endTime - startTime;

        int betweenSeconds = BigDecimal.valueOf(betweenMillionSeconds).divide(new BigDecimal(1000),
                                                                              BigDecimal.ROUND_HALF_DOWN).intValue();

        return betweenSeconds;
    }

    /**
     * 获取当前系统时间
     * 
     * @author wangxm 2013年11月4日
     * @return
     */
    public static Date getSystemDate() {
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        return curDate;
    }

    /**
     * 查询上周周一
     * 
     * @author wangxm 2013年11月21日
     * @return
     */
    public static Date getMondayOfLastWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1 - 7);
        return c.getTime();
    }

    /**
     * 查询上周周日
     * 
     * @author wangxm 2013年11月21日
     * @return
     */
    public static Date getSundayOfLastWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week);
        return c.getTime();
    }

    /**
     * 时间比较
     * 
     * @author wangxm 2013年11月4日
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isDateBefore(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return date1.before(date2);
    }

    /**
     * 两个时间之间相差距离多少天
     * 
     * @author wangxm 2013年11月4日
     * @param date1
     * @param date2
     * @return
     */
    public static long getDistanceDays(Date date1, Date date2) {
        long days = 0;
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        days = diff / (1000 * 60 * 60 * 24);
        return days;
    }

    /**
     * 两个时间之间相差距离多少分钟
     * 
     * @author wangxm 2013年11月4日
     * @param date1
     * @param date2
     * @return
     */
    public static long getDistanceMinutes(Date date1, Date date2) {
        long days = 0;
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        days = diff / (1000 * 60);
        return days;
    }

    /**
     * 格式化date。
     * 
     * @param date
     * @return
     */
    public static final String formatDate(Date date, String format) {
        if (date == null) return "";
        DateFormat ymdhmsFormat = new SimpleDateFormat(format);
        return ymdhmsFormat.format(date);
    }

    public static final Date getFirstDayOfCurrentMonth() {
        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.getActualMinimum(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return c.getTime();
    }

    public static final Date getLastDayOfCurrentMonth() {
        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return c.getTime();
    }

    public static final Date getFirstDayOfLastMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(getFirstDayOfCurrentMonth());
        c.roll(Calendar.MONTH, -1);
        return c.getTime();
    }

    public static final Date getlastDayOfLastMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(getLastDayOfCurrentMonth());
        c.roll(Calendar.MONTH, -1);
        return c.getTime();
    }

    /**
     * 获取两个日期间隔的时间列表()
     * 
     * @param d1
     * @param d2
     * @return
     * @throws ParseException 2014年4月30日
     * @author wangxm
     */
    public static List<String> getBetweenDate(String d1, String d2) throws ParseException {
        List<String> list = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar gc1 = Calendar.getInstance();
        Calendar gc2 = Calendar.getInstance();

        if (!StringUtil.isEmpty(d2)) {
            gc2.setTime(sdf.parse(d2));
        } else {
            gc1.setTime(addToDay(gc2.getTime(), -2));
            ;
        }
        if (!StringUtil.isEmpty(d1)) {
            gc1.setTime(sdf.parse(d1));
        } else {
            gc1.setTime(addToDay(gc2.getTime(), -32));
            ;
        }
        do {
            Calendar gc3 = (Calendar) gc1.clone();
            list.add(sdf.format(gc3.getTime()));
            gc1.add(Calendar.DAY_OF_MONTH, 1);
        } while (!gc1.after(gc2));
        return list;
    }
    
    /**
     * 字符传化成时间类型
     * @date 2014年11月11日
     * @author wanghehua
     * @param ds
     * @return
     * @throws ParseException
     * @since JDK 1.6
     * @Description
     */
    public static Date strintToDatetimeYMD(String ds) throws ParseException {
        if (ds == null) {
            return null;
        }
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(ds);
        return d;
    }
    /**
     * 字符传化成时间类型
     * @date 2014年11月11日
     * @author wanghehua
     * @param ds
     * @return
     * @throws ParseException
     * @since JDK 1.6
     * @Description
     */
    public static Date strintToDatetimeYMDHMS(String ds) throws ParseException {
        if (ds == null) {
            return null;
        }
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = sdf.parse(ds);
        return d;
    }

    /********************************************************************************/
    public static Date getTimeByUTC()
    {
    	Calendar c = Calendar.getInstance();
    	c.add(java.util.Calendar.MILLISECOND, -(c.get(java.util.Calendar.ZONE_OFFSET) + c.get(java.util.Calendar.DST_OFFSET)));
    	return new Date(c.getTimeInMillis());
    }

    public static String getStringByUTC()
    {
    	Calendar c = Calendar.getInstance();
    	c.add(java.util.Calendar.MILLISECOND, -(c.get(java.util.Calendar.ZONE_OFFSET) + c.get(java.util.Calendar.DST_OFFSET)));
    	return getDateTimeUTC(new Date(c.getTimeInMillis()));
    }
    
    public static void main(String[] args) {
//        try {
//            List<String> list = getBetweenDate("", "");
//            System.out.println(list.size());
//            System.out.println(list.size() / 7);
//            System.out.println(list.size() % 7);
//            String starttime=DateUtil.getDateAgo(7);
//            String endtime=DateUtil.getDateAgo(0);
//            System.out.println(starttime+""+endtime);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    	
    	System.out.println("UTC:"+DateUtil.getTimeByUTC());
    	System.out.println("UTC-String:"+DateUtil.getStringByUTC());
    	java.util.Calendar cal = java.util.Calendar.getInstance(java.util.Locale.CHINA);

    	System.out.println("locale:"+new Date(cal.getTimeInMillis()));
    	System.out.println(DateUtil.getDateFormat(new Date(cal.getTimeInMillis()), DateUtil.formatStr6));
    	cal = Calendar.getInstance();
    	int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);

    	int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
    	
    	cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));

    	System.out.println("UTC:"+new Date(cal.getTimeInMillis()));
    	System.out.println(DateUtil.getDateTimeUTC(new Date(cal.getTimeInMillis())));
    	String d = "2016-02-27T05:28:51.266Z";
    	try {
			System.out.println(DateUtil.strintToDatetime(d, DateUtil.formatUTC));
			System.out.println(DateUtil.getDateTimeUTC(DateUtil.strintToDatetime(d, DateUtil.formatUTC)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
}
