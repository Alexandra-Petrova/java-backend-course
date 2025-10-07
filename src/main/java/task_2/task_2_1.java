package task_2;

import java.util.Scanner;

public class task_2_1 {
    public static void main(String[] args) {
        String expression;
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.println("Поддерживаемые типы операций: +, -, *, /, %, //, ^. ");
        System.out.print("Введите выражение (десятичные числа вводятся через точку): ");
        while (!(expression = scanner.nextLine()).equals("exit")) {
            // Вызов функции для проверки корректности введенного выражения
            String[] userArray = Validator.isExpressionCorrect(expression);
            if (userArray != null) {
                double a = Double.parseDouble(userArray[0]);
                double b = Double.parseDouble(userArray[2]);
                String operator = userArray[1];
                // Вызов функции для нахождения значения выражения
                double result = calculator.compute(a, b, operator);
                // Обработка результатов вычислений
                if (!Double.isNaN(result)) {
                    System.out.println("Результат: " + result);
                } else {
                    System.out.println("Ошибка! Деление на ноль.");
                }
            } else {
                System.out.println("Некорректное выражение! Введите еще раз.");
            }
        }
        System.out.println("Выход из программы...");
        scanner.close();
    }
}
