package com.example.zzbmi.dzfnewcore.dzf.utils;

import android.content.SharedPreferences;

import com.dazf.frame.baseapp.BaseApplication;


public class SPFUitl {

    private static SharedPreferences sharedPreferences = BaseApplication.getAppContext().getSharedPreferences("xwwy_config", 0);
    private static SharedPreferences noticeSPF = BaseApplication.getAppContext().getSharedPreferences(DZFConfig.APP_NOTICE_SETTING, 0);

    //存String(上下文,key,value)
    public static void saveStringData(String key, String value) {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getAppContext().getSharedPreferences("xwwy_config", 0);
        }
        sharedPreferences.edit().putString(key, value).commit();
    }

    //取
    public static String getStringData(String key, String defValue) {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getAppContext().getSharedPreferences("xwwy_config", 0);
        }
        return sharedPreferences.getString(key, defValue);
    }

    public static SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getAppContext().getSharedPreferences("xwwy_config", 0);
        }
        return sharedPreferences;
    }


    //存String(上下文,key,value)
    public static void saveIntData(String key, int value) {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getAppContext().getSharedPreferences("xwwy_config", 0);
        }
        sharedPreferences.edit().putInt(key, value).commit();
    }

    //取
    public static int getIntData(String key, int defValue) {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getAppContext().getSharedPreferences("xwwy_config", 0);
        }
        return sharedPreferences.getInt(key, defValue);
    }


    //存String(上下文,key,value)
    public static void saveBooleanData(String key, boolean value) {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getAppContext().getSharedPreferences("xwwy_config", 0);
        }
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    //取
    public static boolean getBooleanData(String key, boolean defValue) {
        if (sharedPreferences == null) {
            sharedPreferences = BaseApplication.getAppContext().getSharedPreferences("xwwy_config", 0);
        }
        return sharedPreferences.getBoolean(key, defValue);
    }

    //存String(上下文,key,value)
    public static void saveNoticeSettingBooleanData(String key, boolean value) {
        if (noticeSPF == null) {
            noticeSPF = BaseApplication.getAppContext().getSharedPreferences(DZFConfig.APP_NOTICE_SETTING, 0);
        }
        noticeSPF.edit().putBoolean(key + ParamsHelper.getUserID(), value).commit();
    }

    //取
    public static boolean getNoticeSettingBooleanData(String key) {
        if (noticeSPF == null) {
            noticeSPF = BaseApplication.getAppContext().getSharedPreferences(DZFConfig.APP_NOTICE_SETTING, 0);
        }
        return noticeSPF.getBoolean(key + ParamsHelper.getUserID(), true);
    }
}
