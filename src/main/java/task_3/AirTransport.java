package task_3;

public sealed class AirTransport extends Vehicle permits AirPlane {

    public AirTransport(String model, double speed, Engine typeOfEngine) {
        super(model, speed, typeOfEngine);
    }

    @Override
    public String getTransportType() {
        return "Воздушный транспорт";
    }

    @Override
    public void move() {
        System.out.println(getTransportType() + " летит со скоростью " + speed + "км/ч.");
    }
}

final class AirPlane extends AirTransport {

    public AirPlane(String model, double speed, Engine typeOfEngine) {
        super(model, speed, typeOfEngine);
    }

    @Override
    public String getTransportType() {
        return "Самолет";
    }
}
