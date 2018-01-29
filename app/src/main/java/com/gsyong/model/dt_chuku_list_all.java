package com.gsyong.model;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/20 11:09
 * 修改人：YI
 * 修改时间：2018/1/20 11:09
 * 修改备注：
 */
public class dt_chuku_list_all extends dt_chuku_list_1
{
    public Integer state;//0 未出库 ,1已出库,2已上传

    public  String shouhuodanwei;//收货单位

    public dt_chuku_list_all()
    {

    }

    public dt_chuku_list_all(String uid, String addtime, String bianhao, Integer qiyeid, Integer shuliang, String qystaff,
                             Integer ylint1, Integer ylint2, Integer ylint3, Integer ylint4, String ylstr1, String ylstr2,
                             String ylstr3, String ylstr4, Integer state, String shouhuodanwei)
    {
        super(uid, addtime, bianhao, qiyeid, shuliang, qystaff, ylint1, ylint2, ylint3, ylint4, ylstr1, ylstr2, ylstr3, ylstr4);
        this.state = state;
        this.shouhuodanwei = shouhuodanwei;
    }

    public Integer getState()
    {
        return state;
    }

    public void setState(Integer state)
    {
        this.state = state;
    }

    public String getShouhuodanwei()
    {
        return shouhuodanwei;
    }

    public void setShouhuodanwei(String shouhuodanwei)
    {
        this.shouhuodanwei = shouhuodanwei;
    }
}
