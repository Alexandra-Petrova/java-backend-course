package task_2;

/**
 * Абстрактный класс для арифметических операций с двумя числами.
 * Содержит операнды и абстрактный метод {@link #calculate()}.
 */
public abstract class Operation {
    double a, b;

    public Operation (double a, double b) {
        this.a = a;
        this.b = b;
    }

    public abstract double calculate();
}

/**
 * Выполняет операцию сложения двух чисел.
 */
class Sum extends Operation {
    /**
     * Создает объект операции сложения.
     *
     * @param a Первое слагаемое.
     * @param b Второе слагаемое.
     */
    public Sum(double a, double b) {
        super(a, b);
    }

    /**
     * Вычисляет сумму двух чисел.
     *
     * @return Сумма чисел a и b.
     */
    @Override
    public double calculate() {
        return a + b;
    }
}

/**
 * Выполняет операцию вычитания двух чисел.
 */
class Subtract extends Operation {
    /**
     * Создает объект операции вычитания.
     *
     * @param a Уменьшаемое.
     * @param b Вычитаемое.
     */
    public Subtract(double a, double b) {
        super(a, b);
    }

    /**
     * Вычисляет разность двух чисел.
     *
     * @return Разность чисел a и b.
     */
    @Override
    public double calculate() {
        return a - b;
    }
}

/**
 * Выполняет операцию умножения двух чисел.
 */
class Multiply extends Operation{
    /**
     * Создает объект операции умножения.
     *
     * @param a Первый множитель.
     * @param b Второй множитель.
     */
    public Multiply(double a, double b) {
        super(a, b);
    }

    /**
     * Вычисляет произведение двух чисел.
     *
     * @return Произведение чисел a и b.
     */
    @Override
    public double calculate() {
        return a * b;
    }
}

/**
 * Выполняет операцию деления двух чисел.
 * Если делитель равен нулю, выбрасывает DivisionByZeroException.
 */
class Divide extends Operation {
    /**
     * Создает объект операции деления.
     *
     * @param a Делимое.
     * @param b Делитель.
     */
    public Divide(double a, double b) {
        super(a, b);
    }

    /**
     * Вычисляет частное от деления двух чисел.
     *
     * @return Частное от деления
     */
    @Override
    public double calculate() {
        if (b == 0) {
            throw new DivisionByZeroException("Ошибка! Деление на ноль.");
        } else {
            return a / b;
        }
    }
}

/**
 * Выполняет операцию возведения числа в степень.
 */
class Pow extends Operation {
    /**
     * Создает объект операции возведения в степень.
     *
     * @param a Основание степени.
     * @param b Показатель степени.
     */
    public Pow(double a, double b) {
        super(a, b);
    }

    /**
     * Возводит число в степень.
     *
     * @return Число a в степени b.
     */
    @Override
    public double calculate() {
        return Math.pow(a, b);
    }
}

/**
 * Выполняет операцию целочисленного деления двух чисел.
 * Если делитель равен нулю, выбрасывает DivisionByZeroException.
 */
class IntegerDivide extends Operation {
    /**
     * Создает объект операции целочисленного деления двух чисел.
     *
     * @param a Делимое.
     * @param b Делитель.
     */
    public IntegerDivide(double a, double b) {
        super (a, b);
    }

    /**
     * Находит целую часть от деления двух чисел.
     *
     * @return Целая часть от деления чисел a и b.
     */
    @Override
    public double calculate() {
        if (b == 0) {
            throw new DivisionByZeroException("Ошибка! Деление на ноль.");
        } else {
            return Math.floor(a / b);
        }
    }
}

/**
 * Выполняет операцию нахождения остатка от деления двух чисел.
 * Если делитель равен нулю, выбрасывает DivisionByZeroException.
 */
class Module extends Operation {
    /**
     * Создает объект операции нахождения остатка от деления двух чисел.
     *
     * @param a Делимое.
     * @param b Делитель.
     */
    public Module(double a, double b) {
        super(a, b);
    }

    /**
     * Вычисляет остаток от деления двух чисел.
     *
     * @return Остаток от деления чисел a и b.
     */
    @Override
    public double calculate() {
        if (b == 0) {
            throw new DivisionByZeroException("Ошибка! Деление на ноль.");
        } else {
            return a % b;
        }
    }
}

class DivisionByZeroException extends RuntimeException {
    public DivisionByZeroException(String message) {
        super(message);
    }
}
