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
public interface ParseData
{
    /**
     * @param result     Soap数据对象
     * @param cla        Class类
     * @param entityname 包名
     * @return
     */
    public abstract Object doing(SoapObject result, @SuppressWarnings("rawtypes") Class cla, String entityname);
}
