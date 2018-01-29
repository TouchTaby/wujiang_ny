package com.gsyong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gsyong.R;
import com.gsyong.model.dt_chuku_list_all;

import java.util.List;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/22 11:49
 * 修改人：YI
 * 修改时间：2018/1/22 11:49
 * 修改备注：
 */
public class WareHouseOutLookAdapter extends BaseAdapter
{
    LayoutInflater layoutInflater;
    List<dt_chuku_list_all> nowList;

    public WareHouseOutLookAdapter(Context context, List<dt_chuku_list_all> data)
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
        dt_chuku_list_all nowMdodel = nowList.get(position);
        WareHouseOutLookAdapter.ViewHolder viewHolder = null;
        if (viewHolder == null)
        {
            viewHolder = new WareHouseOutLookAdapter.ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_warehouseoutlook, null);
            viewHolder.tv_View = convertView.findViewById(R.id.tv_View);
            viewHolder.tv_View1 = convertView.findViewById(R.id.tv_View1);
            viewHolder.tv_View2 = convertView.findViewById(R.id.tv_View2);
            viewHolder.tv_View3 = convertView.findViewById(R.id.tv_View3);
            viewHolder.tv_View4 = convertView.findViewById(R.id.tv_View4);
            viewHolder.tv_View5 = convertView.findViewById(R.id.tv_View5);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (WareHouseOutLookAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.tv_View.setText(String.valueOf(position + 1));
        viewHolder.tv_View1.setText(nowMdodel.getAddtime());
        viewHolder.tv_View2.setText(nowMdodel.getShouhuodanwei());
        viewHolder.tv_View3.setText(nowMdodel.getState() < 1 ? "未出库" : "已出库");
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
