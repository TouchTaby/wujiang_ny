package com.gsyong.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.gsyong.R;
import com.gsyong.adapter.WareHouseInAdapter;
import com.gsyong.bll.WareHouseInBll;
import com.gsyong.bll.WebServiceDataBll;
import com.gsyong.config.Sysconfig;
import com.gsyong.model.dt_ruku_cp_all;
import com.gsyong.model.dt_ruku_list_all;
import com.zebra.adc.decoder.Barcode2DWithSoft;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import commonutils.DateUtils;
import commonutils.ToastUtils;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/19 17:05
 * 修改人：YI
 * 修改时间：2018/1/19 17:05
 * 修改备注：
 */
public class WareHouseInActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener {
    private Button btnBack;
    private TextView tvTopName;
    private ListView lstGoods;
    private Button btnScan;
    private Button btnSave;
    private Button btnSubmit;


    static String TAG = "TAG";
    private Barcode2DWithSoft barcode2DWithSoft = null;
    boolean scanning = false;

    private Context context;
    private WareHouseInBll wareHouseInBll;
    private WebServiceDataBll webServiceDataBll;
    private int san_Open = 1;
    private int click_Open = 2;
    private String now_ruku_cp_uid;//当前点击入库单的数据的ID
    private String now_ruku_list_uid;//当前出库组ID
    private List<dt_ruku_cp_all> dt_ruku_cp_allList = new ArrayList<>();//当前入库组子数据;//当前入库组子数据
    private dt_ruku_list_all dt_ruku_list_allModel;//当前入库组数据
    private WareHouseInAdapter adapter;

    private String uidParm;
    private Integer stateParm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_warehousein);

        context = WareHouseInActivity.this;
        wareHouseInBll = new WareHouseInBll(context);
        webServiceDataBll = new WebServiceDataBll(context);

        initData();
        initView();

        uidParm = this.getIntent().getStringExtra("uid");
        stateParm = this.getIntent().getIntExtra("state", 0);
        if (uidParm != null) {
            now_ruku_list_uid = uidParm;
            if (stateParm > 0) {
                btnScan.setVisibility(View.GONE);
                btnSave.setVisibility(View.GONE);
                btnSubmit.setVisibility(View.GONE);
            }
            dt_ruku_cp_allList = wareHouseInBll.get_ruku_cpInfo(uidParm);
            adapter = new WareHouseInAdapter(this, dt_ruku_cp_allList);
            lstGoods.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        new InitTask().execute();//读头初始化
    }

    private void initView() {
        btnBack =  findViewById(R.id.btnBack);
        tvTopName =  findViewById(R.id.tvTopName);
        lstGoods =  findViewById(R.id.lstGoods);
        btnScan =  findViewById(R.id.btnScan);
        btnSave =  findViewById(R.id.btnSave);
        btnSubmit =  findViewById(R.id.btnSubmit);
        btnBack.setOnClickListener(this);
        btnScan.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        adapter = new WareHouseInAdapter(this, dt_ruku_cp_allList);
        lstGoods.setAdapter(adapter);
        lstGoods.setOnItemClickListener(this);
        lstGoods.setOnItemLongClickListener(this);
        handler.sendEmptyMessage(1);
        Log.e(TAG, "initView: 是否从新初始化");
    }
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (manager != null) {
                    manager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
            return false;
        }
    });

    private void initData() {
        now_ruku_cp_uid = "";
        now_ruku_list_uid = UUID.randomUUID().toString().toLowerCase().replace("-", "");//当前出库组ID
        dt_ruku_cp_allList.clear();
        dt_ruku_list_allModel = new dt_ruku_list_all();//当前入库组数据
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(context, WareHouseDetailActivity.class);
        now_ruku_cp_uid = dt_ruku_cp_allList.get(position).uid;
        intent.putExtra("daima", dt_ruku_cp_allList.get(position).daima);
        intent.putExtra("hanliang", dt_ruku_cp_allList.get(position).hanliang);
        intent.putExtra("jianshu", String.valueOf(dt_ruku_cp_allList.get(position).jianshu));
        intent.putExtra("state", stateParm);
        startActivityForResult(intent, click_Open);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        if (stateParm == 0) {
            final int fp3 = position;
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("删除确认");
            builder.setMessage("您真的要删除这个记录吗？");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dt_ruku_cp_allList.remove(fp3);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getBaseContext(), "删除成功", Toast.LENGTH_SHORT).show();
                }
            });
            //    设置一个NegativeButton
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.show();
        }
        return true;
    }

    private class ScanBack implements Barcode2DWithSoft.ScanCallback {
        @Override
        public void onScanComplete(int i, int length, byte[] bytes) {
            String barCode = new String(bytes, 0, length);
            scanning = false;
            barcode2DWithSoft.stopScan();
            Log.e(TAG, "onScanComplete:-------------- " + barCode);
            if (barCode != null) {
                if (barCode.indexOf("http://") == 0 || barCode.indexOf("https://") == 0) {
                    Log.d("二维码", barCode);
                    if (barCode.length() < 32) {
                        Toast.makeText(getBaseContext(), barCode + "\n\n结果不符合二维码规格", Toast.LENGTH_LONG).show();
                    } else {
                        String daima = barCode.substring(barCode.length() - 32);
                        Log.e(TAG, "onScanComplete: 二维码：" + daima);
                        if (daima.length() < 7) {
                            Toast.makeText(getBaseContext(), "产品代码不正确", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //跳转到产品详细信息界面
                        Intent intent = new Intent(getApplicationContext(), WareHouseDetailActivity.class);
                        intent.putExtra("daima", daima);
                        startActivityForResult(intent, san_Open);
                    }
                } else {
                    Toast.makeText(getBaseContext(), barCode + "\n\n结果不符合规范", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public class InitTask extends AsyncTask<String, Integer, Boolean> {
        ProgressDialog mypDialog;

        @Override
        protected Boolean doInBackground(String... params) {
            if (barcode2DWithSoft == null) {
                barcode2DWithSoft = Barcode2DWithSoft.getInstance();
            }
            boolean reuslt = false;
            if (barcode2DWithSoft != null) {
                reuslt = barcode2DWithSoft.open(WareHouseInActivity.this);
                barcode2DWithSoft.setScanCallback(new ScanBack());
            }
            return reuslt;

        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                Toast.makeText(WareHouseInActivity.this, "Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(WareHouseInActivity.this, "fail", Toast.LENGTH_SHORT).show();
            }
            mypDialog.cancel();
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            mypDialog = new ProgressDialog(WareHouseInActivity.this);
            mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mypDialog.setMessage("init...");
            mypDialog.setCanceledOnTouchOutside(false);
            mypDialog.show();
        }

    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy");
        if (barcode2DWithSoft != null) {
            barcode2DWithSoft.stopScan();
            barcode2DWithSoft.close();
        }
        super.onDestroy();
    }

    private void ScanBarcode() {
        if (barcode2DWithSoft != null && !scanning) {
            Log.e(TAG, "ScanBarcode");
            scanning = true;
            barcode2DWithSoft.scan();
        }
    }



    @Override
    protected void onStop() {
        super.onStop();
        if (barcode2DWithSoft != null) {
            barcode2DWithSoft.close();//屏幕熄灭
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        handler.sendEmptyMessage(1);
        new InitTask().execute();//屏幕重新启动的时候初始化读头
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 139) {
            if (event.getRepeatCount() == 0) {
                ScanBarcode();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == san_Open || requestCode == click_Open) {
            if (resultCode == Activity.RESULT_OK) {
                //得到新Activity 关闭后返回的数据
                String daima = data.getExtras().getString("daima");
                String mingzi = data.getExtras().getString("mingzi");
                String hanliang = data.getExtras().getString("hanliang");
                int jianshu = Integer.parseInt(data.getExtras().getString("jianshu"));
                Log.e(TAG, "onActivityResult: 代码：" + daima);
                Log.e(TAG, "onActivityResult: 名字：" + mingzi);
                Log.e(TAG, "onActivityResult: 含量 ：" + hanliang);
                Log.e(TAG, "onActivityResult: 件数：" + jianshu);
                if (requestCode == san_Open) {
                    dt_ruku_cp_all dt_ruku_cp_allModel = new dt_ruku_cp_all();
                    dt_ruku_cp_allModel.setUid(UUID.randomUUID().toString().toLowerCase().replace("-", ""));
                    dt_ruku_cp_allModel.setListuid(now_ruku_list_uid);
                    dt_ruku_cp_allModel.setDaima(daima.substring(0, 11));
                    dt_ruku_cp_allModel.setMingcheng(mingzi);
                    dt_ruku_cp_allModel.setHanliang(hanliang);
                    dt_ruku_cp_allModel.setJianshu(jianshu);
                    dt_ruku_cp_allModel.setState(0);
                    dt_ruku_cp_allModel.setYlint1(Integer.parseInt(hanliang) * jianshu);
                    dt_ruku_cp_allList.add(dt_ruku_cp_allModel);
                } else {
                    for (int i = 0; i < dt_ruku_cp_allList.size(); i++) {
                        dt_ruku_cp_all dt_ruku_cp_allModel = dt_ruku_cp_allList.get(i);
                        if (dt_ruku_cp_allModel.getUid().equals(now_ruku_cp_uid)) {
                            dt_ruku_cp_allModel.setHanliang(hanliang);
                            dt_ruku_cp_allModel.setJianshu(jianshu);
                            dt_ruku_cp_allModel.setYlint1(Integer.parseInt(hanliang) * jianshu);
                            break;
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }
        } else {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (scanResult != null) {
                String result = scanResult.getContents();
                if (result != null) {
                    if (result.indexOf("http://") == 0 || result.indexOf("https://") == 0) {
                        Log.d("二维码", result);
                        if (result.length() < 32) {
                            Toast.makeText(getBaseContext(), result + "\n\n结果不符合二维码规格", Toast.LENGTH_LONG).show();
                        } else {
                            String daima = result.substring(result.length() - 32);
                            Log.d("二维码解析", daima);
                            if (daima.length() < 11) {
                                Toast.makeText(getBaseContext(), "产品代码不正确", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Intent intent = new Intent(this, WareHouseDetailActivity.class);
                            intent.putExtra("daima", daima);
                            startActivityForResult(intent, san_Open);
                        }
                    } else {
                        Toast.makeText(getBaseContext(), result + "\n\n结果不符合规范", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnScan:
                //相机扫码
                barcode2DWithSoft.close();//读头和摄像头不能同时打开
                IntentIntegrator integrator = new IntentIntegrator(WareHouseInActivity.this);
                // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setCaptureActivity(CaptureActivity.class);
                integrator.setPrompt("请扫描二维码"); //底部的提示文字，设为""可以置空
                integrator.setCameraId(0); //前置或者后置摄像头
                integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
                break;
            case R.id.btnSave:
            case R.id.btnSubmit:
                dt_ruku_list_allModel.setUid(now_ruku_list_uid);
                dt_ruku_list_allModel.setAddtime(DateUtils.getCurrentDateEN());
                dt_ruku_list_allModel.setBianhao(DateUtils.getCurrentDateNumber());
                dt_ruku_list_allModel.setQiyeid(Sysconfig.qiyeId);
                dt_ruku_list_allModel.setShuliang(dt_ruku_cp_allList.size());
                dt_ruku_list_allModel.setQystaff("android");
                dt_ruku_list_allModel.setState(0);
                if (v.getId() == R.id.btnSave) {
                    dt_ruku_list_allModel.setState(0);
                    wareHouseInBll.add_ruku_Info(dt_ruku_cp_allList, dt_ruku_list_allModel);
                    if (uidParm != null || dt_ruku_cp_allList.size() > 0) {
                        ToastUtils.showShort(context, "数据已保存");
                    }
                } else {
                    dt_ruku_list_allModel.setState(1);
                    for (int i = 0; i < dt_ruku_cp_allList.size(); i++) {
                        dt_ruku_cp_allList.get(i).setState(1);
                    }
                    wareHouseInBll.add_ruku_Info(dt_ruku_cp_allList, dt_ruku_list_allModel);
                    if (uidParm != null || dt_ruku_cp_allList.size() > 0) {
                        ToastUtils.showShort(context, "数据已提交");
                    }
                    if (uidParm != null) {
                        finish();
                    } else {
                        initData();
                        adapter.notifyDataSetChanged();
                    }
                }
                break;
            case R.id.btnBack:
                finish();
                break;
            default:
                break;
        }
    }


}
