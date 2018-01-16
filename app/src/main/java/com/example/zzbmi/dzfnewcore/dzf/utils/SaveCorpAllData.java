package com.example.zzbmi.dzfnewcore.dzf.utils;

import android.content.SharedPreferences;

import com.example.zzbmi.dzfnewcore.dzf.mvp.model.LoginBean;


/**
 * 登录（或切换公司）成功后保存本地信息
 *
 * @author wbb
 */
public class SaveCorpAllData {

    public static void saveAllData(LoginBean loginBean) {
        SharedPreferences sharedPreferences = SPFUitl.getSharedPreferences();

        try {

            if (loginBean != null) {
                LoginBean.CorpBean corp = loginBean.getCorp();
                LoginBean.UserBean user = loginBean.getUser();
                sharedPreferences.edit()
                        //公司信息
                        .putString("accountcorp", corp.getAccountcorp())
                        .putString("books", corp.getBooks())
                        .putBoolean("backup", corp.isBackup())
                        .putString("bdate", corp.getBdate())
                        .putString("bodycode", corp.getBodycode())
                        .putString("ccounty", corp.getCcounty())
                        .putString("d14", corp.getD14())
                        .putString("datacorp", corp.getDatacorp())
                        .putString("entityName", corp.getEntityName())
                        .putString("fcorp", corp.getFcorp())
                        .putString("hasaccount", corp.getHasaccount())
                        .putString("incode", corp.getIncode())
                        .putBoolean("m_isbackup", corp.isM_isbackup())
                        .putString("ownerrate", corp.getOwnerrate())
                        .putString("p1", corp.getP1())  //开票电话（联系电话）
                        .putString("p2", corp.getP2())
                        .putString("pk_gs", corp.getPk_gs())  //公司主键
                        .putString("postadd", corp.getPostadd())
                        .putString("seal", corp.getSeal())
                        .putBoolean("settleCenter", corp.isSettleCenter())
                        .putString("ts", corp.getTs())
                        .putString("ucode", corp.getUcode())
                        .putString("corp_uname", corp.getUname())   //公司名,后台返回字段为uname,避免混淆改成corp_uname
                        .putString("useretail", corp.getUseretail())
                        .putString("ushortname", corp.getUshortname())
                        .putString("url", corp.getUrl())
                        .putString("nkname", corp.getNkname())  //开户行
                        .putString("nkcode", corp.getNkcode())  //开户行账号
                        .putString("tax_code", corp.getTax_code())  //税号
                        //token
                        .putString("token", loginBean.getToken())
                        //用户信息
                        .putString("b_mng", user.getB_mng())
                        .putString("ck_code", user.getCk_code())
                        .putString("corp_id", user.getCorp_id())
                        .putString("crtcorp_id", user.getCrtcorp_id())
                        .putString("en_time", user.getEn_time())
                        .putString("islogin", user.getIslogin())
                        .putInt("pwdtype", user.getPwdtype())
                        .putString("ts", user.getTs())
                        .putString("u_pwd", user.getU_pwd())
                        .putString("ucode", user.getUcode())
                        .putString("uid", user.getUid())   //用户主键
                        .putString("user_url", user.getUser_url())
                        .putString("user_uname", user.getUname())  //用户名,后台返回字段为uname,避免混淆改成user_uname
                        .putString("img_url", user.getImg_url())
                        .commit();
            }

        } catch (Exception e) {

        }
    }
}
