package com.smh.szyproject.test.DesignPattern;

/**
 * 简单的工厂模式
 */
public class SimpleFactoryTon {
    public Car createCarFactory(int num) {
        Car car = null;
        switch (num) {
            case 1:
                car = new Car("花冠");
                break;
            case 2:
                car = new Car("雅阁", "白色");
                break;
            default:
                car = new Car();
                break;
        }
        return car;
    }


    class Car {
        private String band;
        private String color;

        public Car() {
        }

        public Car(String band) {
            this.band = band;
        }

        public Car(String band, String color) {
            this.band = band;
            this.color = color;
        }

        public String getBand() {
            return band;
        }

        public void setBand(String band) {
            this.band = band;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
