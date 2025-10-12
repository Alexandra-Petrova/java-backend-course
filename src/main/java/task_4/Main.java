package task_4;

public class Main {
    public static void main(String[] args) {
        ObservableStringBuilder stringBuilder = new ObservableStringBuilder();

        Observer firstObserver = new ExpressionObserver("Первый наблюдатель");
        Observer secondObserver = new LengthCounterObserver("Второй наблюдатель");

        stringBuilder.registerObserver(firstObserver);
        stringBuilder.registerObserver(secondObserver);

        stringBuilder.append("Ваш заказ создан.");
        stringBuilder.insert(9, " №523");
        stringBuilder.replace(15, 22,"в пути.");

        stringBuilder.removeObserver(secondObserver);
        System.out.printf("\n%s: подписка на изменения отменена!\n\n", secondObserver.getName());

        stringBuilder.delete(15, 22).append("доставлен.");
        System.out.printf("Длина текущего сообщения - %s.", stringBuilder.length());
    }
}
