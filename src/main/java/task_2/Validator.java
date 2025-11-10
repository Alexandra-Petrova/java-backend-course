package task_2;

/**
 * Реализует проверку выражения, введенного пользователем.
 */
public class Validator {
    /**
     * Проверяет корректность выражения: его длину, тип данных, тип операций.
     * При некорректных данных выбрасывает InvalidExpressionException.
     *
     * @param userExpression Строка с выражением вида "число оператор число".
     * @return Массив типа String с числами и оператором.
     */
    public static String[] isExpressionCorrect (String userExpression) {
        String[] parts = userExpression.trim().split(" ");
        if (parts.length != 3) {
            throw new InvalidExpressionException("Некорректная длина выражения!");
        }
        try {
            Double.parseDouble(parts[0]);
            Double.parseDouble(parts[2]);
        } catch (NumberFormatException e) {
            throw new InvalidExpressionException("Некорректные операнды!");
        }
        if (!parts[1].equals("+") && !parts[1].equals("-") && !parts[1].equals("*") && !parts[1].equals("/")
                && !parts[1].equals("%") && !parts[1].equals("//") && !parts[1].equals("^")) {
            throw new InvalidExpressionException("Недопустимый оператор!");
        }
        return parts;
    }
}

class InvalidExpressionException extends RuntimeException {
    public InvalidExpressionException(String message) {
        super(message);
    }
}
