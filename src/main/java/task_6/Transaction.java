package task_6;

/**
 * Реализует транзакцию – описание одной банковской операции.
 * Содержит тип операции и параметры, необходимые для их выполнения.
 */
public class Transaction {
    public enum transactionType {DEPOSIT, WITHDRAW, TRANSFER, EXCHANGE}
    public transactionType type;
    public int senderId;
    public int receiverId;
    public double amount;
    public String fromCurrency;
    public String toCurrency;

    /**
     * Создает транзакцию.
     *
     * @param type тип операции
     * @param senderId id отправителя или клиента при осуществлении операций DEPOSIT, WITHDRAW или EXCHANGE
     * @param receiverId id получателя (для TRANSFER не 0, иначе 0)
     * @param amount сумма
     * @param fromCurrency исходная валюта (для EXCHANGE)
     * @param toCurrency целевая валюта (для EXCHANGE)
     */
    public Transaction (transactionType type, int senderId, int receiverId, double amount, String fromCurrency,
                        String toCurrency) {
        this.type = type;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }
}
