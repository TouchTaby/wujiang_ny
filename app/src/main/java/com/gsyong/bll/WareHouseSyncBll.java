package com.gsyong.bll;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gsyong.model.dt_chuku_cp_1;
import com.gsyong.model.dt_chuku_list_1;
import com.gsyong.model.dt_ruku_cp_1;
import com.gsyong.model.dt_ruku_list_1;


import java.util.ArrayList;
import java.util.List;

import commonutils.DataUtils;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/27 19:48
 * 修改人：YI
 * 修改时间：2018/1/27 19:48
 * 修改备注：
 */
public class WareHouseSyncBll
{
    private SQLiteDatabase db;
    private DBHelper helper;

    public WareHouseSyncBll(Context context)
    {
        helper = new DBHelper(context);
    }


     /*上传入库数据时使用****************************************************************/

    /**
     * 得到所有未入库组数据
     *
     * @return
     */
    public List<dt_ruku_list_1> get_ruku_listInfo0(String uid)
    {
        List<dt_ruku_list_1> listModels = new ArrayList<>();
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
                listModels.add(new dt_ruku_list_1(
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
                                        cursor.getString(cursor.getColumnIndex("ylstr4"))

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
     * 得到所有未入库组数据详情
     *
     * @return
     */
    public List<dt_ruku_cp_1> get_ruku_cpInfo0(String uid)
    {
        List<dt_ruku_cp_1> listModels = new ArrayList<>();
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
                listModels.add(new dt_ruku_cp_1(
                                cursor.getString(cursor.getColumnIndex("uid")),
                                cursor.getString(cursor.getColumnIndex("listuid")),
                                cursor.getString(cursor.getColumnIndex("daima")),
                                cursor.getString(cursor.getColumnIndex("cpuid")),
                                DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("ylint1"))),
                                DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("ylint2"))),
                                DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("ylint3"))),
                                cursor.getString(cursor.getColumnIndex("ylstr1")),
                                cursor.getString(cursor.getColumnIndex("ylstr2")),
                                cursor.getString(cursor.getColumnIndex("ylstr3"))))
                ;
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

    /*上传出库数据时使用****************************************************************/

    /**
     * 得到所有未出库组数据
     *
     * @return
     */
    public List<dt_chuku_list_1> get_chuku_listInfo0(String uid)
    {
        List<dt_chuku_list_1> listModels = new ArrayList<>();
        db = helper.getWritableDatabase();
        try
        {
            Cursor cursor;
            if (uid == null)
            {
                cursor = db.rawQuery("select a.*,b.[mingzi],b.[mima] from dt_chuku_list a left join  dt_qiye b on a.[qiyeid] =b.[id] where  a.state=1 ", null);
            } else
            {
                cursor = db.rawQuery("select a.*,b.[mingzi],b.[mima] from dt_chuku_list a left join  dt_qiye b on a.[qiyeid] =b.[id] where  a.state=1  and  a.uid=? ", new String[]{uid});
            }
            while (cursor.moveToNext())
            {
                listModels.add( new dt_chuku_list_1(
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
                                        cursor.getString(cursor.getColumnIndex("ylstr4"))

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
     * 得到所有未出库组数据详情
     *
     * @return
     */
    public List<dt_chuku_cp_1> get_chuku_cpInfo0(String uid)
    {
        List<dt_chuku_cp_1> listModels = new ArrayList<>();
        db = helper.getWritableDatabase();
        try
        {
            Cursor cursor;
            if (uid == null)
            {
                cursor = db.rawQuery("select a.* ,c.[mingzi],c.[mima] from dt_chuku_cp  a left join dt_chuku_list b on a.[listuid]=b.[uid] left join dt_qiye c on b.[qiyeid]=c.[id] where a.state=1 ", null);
            } else
            {
                cursor = db.rawQuery("select a.* ,c.[mingzi],c.[mima] from dt_chuku_cp  a left join dt_chuku_list b on a.[listuid]=b.[uid] left join dt_qiye c on b.[qiyeid]=c.[id] where a.state=1 and  a.uid=? ", new String[]{uid});
            }
            while (cursor.moveToNext())
            {
                listModels.add(new dt_chuku_cp_1(cursor.getString(cursor.getColumnIndex("uid")),
                                cursor.getString(cursor.getColumnIndex("listuid")),
                                cursor.getString(cursor.getColumnIndex("daima")),
                                cursor.getString(cursor.getColumnIndex("cpuid")),
                                DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("ylint1"))),
                                DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("ylint2"))),
                                DataUtils.getInteger(cursor.getString(cursor.getColumnIndex("ylint3"))),
                                cursor.getString(cursor.getColumnIndex("ylstr1")),
                                cursor.getString(cursor.getColumnIndex("ylstr2")),
                                cursor.getString(cursor.getColumnIndex("ylstr3")))
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
     * 修改所有出库组数据
     *
     * @return
     */
    public boolean update_chuku_listInfo0(String uid)
    {
        Boolean result = true;
        db = helper.getWritableDatabase();
        try
        {
            Cursor cursor;
            if (uid == null)
            {
                db.execSQL("update  dt_chuku_list  set [state]=2 where [state]=1", null);
            } else
            {
                db.execSQL("update  dt_chuku_list  set [state]=2 where [state]=1 and  uid=?",  new String[]{uid});
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
     * 修改所有出库组详情数据
     *
     * @return
     */
    public boolean update_chuku_cpInfo0(String uid)
    {
        Boolean result = true;
        db = helper.getWritableDatabase();
        try
        {
            Cursor cursor;
            if (uid == null)
            {
                db.execSQL("update  dt_chuku_cp  set [state]=2 where [state]=1", null);
            }else
            {
                db.execSQL("update  dt_chuku_cp  set [state]=2 where [state]=1 and uid=?", new String[]{uid});
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
