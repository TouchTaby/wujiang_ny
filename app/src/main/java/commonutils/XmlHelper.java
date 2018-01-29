package commonutils;

import android.util.Xml;

import com.gsyong.model.dt_chuku_cp_1;
import com.thoughtworks.xstream.XStream;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/19 13:10
 * 修改人：YI
 * 修改时间：2018/1/19 13:10
 * 修改备注：
 */
public class XmlHelper
{
    public static String getXmlString()
    {
        //把对象转化为 XML,并且设置别名

        XStream xstream2 = new XStream();
        dt_chuku_cp_1 d =new dt_chuku_cp_1();
        d.setUid("aaaa");
        xstream2.alias("Root", dt_chuku_cp_1.class);   //修改别名
        String string2 = xstream2.toXML(d );//把最外层 标签com.example.hasee.myxml 改成Root
        System.out.println(string2);
        return string2;

    }

    public static String createXml(List list, String tableName)
    {
        String xml = "";
        StringWriter xmlWriter = new StringWriter();
        XmlSerializer xmlSerializer = Xml.newSerializer();
        try
        {
            xmlSerializer.setOutput(xmlWriter);
            xmlSerializer.startDocument("UTF-16", null);
            xmlSerializer.startTag("", "NewDataSet");
            // DT
            if (null != list)
            {
                FanShe reflect = new FanShe();

                List<String> shuxingList = reflect.getPropertyNames(list.get(0).getClass());
                for (Object object : list)
                {
                    xmlSerializer.startTag("", object.getClass().getName());
                    List<String> vlaueList = reflect.getValueList(shuxingList, object);
                    for (int i = 0; i < shuxingList.size(); i++)
                    {
                        xmlSerializer.startTag("", shuxingList.get(i));
                        if (vlaueList.get(i) == null)
                        {
                            xmlSerializer.text("");
                        } else
                        {
                           // xmlSerializer.text(vlaueList.get(i));
                            xmlSerializer.text(String.valueOf(vlaueList.get(i)));
                        }
                        xmlSerializer.endTag("", shuxingList.get(i));
                    }
                    xmlSerializer.endTag("", object.getClass().getName());
                }
                xmlSerializer.endTag("", "NewDataSet");
                // 属性集合
                xmlSerializer.endDocument();
                xmlSerializer.flush();
                String aaa=xmlWriter.toString();
                xml = xmlWriter.toString().split("\\?>")[1];
            }
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            String mm=e.toString();
            e.printStackTrace();
        } finally
        {
            if (xmlWriter != null)
            {
                try
                {
                    xmlWriter.close();
                } catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return xml;
    }

}
