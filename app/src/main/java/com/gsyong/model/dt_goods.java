package com.gsyong.model;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/20 22:18
 * 修改人：YI
 * 修改时间：2018/1/20 22:18
 * 修改备注：
 */
public class dt_goods
{
     private  String _uid;
     private  String _addtime;
     private  String _cpchangjia;
     private  String _cpdaima;//农药流向码
     private  Double _cpjiage;
     private  String _cpleixing;
     private  Integer _cplock;
     private  String _cpmingzi;
     private  String _cppici;
     private  String _cpurl;
     private  Integer _status;
     private  Integer _ylint1;
     private  Integer _ylint2;
     private  Integer _ylint3;
     private  Integer _ylint4;
     private  String _ylstr1;
     private  String _ylstr2;//农药证号
     private  String _ylstr3;
     private  String _ylstr4;

     public dt_goods()
     {
     }

     public dt_goods(String _uid, String _addtime, String _cpchangjia, String _cpdaima, Double _cpjiage, String _cpleixing,
                     Integer _cplock, String _cpmingzi, String _cppici, String _cpurl, Integer _status, Integer _ylint1,
                     Integer _ylint2, Integer _ylint3, Integer _ylint4, String _ylstr1, String _ylstr2, String _ylstr3, String
                             _ylstr4)
     {
          this._uid = _uid;
          this._addtime = _addtime;
          this._cpchangjia = _cpchangjia;
          this._cpdaima = _cpdaima;
          this._cpjiage = _cpjiage;
          this._cpleixing = _cpleixing;
          this._cplock = _cplock;
          this._cpmingzi = _cpmingzi;
          this._cppici = _cppici;
          this._cpurl = _cpurl;
          this._status = _status;
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

     public String get_cpmingzi()
     {
          return _cpmingzi;
     }

     public void set_cpmingzi(String _cpmingzi)
     {
          this._cpmingzi = _cpmingzi;
     }

     public String get_cpdaima()
     {
          return _cpdaima;
     }

     public void set_cpdaima(String _cpdaima)
     {
          this._cpdaima = _cpdaima;
     }

     public String get_cpchangjia()
     {
          return _cpchangjia;
     }

     public void set_cpchangjia(String _cpchangjia)
     {
          this._cpchangjia = _cpchangjia;
     }

     public Double get_cpjiage()
     {
          return _cpjiage;
     }

     public void set_cpjiage(Double _cpjiage)
     {
          this._cpjiage = _cpjiage;
     }

     public String get_cpleixing()
     {
          return _cpleixing;
     }

     public void set_cpleixing(String _cpleixing)
     {
          this._cpleixing = _cpleixing;
     }

     public Integer get_cplock()
     {
          return _cplock;
     }

     public void set_cplock(Integer _cplock)
     {
          this._cplock = _cplock;
     }

     public String get_cppici()
     {
          return _cppici;
     }

     public void set_cppici(String _cppici)
     {
          this._cppici = _cppici;
     }

     public String get_cpurl()
     {
          return _cpurl;
     }

     public void set_cpurl(String _cpurl)
     {
          this._cpurl = _cpurl;
     }

     public Integer get_status()
     {
          return _status;
     }

     public void set_status(Integer _status)
     {
          this._status = _status;
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
