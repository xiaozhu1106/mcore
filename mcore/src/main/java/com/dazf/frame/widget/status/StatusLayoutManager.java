package com.dazf.frame.widget.status;

/**
 * @author ZhuZiBo
 * @date 2018/1/16
 * 代码中给Activity或Fragment或View注入状态布局的方式
 */
public class StatusLayoutManager {

    private Config config;

    private static StatusLayoutManager instance = null;

    private StatusLayoutManager(){
    }

    public static StatusLayoutManager getInstance() {
        if (instance == null) {
            synchronized (StatusLayoutManager.class) {
                if (instance == null) {
                    instance = new StatusLayoutManager();
                }
            }
        }
        return instance;
    }


    public void setConfig(Config config) {
        this.config  = config;
    }
    public Config getConfig() {
        return this.config;
    }
}
