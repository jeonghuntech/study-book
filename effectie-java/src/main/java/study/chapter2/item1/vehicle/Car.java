package study.chapter2.item1.vehicle;

import java.math.BigInteger;
import java.util.EnumSet;

public class Car {

    private final String type;

    private Car(String type) {
        this.type = type;
    }

    public static Car createAudi() {
        return new Car("audi");
    }

    public static Car createBmw() {
        return new Car("bmw");
    }

}
