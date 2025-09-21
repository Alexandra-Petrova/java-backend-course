package task_1;

import java.util.Scanner;

public class task_1_2 {
    public static void main(String[] args) {
        String expression;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Поддерживаемые типы операций: +, -, *, /, %, //, ^");
        System.out.print("Введите выражение: ");
        while (!(expression = scanner.nextLine()).equals("exit")) {
            String[] userArray = isExpressionCorrect(expression);
            if (userArray != null) {
                double result = calculate(userArray);
                if (!Double.isNaN(result)) {
                    System.out.println("Результат: " + calculate(userArray));
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
     * @param userExpression Строка с выражением.
     * @return Массив типа String с числами и оператором,
     * null в случае невыполнения одного из заданных условий.
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
     * Находит результат выражения, указанного в массиве.
     *
     * @param expParts Массив с данными выражения.
     * @return Результат вычисления.
     */
    public static double calculate (String[] expParts) {
        double firstNumber = Double.parseDouble(expParts[0]);
        double secondNumber = Double.parseDouble(expParts[2]);
        double result = Double.NaN;
        switch (expParts[1]) {
            case ("+"): {
                result = sum(firstNumber, secondNumber);
                break;
            }
            case ("-"): {
                result = subtract(firstNumber, secondNumber);
                break;
            }
            case ("*"): {
                result = multiply(firstNumber, secondNumber);
                break;
            }
            case ("/"): {
                result = divide(firstNumber, secondNumber);
                break;
            }
            case ("%"): {
                result = module(firstNumber, secondNumber);
                break;
            }
            case ("^"): {
                result = pow(firstNumber, secondNumber);
                break;
            }
            case ("//"): {
                result = integerDivide(firstNumber, secondNumber);
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
     * @return Частное от деления чисел a и b.
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
     * @return Остаток от деления чисел a и b.
     */
    public static double module (double a, double b) {
        return a % b;
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
     * Вычисляет целочисленный остаток при делении двух чисел.
     *
     * @param a Делимое.
     * @param b Делитель.
     * @return Целочисленный остаток от деления чисел a и b.
     */
    public static double integerDivide (double a, double b) {
        if (b == 0) {
            return Double.NaN;
        } else {
            return Math.floor(a / b);
        }
    }
}
