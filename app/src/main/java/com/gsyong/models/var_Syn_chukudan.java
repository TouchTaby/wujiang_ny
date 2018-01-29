package com.gsyong.models;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/18 11:03
 * 修改人：YI
 * 修改时间：2018/1/18 11:03
 * 修改备注：
 */
public class var_Syn_chukudan extends  var_base
{
    public dt_chuku_list model;//出库单实体类模型

    public var_Syn_chukudan()
    {
    }

    public var_Syn_chukudan(String qiyemingzi, String qiyemima, dt_chuku_list model)
    {
        super(qiyemingzi, qiyemima);
        this.model = model;
    }

    public dt_chuku_list getModel()
    {
        return model;
    }

    public void setModel(dt_chuku_list model)
    {
        this.model = model;
    }
}
