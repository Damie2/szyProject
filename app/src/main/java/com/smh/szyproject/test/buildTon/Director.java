package com.smh.szyproject.test.buildTon;

/**
 * 建造者的核心，指导者
 */
public class Director {
    public Human createHumanByDirecotr(IBuildHuman bh){
        bh.buildBody();
        bh.buildFoot();
        bh.buildHand();
        bh.buildHead();
        return bh.createHuman();
    }
}
