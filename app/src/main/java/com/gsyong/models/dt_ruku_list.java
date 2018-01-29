package com.gsyong.models;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/18 11:04
 * 修改人：YI
 * 修改时间：2018/1/18 11:04
 * 修改备注：
 */
public class dt_ruku_list
{
    public String _uid; //GUID 32位唯一字符串 不带符号，字母小写  如：077a2585e54044b1bbfd112be6371f24
    public String _addtime;//创建时间
    public String _bianhao;//入库单编号 可以使用 yyyyMMddHHmmssffff 格式化日期来得到
    public Integer _qiyeid;   //关联的企业ID
    public Integer _shuliang; //入库单包含的 流向码个数
    public String _qystaff; //员工ID 只针对PC机  CE端：直接赋值 winCe  android端：直接赋值android
    public Integer _ylint1 = 0; //预留
    public Integer _ylint2 = 0;//预留
    public Integer _ylint3 = 0;//预留
    public Integer _ylint4 = 0;//预留
    public String _ylstr1 = "";//预留
    public String _ylstr2 = "";//预留
    public String _ylstr3 = "";//预留
    public String _ylstr4 = "";//预留

    public dt_ruku_list()
    {
    }

    public dt_ruku_list(String _uid, String _addtime, String _bianhao, Integer _qiyeid, Integer _shuliang, String _qystaff,
                        Integer _ylint1, Integer _ylint2, Integer _ylint3, Integer _ylint4, String _ylstr1, String _ylstr2,
                        String _ylstr3, String _ylstr4)
    {
        this._uid = _uid;
        this._addtime = _addtime;
        this._bianhao = _bianhao;
        this._qiyeid = _qiyeid;
        this._shuliang = _shuliang;
        this._qystaff = _qystaff;
        this._ylint1 = _ylint1;
        this._ylint2 = _ylint2;
        this._ylint3 = _ylint3;
        this._ylint4 = _ylint4;
        this._ylstr1 = _ylstr1;
        this._ylstr2 = _ylstr2;
        this._ylstr3 = _ylstr3;
        this._ylstr4 = _ylstr4;
    }

    public String get_uid()
    {
        return _uid;
    }

    public void set_uid(String _uid)
    {
        this._uid = _uid;
    }

    public String get_addtime()
    {
        return _addtime;
    }

    public void set_addtime(String _addtime)
    {
        this._addtime = _addtime;
    }

    public String get_bianhao()
    {
        return _bianhao;
    }

    public void set_bianhao(String _bianhao)
    {
        this._bianhao = _bianhao;
    }

    public Integer get_qiyeid()
    {
        return _qiyeid;
    }

    public void set_qiyeid(Integer _qiyeid)
    {
        this._qiyeid = _qiyeid;
    }

    public Integer get_shuliang()
    {
        return _shuliang;
    }

    public void set_shuliang(Integer _shuliang)
    {
        this._shuliang = _shuliang;
    }

    public String get_qystaff()
    {
        return _qystaff;
    }

    public void set_qystaff(String _qystaff)
    {
        this._qystaff = _qystaff;
    }

    public Integer get_ylint1()
    {
        return _ylint1;
    }

    public void set_ylint1(Integer _ylint1)
    {
        this._ylint1 = _ylint1;
    }

    public Integer get_ylint2()
    {
        return _ylint2;
    }

    public void set_ylint2(Integer _ylint2)
    {
        this._ylint2 = _ylint2;
    }

    public Integer get_ylint3()
    {
        return _ylint3;
    }

    public void set_ylint3(Integer _ylint3)
    {
        this._ylint3 = _ylint3;
    }

    public Integer get_ylint4()
    {
        return _ylint4;
    }

    public void set_ylint4(Integer _ylint4)
    {
        this._ylint4 = _ylint4;
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

    public String get_ylstr4()
    {
        return _ylstr4;
    }

    public void set_ylstr4(String _ylstr4)
    {
        this._ylstr4 = _ylstr4;
    }
}
