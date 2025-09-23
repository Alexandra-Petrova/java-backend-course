package task_2;

public class Validator {
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
