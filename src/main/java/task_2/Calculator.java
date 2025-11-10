package task_2;

/**
 * Реализует калькулятор, который умеет выполнять следующие арифметические операции: +, -, *, /, %, //, ^.
 * При недопустимом операторе выбрасывает UnsupportedOperatorException.
 */
public class Calculator {
    /**
     * Вычисляет результат выражения для двух операндов и оператора.
     *
     * @param a Первый операнд.
     * @param b Второй операнд.
     * @param operator Оператор ("+", "-", "*", "/", "%", "//", "^").
     * @return Результат вычисления.
     */
    public double compute(double a, double b, String operator) {
        Operation operation;
        switch (operator) {
            case "+" -> operation = new Sum(a, b);
            case "-" -> operation = new Subtract(a, b);
            case "*" -> operation = new Multiply(a, b);
            case "/" -> operation = new Divide(a, b);
            case "%" -> operation = new Module(a, b);
            case "^" -> operation = new Pow(a, b);
            case "//" -> operation = new IntegerDivide(a, b);
            default -> throw new UnsupportedOperatorException("Недопустимый оператор!");
        }

        return operation.calculate();
    }
}

class UnsupportedOperatorException extends RuntimeException {
    public UnsupportedOperatorException(String message) {
        super(message);
    }
}
