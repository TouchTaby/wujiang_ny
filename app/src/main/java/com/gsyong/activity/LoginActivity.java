package com.gsyong.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.gsyong.R;
import com.gsyong.bll.Qiye_ZhucemaInfoBll;
import com.gsyong.bll.WebServiceDataBll;
import com.gsyong.config.Sysconfig;
import com.gsyong.model.dt_qiye;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import commonutils.AppUtils;
import commonutils.SPUtils;
import commonutils.WeiboDialogUtils;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/19 17:12
 * 修改人：YI
 * 修改时间：2018/1/19 17:12
 * 修改备注：
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtName;
    private Button btnSelect;
    private EditText edtPwd;
    private CheckBox chkRememberName;
    private CheckBox chkRememberPwd;
    private Button btnLogin;

    private Context context;
    private Dialog mDialog;
    private Qiye_ZhucemaInfoBll qiye_zhucemaInfoBll;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            WeiboDialogUtils.closeDialog(mDialog);
            setWeightState(true);
            switch (msg.what) {
                case 1:
                    dt_qiye dt_qiyeModel = qiye_zhucemaInfoBll.getQiYeInfo(AppUtils.getMacAddress());
                    if (dt_qiyeModel != null) {
                        edtName.setText(dt_qiyeModel.getMingzi());
                    }
                    break;
                case 2://无信息，开启注册界面
                    Intent intent = new Intent(context, ZhuCeMaActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_login);
        context = LoginActivity.this;
        qiye_zhucemaInfoBll = new Qiye_ZhucemaInfoBll(this);
        initView();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        testData();
    }


    private void initView() {
        edtName = findViewById(R.id.edtName);
        btnSelect = findViewById(R.id.btnSelect);
        edtPwd = findViewById(R.id.edtPwd);
        chkRememberName = findViewById(R.id.chkRememberName);
        chkRememberPwd = findViewById(R.id.chkRememberPwd);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        dt_qiye dt_qiyeModel = qiye_zhucemaInfoBll.getQiYeInfo(AppUtils.getMacAddress());
        if (dt_qiyeModel != null) {
            Sysconfig.qiyeId = Integer.parseInt(dt_qiyeModel.getId());
            edtName.setText(dt_qiyeModel.getMingzi());
            edtPwd.setText(dt_qiyeModel.getMima());

        } else {
            mDialog = WeiboDialogUtils.createLoadingDialog(context, "正在获取注册信息...");
            new Thread(networkTask).start();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                if (chkRememberName.isChecked()) {
                    SPUtils.put(context, "mingzi", edtName.getText().toString());
                }
                if (chkRememberPwd.isChecked()) {
                    SPUtils.put(context, "mima", edtPwd.getText().toString());
                }
                Sysconfig.qiyemingzi = edtName.getText().toString().trim();
                Sysconfig.qiyemima = edtPwd.getText().toString().trim();
                Sysconfig.qiyemimaMd5 = getMD5(edtPwd.getText().toString().trim());

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void setWeightState(boolean state) {
        btnLogin.setEnabled(state);
    }


    /**
     * 网络操作相关的子线程
     */
    Runnable networkTask = new Runnable() {
        @Override
        public void run() {
            // TODO
            Message msg = mHandler.obtainMessage();
            try {
                WebServiceDataBll webServiceDataBll = new WebServiceDataBll(context);
                boolean result = webServiceDataBll.Get_zhuce_file();
                if (result) {
                    msg.what = 1;
                } else {
                    msg.what = 2;
                }
            } catch (Exception ex) {
                msg.what = 2;
            }
            mHandler.sendMessage(msg);
        }
    };


    public static String getMD5(String val) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(val.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    private void testData() {
        //  SoapObject nowObject = WebService.sendAndRequestInfo(WebServiceEnum.Get_zhuce_file.name(), "4271217244",
        // getMacAddress());

//        SoapObject nowObject = WebService.sendAndRequestInfo(WebServiceEnum.Get_zhuce_file.name(), AppUtils.getMacAddress());
//
//        OneClass oneClass = new OneClass();
//        dt_qiye_zhucema mm = (dt_qiye_zhucema) oneClass.doing(nowObject, dt_qiye_zhucema.class, "com.gsyong.model.dt_");
//
//        dt_qiye dt_qiyeModel = (dt_qiye) mm.getQiye();
//        dt_zhucema dt_zhucemaModel = (dt_zhucema) mm.getZhucema();
//
//        Qiye_ZhucemaInfoBll qiye_zhucemaInfoBll=new Qiye_ZhucemaInfoBll(this);
//        qiye_zhucemaInfoBll.addQiYeInfo(dt_qiyeModel);
//        qiye_zhucemaInfoBll.addZhuCeMaInfo(dt_zhucemaModel);

//        Qiye_ZhucemaInfoBll qiye_zhucemaInfoBll=new Qiye_ZhucemaInfoBll(this);
//        dt_zhucema  aa= qiye_zhucemaInfoBll.getZhuCeMaInfo(getMacAddress());
//        dt_qiye  bbb= qiye_zhucemaInfoBll.getQiYeInfo(getMacAddress());

//        PesticideInfoBll pesticideInfoBll=new PesticideInfoBll(this);
//        dt_PesticideInfo dd=new dt_PesticideInfo("1","2222","","","","","");
//        pesticideInfoBll.addPesticideInfo(  dd);
//        dt_PesticideInfo ddd111d=  pesticideInfoBll.getPesticideInfo("2222");

//        WebServiceDataBll webServiceDataBll=new WebServiceDataBll();
//        webServiceDataBll.Syn_chukudanAll();


//        dt_chuku_cp_all aa=new dt_chuku_cp_all("aa","11","aaaaa",null,null,null,null,null,null,null,null,null,null);
//        dt_chuku_list_all bb=new dt_chuku_list_all("11",null,null,null,null,null,null,null,null,null,null,null,null,null,
// null,null);
//        List<dt_chuku_cp_all>  aalist=new ArrayList<>();
//        aalist.add(aa);
//        List<dt_chuku_list_all>  bblist=new ArrayList<>();
//        bblist.add(bb);
//
//        WareHouseOutBll wareHouseOutBll=new WareHouseOutBll(this);
//        wareHouseOutBll.add_chuku_Info(aalist,bb);
//        wareHouseOutBll.update_chuku_cpInfo(aalist);
//        wareHouseOutBll.update_chuku_listInfo(bblist);
//        wareHouseOutBll.get_chuku_cpInfo("11");
//        wareHouseOutBll.get_chuku_cpInfo0();
//        wareHouseOutBll.get_chuku_listInfo0();
//
//        dt_ruku_cp_all cc=new dt_ruku_cp_all("cc","22","cccc",null,null,null,null,null,null,null,null,null,null,null);
//        dt_ruku_list_all dd=new dt_ruku_list_all("22",null,null,null,null,null,null,null,null,null,null,null,null,null,null);
//        List<dt_ruku_cp_all>  cclist=new ArrayList<>();
//        cclist.add(cc);
//        List<dt_ruku_list_all>  ddlist=new ArrayList<>();
//        ddlist.add(dd);
//
//        WareHouseInBll wareHouseInBll=new WareHouseInBll(this);
//        wareHouseInBll.add_ruku_Info(cclist,dd);
//        wareHouseInBll.update_ruku_cpInfo(cclist);
//        wareHouseInBll.update_ruku_listInfo(ddlist);
//        wareHouseInBll.get_ruku_cpInfo("22");
//        wareHouseInBll.get_ruku_listInfo0();
//        wareHouseInBll.get_ruku_cpInfo0();

        String mmddd = "";
    }


}

