package com.henry.tryout.design_pattern.builder_pattern;

// 车子说明书
public class Manual {

    private CarType carType;
    private int seats;
    private Engine engine;

    public Manual() {

    }

    @Override
    public String toString() {
        return "Manual{" +
                "carType=" + carType +
                ", seats=" + seats +
                ", engine=" + engine +
                "}";
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
