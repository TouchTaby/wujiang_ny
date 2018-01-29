package commonutils;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/19 10:04
 * 修改人：YI
 * 修改时间：2018/1/19 10:04
 * 修改备注：
 */
public class MultiClass implements ParseData
{
    @Override
    public Object doing(SoapObject result, Class cla, String entityname)
    {
        // TODO Auto-generated method stub
        try
        {
            FanShe fs = new FanShe();
            List<Object> list = new ArrayList<>();
            for (int i = 0; i < result.getPropertyCount(); i++)
            {
                SoapObject result1 = (SoapObject) result.getProperty(i);
                Object obj = fs.useFanSheToData(result1, cla.newInstance(), entityname);
                list.add(obj);
            }
            return list;
        } catch (Exception e)
        {
            return null;
        }
    }
}