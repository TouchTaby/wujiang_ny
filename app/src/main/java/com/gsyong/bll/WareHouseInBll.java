package com.gsyong.bll;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gsyong.model.dt_ruku_cp_1;
import com.gsyong.model.dt_ruku_cp_all;
import com.gsyong.model.dt_ruku_list_all;
import com.gsyong.models.dt_ruku_cp;
import com.gsyong.models.dt_ruku_list;
import com.gsyong.models.var_Syn_rukucp;
import com.gsyong.models.var_Syn_rukudan;

import java.util.ArrayList;
import java.util.List;

import commonutils.DataUtils;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/19 17:45
 * 修改人：YI
 * 修改时间：2018/1/19 17:45
 * 修改备注：
 */
public class WareHouseInBll
{
    private SQLiteDatabase db;
    private DBHelper helper;

    public WareHouseInBll(Context context)
    {
        helper = new DBHelper(context);
    }

    /**
     * 插入一组数据入库数据
     *
     * @param listModes
     * @param nowModel
     * @return
     */
    public boolean add_ruku_Info(List<dt_ruku_cp_all> listModes, dt_ruku_list_all nowModel)
    {
        Boolean result = true;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        try
        {
            db.execSQL("delete from dt_ruku_cp where listuid=?", new Object[]{nowModel.getUid()});
            db.execSQL("delete from dt_ruku_list where uid=?", new Object[]{nowModel.getUid()});
            if(listModes.size()>0)
            {
            for (dt_ruku_cp_all dt_ruku_cp_allModel : listModes)
            {
                db.execSQL("Insert Into dt_ruku_cp ([uid],[listuid],[daima],[cpuid],[ylint1],[ylint2],[ylint3],[ylstr1]," +
                        "[ylstr2],[ylstr3],[hanliang],[jianshu],[state])  values(?,?,?,?,?,?,?,?,?,?,?,?,?)", new
                        Object[]{dt_ruku_cp_allModel.getUid(), dt_ruku_cp_allModel.getListuid(), dt_ruku_cp_allModel.getDaima()
                        , dt_ruku_cp_allModel.getCpuid(), dt_ruku_cp_allModel.getYlint1(), dt_ruku_cp_allModel.getYlint2(),
                        dt_ruku_cp_allModel.getYlint3(), dt_ruku_cp_allModel.getYlstr1(), dt_ruku_cp_allModel.getYlstr2(),
                        dt_ruku_cp_allModel.getYlstr3(), dt_ruku_cp_allModel.getHanliang(), dt_ruku_cp_allModel.getJianshu(),
                        dt_ruku_cp_allModel.getState()});
            }
            db.execSQL("Insert Into dt_ruku_list ([uid],[addtime],[bianhao],[qiyeid],[shuliang],[qystaff],[ylint1],[ylint2]," +
                    "[ylint3],[ylint4],[ylstr1],[ylstr2],[ylstr3],[ylstr4],[state])  values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                    new Object[]{nowModel.getUid(), nowModel.getAddtime(), nowModel.getBianhao(), nowModel.getQiyeid(),
                            nowModel.getShuliang(), nowModel.getQystaff(), nowModel.getYlint1(), nowModel.getYlint2(), nowModel
                            .getYlint3(), nowModel.getYlint4(), nowModel.getYlstr1(), nowModel.getYlstr2(), nowModel.getYlstr3
                            (), nowModel.getYlstr4(), nowModel.getState()});
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
     * 修改入库数据的状态
     *
     * @param listModes
     * @return
     */
    public boolean update_ruku_listInfo(List<dt_ruku_list_all> listModes)
    {
        Boolean result = true;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        try
        {
            for (dt_ruku_list_all dt_ruku_list_allModel : listModes)
            {
                db.execSQL("update dt_ruku_list set  [addtime]=? ,[bianhao]=? ,[qiyeid]=? ,[shuliang]=? ,[qystaff]=? ," +
                        "[ylint1]=? ,[ylint2]=? ," + "[ylint3]=? ,[ylint4]=? ,[ylstr1]=? ,[ylstr2]=? ,[ylstr3]=? ,[ylstr4]=? ,"
                        + "[state]=?  where [uid]=? ", new Object[]{dt_ruku_list_allModel.getAddtime(), dt_ruku_list_allModel
                        .getBianhao(), dt_ruku_list_allModel.getQiyeid(), dt_ruku_list_allModel.getShuliang(),
                        dt_ruku_list_allModel.getQystaff(), dt_ruku_list_allModel.getYlint1(), dt_ruku_list_allModel.getYlint2
                        (), dt_ruku_list_allModel.getYlint3(), dt_ruku_list_allModel.getYlint4(), dt_ruku_list_allModel
                        .getYlstr1(), dt_ruku_list_allModel.getYlstr2(), dt_ruku_list_allModel.getYlstr3(),
                        dt_ruku_list_allModel.getYlstr4(), dt_ruku_list_allModel.getState(), dt_ruku_list_allModel.getUid()});
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
     * 修改一组入库组详情数据
     *
     * @param listModes
     * @return
     */
    public boolean update_ruku_cpInfo(List<dt_ruku_cp_all> listModes)
    {
        Boolean result = true;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        try
        {
            for (dt_ruku_cp_all dt_ruku_cp_allModel : listModes)
            {
                db.execSQL("update  dt_ruku_cp  set listuid = ?,  [listuid]= ?,[daima]= ?,[cpuid]= ?,[ylint1]= ?,[ylint2]= ?,"
                        + "[ylint3]= ?,[ylstr1]= ?," + "[ylstr2]= ?,[ylstr3]= ?,[hanliang]= ?,[jianshu]= ?,[state]= ? where " +
                        "uid=?" + " ", new Object[]{dt_ruku_cp_allModel.getListuid(), dt_ruku_cp_allModel.getDaima(),
                        dt_ruku_cp_allModel.getCpuid(), dt_ruku_cp_allModel.getYlint1(), dt_ruku_cp_allModel.getYlint2(),
                        dt_ruku_cp_allModel.getYlint3(), dt_ruku_cp_allModel.getYlstr1(), dt_ruku_cp_allModel.getYlstr2(),
                        dt_ruku_cp_allModel.getYlstr3(), dt_ruku_cp_allModel.getHanliang(), dt_ruku_cp_allModel.getJianshu(),
                        dt_ruku_cp_allModel.getState(), dt_ruku_cp_allModel.getUid()});
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
     * 得到入库组数据
     *
     * @param addtime1
     * @param addtime2
     * @param state    -1表示全部，0 未入库 ,1已入库
     * @return
     */
    public List<dt_ruku_list_all> get_ruku_listInfo(String addtime1, String addtime2, Integer state)
    {
        List<dt_ruku_list_all> listModels = new ArrayList<>();
        db = helper.getWritableDatabase();
        try
        {
            Cursor cursor;
            if (state == -1)
            {
                cursor = db.rawQuery("select * from dt_ruku_list where  addtime>=? and addtime< ?  ", new String[]{addtime1,
                        addtime2});
            } else
            {
                cursor = db.rawQuery("select * from dt_ruku_list where  addtime>=? and addtime< ? and state=? ", new
                        String[]{addtime1, addtime2, String.valueOf(state)});
            }
            while (cursor.moveToNext())
            {
                listModels.add(new dt_ruku_list_all(cursor.getString(cursor.getColumnIndex("uid")), cursor.getString(cursor
                        .getColumnIndex("addtime")), cursor.getString(cursor.getColumnIndex("bianhao")), DataUtils.getInteger
                        (cursor.getString(cursor.getColumnIndex("qiyeid"))), DataUtils.getInteger(cursor.getString(cursor
                        .getColumnIndex("shuliang"))), cursor.getString(cursor.getColumnIndex("qystaff")), DataUtils.getInteger
                        (cursor.getString(cursor.getColumnIndex("ylint1"))), DataUtils.getInteger(cursor.getString(cursor
                        .getColumnIndex("ylint2"))), DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("ylint3"))),
                        DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("ylint4"))), cursor.getString(cursor
                        .getColumnIndex("ylstr1")), cursor.getString(cursor.getColumnIndex("ylstr2")), cursor.getString(cursor
                        .getColumnIndex("ylstr3")), cursor.getString(cursor.getColumnIndex("ylstr4")), DataUtils.getInteger
                        (cursor.getString(cursor.getColumnIndex("state")))));

            }
        } catch (Exception e)
        {

        }
        db.close();
        return listModels;
    }

    /**
     * 得到一组入库组数据详情
     *
     * @return
     */
    public List<dt_ruku_cp_all> get_ruku_cpInfo(String listid)
    {
        List<dt_ruku_cp_all> listModels = new ArrayList<>();
        db = helper.getWritableDatabase();
        try
        {
            Cursor cursor = db.rawQuery("select a.*,b.[_cpmingzi]  from dt_ruku_cp  a left join dt_goods b on a" + "" + "" +
                    ".[daima]=b.[_cpdaima] where a.listuid=? ", new String[]{listid});
            while (cursor.moveToNext())
            {
                listModels.add(new dt_ruku_cp_all(cursor.getString(cursor.getColumnIndex("uid")), cursor.getString(cursor
                        .getColumnIndex("listuid")), cursor.getString(cursor.getColumnIndex("daima")), cursor.getString(cursor
                        .getColumnIndex("cpuid")), DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("ylint1"))),
                        DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("ylint2"))), DataUtils.getInteger(cursor
                        .getString(cursor.getColumnIndex("ylint3"))), cursor.getString(cursor.getColumnIndex("ylstr1")), cursor
                        .getString(cursor.getColumnIndex("ylstr2")), cursor.getString(cursor.getColumnIndex("ylstr3")), cursor
                        .getString(cursor.getColumnIndex("_cpmingzi")), cursor.getString(cursor.getColumnIndex("hanliang")),
                        DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("jianshu"))), DataUtils.getInteger(cursor
                        .getString(cursor.getColumnIndex("state")))));
            }
        } catch (Exception e)
        {

        }
        db.close();
        return listModels;
    }




    /*上传数据时使用****************************************************************/

    /**
     * 得到所有未入库组数据
     *
     * @return
     */
    public List<var_Syn_rukudan> get_ruku_listInfo0(String uid)
    {
        List<var_Syn_rukudan> listModels = new ArrayList<>();
        db = helper.getWritableDatabase();
        try
        {
            Cursor cursor;
            if (uid == null)
            {
                cursor = db.rawQuery("select a.*,b.[mingzi],b.[mima] from dt_ruku_list a left join dt_qiye b on a.[qiyeid]=b.[id] where  a.state=1 ", null);
            } else
            {
                cursor = db.rawQuery("select a.*,b.[mingzi],b.[mima] from dt_ruku_list a left join dt_qiye b on a.[qiyeid]=b.[id] where a.state=1 and  a.uid=? ", new String[]{uid});
            }
            while (cursor.moveToNext())
            {
                listModels.add(new var_Syn_rukudan(
                        cursor.getString(cursor.getColumnIndex("mingzi")),
                        cursor.getString(cursor.getColumnIndex("mima")),
                        new dt_ruku_list(
                                cursor.getString(cursor.getColumnIndex("uid")),
                                cursor.getString(cursor.getColumnIndex("addtime")),
                                cursor.getString(cursor.getColumnIndex("bianhao")),
                                DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("qiyeid"))),
                                DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("shuliang"))),
                                cursor.getString(cursor.getColumnIndex("qystaff")),
                                DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("ylint1"))),
                                DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("ylint2"))),
                                DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("ylint3"))),
                                DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("ylint4"))),
                                cursor.getString(cursor.getColumnIndex("ylstr1")),
                                cursor.getString(cursor.getColumnIndex("ylstr2")),
                                cursor.getString(cursor.getColumnIndex("ylstr3")),
                                cursor.getString(cursor.getColumnIndex("ylstr4")))
                        )
                );

            }
        } catch (Exception e)
        {
            listModels = null;
        }
        db.close();
        return listModels;
    }


    /**
     * 得到所有未入库组数据详情
     *
     * @return
     */
    public List<var_Syn_rukucp> get_ruku_cpInfo0(String uid)
    {
        List<var_Syn_rukucp> listModels = new ArrayList<>();
        db = helper.getWritableDatabase();
        try
        {
            Cursor cursor;
            if (uid == null)
            {
                cursor = db.rawQuery("select a.* ,c.[mingzi],c.[mima] from dt_ruku_cp  a left join dt_ruku_list b on a.[listuid]=b.[uid] left join dt_qiye c on b.[qiyeid]=c.[id] where a.state=1 ", null);
            } else
            {
                cursor = db.rawQuery("select a.* ,c.[mingzi],c.[mima] from dt_ruku_cp  a left join dt_ruku_list b on a.[listuid]=b.[uid] left join dt_qiye c on b.[qiyeid]=c.[id] where a.state=1   and a.uid=?", new String[]{uid});
            }
            while (cursor.moveToNext())
            {
                listModels.add(new var_Syn_rukucp(
                        cursor.getString(cursor.getColumnIndex("mingzi")),
                        cursor.getString(cursor.getColumnIndex("mima")),
                        new dt_ruku_cp(
                                cursor.getString(cursor.getColumnIndex("uid")),
                                cursor.getString(cursor.getColumnIndex("listuid")),
                                cursor.getString(cursor.getColumnIndex("daima")),
                                cursor.getString(cursor.getColumnIndex("cpuid")),
                                DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("ylint1"))),
                                DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("ylint2"))),
                                DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("ylint3"))),
                                cursor.getString(cursor.getColumnIndex("ylstr1")),
                                cursor.getString(cursor.getColumnIndex("ylstr2")),
                                cursor.getString(cursor.getColumnIndex("ylstr3")))
                        ));
            }
        } catch (Exception e)
        {
            listModels = null;
        }
        db.close();
        return listModels;
    }

    /**
     * 修改所有入库组数据
     *
     * @return
     */
    public boolean update_ruku_listInfo0(String uid)
    {
        Boolean result = true;
        db = helper.getWritableDatabase();
        try
        {
            if (uid == null)
            {
                db.execSQL("update  dt_ruku_list  set [state]=2 where [state]=1", null);
            } else
            {
                db.execSQL("update  dt_ruku_list  set [state]=2 where [state]=1 and uid=?", new String[]{uid});
            }
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
     * 修改所有入库组详情数据
     *
     * @return
     */
    public boolean update_ruku_cpInfo0(String uid)
    {
        Boolean result = true;
        db = helper.getWritableDatabase();
        try
        {
            if (uid == null)
            {
                db.execSQL("update  dt_ruku_cp  set [state]=2 where [state]=1", null);
            } else
            {
                db.execSQL("update  dt_ruku_cp  set [state]=2 where [state]=1 and uid=?", new String[]{uid});
            }
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
