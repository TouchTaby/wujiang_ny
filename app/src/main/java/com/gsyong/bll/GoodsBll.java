package com.gsyong.bll;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gsyong.model.dt_goods;

import java.util.List;

import commonutils.DataUtils;

/**
 * 类描述：商品信息
 * 创建人：YI
 * 创建时间：2018/1/21 14:33
 * 修改人：YI
 * 修改时间：2018/1/21 14:33
 * 修改备注：
 */
public class GoodsBll
{
    private SQLiteDatabase db;
    private DBHelper helper;

    public GoodsBll(Context context)
    {
        helper = new DBHelper(context);
    }

    /**
     * 插入一组农药信息
     *
     * @param listModes
     * @return
     */
    public boolean addGoodsInfo(List<dt_goods> listModes)
    {
        Boolean result = true;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        try
        {
            for (dt_goods nowModel : listModes)
            {
                db.execSQL("Insert Into dt_goods ([_uid],[_addtime],[_cpmingzi],[_cpdaima],[_cpchangjia],[_cpjiage]," +
                        "[_cpleixing],[_cplock],[_cppici],[_cpurl],[_status],[_ylint1],[_ylint2],[_ylint3],[_ylint4],[_ylstr1]," +
                        "[_ylstr2],[_ylstr3],[_ylstr4])  values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                        new Object[]{nowModel.get_uid(), nowModel.get_addtime(), nowModel.get_cpmingzi(),
                                nowModel.get_cpdaima(), nowModel.get_cpchangjia(), nowModel.get_cpjiage(),
                                nowModel.get_cpleixing(),nowModel.get_cplock(),nowModel.get_cppici(),nowModel.get_cpurl(),
                                nowModel.get_status(),nowModel.get_ylint1(),nowModel.get_ylint2(),nowModel.get_ylint3(),
                                nowModel.get_ylint4(),nowModel.get_ylstr1(),nowModel.get_ylstr2(),nowModel.get_ylstr3(),nowModel.get_ylstr4()});
            }
            db.setTransactionSuccessful();
        } catch (Exception e)
        {
            result = false;
        } finally
        {
            db.endTransaction();
            db.close();
        }
        return result;
    }


    /**
     * 插入农药信息
     *
     * @return
     */
    public boolean addGoodsInfo(dt_goods nowModel)
    {
        Boolean result = true;
        db = helper.getWritableDatabase();
        try
        {
            db.execSQL("Insert Into dt_goods ([_uid],[_addtime],[_cpmingzi],[_cpdaima],[_cpchangjia],[_cpjiage]," +
                            "[_cpleixing],[_cplock],[_cppici],[_cpurl],[_status],[_ylint1],[_ylint2],[_ylint3],[_ylint4],[_ylstr1]," +
                            "[_ylstr2],[_ylstr3],[_ylstr4])  values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                    new Object[]{nowModel.get_uid(), nowModel.get_addtime(), nowModel.get_cpmingzi(),
                            nowModel.get_cpdaima(), nowModel.get_cpchangjia(), nowModel.get_cpjiage(),
                            nowModel.get_cpleixing(),nowModel.get_cplock(),nowModel.get_cppici(),nowModel.get_cpurl(),
                            nowModel.get_status(),nowModel.get_ylint1(),nowModel.get_ylint2(),nowModel.get_ylint3(),
                            nowModel.get_ylint4(),nowModel.get_ylstr1(),nowModel.get_ylstr2(),nowModel.get_ylstr3(),nowModel.get_ylstr4()});


        } catch (Exception e)
        {
            result = false;
        } finally
        {
            db.close();
        }
        return result;
    }


    /**
     * 根据农药代码得到农药信息
     */
    public dt_goods getGoodsInfo(String daima)
    {
        dt_goods nowModel = null;
        try
        {
            db = helper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select a.* from dt_goods a where  a.[_cpdaima]=? ", new String[]{daima});
            while (cursor.moveToNext())
            {
                nowModel = new dt_goods(
                        cursor.getString(cursor.getColumnIndex("_uid")),
                        cursor.getString(cursor.getColumnIndex("_addtime")),
                        cursor.getString(cursor.getColumnIndex("_cpchangjia")),
                        cursor.getString(cursor.getColumnIndex("_cpdaima")),
                        DataUtils.getDouble( cursor.getString(cursor.getColumnIndex("_cpjiage"))),
                        cursor.getString(cursor.getColumnIndex("_cpleixing")),
                        DataUtils.getInteger( cursor.getString(cursor.getColumnIndex("_cplock"))),
                        cursor.getString(cursor.getColumnIndex("_cpmingzi")),
                        cursor.getString(cursor.getColumnIndex("_cppici")),
                        cursor.getString(cursor.getColumnIndex("_cpurl")),
                        DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("_status"))),
                        DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("_ylint1"))),
                        DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("_ylint2"))),
                        DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("_ylint3"))),
                        DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("_ylint4"))),
                        cursor.getString(cursor.getColumnIndex("_ylstr1")),
                        cursor.getString(cursor.getColumnIndex("_ylstr2")),
                        cursor.getString(cursor.getColumnIndex("_ylstr3")),
                        cursor.getString(cursor.getColumnIndex("_ylstr4"))
                );
            }
            db.close();
        } catch (Exception e)
        {
        }
        return nowModel;
    }
}
