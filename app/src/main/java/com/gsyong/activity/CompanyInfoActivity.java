package com.gsyong.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.gsyong.R;
import com.gsyong.bll.Qiye_ZhucemaInfoBll;
import com.gsyong.model.dt_qiye;

import commonutils.AppUtils;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/19 17:11
 * 修改人：YI
 * 修改时间：2018/1/19 17:11
 * 修改备注：
 */
public class CompanyInfoActivity extends AppCompatActivity
{
    EditText et_dizhi;
    EditText et_qixian;
    EditText et_mingchen;
    EditText et_youxiaoqi;
    EditText et_xukezheng;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companyinfo);
        et_dizhi = findViewById(R.id.edt_xx_dizhi);
        et_mingchen = findViewById(R.id.edt_xx_mingzi);
        et_qixian = findViewById(R.id.edt_xx_qixian);
        et_xukezheng = findViewById(R.id.edt_xx_xukezheng);
        et_youxiaoqi = findViewById(R.id.edt_xx_youxiaoqi);
        Qiye_ZhucemaInfoBll qiye = new Qiye_ZhucemaInfoBll(this);
        dt_qiye dt_qiye = qiye.getQiYeInfo(AppUtils.getMacAddress());
        et_dizhi.setText(dt_qiye.getDizhi());
        et_mingchen.setText(dt_qiye.getMingzi());
        et_youxiaoqi.setText(dt_qiye.getNyvld2());
        et_xukezheng.setText(dt_qiye.getNyxukehao());
        et_qixian.setText(dt_qiye.getYingyevld2());
    }
}


