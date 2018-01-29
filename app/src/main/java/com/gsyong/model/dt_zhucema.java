package com.gsyong.model;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/19 12:40
 * 修改人：YI
 * 修改时间：2018/1/19 12:40
 * 修改备注：
 */
public class dt_zhucema
{
    public String id; //数据编号 唯一
    public String qiyeid; //关联企业ID
    public String zhucema;//注册码 10位纯数字
    public String addtime;//添加时间
    public String valtime;//有效期 当时间大于有效期则代表这个数据已经过期应该要求设备重新注册
    public String pcmac;//设备编号 唯一
    public String fangshi;//注册码支付方式
    public String zhuangtai;//状态 0:未使用 1:已使用 2:已注销  当状态为 1 （已使用）时则要判断 设备编码是否有当前注册机一致 如果不一致则不能写入授权信息 提示已经绑定。 当状态为 2 （已注销） 则提示注册码失效
    public String ylint1;//预留
    public String ylint2;//预留
    public String ylint3;//预留
    public String ylstr1;//预留
    public String ylstr2;//预留
    public String ylstr3;//预留
    public String jine;//支付金额

    public dt_zhucema()
    {
    }

    public dt_zhucema(String id, String qiyeid, String zhucema, String addtime, String valtime, String pcmac, String
            fangshi, String zhuangtai, String ylint1, String ylint2, String ylint3, String ylstr1, String ylstr2, String
            ylstr3, String jine)
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

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getQiyeid()
    {
        return qiyeid;
    }

    public void setQiyeid(String qiyeid)
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

    public String getZhuangtai()
    {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai)
    {
        this.zhuangtai = zhuangtai;
    }

    public String getYlint1()
    {
        return ylint1;
    }

    public void setYlint1(String ylint1)
    {
        this.ylint1 = ylint1;
    }

    public String getYlint2()
    {
        return ylint2;
    }

    public void setYlint2(String ylint2)
    {
        this.ylint2 = ylint2;
    }

    public String getYlint3()
    {
        return ylint3;
    }

    public void setYlint3(String ylint3)
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

    public String getJine()
    {
        return jine;
    }

    public void setJine(String jine)
    {
        this.jine = jine;
    }
}
