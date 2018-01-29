package commonutils;

import java.text.DecimalFormat;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2017/11/15 17:01
 * 修改人：YI
 * 修改时间：2017/11/15 17:01
 * 修改备注：
 */
public class DataUtils
{
    /**
     * 根据字符串得到Integer
     *
     * @param pramString
     * @return
     */
    public static Integer getInteger(String pramString)
    {
        if (pramString != null)
        {
            return Integer.valueOf(pramString);
        }
        return null;
    }

    /**
     * 根据字符串得到Float
     *
     * @param pramString
     * @return
     */
    public static Float getFloat(String pramString)
    {
        if (pramString != null)
        {
            return Float.valueOf(pramString);
        }
        return null;
    }

    /**
     * 根据字符串得到Double
     *
     * @param pramString
     * @return
     */
    public static Double getDouble(String pramString)
    {
        if (pramString != null)
        {
            return Double.valueOf(pramString);
        }
        return null;
    }

    /**
     * 根据字符串得到Boolean
     *
     * @param pramString
     * @return
     */
    public static Boolean getBoolean(String pramString)
    {
        if (pramString != null)
        {
            if (pramString.equals("1"))
            {
                return true;
            } else
            {
                return false;
            }
        }
        return null;
    }

    public static String getString(Double pramString)
    {
        if (pramString != null)
        {
            DecimalFormat df = new DecimalFormat("#0.00000");
            String result = df.format(pramString);
            for (int i = result.length() - 1; i >= result.length() - 5; i--)
            {
                if (result.charAt(i) != '0' || i == result.length() - 5)
                {
                    result = result.substring(0, i + 1);
                    break;
                }
            }
            return result;
        }
        return null;
    }

    public static String getString(Float pramString)
    {
        if (pramString != null)
        {
            DecimalFormat df = new DecimalFormat("#0.00000");
            String result = df.format(pramString);
            for (int i = result.length() - 1; i >= result.length() - 5; i--)
            {
                if (result.charAt(i) != '0' || i == result.length() - 5)
                {
                    result = result.substring(0, i + 1);
                    break;
                }
            }
            return result;
        }
        return null;
    }
}
