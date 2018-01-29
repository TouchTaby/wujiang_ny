package com.gsyong.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.gsyong.R;
import com.gsyong.bll.WebServiceSyncDataBll;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/19 14:55
 * 修改人：YI
 * 修改时间：2018/1/19 14:55
 * 修改备注：
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button btnBack;
    private TextView tvTopName;
    private TextView tvRukucaozuo;
    private TextView tvChukucaozuo;
    private TextView tvRukuchaxun;
    private TextView tvChukuchaxun;
    private TextView tvWodexinxi;

    private Context context;
    private boolean isSend = true;//上传数据
    private Thread thread;//后台上传数据
    private WebServiceSyncDataBll webServiceSyncDataBll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);// 调用webservice
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        webServiceSyncDataBll = new WebServiceSyncDataBll(context);
        initView();
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            //先判断有没有权限 ，没有就在这里进行权限的申请
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.CAMERA},1);

        }
        thread = new Thread(networkTask);
        thread.start();
    }


    private void initView()
    {
        btnBack = (Button) findViewById(R.id.btnBack);
        tvTopName = (TextView) findViewById(R.id.tvTopName);
        tvRukucaozuo = (TextView) findViewById(R.id.tv_rukucaozuo);
        tvChukucaozuo = (TextView) findViewById(R.id.tv_chukucaozuo);
        tvRukuchaxun = (TextView) findViewById(R.id.tv_rukuchaxun);
        tvChukuchaxun = (TextView) findViewById(R.id.tv_chukuchaxun);
        tvWodexinxi = (TextView) findViewById(R.id.tv_wodexinxi);
        btnBack.setOnClickListener(this);
        tvRukucaozuo.setOnClickListener(this);
        tvChukucaozuo.setOnClickListener(this);
        tvRukuchaxun.setOnClickListener(this);
        tvChukuchaxun.setOnClickListener(this);
        tvWodexinxi.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        Intent intent;
        switch (v.getId())
        {
            case R.id.tv_rukucaozuo:
                intent = new Intent(this, WareHouseInActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_chukucaozuo:
                intent = new Intent(this, WareHouseOutActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_rukuchaxun:
                intent = new Intent(this, WareHouseInLookActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_chukuchaxun:
                intent = new Intent(this, WareHouseOutLookActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_wodexinxi:
                intent = new Intent(this, CompanyInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.btnBack:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 网络操作相关的子线程
     */
    private Runnable networkTask = new Runnable()
    {
        @Override
        public void run()
        {
            // TODO
            while (isSend)
            {
                try
                {
                    boolean result = webServiceSyncDataBll.Syn_chukudanAll();
                    result = webServiceSyncDataBll.Syn_rukudanAll();
                    Thread.sleep(1000 * 60 * 5);
                } catch (Exception e)
                {

                }
            }
        }
    };

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        isSend = false;
    }
}
