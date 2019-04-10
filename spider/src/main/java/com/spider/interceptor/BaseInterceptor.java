package com.spider.interceptor;

import com.spider.interfaces.InterceptorChain;
import com.spider.interfaces.SpiderInterceptor;

/**
 * @time 2019/1/7
 * //拦截器的基本类，自定义拦截器需要实现它
 */
public abstract class BaseInterceptor implements SpiderInterceptor, InterceptorChain {
    private InterceptorChain preChain = null;
    private InterceptorChain postChain = null;

    @Override
    public void addPreChain(InterceptorChain preChain) {
        this.preChain = preChain;
    }

    @Override
    public void addPostChain(InterceptorChain postChain) {
        this.postChain = postChain;
    }

    @Override
    public InterceptorChain preNext() {
        return preChain;
    }

    @Override
    public InterceptorChain postNext() {
        return postChain;
    }

    @Override
    public void preExecute() {
        this.preTask();
        if (preNext() != null) {
            doPreChain(preNext());
        }
    }

    @Override
    public void postExecute() {
        this.postTask();
        if (postNext() != null) {
            doPostChain(postNext());
        }
    }

    private void doPreChain(InterceptorChain chain) {
        chain.preExecute();
    }

    private void doPostChain(InterceptorChain chain) {
        chain.postExecute();
    }
}
