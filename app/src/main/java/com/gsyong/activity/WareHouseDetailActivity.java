package com.gsyong.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gsyong.R;
import com.gsyong.bll.GoodsBll;
import com.gsyong.bll.PostRequest;
import com.gsyong.model.dt_goods;

import org.json.JSONObject;

import java.util.HashMap;

import commonutils.DataUtils;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/19 18:46
 * 修改人：YI
 * 修改时间：2018/1/19 18:46
 * 修改备注：
 */
public class WareHouseDetailActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button btnBack;
    private TextView tvTopName;
    private EditText edtCpDaima;
    private EditText edtCpMingzi;
    private EditText edtCpDengjizheng;
    private EditText edtCpChiyouren;
    private EditText edtCpJixing;
    private EditText edtCpHanliang;
    private EditText edtCpJianshu;
    private Button btnSubmit;
    private String result_daima = "";
    static String TAG = "TAG";
    private String daima = "";
    private String mingzi = "";//农药名称
    private String zhengshu = "";//登记证号
    private String chiyouren = "";//持有人
    private String jixing = "";//剂型
    private String hanliang = "";//含量
    private String jianshu = "";//件数

    private Context context;
    private GoodsBll goodsBll;
    private dt_goods dt_goodsModel;

    private Integer stateParm;//大于0说明是已经保存的，不能修改

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_warehousedetail);
        context = WareHouseDetailActivity.this;
        goodsBll = new GoodsBll(context);

        initView();
        //获取代码参数
        result_daima = this.getIntent().getStringExtra("daima");
        hanliang = this.getIntent().getStringExtra("hanliang");
        jianshu = this.getIntent().getStringExtra("jianshu");
        stateParm = this.getIntent().getIntExtra("state", 0);
        Log.e(TAG, "onCreate: 从上一界面获取到代码：" + result_daima);
        dt_goodsModel = goodsBll.getGoodsInfo(result_daima.substring(0, 11));
        if (hanliang == null)//说明是扫描开启的
        {
            if (dt_goodsModel != null)
            {
                //本地有该商品数据
                setValue();
            } else
            {
                new Thread(networkTask_getchanpin).start();
            }
        } else
        {
            if (stateParm > 0)
            {
                edtCpHanliang.setEnabled(false);
                edtCpJianshu.setEnabled(false);
                btnSubmit.setVisibility(View.GONE);
            }
            setValue();
        }
        edtCpDaima.setText(result_daima.substring(0, 11));
    }



    private void initView()
    {
        btnBack = (Button) findViewById(R.id.btnBack);
        tvTopName = (TextView) findViewById(R.id.tvTopName);
        edtCpDaima = (EditText) findViewById(R.id.edt_cp_daima);
        edtCpMingzi = (EditText) findViewById(R.id.edt_cp_mingzi);
        edtCpDengjizheng = (EditText) findViewById(R.id.edt_cp_dengjizheng);
        edtCpChiyouren = (EditText) findViewById(R.id.edt_cp_chiyouren);
        edtCpJixing = (EditText) findViewById(R.id.edt_cp_jixing);
        edtCpHanliang = (EditText) findViewById(R.id.edt_cp_hanliang);
        edtCpJianshu = (EditText) findViewById(R.id.edt_cp_jianshu);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        edtCpJianshu.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    edtCpJianshu.setText("");
                }
            }
        });
        edtCpHanliang.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    edtCpHanliang.setText("");
                }
            }
        });
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnSubmit:
                daima = edtCpDaima.getText().toString();
                mingzi = edtCpMingzi.getText().toString();
                chiyouren = edtCpChiyouren.getText().toString();
                zhengshu = edtCpDengjizheng.getText().toString();
                jixing = edtCpJixing.getText().toString();
                hanliang = edtCpHanliang.getText().toString();
                jianshu = edtCpJianshu.getText().toString();
                if (hanliang .equals("")) {
                    Toast.makeText(getApplicationContext(),"含量不能为空！",Toast.LENGTH_SHORT).show();
                }
                if (jianshu .equals("")) {
                    Toast.makeText(getApplicationContext(),"件数不能为空！",Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent();
                intent.putExtra("daima", result_daima);
                intent.putExtra("mingzi", mingzi);
                intent.putExtra("hanliang", hanliang);
                intent.putExtra("jianshu", jianshu);
                setResult(RESULT_OK, intent);//数据返回入库界面
                finish();
                break;
            case R.id.btnBack:
                finish();
                break;

            default:
                break;
        }
    }

    private void result_data(String data)
    {
        try
        {
            JSONObject jsonObj = new JSONObject(data);
            if (jsonObj.getString("status").equals("y"))
            {
                JSONObject m = jsonObj.getJSONObject("model");

                //添加商品信息
                dt_goodsModel = new dt_goods(m.getString("_uid"), m.getString("_addtime"), m.getString("_cpchangjia"), m
                        .getString("_cpdaima"), DataUtils.getDouble(m.getString("_cpjiage")), m.getString("_cpleixing"),
                        DataUtils.getInteger(m.getString("_cplock")), m.getString("_cpmingzi"), m.getString("_cppici"), m
                        .getString("_cpurl"), DataUtils.getInteger(m.getString("_status")), DataUtils.getInteger(m.getString
                        ("_ylint1")), DataUtils.getInteger(m.getString("_ylint2")), DataUtils.getInteger(m.getString("_ylint3")
                ), DataUtils.getInteger(m.getString("_ylint4")), m.getString("_ylstr1"), m.getString("_ylstr2"), m.getString
                        ("_ylstr3"), m.getString("_ylstr4"));
                goodsBll.addGoodsInfo(dt_goodsModel);

                setValue();

            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * 显示数据
     */
    private void setValue()
    {
        edtCpMingzi.setText(dt_goodsModel.get_cpmingzi());
        edtCpDengjizheng.setText(dt_goodsModel.get_ylstr2());
        edtCpChiyouren.setText(dt_goodsModel.get_cpchangjia());
        edtCpJixing.setText(dt_goodsModel.get_cppici());
        if (hanliang != null)//说明是扫描开启的
        {
            edtCpHanliang.setText(hanliang);
            edtCpJianshu.setText(jianshu);
        }else {
            edtCpHanliang.setText("1");
            edtCpJianshu.setText("1");
        }

    }

    Runnable networkTask_getchanpin = new Runnable()
    {
        @Override
        public void run()
        {
            // TODO
            // 二维码获取产品详情请求
            try
            {
                HashMap<String, String> var = new HashMap<String, String>();
                var.put("action", "GetChanPin");
                var.put("daima", result_daima);
                Log.e(TAG, "run:aaaaaaa " + result_daima);
                String str = PostRequest.sendPostRequest( var, null);
                Log.e(TAG, "run: 返回:" + str);

                Message msg = new Message();
                msg.what = 1;
                msg.obj = str;//可以是基本类型，可以是对象，可以是List、map等；
                mHandler.sendMessage(msg);
            } catch (Exception ex)
            {
                //需要数据传递，用下面方法；
                Message msg = new Message();
                msg.what = 2;
                msg.obj = ex.getMessage();//可以是基本类型，可以是对象，可以是List、map等；
                mHandler.sendMessage(msg);
            }
        }
    };
    Handler mHandler = new Handler()
    {

        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            String data = (String) msg.obj;
            switch (msg.what)
            {
                case 1:
                    result_data(data);
                    break;
                case 2:
                    Toast.makeText(getBaseContext(), data, Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }

        }


    };

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        //隐藏软键盘
        InputMethodManager imm3 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm3.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }
}