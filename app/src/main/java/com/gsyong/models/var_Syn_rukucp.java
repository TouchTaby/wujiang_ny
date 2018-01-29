package com.gsyong.models;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/18 11:02
 * 修改人：YI
 * 修改时间：2018/1/18 11:02
 * 修改备注：
 */
public class var_Syn_rukucp extends  var_base
{
    public dt_ruku_cp model; //入库单流向码实体类模型

    public var_Syn_rukucp()
    {
    }

    public var_Syn_rukucp(String qiyemingzi, String qiyemima, dt_ruku_cp model)
    {
        super(qiyemingzi, qiyemima);
        this.model = model;
    }

    public dt_ruku_cp getModel()
    {
        return model;
    }

    public void setModel(dt_ruku_cp model)
    {
        this.model = model;
    }
}
