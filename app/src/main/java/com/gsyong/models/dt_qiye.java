package com.gsyong.models;

/**
 * Created by Administrator on 2017-11-23.
 */

public class dt_qiye
{
    public Integer id;  //企业编号 唯一
    public String mingzi; //企业名字
    public String mima;   //企业密码
    public String dizhi;  //企业地址
    public String leixing;//企业类型  生产厂家/批发经营/零售经营
    public String fuzeren;//负责人
    public String fzrshenfenzheng;//负责人身份证
    public String fzrdianhua;//负责人电话
    public String yingyezhizhao;//营业执照
    public String yingyevld1;//营业执照有效期开始时间
    public String yingyevld2;//营业执照有效期结束时间
    public String nyxukehao;//农药许可证
    public String nyvld1;//农药许可证有效期开始时间
    public String nyvld2;//农药许可证有效期结束时间
    public String addtime;//企业注册时间
    public Integer status;//企业状态  1:正常 2:冻结
    public Integer provinceid;//省份ID
    public Integer cityid;//城市ID
    public Integer countyid;//区县ID
    public Integer townshipid;//街道ID 无用
    public Integer villageid;//居委会ID 无用
    public Integer ylint1;//预留
    public Integer ylint2;//预留
    public Integer ylint3;//预留
    public Integer ylint4;//预留
    public String ylstr1;//预留
    public String ylstr2;//预留
    public String ylstr3;//预留
    public String ylstr4;//预留

    public dt_qiye()
    {
    }

    public dt_qiye(Integer id, String mingzi, String mima, String dizhi, String leixing, String fuzeren, String
            fzrshenfenzheng, String fzrdianhua, String yingyezhizhao, String yingyevld1, String yingyevld2, String nyxukehao,
                   String nyvld1, String nyvld2, String addtime, Integer status, Integer provinceid, Integer cityid, Integer
                           countyid, Integer townshipid, Integer villageid, Integer ylint1, Integer ylint2, Integer ylint3,
                   Integer ylint4, String ylstr1, String ylstr2, String ylstr3, String ylstr4)
    {
        this.id = id;
        this.mingzi = mingzi;
        this.mima = mima;
        this.dizhi = dizhi;
        this.leixing = leixing;
        this.fuzeren = fuzeren;
        this.fzrshenfenzheng = fzrshenfenzheng;
        this.fzrdianhua = fzrdianhua;
        this.yingyezhizhao = yingyezhizhao;
        this.yingyevld1 = yingyevld1;
        this.yingyevld2 = yingyevld2;
        this.nyxukehao = nyxukehao;
        this.nyvld1 = nyvld1;
        this.nyvld2 = nyvld2;
        this.addtime = addtime;
        this.status = status;
        this.provinceid = provinceid;
        this.cityid = cityid;
        this.countyid = countyid;
        this.townshipid = townshipid;
        this.villageid = villageid;
        this.ylint1 = ylint1;
        this.ylint2 = ylint2;
        this.ylint3 = ylint3;
        this.ylint4 = ylint4;
        this.ylstr1 = ylstr1;
        this.ylstr2 = ylstr2;
        this.ylstr3 = ylstr3;
        this.ylstr4 = ylstr4;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getMingzi()
    {
        return mingzi;
    }

    public void setMingzi(String mingzi)
    {
        this.mingzi = mingzi;
    }

    public String getMima()
    {
        return mima;
    }

    public void setMima(String mima)
    {
        this.mima = mima;
    }

    public String getDizhi()
    {
        return dizhi;
    }

    public void setDizhi(String dizhi)
    {
        this.dizhi = dizhi;
    }

    public String getLeixing()
    {
        return leixing;
    }

    public void setLeixing(String leixing)
    {
        this.leixing = leixing;
    }

    public String getFuzeren()
    {
        return fuzeren;
    }

    public void setFuzeren(String fuzeren)
    {
        this.fuzeren = fuzeren;
    }

    public String getFzrshenfenzheng()
    {
        return fzrshenfenzheng;
    }

    public void setFzrshenfenzheng(String fzrshenfenzheng)
    {
        this.fzrshenfenzheng = fzrshenfenzheng;
    }

    public String getFzrdianhua()
    {
        return fzrdianhua;
    }

    public void setFzrdianhua(String fzrdianhua)
    {
        this.fzrdianhua = fzrdianhua;
    }

    public String getYingyezhizhao()
    {
        return yingyezhizhao;
    }

    public void setYingyezhizhao(String yingyezhizhao)
    {
        this.yingyezhizhao = yingyezhizhao;
    }

    public String getYingyevld1()
    {
        return yingyevld1;
    }

    public void setYingyevld1(String yingyevld1)
    {
        this.yingyevld1 = yingyevld1;
    }

    public String getYingyevld2()
    {
        return yingyevld2;
    }

    public void setYingyevld2(String yingyevld2)
    {
        this.yingyevld2 = yingyevld2;
    }

    public String getNyxukehao()
    {
        return nyxukehao;
    }

    public void setNyxukehao(String nyxukehao)
    {
        this.nyxukehao = nyxukehao;
    }

    public String getNyvld1()
    {
        return nyvld1;
    }

    public void setNyvld1(String nyvld1)
    {
        this.nyvld1 = nyvld1;
    }

    public String getNyvld2()
    {
        return nyvld2;
    }

    public void setNyvld2(String nyvld2)
    {
        this.nyvld2 = nyvld2;
    }

    public String getAddtime()
    {
        return addtime;
    }

    public void setAddtime(String addtime)
    {
        this.addtime = addtime;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getProvinceid()
    {
        return provinceid;
    }

    public void setProvinceid(Integer provinceid)
    {
        this.provinceid = provinceid;
    }

    public Integer getCityid()
    {
        return cityid;
    }

    public void setCityid(Integer cityid)
    {
        this.cityid = cityid;
    }

    public Integer getCountyid()
    {
        return countyid;
    }

    public void setCountyid(Integer countyid)
    {
        this.countyid = countyid;
    }

    public Integer getTownshipid()
    {
        return townshipid;
    }

    public void setTownshipid(Integer townshipid)
    {
        this.townshipid = townshipid;
    }

    public Integer getVillageid()
    {
        return villageid;
    }

    public void setVillageid(Integer villageid)
    {
        this.villageid = villageid;
    }

    public Integer getYlint1()
    {
        return ylint1;
    }

    public void setYlint1(Integer ylint1)
    {
        this.ylint1 = ylint1;
    }

    public Integer getYlint2()
    {
        return ylint2;
    }

    public void setYlint2(Integer ylint2)
    {
        this.ylint2 = ylint2;
    }

    public Integer getYlint3()
    {
        return ylint3;
    }

    public void setYlint3(Integer ylint3)
    {
        this.ylint3 = ylint3;
    }

    public Integer getYlint4()
    {
        return ylint4;
    }

    public void setYlint4(Integer ylint4)
    {
        this.ylint4 = ylint4;
    }

    public String getYlstr1()
    {
        return ylstr1;
    }

    public void setYlstr1(String ylstr1)
    {
        this.ylstr1 = ylstr1;
    }

    public String getYlstr2()
    {
        return ylstr2;
    }

    public void setYlstr2(String ylstr2)
    {
        this.ylstr2 = ylstr2;
    }

    public String getYlstr3()
    {
        return ylstr3;
    }

    public void setYlstr3(String ylstr3)
    {
        this.ylstr3 = ylstr3;
    }

    public String getYlstr4()
    {
        return ylstr4;
    }

    public void setYlstr4(String ylstr4)
    {
        this.ylstr4 = ylstr4;
    }
}
