package com.gsyong.bll;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/19 15:19
 * 修改人：YI
 * 修改时间：2018/1/19 15:19
 * 修改备注：
 */
public class DBHelper extends SQLiteOpenHelper
{
    private final static int VERSION = 1;
    private final static String DBNAME = "ny.db";

    public DBHelper(Context context)
    {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            createDb(db);
        } catch (Exception e)
        {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (oldVersion < newVersion)
        {
            Cursor cursor = db.rawQuery("select name from sqlite_master where type='table' order by name", null);
            while (cursor.moveToNext())
            {
                // 遍历出表名
                String name = cursor.getString(0);
                db.execSQL("DROP TABLE " + name);
            }
            createDb(db);
        }
    }

    private void createDb(SQLiteDatabase db)
    {
        try
        {
            db.execSQL("Create  TABLE dt_zhucema([id] Integer PRIMARY KEY,[qiyeid] integer,[zhucema] nvarchar(200),[addtime] "
                    + "nvarchar(200),[valtime] nvarchar(200),[pcmac] nvarchar(200),[fangshi] nvarchar(200),[zhuangtai] integer," +
                    "" + "[ylint1] Integer,[ylint2] Integer,[ylint3] Integer,[ylstr1] nvarchar(200),[ylstr2] nvarchar(200)," +
                    "[ylstr3] " + "nvarchar(200),[jine] decimal)");

            db.execSQL("Create  TABLE dt_qiye([id] integer PRIMARY KEY,[mingzi] nvarchar(200),[mima] nvarchar(200),[dizhi] " +
                    "nvarchar(200),[leixing] nvarchar(200),[fuzeren] nvarchar(200),[fzrshenfenzheng] nvarchar(200)," +
                    "[fzrdianhua]" + " nvarchar(200),[yingyezhizhao] nvarchar(200),[yingyevld1] nvarchar(200),[yingyevld2] " +
                    "nvarchar(200)," + "[nyxukehao] nvarchar(200),[nyvld1] nvarchar(200),[nyvld2] nvarchar(200),[addtime] " +
                    "nvarchar(200),[status] " + "integer,[provinceid] integer,[cityid] integer,[countyid] integer,[townshipid] " +
                    "integer,[villageid] integer," + "[ylint1] integer,[ylint2] integer,[ylint3] integer,[ylint4] integer," +
                    "[ylstr1] nvarchar(200),[ylstr2] " + "nvarchar(200),[ylstr3] nvarchar(200),[ylstr4] nvarchar(200))");

            db.execSQL("Create  TABLE dt_goods([_uid] nvarchar(200) PRIMARY KEY,[_addtime] nvarchar(200),[_cpmingzi] nvarchar" +
                    "(200),[_cpdaima] nvarchar(200),[_cpchangjia] nvarchar(200),[_cpjiage] decimal,[_cpleixing] nvarchar(200)," +
                    "[_cplock] Integer,[_cppici] nvarchar(200),[_cpurl] nvarchar(200),[_status] Integer,[_ylint1] Integer," +
                    "[_ylint2] Integer,[_ylint3] Integer,[_ylint4] Integer,[_ylstr1] nvarchar(200),[_ylstr2] nvarchar(200)," +
                    "[_ylstr3] nvarchar(200),[_ylstr4] nvarchar(200))");

            db.execSQL("Create  TABLE dt_chuku_list([uid] nvarchar(200) PRIMARY KEY,[addtime] nvarchar(200),[bianhao] nvarchar"
                    + "(200),[qiyeid] Integer,[shuliang] Integer,[qystaff] nvarchar(200),[ylint1] Integer,[ylint2] Integer," +
                    "[ylint3] Integer,[ylint4] Integer,[ylstr1] nvarchar(200),[ylstr2] nvarchar(200),[ylstr3] nvarchar(200)," +
                    "[ylstr4] nvarchar(200),[state] Integer,[shouhuodanwei] nvarchar(200))");

            db.execSQL("Create  TABLE dt_chuku_cp([uid] nvarchar(200) PRIMARY KEY ,[listuid] nvarchar(200),[daima] nvarchar" +
                    "(200),[cpuid] nvarchar(200),[ylint1] Integer,[ylint2] Integer,[ylint3] Integer,[ylstr1] nvarchar(200)," +
                    "[ylstr2] nvarchar(200),[ylstr3] nvarchar(200),[hanliang] nvarchar(200),[jianshu] Integer,[state] Integer)");

            db.execSQL("Create  TABLE dt_ruku_list([uid] nvarchar(200) PRIMARY KEY,[addtime] nvarchar(200),[bianhao] nvarchar"
                    + "(200),[qiyeid] integer,[shuliang] integer,[qystaff] nvarchar(200),[ylint1] integer,[ylint2] integer," +
                    "[ylint3] integer,[ylint4] integer,[ylstr1] nvarchar(200),[ylstr2] nvarchar(200),[ylstr3] nvarchar(200)," +
                    "[ylstr4] nvarchar(200),[state] Integer)");

            db.execSQL("Create  TABLE dt_ruku_cp([uid] nvarchar(200) PRIMARY KEY,[listuid] nvarchar(200),[daima] nvarchar(200)," +
                    "" + "[cpuid] nvarchar(200),[ylint1] integer,[ylint2] integer,[ylint3] integer,[ylstr1] nvarchar(200)," +
                    "[ylstr2] " + "nvarchar(200),[ylstr3] nvarchar(200),[hanliang] nvarchar(200),[jianshu] Integer,[state] " +
                    "Integer)");

        } catch (Exception e)
        {
            String mm = e.toString();
        }

    }
}
