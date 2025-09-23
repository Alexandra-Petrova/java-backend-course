package task_2;

import java.util.Scanner;

import static task_2.Validator.isExpressionCorrect;

public class task_2_1 {
    public static void main(String[] args) {
        String expression;
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.println("Поддерживаемые типы операций: +, -, *, /, %, //, ^");
        System.out.print("Введите выражение: ");
        while (!(expression = scanner.nextLine()).equals("exit")) {

            String[] userArray = isExpressionCorrect(expression);
            if (userArray != null) {
                double result = calculator.compute(Double.parseDouble(userArray[0]), Double.parseDouble(userArray[2]), userArray[1]);
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
