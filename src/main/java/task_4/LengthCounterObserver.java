package task_4;

public class LengthCounterObserver implements Observer{
    private final String name;

    public LengthCounterObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String newValue) {
        System.out.printf("%s, обновлено значение: %s\n", name, newValue.length());
    }

    @Override
    public String getName() {
        return name;
    }
}
