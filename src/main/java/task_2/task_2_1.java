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
            try {
                // Вызов функции для проверки корректности введенного выражения
                String[] userArray = Validator.isExpressionCorrect(expression);

                double a = Double.parseDouble(userArray[0]);
                double b = Double.parseDouble(userArray[2]);
                String operator = userArray[1];

                // Вызов функции для нахождения значения выражения
                double result = calculator.compute(a, b, operator);
                System.out.println("Результат: " + result);
            } catch (InvalidExpressionException | DivisionByZeroException | UnsupportedOperatorException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Непредвиденная ошибка: " + e.getMessage());
            }
        }
        System.out.println("Выход из программы...");
        scanner.close();
    }
}
