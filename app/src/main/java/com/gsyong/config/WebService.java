package com.gsyong.config;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/19 15:06
 * 修改人：YI
 * 修改时间：2018/1/19 15:06
 * 修改备注：
 */
public class WebService
{
    // webservice使用
    public static String webServiceNAMESPACE = "http://tempuri.org/";
    // 服务器webservice地址
    public static String webServiceURL = "http://ny.gsyong.com/WebService.asmx";

    public static SoapObject sendAndRequestInfo(String methodName, String parm1)
    {
        SoapObject rpc = new SoapObject(webServiceNAMESPACE, methodName);
        if (methodName.equals(WebServiceEnum.Get_zhuce_file.name()))
        {
            rpc.addProperty("mac", parm1);
        }else
        {
            rpc.addProperty("vars", parm1);
        }
        return sendAndRequestInfo(methodName, rpc);
    }


    public static SoapObject sendAndRequestInfo(String methodName, String parm1, String parm2)
    {
        SoapObject rpc = new SoapObject(webServiceNAMESPACE, methodName);
        if (methodName.equals(WebServiceEnum.zhucemabangding.name()))
        {
            rpc.addProperty("zhucema", parm1);
            rpc.addProperty("pcmac", parm2);
        }
        return sendAndRequestInfo(methodName, rpc);
    }


    public static SoapObject sendAndRequestInfo(String methodName, SoapObject rpc)
    {
        SoapObject detail = null;
        try
        {
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = rpc;
            envelope.dotNet = true;
            envelope.setOutputSoapObject(rpc);
            HttpTransportSE ht = new HttpTransportSE(webServiceURL, 10000);
            ht.debug = true;
            ht.call(webServiceNAMESPACE + methodName, envelope);
            detail = (SoapObject) envelope.getResponse();

            String kk = detail.toString();
        } catch (Exception e)
        {
            Log.i("Tag", e.toString());
        }
        return detail;
    }

}
