package com.dazf.frame.http;

/**
 * @author ZhuZiBo
 * @date 2018/1/10
 */

public interface IRepositoryManager {

    /**
     * 根据传入的Class获取对应的Retrift service
     *
     * @param service
     * @param <T>
     * @return
     */
    <T> T obtainRetrofitService(Class<T> service);

}
