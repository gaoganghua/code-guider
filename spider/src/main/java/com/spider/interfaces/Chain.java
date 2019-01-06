package com.spider.interfaces;

public interface Chain {
    void addChain(Chain chain);

    Chain next();

    void execute();

}
