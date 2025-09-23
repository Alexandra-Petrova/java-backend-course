package task_2;

public class Calculator {
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
            default -> {
                return Double.NaN;
            }
        }

        return operation.calculate();
    }
}
