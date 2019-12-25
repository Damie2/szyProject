package com.smh.szyproject.test.DesignPattern.Factory;

public class FactoryTonB extends Factory{
    @Override
    public Product Manufacture() {
        return new ProductB();
    }
}
