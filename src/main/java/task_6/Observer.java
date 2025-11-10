package task_6;

/** Реализует интерфейс наблюдателя */
public interface Observer {
    void update(String newValue);
}

/**
 * Реализует логгер, который выводит сообщения в консоль
 */
class Logger implements Observer {

    @Override
    public void update(String message) {
        System.out.printf("Log: %s\n", message);
    }
}
