package com.gsyong.config;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/19 15:07
 * 修改人：YI
 * 修改时间：2018/1/19 15:07
 * 修改备注：
 */
public enum WebServiceEnum
{
    zhucemabangding,//(string zhucema,string pcmac)
    Get_zhuce_file,//(string mac)
    Syn_rukudan,//(var_Syn_rukudan vars)说明：设备上传入库单调用 ,返回值: true为成功 false为失败
    Syn_rukucp,//(var_Syn_rukucp vars)说明：设备上传入库单关联的流向码, 返回值：true为成功 false为失败
    Syn_chukudan,//(var_Syn_chukudan vars)说明：设备上传出库单调用, 返回值：true为成功 false为失败
    Syn_chukucp,//(var_Syn_chukucp vars)说明：设备上传出库单关联的流向码, 返回值：true为成功 false为失败

}
