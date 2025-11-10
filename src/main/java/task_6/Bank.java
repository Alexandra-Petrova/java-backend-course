package task_6;

import java.util.*;
import java.util.concurrent.*;

/**
 * Реализует банк.
 * Хранит клиентов, кассиров, курсы валют, очередь транзакций и планировщик автоматического обновления курсов валют.
 */
public class Bank {
    private final ConcurrentHashMap<Integer, Client> clients = new ConcurrentHashMap<>();
    private final List<Cashier> cashiers = new ArrayList<>();
    private final ConcurrentHashMap<String, Double> exchangeRates = new ConcurrentHashMap<>();
    private final BlockingQueue<Transaction> transactionQueue = new LinkedBlockingQueue<>();
    public final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    private final List<Observer> observers = new CopyOnWriteArrayList<>();

    /**
     * Создает банк.
     * Запускает автоматическое обновление курсов валют с заданным интервалом.
     */
    public Bank() {
        executor.scheduleAtFixedRate(() -> {
            try {
                exchangeRates.forEach((k, v) -> {
                    double delta = v * (Math.random() * 0.04 - 0.02);
                    exchangeRates.put(k, v + delta);
                });
                notifyObservers("Курсы валют обновлены: " + exchangeRates);
            } catch (Exception e) {
                notifyObservers("Возникла ошибка при обновлении курсов валют: " + e.getMessage());
            }
            }, 1, 2, TimeUnit.SECONDS);
    }

    /**
     * Добавляет нового клиента.
     *
     * @param client клиент, который будет добавлен в банк
     */
    public void addClient(Client client) {
        clients.put(client.getId(), client);
    }

    /**
     * Добавляет нового кассира.
     *
     * @param cashier кассир, который будет добавлен в банк
     */
    public void addCashier(Cashier cashier) {
        cashiers.add(cashier);
    }

    /**
     * Добавляет наблюдателя.
     *
     * @param observer наблюдатель, получающий уведомление о событиях
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Рассылает сообщение всем зарегистрированным наблюдателям.
     *
     * @param message текст сообщения
     */
    public void notifyObservers(String message) {
        for (Observer o: observers) {
            o.update(message);
        }
    }

    /**
     * Добавляет новую валюту и ее курс
     *
     * @param currency код валюты
     * @param rate курс
     */
    public void addCurrency(String currency, double rate) {
        exchangeRates.putIfAbsent(currency, rate);
        notifyObservers("Добавлена валюта: " + currency + " = " + rate);
    }

    /** @return очередь транзакций */
    public BlockingQueue<Transaction> getTransactionQueue() {
        return transactionQueue;
    }

    /** @return карту клиентов */
    public ConcurrentHashMap<Integer, Client> getClients() {
        return clients;
    }

    /** @return карту валют */
    public ConcurrentHashMap<String, Double> getExchangeRate() {
        return exchangeRates;
    }

    /**
     * Добавляет новую транзакцию в очередь.
     *
     * @param transaction транзакция
     */
    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            notifyObservers("Попытка добавить пустую транзакцию: null");
            return;
        }

        String transactionDescription = switch (transaction.type) {
            case DEPOSIT -> "пополнение";
            case WITHDRAW -> "снятие";
            case TRANSFER -> "перевод";
            case EXCHANGE -> "обмен валюты";
        };

        transactionQueue.add(transaction);
        notifyObservers("Добавлена транзакция: " + transactionDescription);
    }

    /** @return список кассиров */
    public List<Cashier> getCashiers() {
        return cashiers;
    }
}
