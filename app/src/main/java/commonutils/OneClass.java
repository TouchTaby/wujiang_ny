package commonutils;

import org.ksoap2.serialization.SoapObject;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/18 18:23
 * 修改人：YI
 * 修改时间：2018/1/18 18:23
 * 修改备注：
 */
public class OneClass implements ParseData
{
    @Override
    public Object doing(SoapObject result, Class cla, String entityname)
    {
        // TODO Auto-generated method stub
        try
        {
            FanShe fs = new FanShe();
            Object obj = fs.useFanSheToData(result, cla.newInstance(), entityname);
            return obj;
        } catch (Exception e)
        {
            String mm=e.toString();
            return null;
        }
    }
}
