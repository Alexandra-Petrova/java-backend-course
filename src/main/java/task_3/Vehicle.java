package task_3;

public abstract class Vehicle {
    protected String model;
    protected double speed;
    protected final Engine typeOfEngine;

    public Vehicle (String model, double speed, Engine typeOfEngine) {
        if (model == null || model.isEmpty()) {
            throw new InvalidVehicleDataException("Некорректная модель!");
        }
        if (speed < 0.) {
            throw new InvalidVehicleDataException("Скорость не может быть меньше нуля!");
        }
        this.model = model;
        this.speed = speed;
        this.typeOfEngine = typeOfEngine;
    }

    public void infoAboutVehicle() {
        System.out.printf("Тип транспортного средства: %s\n", getTransportType());
        System.out.printf("Модель - %s, скорость - %s км/ч\n", model, speed);
        if (typeOfEngine != null && typeOfEngine.getFuelType() != Fuel.NONE) {
            System.out.println(typeOfEngine.engineDescription());
        }
    }

    public abstract String getTransportType();

    public abstract void move();

    public void stop() {
        System.out.println("Остановка!");
    }

    public void setSpeed(double newSpeed) {
        if (newSpeed < 0.) {
            throw new InvalidVehicleDataException("Скорость не может быть меньше нуля!");
        }
        speed = newSpeed;
    }

    public double getSpeed() {
        return speed;
    }
}
