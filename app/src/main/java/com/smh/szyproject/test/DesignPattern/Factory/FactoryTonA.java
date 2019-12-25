package com.smh.szyproject.test.DesignPattern.Factory;

/**
 *
 */
public class FactoryTonA extends Factory{
    @Override
    public Product Manufacture() {
        return new ProductA();
    }
}
