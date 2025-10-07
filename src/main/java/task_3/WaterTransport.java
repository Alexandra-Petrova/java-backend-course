package task_3;

public sealed class WaterTransport extends Vehicle permits Ship {

    public WaterTransport(String model, double speed, Engine typeOfEngine) {
        super(model, speed, typeOfEngine);
    }

    @Override
    public String getTransportType() {
        return "Водный транспорт";
    }

    @Override
    public void move() {
        System.out.println(getTransportType() + " плывет со скоростью " + speed + "км/ч.");
    }
}

final class Ship extends WaterTransport {

    public Ship(String model, double speed, Engine typeOfEngine) {
        super(model, speed, typeOfEngine);
    }

    @Override
    public String getTransportType() {
        return "Корабль";
    }
}
