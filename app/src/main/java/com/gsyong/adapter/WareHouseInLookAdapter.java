package com.gsyong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gsyong.R;
import com.gsyong.model.dt_ruku_list_all;

import java.util.List;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/21 22:14
 * 修改人：YI
 * 修改时间：2018/1/21 22:14
 * 修改备注：
 */
public class WareHouseInLookAdapter extends BaseAdapter
{
    LayoutInflater layoutInflater;
    List<dt_ruku_list_all> nowList;


    public WareHouseInLookAdapter(Context context, List<dt_ruku_list_all> data)
    {
        layoutInflater = LayoutInflater.from(context);
        nowList = data;
    }

    @Override
    public int getCount()
    {
        return nowList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return nowList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        dt_ruku_list_all nowMdodel = nowList.get(position);
        WareHouseInLookAdapter.ViewHolder viewHolder = null;
        if (viewHolder == null)
        {
            viewHolder = new WareHouseInLookAdapter.ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_warehouseinlook, null);
            viewHolder.tv_View = convertView.findViewById(R.id.tv_View);
            viewHolder.tv_View1 = convertView.findViewById(R.id.tv_View1);
            viewHolder.tv_View2 = convertView.findViewById(R.id.tv_View2);
            viewHolder.tv_View3 = convertView.findViewById(R.id.tv_View3);
            viewHolder.tv_View4 = convertView.findViewById(R.id.tv_View4);
            viewHolder.tv_View5 = convertView.findViewById(R.id.tv_View5);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (WareHouseInLookAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.tv_View.setText(String.valueOf(position + 1));
        viewHolder.tv_View1.setText(nowMdodel.getAddtime());
        viewHolder.tv_View2.setText(String.valueOf(nowMdodel.getShuliang()));
        viewHolder.tv_View3.setText(nowMdodel.getState() < 1 ? "未入库" : "已入库");
        viewHolder.tv_View4.setText(nowMdodel.getUid());
        viewHolder.tv_View5.setText("");
        return convertView;
    }

    public class ViewHolder
    {
        TextView tv_View;
        TextView tv_View1;
        TextView tv_View2;
        TextView tv_View3;
        TextView tv_View4;
        TextView tv_View5;
    }
}
