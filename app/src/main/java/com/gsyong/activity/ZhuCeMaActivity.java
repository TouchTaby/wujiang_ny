package com.gsyong.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.gsyong.R;
import com.gsyong.bll.WebServiceDataBll;

import commonutils.ToastUtils;
import commonutils.WeiboDialogUtils;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/19 17:10
 * 修改人：YI
 * 修改时间：2018/1/19 17:10
 * 修改备注：
 */
public class ZhuCeMaActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtCode;
    private Button btnRegister;
    private Context context;
    private String zhucema = "";
    private Dialog mDialog;

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            WeiboDialogUtils.closeDialog(mDialog);
            switch (msg.what) {
                case 1:
                    ToastUtils.showShort(context, "注册成功");
                    finish();
                    break;
                case 2:
                    setWeightState(true);
                    ToastUtils.showLong(context, "注册失败，注册码不正确或已失效");
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
        setContentView(R.layout.activity_zhucema);
        context = ZhuCeMaActivity.this;

        edtCode = findViewById(R.id.edtCode);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRegister) {
            //开始注册
            zhucema = edtCode.getText().toString().trim();
            if (zhucema.length() != 10) {
                ToastUtils.showLong(context, "注册码长度不正确，应该为10位");
                return;
            }
            setWeightState(false);
            mDialog = WeiboDialogUtils.createLoadingDialog(context, "注册中...");
            new Thread(networkTask).start();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setWeightState(boolean state) {
        edtCode.setEnabled(state);
        btnRegister.setEnabled(state);
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
                boolean result = webServiceDataBll.getzhucemabangding(zhucema);
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

}
