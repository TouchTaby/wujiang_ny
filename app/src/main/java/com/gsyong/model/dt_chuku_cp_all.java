package com.gsyong.model;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/20 11:00
 * 修改人：YI
 * 修改时间：2018/1/20 11:00
 * 修改备注：
 */
public class dt_chuku_cp_all extends dt_chuku_cp_1
{
    public String mingcheng;
    public String hanliang;
    public Integer jianshu;
    public Integer state;//0 未出库 ,1已出库,2已上传

    public dt_chuku_cp_all()
    {
    }

    public dt_chuku_cp_all(String uid, String listuid, String daima, String cpuid, Integer ylint1, Integer ylint2, Integer
            ylint3, String ylstr1, String ylstr2, String ylstr3, String mingcheng, String hanliang, Integer jianshu, Integer
            state)
    {
        super(uid, listuid, daima, cpuid, ylint1, ylint2, ylint3, ylstr1, ylstr2, ylstr3);
        this.mingcheng = mingcheng;
        this.hanliang = hanliang;
        this.jianshu = jianshu;
        this.state = state;
    }

    public String getMingcheng()
    {
        return mingcheng;
    }

    public void setMingcheng(String mingcheng)
    {
        this.mingcheng = mingcheng;
    }

    public String getHanliang()
    {
        return hanliang;
    }

    public void setHanliang(String hanliang)
    {
        this.hanliang = hanliang;
    }

    public Integer getJianshu()
    {
        return jianshu;
    }

    public void setJianshu(Integer jianshu)
    {
        this.jianshu = jianshu;
    }

    public Integer getState()
    {
        return state;
    }

    public void setState(Integer state)
    {
        this.state = state;
    }
}
