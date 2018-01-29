package com.gsyong.bll;

import android.content.Context;

import com.gsyong.config.WebService;
import com.gsyong.config.WebServiceEnum;
import com.gsyong.model.dt_chuku_cp_1;
import com.gsyong.model.dt_chuku_list_1;
import com.gsyong.model.dt_qiye;
import com.gsyong.model.dt_qiye_zhucema;
import com.gsyong.model.dt_ruku_cp_1;
import com.gsyong.model.dt_ruku_list_1;
import com.gsyong.model.dt_zhucema;

import org.json.JSONObject;
import org.ksoap2.serialization.SoapObject;

import java.util.HashMap;
import java.util.List;

import commonutils.AppUtils;
import commonutils.OneClass;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/27 19:57
 * 修改人：YI
 * 修改时间：2018/1/27 19:57
 * 修改备注：
 */
public class WebServiceSyncDataBll {
    WareHouseOutBll wareHouseOutBll = null;
    WareHouseSyncBll wareHouseSyncBll = null;
    Qiye_ZhucemaInfoBll qiye_zhucemaInfoBll = null;


    public WebServiceSyncDataBll(Context context) {
        wareHouseOutBll = new WareHouseOutBll(context);
        wareHouseSyncBll = new WareHouseSyncBll(context);
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
        boolean result = true;
        try {
            List<dt_ruku_list_1> listModels = wareHouseSyncBll.get_ruku_listInfo0(uid);
            if (listModels != null && listModels.size() > 0) {
                for (int i = 0; i < listModels.size(); i++) {
                    dt_ruku_list_1 nowModel = listModels.get(i);

                    HashMap<String, String> var = new HashMap<String, String>();
                    var.put("action", "upload_rukudan");
                    var.put("addtime", nowModel.getAddtime());
                    var.put("bianhao", nowModel.getBianhao());
                    var.put("qiyeid", String.valueOf(nowModel.getQiyeid()));
                    var.put("qystaff", nowModel.getQystaff());
                    var.put("shuliang", String.valueOf(nowModel.getShuliang()));
                    var.put("ylint2", String.valueOf(nowModel.getYlint2()));
                    var.put("uid", nowModel.getUid());
                    String str = PostRequest.sendPostRequest(var, null);
                    JSONObject jsonObj = new JSONObject(str);
                    if (jsonObj.getString("status").equals("y")) {
                        result = true;
                        //修改入库单状态
                        wareHouseSyncBll.update_ruku_listInfo0(nowModel.getUid());
                    } else {
                        result = false;
                    }
                    Thread.sleep(200);
                }
            }
        } catch (Exception e) {
            String mm = e.toString();
        }
        return result;
    }

    //设备上传入库单关联的流向码
    private boolean Syn_rukucp(String uid) {
        //上传后先修改状态
        boolean result = true;
        try {
            List<dt_ruku_cp_1> listModels = wareHouseSyncBll.get_ruku_cpInfo0(uid);
            if (listModels != null && listModels.size() > 0) {
                for (int i = 0; i < listModels.size(); i++) {

                    dt_ruku_cp_1 nowModel = listModels.get(i);

                    HashMap<String, String> var = new HashMap<String, String>();
                    var.put("action", "upload_rukucp");
                    var.put("cpuid", nowModel.getCpuid() == null ? "" : nowModel.getCpuid());
                    var.put("daima", nowModel.getDaima());
                    var.put("listUid", nowModel.getListuid());
                    var.put("uid", nowModel.getUid());
                    var.put("ylint1", String.valueOf(nowModel.getYlint1()));
                    var.put("ylint2", String.valueOf(nowModel.getYlint2()));
                    var.put("ylstr1", nowModel.getYlstr1());

                    String str = PostRequest.sendPostRequest(var, null);

                    JSONObject jsonObj = new JSONObject(str);
                    if (jsonObj.getString("status").equals("y")) {
                        result = true;
                        //修改入库单状态
                        wareHouseSyncBll.update_ruku_cpInfo0(nowModel.getUid());
                    } else {
                        result = false;
                    }
                    Thread.sleep(200);
                }
            }
        } catch (Exception e) {
            String mm = e.toString();
        }
        return result;
    }


    //设备上传出库单调用
    private boolean Syn_chukudan(String uid) {
        //上传后先修改状态
        boolean result = true;
        try {
            List<dt_chuku_list_1> listModels = wareHouseSyncBll.get_chuku_listInfo0(uid);
            if (listModels != null && listModels.size() > 0) {
                for (int i = 0; i < listModels.size(); i++) {
                    dt_chuku_list_1 nowModel = listModels.get(i);

                    HashMap<String, String> var = new HashMap<String, String>();
                    var.put("action", "upload_chukudan");
                    var.put("addtime", nowModel.getAddtime());
                    var.put("bianhao", nowModel.getBianhao());
                    var.put("qiyeid", String.valueOf(nowModel.getQiyeid()));
                    var.put("qystaff", nowModel.getQystaff());
                    var.put("shuliang", String.valueOf(nowModel.getShuliang()));
                    var.put("ylint2", String.valueOf(nowModel.getYlint2()));
                    var.put("uid", nowModel.getUid());

                    String str = PostRequest.sendPostRequest(var, null);

                    JSONObject jsonObj = new JSONObject(str);
                    if (jsonObj.getString("status").equals("y")) {
                        result = true;
                        //修改出库单状态
                        wareHouseSyncBll.update_chuku_listInfo0(nowModel.getUid());
                    } else {
                        result = false;
                    }
                    Thread.sleep(200);
                }
            }
        } catch (Exception e) {
            String mm = e.toString();
        }
        return result;
    }

    //设备上传出库单关联的流向码
    private boolean Syn_chukucp(String uid) {
        boolean result = true;
        try {
            List<dt_chuku_cp_1> listModels = wareHouseSyncBll.get_chuku_cpInfo0(uid);
            if (listModels != null && listModels.size() > 0) {
                for (int i = 0; i < listModels.size(); i++) {
                    dt_chuku_cp_1 nowModel = listModels.get(i);
                    HashMap<String, String> var = new HashMap<String, String>();
                    var.put("action", "upload_chukucp");
                    var.put("cpuid", nowModel.getCpuid() == null ? "" : nowModel.getCpuid());
                    var.put("daima", nowModel.getDaima());
                    var.put("listUid", nowModel.getListuid());
                    var.put("uid", nowModel.getUid());
                    var.put("ylint1", String.valueOf(nowModel.getYlint1() == null ? 0 : nowModel.getYlint1()));
                    var.put("ylint2", String.valueOf(nowModel.getYlint2() == null ? 0 : nowModel.getYlint2()));
                    // var.put("ylstr1", nowModel.getYlstr1());
                    String str = PostRequest.sendPostRequest(var, null);

                    JSONObject jsonObj = new JSONObject(str);
                    if (jsonObj.getString("status").equals("y")) {
                        result = true;
                        //修改入库单状态
                        wareHouseSyncBll.update_chuku_cpInfo0(nowModel.getUid());
                    } else {
                        result = false;
                    }
                    Thread.sleep(200);

                }
            }
        } catch (Exception e) {
            String mm = e.toString();
        }
        return result;
    }


}
