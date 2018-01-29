package com.gsyong.models;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/18 11:01
 * 修改人：YI
 * 修改时间：2018/1/18 11:01
 * 修改备注：
 */
public class var_base {
    public String qiyemingzi; //企业名字 授权信息提取
    public String qiyemima;    //企业密码 授权信息提取

    public var_base() {
    }

    public var_base(String qiyemingzi, String qiyemima) {
        this.qiyemingzi = qiyemingzi;
        this.qiyemima = qiyemima;
    }

    public String getQiyemingzi() {
        return qiyemingzi;
    }

    public void setQiyemingzi(String qiyemingzi) {
        this.qiyemingzi = qiyemingzi;
    }

    public String getQiyemima() {
        return qiyemima;
    }

    public void setQiyemima(String qiyemima) {
        this.qiyemima = qiyemima;
    }
}
