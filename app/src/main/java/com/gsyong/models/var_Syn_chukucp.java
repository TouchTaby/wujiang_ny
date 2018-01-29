package com.gsyong.models;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/18 11:03
 * 修改人：YI
 * 修改时间：2018/1/18 11:03
 * 修改备注：
 */
public class var_Syn_chukucp extends  var_base
{
    public dt_chuku_cp model;//出库单流向码实体类模型

    public var_Syn_chukucp()
    {
    }

    public var_Syn_chukucp(String qiyemingzi, String qiyemima, dt_chuku_cp model)
    {
        super(qiyemingzi, qiyemima);
        this.model = model;
    }

    public dt_chuku_cp getModel()
    {
        return model;
    }

    public void setModel(dt_chuku_cp model)
    {
        this.model = model;
    }
}
