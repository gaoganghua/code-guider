package com.spider.interfaces;

public interface InterceptorChain {

    void addPreChain(InterceptorChain preChain);

    void addPostChain(InterceptorChain postChain);

    InterceptorChain preNext();

    InterceptorChain postNext();

    void preExecute();

    void postExecute();
}
