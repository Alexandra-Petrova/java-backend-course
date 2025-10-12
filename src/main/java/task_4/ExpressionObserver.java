package task_4;

public class ExpressionObserver implements Observer {
    private final String name;

    public ExpressionObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String newValue) {
        System.out.printf("%s, обновлено значение: %s\n", name, newValue);
    }

    @Override
    public String getName() {
        return name;
    }
}
