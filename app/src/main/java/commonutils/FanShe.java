package commonutils;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/18 18:25
 * 修改人：YI
 * 修改时间：2018/1/18 18:25
 * 修改备注：
 */
public class FanShe {

    /**
     * 将obj类的属性列表一个个赋值
     *
     * @param obj           要反射的类
     * @param propertyNames 类的属性列表
     * @param propertyVales 与属性相对应的属性值列表
     */
    @SuppressWarnings("rawtypes")
    public void method(Object obj, List<String> propertyNames, List propertyVales) {
        try {
            Class cla = obj.getClass();
            for (int i = 0; i < propertyNames.size(); i++) {
                Field f = cla.getDeclaredField(propertyNames.get(i).toString());
                f.setAccessible(true);// 加了这句才能改私有的值
                f.set(obj, propertyVales.get(i));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            String mm = e.toString();
            e.printStackTrace();
        }
    }


    /**
     * 对类解析并赋值
     *
     * @param documentElement
     * @param object
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public Object useFanSheToData(SoapObject documentElement, Object object, String entityname) {
        // 存放类的属性
        List<String> classFileList = new ArrayList<String>();
        // 存放类的属性值
        List<Object> classValueList = new ArrayList<Object>();
        // 得到类反射类的属性名称
        Object obj = null;
        // 得到数据的key和value
        for (int i = 0; i < documentElement.getPropertyCount(); i++) {

            PropertyInfo propertyInfo = new PropertyInfo();
            documentElement.getPropertyInfo(i, propertyInfo);
            String propertyName = propertyInfo.getName();
            // System.out.println(propertyName);
            if (documentElement.getProperty(propertyName).toString().startsWith("anyType")) {
                if (documentElement.getProperty(propertyName).toString().equals("anyType{}")) {
                    classFileList.add(propertyName);
                    classValueList.add("");
                } else {
                    try {
                        obj = useFanSheToData((SoapObject) documentElement.getProperty(propertyName), Class.forName(entityname
                                + propertyName).newInstance(), entityname);
                        classFileList.add(propertyName);
                        classValueList.add(obj);
                    } catch (Exception e) {
                        String mm = e.toString();
                    }
                }
            } else {
                classFileList.add(propertyName);
                classValueList.add(documentElement.getProperty(propertyName).toString());
            }

        }
        method(object, classFileList, classValueList);
        return object;
    }


    /**
     * 得到cla类的属性列表
     *
     * @param cla 要反射的类
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List<String> getPropertyNames(Class cla) {
        List<String> list = new ArrayList<String>();
        Field[] fs = cla.getDeclaredFields();
        // fs=cla.getFields();加了这个的话就只获得public 公有的
        for (Field f : fs) {
            list.add(f.getName());
        }
        return list;
    }


    /**
     * 得到与属性列表相对应的属性值列表
     *
     * @param shuxingList 属性列表
     * @param obj         实体类
     * @return 与属性对应的属性值列表
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public List getValueList(List<String> shuxingList, Object obj) {
        List valueList = null;
        try {

            Class cla = obj.getClass();
            valueList = new ArrayList();
            for (int i = 0; i < shuxingList.size(); i++) {
                Field f = cla.getDeclaredField(shuxingList.get(i).toString());
                f.setAccessible(true);// 加了这句才能改私有的值
                // 得到属性值
                Object str = f.get(obj);
                valueList.add(str);
            }
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return valueList;
    }
}
