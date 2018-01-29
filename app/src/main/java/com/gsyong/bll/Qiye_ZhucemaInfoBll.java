package com.gsyong.bll;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gsyong.model.dt_qiye;
import com.gsyong.model.dt_zhucema;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/19 17:46
 * 修改人：YI
 * 修改时间：2018/1/19 17:46
 * 修改备注：
 */
public class Qiye_ZhucemaInfoBll
{
    private SQLiteDatabase db;
    private DBHelper helper;

    public Qiye_ZhucemaInfoBll(Context context)
    {
        helper = new DBHelper(context);
    }

    /**
     * 得到企业信息
     */
    public dt_qiye getQiYeInfo(String mac)
    {
        dt_qiye nowModel = null;
        try
        {
            db = helper.getWritableDatabase();

            Cursor cursor = db.rawQuery("select b.* from dt_zhucema a left join dt_qiye b on a.[qiyeid]=b.[id] where  a.[pcmac]=? ", new String[]{mac});
            while (cursor.moveToNext())
            {
                nowModel = new dt_qiye(cursor.getString(cursor.getColumnIndex("id")), cursor.getString(cursor.getColumnIndex
                        ("mingzi")), cursor.getString(cursor.getColumnIndex("mima")), cursor.getString(cursor.getColumnIndex
                        ("dizhi")), cursor.getString(cursor.getColumnIndex("leixing")), cursor.getString(cursor.getColumnIndex
                        ("fuzeren")), cursor.getString(cursor.getColumnIndex("fzrshenfenzheng")), cursor.getString(cursor
                        .getColumnIndex("fzrdianhua")), cursor.getString(cursor.getColumnIndex("yingyezhizhao")), cursor
                        .getString(cursor.getColumnIndex("yingyevld1")), cursor.getString(cursor.getColumnIndex("yingyevld2")),
                        cursor.getString(cursor.getColumnIndex("nyxukehao")), cursor.getString(cursor.getColumnIndex("nyvld1"))
                        , cursor.getString(cursor.getColumnIndex("nyvld2")), cursor.getString(cursor.getColumnIndex("addtime"))
                        , cursor.getString(cursor.getColumnIndex("status")), cursor.getString(cursor.getColumnIndex
                        ("provinceid")), cursor.getString(cursor.getColumnIndex("cityid")), cursor.getString(cursor
                        .getColumnIndex("countyid")), cursor.getString(cursor.getColumnIndex("townshipid")), cursor.getString
                        (cursor.getColumnIndex("villageid")), cursor.getString(cursor.getColumnIndex("ylint1")), cursor
                        .getString(cursor.getColumnIndex("ylint2")), cursor.getString(cursor.getColumnIndex("ylint3")), cursor
                        .getString(cursor.getColumnIndex("ylint4")), cursor.getString(cursor.getColumnIndex("ylstr1")), cursor
                        .getString(cursor.getColumnIndex("ylstr2")), cursor.getString(cursor.getColumnIndex("ylstr3")), cursor
                        .getString(cursor.getColumnIndex("ylstr4")));


            }
            db.close();
        } catch (Exception e)
        {
        }
        return nowModel;
    }

    /**
     * 得到所有企业信息
     */
    public List<dt_qiye> getAllQiYeInfo()
    {
        List<dt_qiye> nowModel = new ArrayList<>();
        try
        {
            db = helper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select a.* from dt_qiye a ", null);
            while (cursor.moveToNext())
            {
                nowModel.add(new dt_qiye());
            }

            db.close();
        } catch (Exception e)
        {
        }
        return nowModel;
    }


    /**
     * 根据mac得到注册码信息
     */
    public dt_zhucema getZhuCeMaInfo(String mac)
    {
        dt_zhucema nowModel = null;
        try
        {
            db = helper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select a.* from dt_zhucema a where  a.[pcmac]=? ", new String[]{mac});
            while (cursor.moveToNext())
            {
                nowModel = new dt_zhucema(cursor.getString(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("qiyeid")),
                        cursor.getString(cursor.getColumnIndex("zhucema")),
                        cursor.getString(cursor.getColumnIndex("addtime")),
                        cursor.getString(cursor.getColumnIndex("valtime")),
                        cursor.getString(cursor.getColumnIndex("pcmac")),
                        cursor.getString(cursor.getColumnIndex("fangshi")),
                        cursor.getString(cursor.getColumnIndex("zhuangtai")),
                        cursor.getString(cursor.getColumnIndex("ylint1")),
                        cursor.getString(cursor.getColumnIndex("ylint2")),
                        cursor.getString(cursor.getColumnIndex("ylint3")),
                        cursor.getString(cursor.getColumnIndex("ylstr1")),
                        cursor.getString(cursor.getColumnIndex("ylstr2")),
                        cursor.getString(cursor.getColumnIndex("ylstr3")),
                        cursor.getString(cursor.getColumnIndex("jine")));

            }
            db.close();
        } catch (Exception e)
        {
        }
        return nowModel;
    }

    /**
     * 得到所有注册码信息
     */
    public List<dt_zhucema> getAllZhuCeMaInfo()
    {
        List<dt_zhucema> nowModel = new ArrayList<>();
        try
        {
            db = helper.getWritableDatabase();


            db.close();
        } catch (Exception e)
        {
        }
        return nowModel;
    }

    /**
     * 插入企业信息
     *
     * @param nowmodel
     * @return
     */
    public boolean addQiYeInfo(dt_qiye nowmodel)
    {
        Boolean result = true;
        db = helper.getWritableDatabase();
        try
        {
            db.execSQL("Insert Into dt_qiye ([id],[mingzi],[mima],[dizhi],[leixing],[fuzeren],[fzrshenfenzheng],[fzrdianhua],"
                    + "[yingyezhizhao],[yingyevld1],[yingyevld2],[nyxukehao],[nyvld1],[nyvld2],[addtime],[status],[provinceid]," +
                    "" + "" + "" + "" + "" + "" + "" + "" + "[cityid],[countyid],[townshipid],[villageid],[ylint1],[ylint2]," +
                    "[ylint3]," + "[ylint4]," + "" + "[ylstr1]," + "[ylstr2]," + "[ylstr3],[ylstr4])  values(?,?,?,?,?,?,?,?,?," +
                    "" + "?,?,?,?,?,?,?," + "?,?,?,?,?,?,?," + "?,?," + "?,?,?,?)", new Object[]{nowmodel.getId(), nowmodel
                    .getMingzi(), nowmodel.getMima(), nowmodel.getDizhi(), nowmodel.getLeixing(), nowmodel.getFuzeren(),
                    nowmodel.getFzrshenfenzheng(), nowmodel.getFzrdianhua(), nowmodel.getYingyezhizhao(), nowmodel
                    .getYingyevld1(), nowmodel.getYingyevld2(), nowmodel.getNyxukehao(), nowmodel.getNyvld1(), nowmodel
                    .getNyvld2(), nowmodel.getAddtime(), nowmodel.getStatus(), nowmodel.getProvinceid(), nowmodel.getCityid(),
                    nowmodel.getCountyid(), nowmodel.getTownshipid(), nowmodel.getVillageid(), nowmodel.getYlint1(), nowmodel
                    .getYlint2(), nowmodel.getYlint3(), nowmodel.getYlint4(), nowmodel.getYlstr1(), nowmodel.getYlstr2(),
                    nowmodel.getYlstr3(), nowmodel.getYlstr4()});
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
     * 插入注册码信息
     *
     * @param nowmodel
     * @return
     */
    public boolean addZhuCeMaInfo(dt_zhucema nowmodel)
    {
        Boolean result = true;
        db = helper.getWritableDatabase();
        try
        {
            db.execSQL("Insert Into dt_zhucema ([id],[qiyeid],[zhucema],[addtime],[valtime],[pcmac],[fangshi],[zhuangtai]," +
                    "[ylint1],[ylint2],[ylint3],[ylstr1],[ylstr2],[ylstr3],[jine])  values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                    new Object[]{nowmodel.getId(), nowmodel.getQiyeid(), nowmodel.getZhucema(), nowmodel.getAddtime(), nowmodel
                            .getValtime(), nowmodel.getPcmac(), nowmodel.getFangshi(), nowmodel.getZhuangtai(),
                            nowmodel.getYlint1(), nowmodel.getYlint2(), nowmodel.getYlint3(), nowmodel
                            .getYlstr1(), nowmodel.getYlstr2(), nowmodel.getYlstr3(), nowmodel.getJine()});
        } catch (Exception e)
        {
            result = false;
        } finally
        {
            db.close();
        }
        return result;
    }

}
