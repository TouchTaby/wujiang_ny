package com.gsyong.models;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/18 11:05
 * 修改人：YI
 * 修改时间：2018/1/18 11:05
 * 修改备注：
 */
public class dt_ruku_cp
{
    public String _uid; //GUID 32位唯一字符串 不带符号，字母小写  如：077a2585e54044b1bbfd112be6371f24
    public String _listuid;//关联入库单的GUID
    public String _daima;//产品的流向码 非限制农药 保存流向码前11位，限制农药保存完整流向码
    public String _cpuid;//已无效
    public int _ylint1=0;//入库数量 限制农药只能为1, 箱数x含量
    public int _ylint2=0;//预留
    public int _ylint3=0;//预留
    public String _ylstr1 = "";//预留
    public String _ylstr2 = "";//预留
    public String _ylstr3 = "";//预留

    public dt_ruku_cp()
    {
    }

    public dt_ruku_cp(String _uid, String _listuid, String _daima, String _cpuid, int _ylint1, int _ylint2, int _ylint3, String
            _ylstr1, String _ylstr2, String _ylstr3)
    {
        this._uid = _uid;
        this._listuid = _listuid;
        this._daima = _daima;
        this._cpuid = _cpuid;
        this._ylint1 = _ylint1;
        this._ylint2 = _ylint2;
        this._ylint3 = _ylint3;
        this._ylstr1 = _ylstr1;
        this._ylstr2 = _ylstr2;
        this._ylstr3 = _ylstr3;
    }

    public String get_uid()
    {
        return _uid;
    }

    public void set_uid(String _uid)
    {
        this._uid = _uid;
    }

    public String get_listuid()
    {
        return _listuid;
    }

    public void set_listuid(String _listuid)
    {
        this._listuid = _listuid;
    }

    public String get_daima()
    {
        return _daima;
    }

    public void set_daima(String _daima)
    {
        this._daima = _daima;
    }

    public String get_cpuid()
    {
        return _cpuid;
    }

    public void set_cpuid(String _cpuid)
    {
        this._cpuid = _cpuid;
    }

    public int get_ylint1()
    {
        return _ylint1;
    }

    public void set_ylint1(int _ylint1)
    {
        this._ylint1 = _ylint1;
    }

    public int get_ylint2()
    {
        return _ylint2;
    }

    public void set_ylint2(int _ylint2)
    {
        this._ylint2 = _ylint2;
    }

    public int get_ylint3()
    {
        return _ylint3;
    }

    public void set_ylint3(int _ylint3)
    {
        this._ylint3 = _ylint3;
    }

    public String get_ylstr1()
    {
        return _ylstr1;
    }

    public void set_ylstr1(String _ylstr1)
    {
        this._ylstr1 = _ylstr1;
    }

    public String get_ylstr2()
    {
        return _ylstr2;
    }

    public void set_ylstr2(String _ylstr2)
    {
        this._ylstr2 = _ylstr2;
    }

    public String get_ylstr3()
    {
        return _ylstr3;
    }

    public void set_ylstr3(String _ylstr3)
    {
        this._ylstr3 = _ylstr3;
    }
}
