package task_2;

/**
 * Реализует проверку выражения, введенного пользователем.
 */
public class Validator {
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
}
