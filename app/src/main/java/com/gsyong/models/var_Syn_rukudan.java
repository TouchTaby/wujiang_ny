package com.gsyong.models;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/18 11:01
 * 修改人：YI
 * 修改时间：2018/1/18 11:01
 * 修改备注：
 */
public class var_Syn_rukudan extends var_base
{
    public dt_ruku_list model; //入库单实体类模型

    public var_Syn_rukudan()
    {
    }

    public var_Syn_rukudan(String qiyemingzi, String qiyemima, dt_ruku_list model)
    {
        super(qiyemingzi, qiyemima);
        this.model = model;
    }

    public dt_ruku_list getModel()
    {
        return model;
    }

    public void setModel(dt_ruku_list model)
    {
        this.model = model;
    }
}
