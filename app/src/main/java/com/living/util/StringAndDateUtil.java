package com.living.util;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAndDateUtil {

	private static final long TIME_SECOND = 1 * 1000L;
	private static final long TIME_MINUTE = 60 * TIME_SECOND;
	private static final long TIME_HOUR = 60 * TIME_MINUTE;
	private static final long TIME_DAY = 24 * TIME_HOUR;
	private static final long TIME_WEEK = 7 * TIME_DAY;
	private static final long TIME_MONTH = 4 * TIME_WEEK;

	private static final SimpleDateFormat HmFormat = new SimpleDateFormat("H:m");
	private static final SimpleDateFormat weekFormat = new SimpleDateFormat("E");
	private static final SimpleDateFormat MdHmFormat = new SimpleDateFormat("M-d H:m");

	public static boolean isBlank(String src) {
		return null == src || "".equals(src.trim());
	}

	public static long str2Long(String src) {
		return src.trim().matches("^\\d+$") ? new Long(src.trim()) : 0L;
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

	public static Integer getMonth(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		return cal.get(Calendar.MONTH) + 1;
	}

	public static Integer getDay(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		return cal.get(Calendar.DATE);
	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		return formatter.parse(strDate, pos);
	}

	public static String longToDate(long currentTime, String formatType) throws ParseException {
		Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
		return new SimpleDateFormat(formatType).format(dateOld);
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

	public static Date simpleDateParse(String dateString, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String ignoreStringBlank(String str) {
		return str.replaceAll("[ 　]", "");
	}

	public static int ignoreBlankLength(String str) {
		if (isBlank(str)) {
			return 0;
		}
		return str.replaceAll("[ 　]", "").length();
	}

	// 判断手机格式是否正确
	public static boolean isMobileNumber(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(16[0-9])|(14[0-9])|(17[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	public static boolean isPassWord(String pass) {
		// 允许空字符串就最后用*.不允许就+
		Pattern ptn = Pattern.compile("^[A-Za-z0-9]{6,18}+$");
		Matcher mth = ptn.matcher(pass);
		return mth.find();
	}

	/*********************** 字符串转码 开始 *************************/
	/**
	 * 转换字符串的编码
	 */
	/** 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块 */
	public static final String US_ASCII = "US-ASCII";

	/** ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1 */
	public static final String ISO_8859_1 = "ISO-8859-1";

	/** 8 位 UCS 转换格式 */
	public static final String UTF_8 = "UTF-8";

	/** 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序 */
	public static final String UTF_16BE = "UTF-16BE";

	/** 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序 */
	public static final String UTF_16LE = "UTF-16LE";

	/** 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识 */
	public static final String UTF_16 = "UTF-16";

	/** 中文超大字符集 */
	public static final String GBK = "GBK";

	/**
	 * 将字符编码转换成US-ASCII码
	 */
	public static String toASCII(String str) throws UnsupportedEncodingException {
		return changeCharset(str, US_ASCII);
	}

	/**
	 * 将字符编码转换成ISO-8859-1码
	 */
	public static String toISO_8859_1(String str) throws UnsupportedEncodingException {
		return changeCharset(str, ISO_8859_1);
	}

	/**
	 * 将字符编码转换成UTF-8码
	 */
	public static String toUTF_8(String str) throws UnsupportedEncodingException {
		return changeCharset(str, UTF_8);
	}

	/**
	 * 将字符编码转换成UTF-16BE码
	 */
	public static String toUTF_16BE(String str)
			throws UnsupportedEncodingException {
		return changeCharset(str, UTF_16BE);
	}

	/**
	 * 将字符编码转换成UTF-16LE码
	 */
	public static String toUTF_16LE(String str) throws UnsupportedEncodingException {
		return changeCharset(str, UTF_16LE);
	}

	/**
	 * 将字符编码转换成UTF-16码
	 */
	public static String toUTF_16(String str) throws UnsupportedEncodingException {
		return changeCharset(str, UTF_16);
	}

	/**
	 * 将字符编码转换成GBK码
	 */
	public static String toGBK(String str) throws UnsupportedEncodingException {
		return changeCharset(str, GBK);
	}

	/**
	 * 字符串编码转换的实现方法
	 * 
	 * @param str
	 *            待转换编码的字符串
	 * @param newCharset
	 *            目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String str, String newCharset) throws UnsupportedEncodingException {
		if (str != null) {
			// 用默认字符编码解码字符串。
			byte[] bs = str.getBytes();
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	/**
	 * 字符串编码转换的实现方法
	 * 
	 * @param str
	 *            待转换编码的字符串
	 * @param oldCharset
	 *            原编码
	 * @param newCharset
	 *            目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String str, String oldCharset,
			String newCharset) throws UnsupportedEncodingException {
		if (str != null) {
			// 用旧的字符编码解码字符串。解码可能会出现异常。
			byte[] bs = str.getBytes(oldCharset);
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}
	/*********************** 字符串转码 结束 *************************/

}
