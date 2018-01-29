package com.gsyong.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.gsyong.R;
import com.gsyong.adapter.WareHouseInLookAdapter;
import com.gsyong.bll.WareHouseInBll;
import com.gsyong.model.dt_ruku_list_all;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import commonutils.DateUtils;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/22 14:14
 * 修改人：YI
 * 修改时间：2018/1/22 14:14
 * 修改备注：
 */
public class WareHouseInLookActivity extends AppCompatActivity implements View.OnClickListener, AdapterView
        .OnItemClickListener, AdapterView.OnItemSelectedListener {
    private Button btnBack;
    private TextView tvTopName;
    private LinearLayout linearLayout;
    private TextView tvStartTime;
    private TextView tvEndTime;
    private Spinner spnState;
    private ListView lstGoods;

    private Context context;
    private WareHouseInBll wareHouseInBll;
    private List<dt_ruku_list_all> dt_ruku_list_allList = new ArrayList<>();
    private WareHouseInLookAdapter adapter;
    private String endTimeValue = "";//实际日期在最后的日期上加1天;
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_warehouseinlook);
        context = WareHouseInLookActivity.this;
        wareHouseInBll = new WareHouseInBll(context);

        initView();

    }


    private void initView() {
        btnBack = findViewById(R.id.btnBack);
        tvTopName = findViewById(R.id.tvTopName);
        linearLayout = findViewById(R.id.linearLayout);
        tvStartTime = findViewById(R.id.tvStartTime);
        tvEndTime = findViewById(R.id.tvEndTime);
        spnState = findViewById(R.id.spnState);
        lstGoods = findViewById(R.id.lstGoods);

        tvStartTime.setText(DateUtils.getCurrentDateENShort());
        tvEndTime.setText(DateUtils.getCurrentDateENShort());
        endTimeValue = DateUtils.getCurrentDayAfter();
        btnBack.setOnClickListener(this);
        tvStartTime.setOnClickListener(this);
        tvEndTime.setOnClickListener(this);
        lstGoods.setOnItemClickListener(this);
        tvStartTime.addTextChangedListener(textWatcher);
        tvEndTime.addTextChangedListener(textWatcher);
        String[] stateStrings = getResources().getStringArray(R.array.wareHouseInState);
        //适配器
        ArrayAdapter<String> adapterState = new ArrayAdapter<>(context, R.layout.spinnertext, stateStrings);
        // 设置下拉列表的风格
        adapterState.setDropDownViewResource(R.layout.spinneritemtext);
        spnState.setAdapter(adapterState);
        spnState.setOnItemSelectedListener(this);
        spnState.setSelection(0);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        bindData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvStartTime:
                showDatePickerDialog(1);
                break;
            case R.id.tvEndTime:
                showDatePickerDialog(2);
                break;
            case R.id.btnBack:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(context, WareHouseInActivity.class);
        String now_ruku_list_uid = dt_ruku_list_allList.get(position).getUid();
        int state = dt_ruku_list_allList.get(position).getState();
        intent.putExtra("uid", now_ruku_list_uid);
        intent.putExtra("state", state);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        bindData();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            bindData();
        }
    };

    private void bindData() {
        int nowState = -1;
        switch (spnState.getSelectedItemPosition()) {
            case 0:
                nowState = -1;
                break;
            case 1:
                nowState = 0;
                break;
            case 2:
                nowState = 1;
                break;
        }
        dt_ruku_list_allList = wareHouseInBll.get_ruku_listInfo(tvStartTime.getText().toString(), endTimeValue, nowState);
        adapter = new WareHouseInLookAdapter(context, dt_ruku_list_allList);
        lstGoods.setAdapter(adapter);
    }

    /**
     * 展示日期选择对话框
     */
    private void showDatePickerDialog(final int type) {
        final Calendar c = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                c.set(year, monthOfYear, dayOfMonth);
                String nowdate = sdf2.format(c.getTime());
                if (type == 1) {
                    if (!nowdate.equals(tvStartTime.getText().toString())) {
                        tvStartTime.setText(nowdate);
                    }
                } else {
                    c.add(Calendar.DAY_OF_MONTH, +1); //今天的时间加一天
                    String nowdate1 = sdf2.format(c.getTime());
                    endTimeValue = nowdate1;
                    if (!nowdate.equals(tvEndTime.getText().toString())) {
                        tvEndTime.setText(nowdate);
                    }
                }

            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }
}
