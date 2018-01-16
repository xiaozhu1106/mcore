package com.example.zzbmi.dzfnewcore.dzf.mvp.model;

import java.io.Serializable;

/**
 * Created by ZZB on 2017/2/24.
 */

public class LoginBean implements Serializable {

    @Override
    public String toString() {
        return "LoginBean{" +
                "corp=" + corp +
                ", page=" + page +
                ", parent_id='" + parent_id + '\'' +
                ", rows=" + rows +
                ", status=" + status +
                ", systime=" + systime +
                ", token='" + token + '\'' +
                ", user=" + user +
                ", version=" + version +
                '}';
    }

    /**
     * books : 003Nq2
     * backup : false
     * bdate : 2017-01-11
     * ccounty : 北京市-海淀区
     * d14 : Y
     * entityName : Corp
     * fcorp : 000001
     * incode : dieyi
     * accountcorp : 是
     * datacorp : 否
     * hasaccount : 是
     * seal : 否
     * useretail : 否
     * bodycode : 717b1bc797a0c23c0d0f71
     * m_isbackup : false
     * ownerrate : -
     * page : 1
     * parent_id :
     * p1 : 717b1bc797a0c23c0d0f71
     * p2 : 717b1bc797a0c23c0d0f71
     * pk_gs : 003Npo
     * postadd : 北京市海淀区万泉庄路15号
     * primaryKey : 003Npo
     * rows : 10
     * settleCenter : false
     * status : 0
     * ts : 2017-01-11 13:54:20
     * ucode : dieyi
     * uname : 蝶艺传媒11
     * ushortname : 蝶艺
     * url : /ImageUpload/website/dzfadmin/accountlogo/dieyi.jpg
     * version : 0
     */

    private CorpBean corp;
    /**
     * corp : {"books":"003Nq2","backup":false,"bdate":"2017-01-11","ccounty":"北京市-海淀区","d14":"Y","entityName":"Corp","fcorp":"000001","incode":"dieyi","accountcorp":"是","datacorp":"否","hasaccount":"是","seal":"否","useretail":"否","bodycode":"717b1bc797a0c23c0d0f71","m_isbackup":false,"ownerrate":"-","page":1,"parent_id":"","p1":"717b1bc797a0c23c0d0f71","p2":"717b1bc797a0c23c0d0f71","pk_gs":"003Npo","postadd":"北京市海淀区万泉庄路15号","primaryKey":"003Npo","rows":10,"settleCenter":false,"status":0,"ts":"2017-01-11 13:54:20","ucode":"dieyi","uname":"蝶艺传媒11","ushortname":"蝶艺","url":"/ImageUpload/website/dzfadmin/accountlogo/dieyi.jpg","version":0}
     * page : 1
     * parent_id :
     * rows : 10
     * status : 0
     * systime : 0
     * token : quYWEVXFeGbE7C9DykD2ANXj/R/R7GtiMoo2fAOkgzsYS+wwXpsPpyDb+sg+/wv05w7dMneIbxZm
     sPm2GhcrH1hZ3CIbMRpofHnTY+7hYZ2T05UQvJLdIAFNB4VTz4mT2fY8V/fbGR3jD54AmV+sTCaT
     GuB67b+wrZtQH3R/gCN2C8AKTTrvVbYnBtoimwosMdEp3OrXx0kH/lI7/7udcSDp0y6adYpWhwyF
     rAyOF2vd5DTD6RzzPra5/8HFzEak7gq1tY9ALluedKRHC5xCCTPDW9IoV0fV6w30/8PpOuM75Mc9
     dl++YgJbvLvcLeGRa+b5hsLGQ/y1dDDStVN17Q==
     * user : {"en_time":"2015-01-01","ck_code":"zongy555","uid":"003Npo00000000q3ZJUp0009","islogin":"是","b_mng":"是","page":1,"parent_id":"","corp_id":"003Npo","crtcorp_id":"003Npo","primaryKey":"003Npo00000000q3ZJUp0009","pwdtype":0,"rows":10,"status":0,"ts":"2017-02-24 10:35:26","ucode":"zongy555","uname":"zongy555","u_pwd":"oabecmiacohepbnn","version":0}
     * version : 0
     */

    private int page;
    private String parent_id;
    private int rows;
    private int status;
    private int systime;
    private String token;
    /**
     * en_time : 2015-01-01
     * ck_code : zongy555
     * uid : 003Npo00000000q3ZJUp0009
     * islogin : 是
     * b_mng : 是
     * page : 1
     * parent_id :
     * corp_id : 003Npo
     * crtcorp_id : 003Npo
     * primaryKey : 003Npo00000000q3ZJUp0009
     * pwdtype : 0
     * rows : 10
     * status : 0
     * ts : 2017-02-24 10:35:26
     * ucode : zongy555
     * uname : zongy555
     * u_pwd : oabecmiacohepbnn
     * version : 0
     */

    private UserBean user;
    private int version;

    public CorpBean getCorp() {
        return corp;
    }

    public void setCorp(CorpBean corp) {
        this.corp = corp;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSystime() {
        return systime;
    }

    public void setSystime(int systime) {
        this.systime = systime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public static class CorpBean {
        private String books;
        private boolean backup;
        private String bdate;
        private String ccounty;
        private String d14;
        private String entityName;
        private String fcorp;
        private String incode;
        private String accountcorp;
        private String datacorp;
        private String hasaccount;
        private String seal;
        private String useretail;
        private String bodycode;
        private boolean m_isbackup;
        private String ownerrate;
        private int page;
        private String parent_id;
        private String p1;
        private String p2;
        private String pk_gs;
        private String postadd;
        private String primaryKey;
        private int rows;
        private boolean settleCenter;
        private int status;
        private String ts;
        private String ucode;
        private String uname;
        private String ushortname;
        private String url;
        private int version;
        private String nkname;
        private String nkcode;
        private String tax_code;

        public String getNkname() {
            return nkname;
        }

        public void setNkname(String nkname) {
            this.nkname = nkname;
        }

        public String getNkcode() {
            return nkcode;
        }

        public void setNkcode(String nkcode) {
            this.nkcode = nkcode;
        }

        public String getTax_code() {
            return tax_code;
        }

        public void setTax_code(String tax_code) {
            this.tax_code = tax_code;
        }

        public String getBooks() {
            return books;
        }

        public void setBooks(String books) {
            this.books = books;
        }

        public boolean isBackup() {
            return backup;
        }

        public void setBackup(boolean backup) {
            this.backup = backup;
        }

        public String getBdate() {
            return bdate;
        }

        public void setBdate(String bdate) {
            this.bdate = bdate;
        }

        public String getCcounty() {
            return ccounty;
        }

        public void setCcounty(String ccounty) {
            this.ccounty = ccounty;
        }

        public String getD14() {
            return d14;
        }

        public void setD14(String d14) {
            this.d14 = d14;
        }

        public String getEntityName() {
            return entityName;
        }

        public void setEntityName(String entityName) {
            this.entityName = entityName;
        }

        public String getFcorp() {
            return fcorp;
        }

        public void setFcorp(String fcorp) {
            this.fcorp = fcorp;
        }

        public String getIncode() {
            return incode;
        }

        public void setIncode(String incode) {
            this.incode = incode;
        }

        public String getAccountcorp() {
            return accountcorp;
        }

        public void setAccountcorp(String accountcorp) {
            this.accountcorp = accountcorp;
        }

        public String getDatacorp() {
            return datacorp;
        }

        public void setDatacorp(String datacorp) {
            this.datacorp = datacorp;
        }

        public String getHasaccount() {
            return hasaccount;
        }

        public void setHasaccount(String hasaccount) {
            this.hasaccount = hasaccount;
        }

        public String getSeal() {
            return seal;
        }

        public void setSeal(String seal) {
            this.seal = seal;
        }

        public String getUseretail() {
            return useretail;
        }

        public void setUseretail(String useretail) {
            this.useretail = useretail;
        }

        public String getBodycode() {
            return bodycode;
        }

        public void setBodycode(String bodycode) {
            this.bodycode = bodycode;
        }

        public boolean isM_isbackup() {
            return m_isbackup;
        }

        public void setM_isbackup(boolean m_isbackup) {
            this.m_isbackup = m_isbackup;
        }

        public String getOwnerrate() {
            return ownerrate;
        }

        public void setOwnerrate(String ownerrate) {
            this.ownerrate = ownerrate;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getP1() {
            return p1;
        }

        public void setP1(String p1) {
            this.p1 = p1;
        }

        public String getP2() {
            return p2;
        }

        public void setP2(String p2) {
            this.p2 = p2;
        }

        public String getPk_gs() {
            return pk_gs;
        }

        public void setPk_gs(String pk_gs) {
            this.pk_gs = pk_gs;
        }

        public String getPostadd() {
            return postadd;
        }

        public void setPostadd(String postadd) {
            this.postadd = postadd;
        }

        public String getPrimaryKey() {
            return primaryKey;
        }

        public void setPrimaryKey(String primaryKey) {
            this.primaryKey = primaryKey;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public boolean isSettleCenter() {
            return settleCenter;
        }

        public void setSettleCenter(boolean settleCenter) {
            this.settleCenter = settleCenter;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTs() {
            return ts;
        }

        public void setTs(String ts) {
            this.ts = ts;
        }

        public String getUcode() {
            return ucode;
        }

        public void setUcode(String ucode) {
            this.ucode = ucode;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getUshortname() {
            return ushortname;
        }

        public void setUshortname(String ushortname) {
            this.ushortname = ushortname;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }

    public static class UserBean {
        private String en_time;
        private String ck_code;
        private String uid;
        private String islogin;
        private String b_mng;
        private int page;
        private String parent_id;
        private String corp_id;
        private String crtcorp_id;
        private String primaryKey;
        private int pwdtype;
        private int rows;
        private int status;
        private String ts;
        private String ucode;
        private String uname;
        private String u_pwd;
        private int version;
        private String user_url;
        private String img_url;

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getUser_url() {
            return user_url;
        }

        public void setUser_url(String user_url) {
            this.user_url = user_url;
        }

        public String getEn_time() {
            return en_time;
        }

        public void setEn_time(String en_time) {
            this.en_time = en_time;
        }

        public String getCk_code() {
            return ck_code;
        }

        public void setCk_code(String ck_code) {
            this.ck_code = ck_code;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getIslogin() {
            return islogin;
        }

        public void setIslogin(String islogin) {
            this.islogin = islogin;
        }

        public String getB_mng() {
            return b_mng;
        }

        public void setB_mng(String b_mng) {
            this.b_mng = b_mng;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getCorp_id() {
            return corp_id;
        }

        public void setCorp_id(String corp_id) {
            this.corp_id = corp_id;
        }

        public String getCrtcorp_id() {
            return crtcorp_id;
        }

        public void setCrtcorp_id(String crtcorp_id) {
            this.crtcorp_id = crtcorp_id;
        }

        public String getPrimaryKey() {
            return primaryKey;
        }

        public void setPrimaryKey(String primaryKey) {
            this.primaryKey = primaryKey;
        }

        public int getPwdtype() {
            return pwdtype;
        }

        public void setPwdtype(int pwdtype) {
            this.pwdtype = pwdtype;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTs() {
            return ts;
        }

        public void setTs(String ts) {
            this.ts = ts;
        }

        public String getUcode() {
            return ucode;
        }

        public void setUcode(String ucode) {
            this.ucode = ucode;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getU_pwd() {
            return u_pwd;
        }

        public void setU_pwd(String u_pwd) {
            this.u_pwd = u_pwd;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }
}
