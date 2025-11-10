package task_6;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Реализует кассира – отдельный поток, обрабатывающий очередь транзакций
 * Каждый кассир берёт {@link Transaction} из общей очереди банка и выполняет её в зависимости от типа.
 */
public class Cashier extends Thread {
    private final int id;
    private final Bank bank;
    private volatile boolean active = true;

    /**
     * Создаёт кассира.
     *
     * @param id   номер кассы
     * @param bank ссылка на банк, в котором работает кассир
     */
    public Cashier(int id, Bank bank) {
        this.id = id;
        this.bank = bank;
    }

    /**
     * Останавливает работу кассира, прерывая поток.
     */
    public void stopCashier() {
        active = false;
        this.interrupt();
    }

    /** Осуществляет операцию пополнения счета клиента
     *
     * @param clientId id клиента
     * @param amount сумма пополнения
     */
    private void deposit(int clientId, double amount) {
        try {
            bank.getClients().get(clientId).increaseBalance(amount);
            bank.notifyObservers("Касса " + id + " пополнила счет клиента " + clientId + " на сумму " + amount);
        } catch (Exception e) {
            bank.notifyObservers("Возникла ошибка: " + e);
        }
    }

    /** Осуществляет списание средств со счета клиента
     *
     * @param clientId id клиента
     * @param amount сумма списания
     */
    private void withdraw(int clientId, double amount) {
        try {
            bank.getClients().get(clientId).decreaseBalance(amount);
            bank.notifyObservers("Касса " + id + " выдала клиенту " + clientId + " сумму " + amount);
        } catch (Exception e) {
            bank.notifyObservers("Возникла ошибка: " + e);
        }
    }

    /**
     * Выполняет обмен валюты.
     *
     * @param clientId id клиента
     * @param fromCurrency исходная валюта (должна совпадать с валютой счёта)
     * @param toCurrency целевая валюта
     * @param amount сумма в исходной валюте
     */
    private void exchangeCurrency(int clientId, String fromCurrency, String toCurrency, double amount) {
        Client client = bank.getClients().get(clientId);

        if (client == null) {
            bank.notifyObservers("Клиент " + clientId + " не найден.");
            return;
        }

        if (!client.getCurrency().equals(fromCurrency)) {
            bank.notifyObservers("Ошибка: валюта счета клиента (" + client.getCurrency() +
                    ") не совпадает с валютой для обмена (" + fromCurrency + ").");
            return;
        }

        ConcurrentHashMap<String, Double> rates = bank.getExchangeRate();

        if (!rates.containsKey(toCurrency)) {
            bank.notifyObservers("Валюта (" + toCurrency + ") не найдена.");
            return;
        }

        double converted = amount * (rates.get(fromCurrency) / rates.get(toCurrency));

        if (client.getBalance() >= amount) {
            client.decreaseBalance(amount);
            client.increaseBalance(converted);
            client.setCurrency(toCurrency);
            bank.notifyObservers("Касса " + id + " произвела обмен " + amount + " " + fromCurrency +
                    " на " + converted + " " + toCurrency + " для клиента " + clientId);
        } else {
            bank.notifyObservers("На счету клиента " + clientId + " недостаточно средств для обмена.");
        }
    }

    /**
     * Выполняет перевод денег от одного клиента к другому.
     *
     * @param senderId id отправителя
     * @param receiverId id отправителя
     * @param amount сумма перевода
     */
    private void transferFunds(int senderId, int receiverId, double amount) {
        Client sender = bank.getClients().get(senderId);
        Client receiver = bank.getClients().get(receiverId);
        if (sender == null || receiver == null) {
            bank.notifyObservers("Невозможно осуществить перевод: неверный ID клиента");
            return;
        }

        if (!sender.getCurrency().equals(receiver.getCurrency())) {
            bank.notifyObservers("Невозможно осуществить перевод: валюты клиентов не совпадают");
            return;
        }

        if (sender.getBalance() < amount) {
            bank.notifyObservers("Невозможно осуществить перевод: недостаточно средств на счету клиента " + senderId);
            return;
        }

        sender.decreaseBalance(amount);
        receiver.increaseBalance(amount);
        bank.notifyObservers("Касса " + id + " осуществила перевод " + amount + " от клиента " + senderId + " к клиенту " + receiverId);
    }

    /**
     * Цикл работы кассира - берет транзакции из очереди и выполняет их.
     * Работает, пока {@link #active} == true.
     */
    @Override
    public void run() {
        while (active) {
            try {
                Transaction transaction = bank.getTransactionQueue().take();
                executeTransaction(transaction);
            } catch (InterruptedException e) {
                bank.notifyObservers("Работа кассы " + id + " прервана");
                Thread.currentThread().interrupt();
            }
        }
    }

    /** Выбирает нужный метод в зависимости от типа транзакции. */
    private void executeTransaction(Transaction transaction) {
        switch (transaction.type) {
            case DEPOSIT -> deposit(transaction.senderId, transaction.amount);
            case WITHDRAW -> withdraw(transaction.senderId, transaction.amount);
            case TRANSFER -> transferFunds(transaction.senderId, transaction.receiverId, transaction.amount);
            case EXCHANGE -> exchangeCurrency(transaction.senderId, transaction.fromCurrency, transaction.toCurrency, transaction.amount);

        }
    }


}
