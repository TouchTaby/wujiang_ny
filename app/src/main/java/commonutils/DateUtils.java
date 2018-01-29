package commonutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2017/10/28 10:51
 * 修改人：YI
 * 修改时间：2017/10/28 10:51
 * 修改备注：
 */
public class DateUtils
{
    private static SimpleDateFormat sdf;
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private static SimpleDateFormat sdf3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss 格林尼治标准时间+0800 yyyy", Locale.ENGLISH);
    private static SimpleDateFormat sdf4 = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
    private static SimpleDateFormat sdf5 = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH);


    /**
     * 获取系统时间 格式为："yyyy/MM/dd "
     **/
    public static String getCurrentDate()
    {
        Date d = new Date();
        sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(d);
    }

    /**
     * 获取系统时间 格式为："yyyy "
     **/
    public static String getCurrentYear()
    {
        Date d = new Date();
        sdf = new SimpleDateFormat("yyyy");
        return sdf.format(d);
    }

    /**
     * 获取系统时间 格式为："MM"
     **/
    public static String getCurrentMonth()
    {
        Date d = new Date();
        sdf = new SimpleDateFormat("MM");
        return sdf.format(d);
    }

    /**
     * 获取系统时间 格式为："dd"
     **/
    public static String getCurrentDay()
    {
        Date d = new Date();
        sdf = new SimpleDateFormat("dd");
        return sdf.format(d);
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static long getCurrentTime()
    {
        long d = new Date().getTime() / 1000;
        return d;
    }

    /**
     * 时间戳转换成字符窜
     */
    public static String getDateToString(long time)
    {
        Date d = new Date(time * 1000);
        sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(d);
    }

    /**
     * 时间戳中获取年
     */
    public static String getYearFromTime(long time)
    {
        Date d = new Date(time * 1000);
        sdf = new SimpleDateFormat("yyyy");
        return sdf.format(d);
    }

    /**
     * 时间戳中获取月
     */
    public static String getMonthFromTime(long time)
    {
        Date d = new Date(time * 1000);
        sdf = new SimpleDateFormat("MM");
        return sdf.format(d);
    }

    /**
     * 时间戳中获取日
     */
    public static String getDayFromTime(long time)
    {
        Date d = new Date(time * 1000);
        sdf = new SimpleDateFormat("dd");
        return sdf.format(d);
    }

    /**
     * 将字符串转为时间戳
     */
    public static long getStringToDate(String time)
    {
        sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try
        {
            date = sdf.parse(time);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date.getTime();
    }


    /**
     * 根据long时间得到时间字符串
     */
    public static String getStringDateFromLong(long time)
    {
        Date d = new Date(time);
        return sdf1.format(d);
    }

    /**
     * 根据时间字符串得到指定格式时间字符串
     */
    public static String getStringDate(String time)
    {
        if (time != null)
        {
            Date date = getDateFromString(time);
            return sdf1.format(date);
/*            return sdf1.format(getDateFromString(time));*/
        }
        return null;

    }

    /**
     * 根据时间字符串获取标准时间
     */
    public static Date getDateFromString(String time)
    {
        if (time != null)
        {
            try
            {
                return sdf3.parse(time);
            } catch (Exception e)
            {
                try
                {
                    return sdf4.parse(time);
                } catch (Exception e2)
                {
                    // TODO: handle exception
                }
            }
        }
        return null;
    }


    /**
     * 获取当前时间字符串
     */
    public static String getCurrentDateEN()
    {
        return sdf1.format(new Date());
    }

    /**
     * 获取当前时间编号字符串
     */
    public static String getCurrentDateNumber()
    {
        return sdf5.format(new Date());
    }

    /**
     * 获取当前时间字符串
     */
    public static String getCurrentDateENShort()
    {
        return sdf2.format(new Date());
    }

    /**
     * 根据时间获取字符串年月日
     */
    public static String getStringFromDate(Date time)
    {
        return sdf1.format(time);
    }

    /**
     * 根据时间获取字符串年月日时分秒
     */
    public static String getStringFromDateShort(Date time)
    {
        return sdf2.format(time);
    }

    /**
     * 获得当前日期的后一天
     *
     * @return
     */
    public static String getCurrentDayAfter()
    {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);
        return sdf2.format(c.getTime());
    }

    /**
     * 获得指定日期的后一天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay)
    {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try
        {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);
        return sdf2.format(c.getTime());
    }

}
