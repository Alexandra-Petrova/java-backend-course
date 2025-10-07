package task_3;

public class Engine {
    protected Fuel fuelType;
    protected double power;

    public Engine(Fuel fuelType, double power) {
        this.fuelType = fuelType;
        this.power = power;
    }

    public String engineDescription() {
        String fuel = switch (fuelType) {
            case NONE -> "нет";
            case PETROL -> "бензин";
            case ELECTRICITY -> "электричество";
            case DIESEL -> "дизель";
        };
       return "Данные двигателя: топливо - " + fuel + ", мощность - " + power + "л.с.";
    }

    public Fuel getFuelType() {
        return fuelType;
    }
}
