package com.smh.szyproject.test.buildTon;

public class SmartManBuilder implements IBuildHuman{
    Human human;
    public SmartManBuilder(){
        human = new Human();
    }
    @Override
    public void buildHead() {
        human.setHead("智商");
    }

    @Override
    public void buildBody() {
        human.setBody("身体");
    }

    @Override
    public void buildHand() {
        human.setFoot("头");
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
