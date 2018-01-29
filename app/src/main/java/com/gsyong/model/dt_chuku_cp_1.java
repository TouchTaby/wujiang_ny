package com.gsyong.model;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/18 11:06
 * 修改人：YI
 * 修改时间：2018/1/18 11:06
 * 修改备注：
 */
public class dt_chuku_cp_1
{
    public String uid; //GUID 32位唯一字符串 不带符号，字母小写  如：077a2585e54044b1bbfd112be6371f24
    public String listuid;//关联出库单的GUID
    public String daima;//产品的流向码 非限制农药 保存流向码前11位，限制农药保存完整流向码
    public String cpuid;//已无效
    public Integer ylint1 = 0;//出库数量 限制农药只能为1,箱数x含量
    public Integer ylint2 = 0;//预留
    public Integer ylint3 = 0;//预留
    public String ylstr1 = "";//预留
    public String ylstr2 = "";//预留
    public String ylstr3 = "";//预留

    public dt_chuku_cp_1()
    {
    }

    public dt_chuku_cp_1(String uid, String listuid, String daima, String cpuid, Integer ylint1, Integer ylint2, Integer ylint3,
                         String ylstr1, String ylstr2, String ylstr3)
    {
        this.uid = uid;
        this.listuid = listuid;
        this.daima = daima;
        this.cpuid = cpuid;
        this.ylint1 = ylint1;
        this.ylint2 = ylint2;
        this.ylint3 = ylint3;
        this.ylstr1 = ylstr1;
        this.ylstr2 = ylstr2;
        this.ylstr3 = ylstr3;
    }

    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getListuid()
    {
        return listuid;
    }

    public void setListuid(String listuid)
    {
        this.listuid = listuid;
    }

    public String getDaima()
    {
        return daima;
    }

    public void setDaima(String daima)
    {
        this.daima = daima;
    }

    public String getCpuid()
    {
        return cpuid;
    }

    public void setCpuid(String cpuid)
    {
        this.cpuid = cpuid;
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
}
