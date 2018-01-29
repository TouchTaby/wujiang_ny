package com.gsyong.model;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/19 13:04
 * 修改人：YI
 * 修改时间：2018/1/19 13:04
 * 修改备注：
 */
public class dt_qiye_zhucema
{
    public String status;
    public String info;
    public dt_qiye qiye;
    public dt_zhucema zhucema;

    public dt_qiye_zhucema()
    {
    }

    public dt_qiye_zhucema(String status, String info, dt_qiye qiye, dt_zhucema zhucema)
    {
        this.status = status;
        this.info = info;
        this.qiye = qiye;
        this.zhucema = zhucema;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public dt_qiye getQiye()
    {
        return qiye;
    }

    public void setQiye(dt_qiye qiye)
    {
        this.qiye = qiye;
    }

    public dt_zhucema getZhucema()
    {
        return zhucema;
    }

    public void setZhucema(dt_zhucema zhucema)
    {
        this.zhucema = zhucema;
    }
}
