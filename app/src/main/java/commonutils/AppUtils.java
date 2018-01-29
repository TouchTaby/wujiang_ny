package commonutils;

import java.net.NetworkInterface;
import java.net.SocketException;

/**
 * 类描述：
 * 创建人：YI
 * 创建时间：2018/1/20 19:39
 * 修改人：YI
 * 修改时间：2018/1/20 19:39
 * 修改备注：
 */
public class AppUtils {
    /**
     * 获取本机MAC地址
     *
     * @return
     */
    public static String getMacAddress() {

        String macAddress = null;
        StringBuffer buf = new StringBuffer();
        NetworkInterface networkInterface = null;
        try {
            networkInterface = NetworkInterface.getByName("eth1");
            if (networkInterface == null) {
                networkInterface = NetworkInterface.getByName("wlan0");
            }
            if (networkInterface == null) {
                return "02:00:00:00:00:02";
            }
            byte[] addr = networkInterface.getHardwareAddress();
            for (byte b : addr) {
                buf.append(String.format("%02X:", b));
            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
            }
            macAddress = buf.toString();
        } catch (SocketException e) {
            e.printStackTrace();
            return "02:00:00:00:00:02";
        }
//      macAddress="80:71:7A:F0:B1:32";
//     macAddress="99:79:57:f6:4A:95";
        return macAddress;
    }
}
