package com.living.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private static final long TIME_SECOND = 1 * 1000L;
    private static final long TIME_MINUTE = 60 * TIME_SECOND;
    private static final long TIME_HOUR = 60 * TIME_MINUTE;
    private static final long TIME_DAY = 24 * TIME_HOUR;

    private static final SimpleDateFormat HmFormat = new SimpleDateFormat("H:m");
    private static final SimpleDateFormat weekFormat = new SimpleDateFormat("E");
    private static final SimpleDateFormat MdHmFormat = new SimpleDateFormat("M-d H:m");

    public static boolean isBlank(String src) {
        return null == src || "".equals(src.trim());
    }

    public static long str2Long(String src) {
        return src.trim().matches("^\\d+$") ? new Long(src.trim()) : 0L;
    }

    public static boolean isSameWeekDates(Calendar cal1, Calendar cal2) {
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

    /**
     * 如果是当分,则N秒前 如果是当前小时,则 N分钟前 如果是当日,则 H:m 如果是昨天,则 昨天 H:m 如果是一周内,则 星期 H:m
     * 如果超过一周,则 M-d H:m
     *
     * @param time
     * @return
     */
    public static String getFormatDate(Long time) {
        if (null == time) {
            return "刚刚";
        }
        long currentTime = System.currentTimeMillis();
        if (currentTime - time < TIME_MINUTE) {
            long dataTime = (currentTime - time) / TIME_SECOND + 1;
            if (dataTime < 0) {
                return "刚刚";
            } else {
                return dataTime + "秒前";
            }
        }
        if (currentTime - time < TIME_HOUR) {
            return (currentTime - time) / TIME_MINUTE + "分钟前";
        }
        if (time > ((currentTime / TIME_DAY) * TIME_DAY)) {
            return HmFormat.format(new Date(time));
        }
        if (time > ((currentTime / TIME_DAY - 1) * TIME_DAY)) {
            return "昨天 " + HmFormat.format(new Date(time));
        }
        Calendar pre = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        pre.setTimeInMillis(time);
        now.setTimeInMillis(currentTime);
        if (isSameWeekDates(pre, now)) {
            return weekFormat.format(pre.getTime());
        }
        return MdHmFormat.format(pre.getTime());
    }

    /**
     * 将yyyyMMddhhmmss格式的时间转换成MM/dd/yyyy HH:mm:ss的格式
     * 可以根据想要的格式做不同的修改
     *
     * @param date
     * @return
     */
    public static String changeDateStr(String date) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddhhmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        try {
            return sdf2.format(sdf1.parse(date));
        } catch (ParseException pe) {
            pe.printStackTrace();
            return "00/00/0000 00:00:00";
        }
    }

    /**
     * Long 转成Date字符串
     *
     * @param time
     * @param formatType "MM/dd/yyyy HH:mm:ss"
     * @return
     */
    public static String long2DateStr(long time, String formatType) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatType);
        //前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
        Date dt = new Date(time * 1000);
        return sdf.format(dt);  //得到精确到秒的表示：08/31/2006 21:08:00
    }

    /**
     * 去掉字符串中空格
     *
     * @param str
     * @return
     */
    public static String ignoreStringBlank(String str) {
        return str.replaceAll("[ 　]", "");
    }

    /**
     * 判断手机格式是否正确
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNumber(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(16[0-9])|(14[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 密码格式是否正确
     *
     * @param pass
     * @return
     */
    public static boolean isPassWord(String pass) {
        // 允许空字符串就最后用*.不允许就+
        Pattern ptn = Pattern.compile("^[A-Za-z0-9]{6,18}+$");
        Matcher mth = ptn.matcher(pass);
        return mth.find();
    }

    /**
     * 日期格式化
     *
     * @param day 2016-06-28 格式的日期
     * @return MM/dd 格式的日期
     */
    public static String getShortDay(String day) {
        String a = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(day);
            SimpleDateFormat format = new SimpleDateFormat("MM/dd");
            a = format.format(date);
        } catch (ParseException e) {
            LogUtil.e(e.getMessage());
        }
        return a;
    }

    /**
     * @param createTime
     * @return
     */
    public static String getWeek(String createTime) {
        String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        try {
            String ret;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(createTime);
            long create = date.getTime();
            Calendar now = Calendar.getInstance();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            long ms_now = now.getTimeInMillis();
            if (create - ms_now <= 0) {
                ret = "今天";
            } else {
                int w = c.get(Calendar.DAY_OF_WEEK) - 1;
                if (w < 0) {
                    w = 0;
                }
                ret = weekOfDays[w];
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
