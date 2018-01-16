package com.example.zzbmi.dzfnewcore.dzf.utils;

import android.os.Build;
import android.provider.Settings;

import com.dazf.frame.baseapp.BaseApplication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DeviceUtils {

    public static String sDeviceId;


    /**
     * 返回imei，如果没有imei则返回软件计算的uuid
     */
    public static String getDeviceId() {
//        if (!TextUtils.isEmpty(sDeviceId)) {
//            return sDeviceId;
//        }
//        Context context = BaseApplication.getApplication();
//        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        if (tm != null) {
//            String imei = "" + tm.getDeviceId();
//            if (TextUtils.isEmpty(imei) || "000000000000000".equals(imei)) {
//                String androidId = "";
//                String deviceMobileNo = "";
//                deviceMobileNo = "" + tm.getLine1Number();
//                androidId = "" + Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
//                String deviceUUID = EncryptUtil.encrypt((imei + androidId + deviceMobileNo), EncryptUtil.MD5);
//                sDeviceId = deviceUUID;
//            } else {
//                sDeviceId = imei;
//            }
//        }
//        return sDeviceId;


        //Pseudo-Unique ID, 手机硬件ID
        String m_szDevIDShort = "35" + //we make this look like a valid IMEI

                Build.BOARD.length() % 10 +
                Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 +
                Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 +
                Build.HOST.length() % 10 +
                Build.ID.length() % 10 +
                Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 +
                Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 +
                Build.TYPE.length() % 10 +
                Build.USER.length() % 10; //13 digits

        //AndroidID
        String m_szAndroidID = Settings.System.getString(BaseApplication.getAppContext().getContentResolver(), Settings.System.ANDROID_ID);
        String m_szLongID = m_szDevIDShort
                + m_szAndroidID;
        // compute md5
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(m_szLongID.getBytes(), 0, m_szLongID.length());
        // get md5 bytes
        byte p_md5Data[] = m.digest();
        // create a hex string
        String m_szUniqueID = new String();
        for (int i = 0; i < p_md5Data.length; i++) {
            int b = (0xFF & p_md5Data[i]);
            // if it is a single digit, make sure it have 0 in front (proper padding)
            if (b <= 0xF)
                m_szUniqueID += "0";
            // add number to string
            m_szUniqueID += Integer.toHexString(b);
        }   // hex string to uppercase
        m_szUniqueID = m_szUniqueID.toUpperCase();
        return  m_szUniqueID;
    }


}
