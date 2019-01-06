package com.spider.interfaces;

public abstract class BaseChain implements Chain {
    protected Chain chain;

    @Override
    public void addChain(Chain chain) {
        this.chain = chain;
    }

    @Override
    public Chain next() {
        return chain;
    }

    @Override
    public void execute(){
        process();
        if(next()!=null) {
            post(next());
        }
    }

    public void post(Chain chain){
        chain.execute();
    }

    public abstract void process();
}
