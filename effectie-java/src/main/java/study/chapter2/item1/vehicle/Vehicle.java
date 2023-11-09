package study.chapter2.item1.vehicle;

interface Vehicle {
    static Vehicle bus() {
        return new Bus();
    }

    static Vehicle bike() {
        return new Bike();
    }
    void move();
}
