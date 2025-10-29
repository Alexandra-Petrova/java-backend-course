package task_6;

/**
 * Реализует клиента банка. Класс хранит идентификатор, баланс и код валюты.
 */
public class Client {
    private final int id;
    private double balance;
    private String currency;

    /**
     * Создаёт клиента с заданными параметрами.
     *
     * @param id       уникальный идентификатор клиента
     * @param balance  начальный баланс
     * @param currency код валюты
     */
    public Client(int id, double balance, String currency) {
        this.id = id;
        this.balance = balance;
        this.currency = currency;
    }

    /** @return идентификатор клиента */
    public int getId() {
        return id;
    }

    /** @return баланс клиента */
    public double getBalance() {
        return balance;
    }

    /** @return валюту счета клиента */
    public String getCurrency() {
        return currency;
    }

    /**
     * Увеличивает баланс на указанную сумму.
     *
     * @param amount сумма пополнения
     */
    public synchronized void increaseBalance(double amount) {
        balance += amount;
    }

    /**
     * Уменьшает баланс на указанную сумму.
     *
     * @param amount сумма списания
     */
    public synchronized void decreaseBalance(double amount) {
        balance -= amount;
    }

    /**
     * Меняет валюту счёта после обмена валют.
     *
     * @param currency новый код валюты
     */
    public synchronized void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return String.format("Client %d: %.2f %s", id, balance, currency);
    }
}
