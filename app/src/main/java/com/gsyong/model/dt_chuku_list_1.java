package com.gsyong.model;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/18 11:05
 * 修改人：YI
 * 修改时间：2018/1/18 11:05
 * 修改备注：
 */
public class dt_chuku_list_1
{
    public String uid;//GUID 32位唯一字符串 不带符号，字母小写  如：077a2585e54044b1bbfd112be6371f24
    public String addtime;//创建时间
    public String bianhao;//出库单编号 可以使用 yyyyMMddHHmmssffff 格式化日期来得到
    public Integer qiyeid;//关联的企业ID
    public Integer shuliang;//出库单包含的 流向码个数  总数
    public String qystaff;//员工ID 只针对PC机  CE端：直接赋值 winCe  android端：直接赋值android
    public Integer ylint1=0;//预留
    public Integer ylint2=0;//预留
    public Integer ylint3=0;//预留
    public Integer ylint4=0;//预留
    public String ylstr1 = "";//预留
    public String ylstr2 = "";//预留
    public String ylstr3 = "";//预留
    public String ylstr4 = "";//预留

    public dt_chuku_list_1()
    {
    }

    public dt_chuku_list_1(String uid, String addtime, String bianhao, Integer qiyeid, Integer shuliang, String qystaff, Integer
            ylint1, Integer ylint2, Integer ylint3, Integer ylint4, String ylstr1, String ylstr2, String ylstr3, String ylstr4)
    {
        this.uid = uid;
        this.addtime = addtime;
        this.bianhao = bianhao;
        this.qiyeid = qiyeid;
        this.shuliang = shuliang;
        this.qystaff = qystaff;
        this.ylint1 = ylint1;
        this.ylint2 = ylint2;
        this.ylint3 = ylint3;
        this.ylint4 = ylint4;
        this.ylstr1 = ylstr1;
        this.ylstr2 = ylstr2;
        this.ylstr3 = ylstr3;
        this.ylstr4 = ylstr4;
    }

    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getAddtime()
    {
        return addtime;
    }

    public void setAddtime(String addtime)
    {
        this.addtime = addtime;
    }

    public String getBianhao()
    {
        return bianhao;
    }

    public void setBianhao(String bianhao)
    {
        this.bianhao = bianhao;
    }

    public Integer getQiyeid()
    {
        return qiyeid;
    }

    public void setQiyeid(Integer qiyeid)
    {
        this.qiyeid = qiyeid;
    }

    public Integer getShuliang()
    {
        return shuliang;
    }

    public void setShuliang(Integer shuliang)
    {
        this.shuliang = shuliang;
    }

    public String getQystaff()
    {
        return qystaff;
    }

    public void setQystaff(String qystaff)
    {
        this.qystaff = qystaff;
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
