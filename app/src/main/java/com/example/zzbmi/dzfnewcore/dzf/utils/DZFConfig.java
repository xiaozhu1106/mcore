package com.example.zzbmi.dzfnewcore.dzf.utils;

import android.os.Environment;

import java.io.File;
import java.text.DecimalFormat;

/**
 * Created by LJW on 2016/7/29.
 */
public class DZFConfig {

    public static final boolean ISDebug = true;

//    public static final String MOBILE_API = "https://gs.dazhangfang.com/xwwy_app/";
    public static final String MOBILE_API = "http://172.16.6.91:8080/xwwy_app/";

    /**
     * 假服务器测试地址
     */
    public static final String TEST_SERVER_API = "http://172.16.2.171:8080/dataserver/dzf_admin2/xwwy_app/";


    /**
     * 小微无忧版本更新项目地址
     */
    public static final String UpDateVersion = ISDebug ? "http://172.16.6.36:8081/dm-dzf-app/"
            : "http://app.dazhangfang.com/";

    /**
     * WebSocket聊天地址
     */
    public static final String WebSocketAddress = ISDebug ? "ws://172.16.6.33:8080/" : "ws://chat.dazhangfang.com/";//dzfchat/

    public static final String ANDROID = "0";
    public static final String VERSION = "322";


    public static final String APP_ROOT = Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + File.separator
            + "dzf";

    public static final String APP_CRASH_LOG = Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + File.separator
            + "crash";

    public static final String XWWY_FILE_DIR = DZFConfig.APP_ROOT + File.separator + "xwwyfile" + File.separator;

    public static DecimalFormat df = new DecimalFormat("0000");
    public static DecimalFormat df1 = new DecimalFormat("000");

    // handle 返回的 图片压缩路径
    public static final int ZIPPATH = 112;
    public static final int SCWJJD = 115;

    public static final String APP_NOTICE_SETTING = "app_notice_setting";
    public static final String APP_NOTICE = "app_notice";
    public static final String APP_NOTICE_VOICE = "app_notice_voice";
    public static final String APP_NOTICE_SHAKE = "app_notice_shake";

}
