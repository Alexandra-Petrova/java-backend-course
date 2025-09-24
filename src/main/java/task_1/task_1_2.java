package task_1;

import java.util.Scanner;

public class task_1_2 {
    public static void main(String[] args) {
        String expression;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Поддерживаемые типы операций: +, -, *, /, %, //, ^.");
        System.out.print("Введите выражение (десятичные числа вводятся через точку): ");

        while (!(expression = scanner.nextLine()).equals("exit")) {
            // Вызов функции для проверки корректности введенного выражения
            String[] userArray = isExpressionCorrect(expression);
            if (userArray != null) {
                double a = Double.parseDouble(userArray[0]);
                double b = Double.parseDouble(userArray[2]);
                String operator = userArray[1];
                // Вызов функции для нахождения значения выражения
                double result = calculate(a, b, operator);
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

    /**
     * Проверяет корректность выражения: его длину, тип данных, тип операций.
     *
     * @param userExpression Строка с выражением вида "число оператор число".
     * @return Массив типа String с числами и оператором, либо null в случае некорректного ввода.
     */
    public static String[] isExpressionCorrect (String userExpression) {
        String[] parts = userExpression.trim().split(" ");
        if (parts.length != 3) {
            return null;
        }
        try {
            Double.parseDouble(parts[0]);
            Double.parseDouble(parts[2]);
        } catch (NumberFormatException e) {
            return null;
        }
        if (!parts[1].equals("+") && !parts[1].equals("-") && !parts[1].equals("*") && !parts[1].equals("/")
                && !parts[1].equals("%") && !parts[1].equals("//") && !parts[1].equals("^")) {
            return null;
        }
        return parts;
    }

    /**
     * Вычисляет результат выражения для двух операндов и оператора.
     *
     * @param a Первый операнд.
     * @param b Второй операнд.
     * @param operator Оператор ("+", "-", "*", "/", "%", "//", "^").
     * @return Результат вычисления или NaN при делении на ноль.
     */
    public static double calculate (double a, double b, String operator) {
        double result = Double.NaN;
        switch (operator) {
            case ("+"): {
                result = sum(a, b);
                break;
            }
            case ("-"): {
                result = subtract(a, b);
                break;
            }
            case ("*"): {
                result = multiply(a, b);
                break;
            }
            case ("/"): {
                result = divide(a, b);
                break;
            }
            case ("%"): {
                result = module(a, b);
                break;
            }
            case ("^"): {
                result = pow(a, b);
                break;
            }
            case ("//"): {
                result = integerDivide(a, b);
                break;
            }
        }
        return result;
    }

    /**
     * Вычисляет сумму двух чисел.
     *
     * @param a Первое слагаемое.
     * @param b Второе слагаемое.
     * @return Сумма чисел a и b.
     */
    public static double sum (double a, double b) {
        return a + b;
    }

    /**
     * Вычисляет разность двух чисел.
     *
     * @param a Уменьшаемое.
     * @param b Вычитаемое.
     * @return Разность чисел a и b.
     */
    public static double subtract (double a, double b) {
        return a - b;
    }

    /**
     * Вычисляет произведение двух чисел.
     *
     * @param a Первый множитель.
     * @param b Второй множитель.
     * @return Произвдение чисел a и b.
     */
    public static double multiply (double a, double b) {
        return a * b;
    }

    /**
     * Вычисляет частное от деления двух чисел.
     *
     * @param a Делимое.
     * @param b Делитель.
     * @return Частное от деления, либо NaN при делении на ноль
     */
    public static double divide (double a, double b) {
        if (b == 0) {
            return Double.NaN;
        } else {
            return a / b;
        }

    }

    /**
     * Вычисляет остаток от деления двух чисел.
     *
     * @param a Делимое.
     * @param b Делитель.
     * @return Остаток от деления чисел a и b, либо NaN при делении на ноль.
     */
    public static double module (double a, double b) {
        if (b == 0) {
            return Double.NaN;
        } else {
            return a % b;
        }
    }

    /**
     * Возводит число в степень.
     *
     * @param a Основание степени.
     * @param b Показатель степени.
     * @return Число a в стенени b.
     */
    public static double pow (double a, double b) {
        return Math.pow(a, b);
    }

    /**
     * Находит целую часть от деления двух чисел.
     *
     * @param a Делимое.
     * @param b Делитель.
     * @return Целая часть от деления чисел a и b, либо NaN при делении на ноль.
     */
    public static double integerDivide (double a, double b) {
        if (b == 0) {
            return Double.NaN;
        } else {
            return Math.floor(a / b);
        }
    }
}
