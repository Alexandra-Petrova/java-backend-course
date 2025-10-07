package task_3;

sealed public class LandTransport extends Vehicle permits Car, Bicycle{

    public LandTransport(String model, double speed, Engine typeOfEngine) {
        super(model, speed, typeOfEngine);
    }

    @Override
    public String getTransportType() {
        return "Наземный транспорт";
    }

    @Override
    public void move() {
        System.out.println(getTransportType() + " едет со скоростью " + speed + "км/ч.");
    }
}

final class Car extends LandTransport{

    public Car(String model, double speed, Engine typeOfEngine) {
        super(model, speed, typeOfEngine);
    }

    @Override
    public String getTransportType() {
        return "Автомобиль";
    }
}

final class Bicycle extends LandTransport{

    public Bicycle(String model, double speed) {
        super(model, speed, new Engine(Fuel.NONE, Double.NaN));
    }

    @Override
    public String getTransportType() {
        return "Велосипед";
    }
}
