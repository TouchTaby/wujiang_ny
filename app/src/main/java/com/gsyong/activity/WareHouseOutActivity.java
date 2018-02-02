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
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.gsyong.R;
import com.gsyong.adapter.WareHouseOutAdapter;
import com.gsyong.bll.WareHouseOutBll;
import com.gsyong.bll.WebServiceDataBll;
import com.gsyong.config.Sysconfig;
import com.gsyong.model.dt_chuku_cp_all;
import com.gsyong.model.dt_chuku_list_all;
import com.zebra.adc.decoder.Barcode2DWithSoft;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import commonutils.DateUtils;
import commonutils.ToastUtils;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/19 17:06
 * 修改人：YI
 * 修改时间：2018/1/19 17:06
 * 修改备注：
 */
public class WareHouseOutActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener {
    private Button btnBack;
    private TextView tvTopName;
    private EditText edtShouhuodanwei;
    private ListView lstGoods;
    private Button btnScan;
    private Button btnSave;
    private Button btnSubmit;

    static String TAG = "TAG";
    private Barcode2DWithSoft barcode2DWithSoft = null;
    boolean scanning = false;
    private Handler outhandler = new Handler();

    private boolean isCheckDanwei = false;
    private Context context;
    private WareHouseOutBll wareHouseOutBll;
    private WebServiceDataBll webServiceDataBll;
    private int san_Open = 1;
    private int click_Open = 2;
    private String now_chuku_cp_uid;//当前点击出库单的数据的ID
    private String now_chuku_list_uid;//当前出库组ID
    private List<dt_chuku_cp_all> dt_chuku_cp_allList = new ArrayList<>();//当前出库组子数据
    private dt_chuku_list_all dt_chuku_list_allModel;//当前出库组数据
    private WareHouseOutAdapter adapter;

    private String uidParm;
    private Integer stateParm;
    private String shouhuodanwei;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_warehouseout);
        context = WareHouseOutActivity.this;
        wareHouseOutBll = new WareHouseOutBll(context);
        webServiceDataBll = new WebServiceDataBll(context);

        initData();
        initView();

        uidParm = this.getIntent().getStringExtra("uid");
        stateParm = this.getIntent().getIntExtra("state", 0);
        shouhuodanwei = this.getIntent().getStringExtra("shouhuodanwei");

        if (uidParm != null) {
            now_chuku_list_uid = uidParm;
            edtShouhuodanwei.setText(shouhuodanwei);
            if (stateParm > 0) {
                edtShouhuodanwei.setEnabled(false);
                btnScan.setVisibility(View.GONE);
                btnSave.setVisibility(View.GONE);
                btnSubmit.setVisibility(View.GONE);
            }
            dt_chuku_cp_allList = wareHouseOutBll.get_chuku_cpInfo(uidParm);
            adapter = new WareHouseOutAdapter(this, dt_chuku_cp_allList);
            lstGoods.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        new InitTask().execute();
    }


    private void initView() {
        btnBack = (Button) findViewById(R.id.btnBack);
        tvTopName = (TextView) findViewById(R.id.tvTopName);
        edtShouhuodanwei = (EditText) findViewById(R.id.edtShouhuodanwei);
        lstGoods = (ListView) findViewById(R.id.lstGoods);
        btnScan = (Button) findViewById(R.id.btnScan);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnBack.setOnClickListener(this);
        btnScan.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        adapter = new WareHouseOutAdapter(this, dt_chuku_cp_allList);
        lstGoods.setAdapter(adapter);
        lstGoods.setOnItemClickListener(this);
        lstGoods.setOnItemLongClickListener(this);
        edtShouhuodanwei.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Log.e(TAG, "onFocusChange: " + "来了吗");
                    isCheckDanwei = hasFocus;
                }
            }
        });
    }

    private void initData() {
        now_chuku_cp_uid = "";//当前点击出库单的数据的ID
        now_chuku_list_uid = UUID.randomUUID().toString().toLowerCase().replace("-", "");//当前出库组ID
        dt_chuku_cp_allList.clear();
        dt_chuku_list_allModel = new dt_chuku_list_all();//当前出库组数据
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getBaseContext(), WareHouseDetailActivity.class);
        now_chuku_cp_uid = dt_chuku_cp_allList.get(position).uid;
        intent.putExtra("daima", dt_chuku_cp_allList.get(position).daima);
        intent.putExtra("hanliang", dt_chuku_cp_allList.get(position).hanliang);
        intent.putExtra("jianshu", String.valueOf(dt_chuku_cp_allList.get(position).jianshu));
        intent.putExtra("state", stateParm);
        startActivityForResult(intent, click_Open);

        edtShouhuodanwei.clearFocus();
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
                    dt_chuku_cp_allList.remove(fp3);
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
                    dt_chuku_cp_all dt_chuku_cp_allModel = new dt_chuku_cp_all();
                    dt_chuku_cp_allModel.setUid(UUID.randomUUID().toString().toLowerCase().replace("-", ""));
                    dt_chuku_cp_allModel.setListuid(now_chuku_list_uid);
                    dt_chuku_cp_allModel.setDaima(daima.substring(0, 11));
                    dt_chuku_cp_allModel.setMingcheng(mingzi);
                    dt_chuku_cp_allModel.setHanliang(hanliang);
                    dt_chuku_cp_allModel.setJianshu(jianshu);
                    dt_chuku_cp_allModel.setState(0);
                    dt_chuku_cp_allModel.setYlint1(Integer.parseInt(hanliang) * jianshu);
                    dt_chuku_cp_allList.add(dt_chuku_cp_allModel);
                } else {
                    for (int i = 0; i < dt_chuku_cp_allList.size(); i++) {
                        dt_chuku_cp_all dt_chuku_cp_allModel = dt_chuku_cp_allList.get(i);
                        if (dt_chuku_cp_allModel.getUid().equals(now_chuku_cp_uid)) {
                            dt_chuku_cp_allModel.setHanliang(hanliang);
                            dt_chuku_cp_allModel.setJianshu(jianshu);
                            dt_chuku_cp_allModel.setYlint1(Integer.parseInt(hanliang) * jianshu);
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
                        } else if (result.length() >= 32) {
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

    private class ScanBack implements Barcode2DWithSoft.ScanCallback {
        @Override
        public void onScanComplete(int i, int length, byte[] bytes) {
            String barCode = new String(bytes, 0, length);
            scanning = false;
            barcode2DWithSoft.stopScan();
            Log.e(TAG, "onScanComplete:-------------- " + barCode);
            if (barCode != null) {
                if (barCode.indexOf("http://") == 0 || barCode.indexOf("https://") == 0) {
                    String endcode = barCode.substring(barCode.lastIndexOf("/") + 1, barCode.length());
                    Log.d("二维码", barCode);
                    if (barCode.length() < 32) {
                        Toast.makeText(getBaseContext(), barCode + "\n\n结果不符合二维码规格", Toast.LENGTH_LONG).show();
                    } else if (endcode.length() >= 32) {
                        String daima = barCode.substring(barCode.length() - 32);
                        Log.e(TAG, "onScanComplete: 二维码：" + daima);
                        if (daima.length() < 7) {
                            Toast.makeText(getBaseContext(), "产品代码不正确", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (barCode.contains("=")) {
                            String danwei = barCode.substring(barCode.lastIndexOf("=") + 1, barCode.length());
                            edtShouhuodanwei.setText(danwei);
                            handler.sendEmptyMessage(1);
                            edtShouhuodanwei.setFocusable(false);
                        } else if (barCode.contains("nyfw.gsyong.com")) {
                            //跳转到产品详细信息界面
                            Intent intent = new Intent(getApplicationContext(), WareHouseDetailActivity.class);
                            intent.putExtra("daima", daima);
                            startActivityForResult(intent, san_Open);
                        } else {
                            Toast.makeText(getBaseContext(), barCode + "\n\n结果不符合规范", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(getBaseContext(), barCode + "\n\n结果不符合规范", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (manager != null) {
                    Log.e(TAG, "handleMessage: " );
                    manager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
            return false;
        }
    });


    public class InitTask extends AsyncTask<String, Integer, Boolean> {
        ProgressDialog mypDialog;

        @Override
        protected Boolean doInBackground(String... params) {
            if (barcode2DWithSoft == null) {
                barcode2DWithSoft = Barcode2DWithSoft.getInstance();
            }
            boolean reuslt = false;
            if (barcode2DWithSoft != null) {
                reuslt = barcode2DWithSoft.open(WareHouseOutActivity.this);
                barcode2DWithSoft.setScanCallback(new ScanBack());
            }
            return reuslt;

        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                Toast.makeText(WareHouseOutActivity.this, "读头上电成功！", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(WareHouseOutActivity.this, "读头上电失败", Toast.LENGTH_SHORT).show();
            }
            mypDialog.cancel();
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            mypDialog = new ProgressDialog(WareHouseOutActivity.this);
            mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mypDialog.setMessage("初始化...");
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
        lstGoods.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (imm.isActive()) {//如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);//关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
        }
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnScan:
                //相机扫码
                barcode2DWithSoft.close();//读头和摄像头不能同时打开
                IntentIntegrator integrator = new IntentIntegrator(WareHouseOutActivity.this);
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
                if (edtShouhuodanwei.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "收货单位不得为空！", Toast.LENGTH_SHORT).show();
                } else {
                    dt_chuku_list_allModel.setUid(now_chuku_list_uid);
                    dt_chuku_list_allModel.setAddtime(DateUtils.getCurrentDateEN());
                    dt_chuku_list_allModel.setBianhao(DateUtils.getCurrentDateNumber());
                    dt_chuku_list_allModel.setQiyeid(Sysconfig.qiyeId);
                    dt_chuku_list_allModel.setShuliang(dt_chuku_cp_allList.size());
                    dt_chuku_list_allModel.setQystaff("android");
                    dt_chuku_list_allModel.setShouhuodanwei(edtShouhuodanwei.getText().toString());
                    if (v.getId() == R.id.btnSave) {
                        dt_chuku_list_allModel.setState(0);
                        wareHouseOutBll.add_chuku_Info(dt_chuku_cp_allList, dt_chuku_list_allModel);
                        if (uidParm != null || dt_chuku_cp_allList.size() > 0) {
                            ToastUtils.showShort(context, "数据已保存");
                        }
                    } else {
                        dt_chuku_list_allModel.setState(1);
                        for (int i = 0; i < dt_chuku_cp_allList.size(); i++) {
                            dt_chuku_cp_allList.get(i).setState(1);
                        }
                        wareHouseOutBll.add_chuku_Info(dt_chuku_cp_allList, dt_chuku_list_allModel);
                        if (uidParm != null || dt_chuku_cp_allList.size() > 0) {
                            ToastUtils.showShort(context, "数据已提交");
                        }
                        if (uidParm != null) {
                            finish();
                        } else {
                            initData();
                            edtShouhuodanwei.setText("");
                            adapter.notifyDataSetChanged();
                        }
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


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null && imm.isActive()) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

}