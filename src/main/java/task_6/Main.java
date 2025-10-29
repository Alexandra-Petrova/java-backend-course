package task_6;

import java.util.*;
import java.util.concurrent.*;

/**
 * Класс для демонстрации работы банковской системы.
 *
 * <p>Программа выполняет следующие действия:
 * <ol>
 *   <li>Создание экземпляра банка и добавление логгера.</li>
 *   <li>Инициализация валют и их курсов.</li>
 *   <li>Создание клиентов с начальными балансами.</li>
 *   <li>Создание и запуск касс (потоков для обработки транзакций).</li>
 *   <li>Добавление транзакций для выполнения (депозит, снятие, перевод, обмен валют).</li>
 *   <li>Демонстрация работы с новой валютой.</li>
 *   <li>Проверка логирования ошибок (недостаток средств, несуществующие клиенты, некорректные валюты).</li>
 * </ol>
 */

public class Main {
    public static void main(String[] args) {

        Bank bank = new Bank();
        bank.addObserver(new Logger());

        bank.addCurrency("USD", 80.0);
        bank.addCurrency("EUR", 90.0);
        bank.addCurrency("RUB", 1);

        System.out.println("\nСистема валют и курсов инициализирована >>>");
        bank.getExchangeRate().forEach((code, rate) ->
                System.out.printf("Валюта: %s, Курс к RUB: %s", code, rate)
        );

        bank.addClient(new Client(1, 50000, "RUB"));
        bank.addClient(new Client(2, 2000, "EUR"));
        bank.addClient(new Client(3, 10000, "RUB"));

        System.out.println("\nИсходные балансы клиентов >>>");
        bank.getClients().forEach((id, client) -> System.out.println(client.toString()));

        for (int i = 1; i <= 3; i++) {
            Cashier cashier = new Cashier(i, bank);
            bank.addCashier(cashier);
            cashier.start();
        }

        bank.addTransaction(new Transaction(Transaction.transactionType.DEPOSIT, 1, 0, 2000, "RUB", "RUB"));
        bank.addTransaction(new Transaction(Transaction.transactionType.WITHDRAW, 2, 0, 500, "EUR", "EUR"));
        bank.addTransaction(new Transaction(Transaction.transactionType.TRANSFER, 1, 3, 100, "RUB", "RUB"));
        bank.addTransaction(new Transaction(Transaction.transactionType.EXCHANGE, 3, 0, 100, "RUB", "USD"));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Поток main был прерван. Продолжаем работу.");
        }

        System.out.println("\nБалансы клиентов >>>");
        bank.getClients().forEach((id, client) -> System.out.println(client));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Поток main был прерван. Продолжаем работу.");
        }

        // Добавляем новую валюту во время работы программы
        System.out.println("\nДобавляем новую валюту MNT (монгольский тугрик)");
        bank.addCurrency("MNT", 45);

        bank.addTransaction(new Transaction(Transaction.transactionType.EXCHANGE, 1, 0, 2000, "RUB", "MNT"));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Поток main был прерван. Продолжаем работу.");
        }

        System.out.println("\nПроверка исключений >>>");
        // Попытка обмена валюты, которой нет на счете
        bank.addTransaction(new Transaction(Transaction.transactionType.EXCHANGE, 3, 0, 50, "EUR", "USD"));

        // Попытка обмена на несуществующую валюту
        bank.addTransaction(new Transaction(Transaction.transactionType.EXCHANGE, 1, 0, 100, "MNT", "GBP"));

        // Попытка перевода между клиентами с разными валютами
        bank.addTransaction(new Transaction(Transaction.transactionType.TRANSFER, 2, 3, 100, "EUR", "EUR"));

        // Попытка перевода несуществующему клиенту
        bank.addTransaction(new Transaction(Transaction.transactionType.TRANSFER, 1, 999, 100, "RUB", "RUB"));

        // Попытка добавления null транзакции
        bank.addTransaction(null);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Поток main был прерван. Продолжаем работу.");
        }

        for (Cashier cashier : bank.getCashiers()) {
            cashier.stopCashier();
        }

        bank.executor.shutdown();

        System.out.println("\nИтоговые балансы клиентов >>>");
        bank.getClients().forEach((id, client) -> System.out.println(client.toString()));
    }
}
