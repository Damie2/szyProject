package com.smh.szyproject.test.buildTon;

public class ActiveManBuilder implements IBuildHuman{
    Human human;
    public ActiveManBuilder() {
        human = new Human();
    }

    @Override
    public void buildHead() {
        human.setHead("头脑智商180");
    }

    @Override
    public void buildBody() {
        human.setBody("身体无敌的运动员");
    }

    @Override
    public void buildHand() {
        human.setHand("手");
    }

    @Override
    public void buildFoot() {
        human.setFoot("脚");
    }

    @Override
    public Human createHuman() {
        return human;
    }

}
