package com.gsyong.bll;

import android.content.Context;

import com.gsyong.config.WebService;
import com.gsyong.config.WebServiceEnum;
import com.gsyong.model.dt_qiye;
import com.gsyong.model.dt_qiye_zhucema;
import com.gsyong.model.dt_zhucema;
import com.gsyong.models.var_Syn_chukucp;
import com.gsyong.models.var_Syn_chukudan;
import com.gsyong.models.var_Syn_rukucp;
import com.gsyong.models.var_Syn_rukudan;
import com.thoughtworks.xstream.XStream;

import org.ksoap2.serialization.SoapObject;

import java.util.List;

import commonutils.AppUtils;
import commonutils.OneClass;

/**
 * 类描述：和服务器数据交互
 * 创建人：YI
 * 创建时间：2018/1/20 18:51
 * 修改人：YI
 * 修改时间：2018/1/20 18:51
 * 修改备注：
 */
public class WebServiceDataBll {
    WareHouseOutBll wareHouseOutBll = null;
    WareHouseInBll wareHouseInBll = null;
    Qiye_ZhucemaInfoBll qiye_zhucemaInfoBll = null;

    XStream xStream = new XStream();

    public WebServiceDataBll(Context context) {
        wareHouseOutBll = new WareHouseOutBll(context);
        wareHouseInBll = new WareHouseInBll(context);
        qiye_zhucemaInfoBll = new Qiye_ZhucemaInfoBll(context);
    }

    /**
     * 根据注册码绑定设备
     *
     * @param zhucema
     * @return
     */
    public boolean getzhucemabangding(String zhucema) {
        boolean result = true;
        try {
            // zhucema = "4271217244";
            SoapObject nowObject = WebService.sendAndRequestInfo(WebServiceEnum.zhucemabangding.name(), zhucema, AppUtils
                    .getMacAddress());
            OneClass oneClass = new OneClass();
            dt_qiye_zhucema dt_qiye_zhucemaModel = (dt_qiye_zhucema) oneClass.doing(nowObject, dt_qiye_zhucema.class, "com" +
                    "" + ".gsyong.model.dt_");
            dt_zhucema dt_zhucemaModel = (dt_zhucema) dt_qiye_zhucemaModel.getZhucema();
            if (dt_zhucemaModel != null) {
                dt_qiye dt_qiyeModel = (dt_qiye) dt_qiye_zhucemaModel.getQiye();
                qiye_zhucemaInfoBll.addQiYeInfo(dt_qiyeModel);
                qiye_zhucemaInfoBll.addZhuCeMaInfo(dt_zhucemaModel);
            } else {
                result = false;
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * 得到注册信息
     *
     * @return
     */
    public boolean Get_zhuce_file() {
        boolean result = true;
        try {
            SoapObject nowObject = WebService.sendAndRequestInfo(WebServiceEnum.Get_zhuce_file.name(), AppUtils.getMacAddress());
            OneClass oneClass = new OneClass();
            dt_qiye_zhucema dt_qiye_zhucemaModel = (dt_qiye_zhucema) oneClass.doing(nowObject, dt_qiye_zhucema.class, "com" +
                    "" + ".gsyong.model.dt_");
            dt_zhucema dt_zhucemaModel = (dt_zhucema) dt_qiye_zhucemaModel.getZhucema();
            if (dt_zhucemaModel != null) {
                dt_qiye dt_qiyeModel = (dt_qiye) dt_qiye_zhucemaModel.getQiye();
                qiye_zhucemaInfoBll.addQiYeInfo(dt_qiyeModel);
                qiye_zhucemaInfoBll.addZhuCeMaInfo(dt_zhucemaModel);
            } else {
                result = false;
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    //设备上传入库数据
    public boolean Syn_rukudanAll() {
        boolean result = true;
        try {
            result = Syn_rukudan(null);
            if (result) {
                result = Syn_rukucp(null);
            }
        } catch (Exception e) {
        }
        return result;
    }

    //设备上传出库数据
    public boolean Syn_chukudanAll() {
        boolean result = true;
        try {
            result = Syn_chukudan(null);
            if (result) {
                result = Syn_chukucp(null);
            }
        } catch (Exception e) {
        }
        return result;
    }

    //设备上传入库数据
    public boolean Syn_rukudanByID(String uid) {
        boolean result = true;
        try {
            result = Syn_rukudan(uid);
            if (result) {
                result = Syn_rukucp(uid);
            }
        } catch (Exception e) {
        }
        return result;
    }

    //设备上传出库数据
    public boolean Syn_chukudanByID(String uid) {
        boolean result = true;
        try {
            result = Syn_chukudan(uid);
            if (result) {
                result = Syn_chukucp(uid);
            }
        } catch (Exception e) {
        }
        return result;
    }

    //设备上传入库单调用
    private boolean Syn_rukudan(String uid) {
        //上传后先修改状态
        boolean result = false;
        try {
            List<var_Syn_rukudan> listModels = wareHouseInBll.get_ruku_listInfo0(uid);
            if (listModels != null && listModels.size() > 0) {
                for (int i = 0; i < listModels.size(); i++) {
                    var_Syn_rukudan var_syn_rukudanModel = listModels.get(i);
                    xStream.alias("vars", var_Syn_rukudan.class);   //修改别名
                    String nowvalue = xStream.toXML(var_syn_rukudanModel);//把最外层
                    SoapObject nowObject = WebService.sendAndRequestInfo(WebServiceEnum.Syn_rukudan.name(), nowvalue);
                    String aa = nowObject.toString();
                    if (nowObject.toString().equals("true")) {
                        result = true;
                        //修改入库单状态
                        wareHouseInBll.update_ruku_listInfo0(var_syn_rukudanModel.getModel().get_uid());
                    }
                    Thread.sleep(200);
                }
            }
        } catch (Exception e) {

        }
        return result;
    }

    //设备上传入库单关联的流向码
    private boolean Syn_rukucp(String uid) {
        //上传后先修改状态
        boolean result = false;
        try {
            List<var_Syn_rukucp> listModels = wareHouseInBll.get_ruku_cpInfo0(uid);
            if (listModels != null && listModels.size() > 0) {
                for (int i = 0; i < listModels.size(); i++) {
                    var_Syn_rukucp var_Syn_rukucpModel = listModels.get(i);
                    xStream.alias("Root", var_Syn_rukudan.class);   //修改别名
                    String nowvalue = xStream.toXML(var_Syn_rukucpModel);//把最外层


                    SoapObject nowObject = WebService.sendAndRequestInfo(WebServiceEnum.Syn_rukucp.name(), nowvalue);
                    //SoapObject nowObject = WebService.sendrukucp(WebServiceEnum.Syn_rukucp.name(), var_Syn_rukucpModel);
                    if (nowObject.toString().equals("true")) {
                        result = true;
                        //修改入库单状态
                        wareHouseInBll.update_ruku_cpInfo0(var_Syn_rukucpModel.getModel().get_uid());
                    }
                    Thread.sleep(200);
                }
            }
        } catch (Exception e) {
        }
        return result;
    }


    //设备上传出库单调用
    private boolean Syn_chukudan(String uid) {
        //上传后先修改状态
        boolean result = false;
        try {
            List<var_Syn_chukudan> listModels = wareHouseOutBll.get_chuku_listInfo0(uid);
            if (listModels != null && listModels.size() > 0) {
                for (int i = 0; i < listModels.size(); i++) {
                    var_Syn_chukudan var_Syn_chukudanModel = listModels.get(i);
                    xStream.alias("Root", var_Syn_chukudan.class);   //修改别名
                    String nowvalue = xStream.toXML(var_Syn_chukudanModel);//把最外层


                    SoapObject nowObject = WebService.sendAndRequestInfo(WebServiceEnum.Syn_chukudan.name(), nowvalue);

                    // SoapObject nowObject = WebService.sendchukudan(WebServiceEnum.Syn_chukudan.name(), var_Syn_chukudanModel);
                    if (nowObject.toString().equals("true")) {
                        result = true;
                        //修改入库单状态
                        wareHouseOutBll.update_chuku_listInfo0(var_Syn_chukudanModel.getModel().get_uid());
                    }
                    Thread.sleep(200);
                }
            }
        } catch (Exception e) {

        }
        return result;
    }

    //设备上传出库单关联的流向码
    private boolean Syn_chukucp(String uid) {
        boolean result = false;
        try {
            List<var_Syn_chukucp> listModels = wareHouseOutBll.get_chuku_cpInfo0(uid);
            if (listModels != null && listModels.size() > 0) {
                for (int i = 0; i < listModels.size(); i++) {
                    var_Syn_chukucp var_Syn_chukucpModel = listModels.get(i);
                    xStream.alias("Root", var_Syn_chukucp.class);   //修改别名
                    String nowvalue = xStream.toXML(var_Syn_chukucpModel);//把最外层

                    SoapObject nowObject = WebService.sendAndRequestInfo(WebServiceEnum.Syn_chukucp.name(), nowvalue);

                    //SoapObject nowObject = WebService.sendchukucp(WebServiceEnum.Syn_chukucp.name(), var_Syn_chukucpModel);
                    if (nowObject.toString().equals("true")) {
                        result = true;
                        //修改入库单状态
                        wareHouseOutBll.update_chuku_cpInfo0(var_Syn_chukucpModel.getModel().get_uid());
                    }
                    Thread.sleep(200);
                }
            }
        } catch (Exception e) {
        }
        return result;
    }


}
