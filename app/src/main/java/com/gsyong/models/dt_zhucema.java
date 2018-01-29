package com.gsyong.models;

/**
 * Created by Administrator on 2017-11-23.
 */

public class dt_zhucema
{

    public Integer id; //数据编号 唯一
    public Integer qiyeid; //关联企业ID
    public String zhucema;//注册码 10位纯数字
    public String addtime;//添加时间
    public String valtime;//有效期 当时间大于有效期则代表这个数据已经过期应该要求设备重新注册
    public String pcmac;//设备编号 唯一
    public String fangshi;//注册码支付方式
    public Integer zhuangtai;//状态 0:未使用 1:已使用 2:已注销  当状态为 1 （已使用）时则要判断 设备编码是否有当前注册机一致 如果不一致则不能写入授权信息 提示已经绑定。 当状态为 2 （已注销） 则提示注册码失效
    public Integer ylint1;//预留
    public Integer ylint2;//预留
    public Integer ylint3;//预留
    public String ylstr1;//预留
    public String ylstr2;//预留
    public String ylstr3;//预留
    public Double jine;//支付金额

    public dt_zhucema()
    {
    }

    public dt_zhucema(Integer id, Integer qiyeid, String zhucema, String addtime, String valtime, String pcmac, String fangshi,
                      Integer zhuangtai, Integer ylint1, Integer ylint2, Integer ylint3, String ylstr1, String ylstr2, String
                              ylstr3, Double jine)
    {
        this.id = id;
        this.qiyeid = qiyeid;
        this.zhucema = zhucema;
        this.addtime = addtime;
        this.valtime = valtime;
        this.pcmac = pcmac;
        this.fangshi = fangshi;
        this.zhuangtai = zhuangtai;
        this.ylint1 = ylint1;
        this.ylint2 = ylint2;
        this.ylint3 = ylint3;
        this.ylstr1 = ylstr1;
        this.ylstr2 = ylstr2;
        this.ylstr3 = ylstr3;
        this.jine = jine;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getQiyeid()
    {
        return qiyeid;
    }

    public void setQiyeid(Integer qiyeid)
    {
        this.qiyeid = qiyeid;
    }

    public String getZhucema()
    {
        return zhucema;
    }

    public void setZhucema(String zhucema)
    {
        this.zhucema = zhucema;
    }

    public String getAddtime()
    {
        return addtime;
    }

    public void setAddtime(String addtime)
    {
        this.addtime = addtime;
    }

    public String getValtime()
    {
        return valtime;
    }

    public void setValtime(String valtime)
    {
        this.valtime = valtime;
    }

    public String getPcmac()
    {
        return pcmac;
    }

    public void setPcmac(String pcmac)
    {
        this.pcmac = pcmac;
    }

    public String getFangshi()
    {
        return fangshi;
    }

    public void setFangshi(String fangshi)
    {
        this.fangshi = fangshi;
    }

    public Integer getZhuangtai()
    {
        return zhuangtai;
    }

    public void setZhuangtai(Integer zhuangtai)
    {
        this.zhuangtai = zhuangtai;
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

    public Double getJine()
    {
        return jine;
    }

    public void setJine(Double jine)
    {
        this.jine = jine;
    }
}
