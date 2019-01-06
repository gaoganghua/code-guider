package com.spider.interceptor;

import com.spider.interfaces.BaseChain;
import com.spider.interfaces.Chain;
import com.spider.interfaces.SpiderInterceptor;

public abstract class BaseInterceptor implements SpiderInterceptor {
    private BaseChain preChain = new InterceptorChain();
    private BaseChain postChain = new InterceptorChain();


    @Override
    public void preTask() {
        preProcess();
    }

    @Override
    public void postTask() {
        postProcess();
        if(postChain.next()!=null){
//            postNext().
        }
    }

    public abstract void preProcess();

    public abstract void postProcess();

    public void addPreChain(Chain chain) {
        preChain.addChain(chain);
    }

    public void addPostChain(Chain chain) {
        postChain.addChain(chain);
    }

    private Chain preNext() {
        return preChain.next();
    }

    private Chain postNext() {
        return postChain.next();
    }
}
