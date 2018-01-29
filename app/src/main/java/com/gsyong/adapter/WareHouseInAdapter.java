package com.gsyong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gsyong.R;
import com.gsyong.model.dt_ruku_cp_all;

import java.util.List;

;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/21 20:23
 * 修改人：YI
 * 修改时间：2018/1/21 20:23
 * 修改备注：
 */
public class WareHouseInAdapter extends BaseAdapter
{
    LayoutInflater layoutInflater;
    List<dt_ruku_cp_all> nowList;


    public WareHouseInAdapter(Context context, List<dt_ruku_cp_all> data)
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
        dt_ruku_cp_all nowMdodel = nowList.get(position);
        WareHouseInAdapter.ViewHolder viewHolder = null;
        if (viewHolder == null)
        {
            viewHolder = new WareHouseInAdapter.ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_warehouse, null);
            viewHolder.tv_View = convertView.findViewById(R.id.tv_View);
            viewHolder.tv_View1 = convertView.findViewById(R.id.tv_View1);
            viewHolder.tv_View2 = convertView.findViewById(R.id.tv_View2);
            viewHolder.tv_View3 = convertView.findViewById(R.id.tv_View3);
            viewHolder.tv_View4 = convertView.findViewById(R.id.tv_View4);
            viewHolder.tv_View5 = convertView.findViewById(R.id.tv_View5);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (WareHouseInAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.tv_View.setText(String.valueOf(position + 1));
        viewHolder.tv_View1.setText(nowMdodel.getMingcheng());
        viewHolder.tv_View2.setText(String.valueOf(nowMdodel.getHanliang()));
        viewHolder.tv_View3.setText(String.valueOf(nowMdodel.getJianshu()));
        viewHolder.tv_View4.setText(nowMdodel.getUid());
        viewHolder.tv_View5.setText(nowMdodel.getDaima());

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
