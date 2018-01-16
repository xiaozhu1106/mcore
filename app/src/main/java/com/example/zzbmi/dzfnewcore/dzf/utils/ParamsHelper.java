package com.example.zzbmi.dzfnewcore.dzf.utils;

/**
 * Created by ZZB on 2017/3/21.
 */

public class ParamsHelper {
    public static final int Photo_Customer = 3; //客户类型
    public static final int Photo_User = 4; //用户类型

    public static final int PHOTO_BANNNER = 5; //banner类型

    public static final int IMAGE_FINANCIAL_DETAIL_IMAGE = 6; //财务处理详情页图片上传类型
    public static final int TASKDO_PHOTO = 7;

    /**
     * 获取图片前缀
     *
     * @param photoType 图片类型， 3为客户， 4为用户
     * @param path      图片url路径
     * @return
     */
    private static String getPhotoUrl(int photoType, String path) {
        String corpId = getCorpID();
        String userId = getUserID();
        String operate = "9";   //显示图片固定参数9
        String pathURI = String.format(DZFConfig.MOBILE_API + "busidata!dealData.action?operate=%1$s&corp_id=%2$s&userid=%3$s&qrytype=%4$s&imgpath=%5$s",
                operate, corpId, userId, photoType + "", path);
//        L.i("getPhotoUrl==" + pathURI);
        return pathURI;
    }

    /**
     * 获取用户头像
     */
    public static String getUserPhotoAvatar() {
        String userUrl = SPFUitl.getStringData("user_url", "");
        return getPhotoUrl(Photo_User, userUrl);
    }

    /**
     * 获取用户其他图片
     */
    public static String getUserPhoto(String url) {
        return getPhotoUrl(Photo_User, url);
    }


    /**
     * 获取banner图片
     */
    public static String getBannerPhoto(String path) {
        return getPhotoUrl(PHOTO_BANNNER, path);
    }

    /**
     * 获取票据详情图片URI前缀图片
     */
    public static String getFinancialDetail(String path) {
        return getPhotoUrl(IMAGE_FINANCIAL_DETAIL_IMAGE, path);
    }


    /**
     * 获取签到图片前缀
     * @param path
     * @return
     */
    public static String getSignPhotoUrl(String path) {
        String corpId = getCorpID();
        String userId = getUserID();
        String operate = "1603";   //显示图片固定参数9
        String pathURI = String.format(DZFConfig.MOBILE_API + "busidata!dealData.action?operate=%1$s&corp_id=%2$s&userid=%3$s&imgpath=%4$s",
                operate, corpId, userId, path);
//        L.i("getPhotoUrl==" + pathURI);
        return pathURI;
    }


    /**
     * 获取客户图片
     */
    public static String getCustomerPhoto(String path) {
        return getPhotoUrl(Photo_Customer, path);
    }

    /**
     * 业务处理附件
     * @param path
     * @return
     */
    public static String getTaskDoPhoto(String path) {
        return getPhotoUrl(TASKDO_PHOTO, path);
    }

    public static String getCorpID() {
        String pk_gs = SPFUitl.getStringData("pk_gs", "");
        return pk_gs;
    }

    public static String getUserID() {
        String uid = SPFUitl.getStringData("uid", "");
        return uid;
    }

    public static String getCorpName() {
        String corp_uname = SPFUitl.getStringData("corp_uname", "");
        return corp_uname;
    }

    public static String getUserName() {
        String user_uname = SPFUitl.getStringData("user_uname", "");
        return user_uname;
    }


}
