package task_1;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class task_1_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();
        if (size <= 0) {
            System.out.println("Ошибка - размер массива должен быть больше 0!");
            return;
        }

        System.out.print("Выберите тип массива (1 - int, 2 - double): ");
        int type = scanner.nextInt();

        if (type == 1) {
            int minValue;
            int maxValue;
            System.out.print("Введите минимальное значение: ");
            try {
                minValue = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка - границы массива для выбранного типа должны быть целочисленными!");
                return;
            }

            System.out.print("Введите максимальное значение: ");
            try {
                maxValue = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка - границы массива для выбранного типа должны быть целочисленными!");
                return;
            }

            if (minValue > maxValue) {
                System.out.println("Ошибка - минимальное значение больше максимального!");
                return;
            }

            if (minValue == maxValue) {
                System.out.println("Ошибка - некорректные значения границ массива!");
                return;
            }

            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = (int) (Math.random() * (maxValue - minValue + 1) + minValue);
            }
            System.out.println("Массив: ");
            for (int val : array) System.out.print(val + " ");
            System.out.println();

            System.out.println("Максимальное значение: " + getMax(array));
            System.out.println("Минимальное значение: " + getMin(array));
            System.out.println("Среднее значение: " + getAverage(array));

            sortArrayAscending(array);
            System.out.println("Сортировка по возрастанию: ");
            for (int val : array) System.out.print(val + " ");
            System.out.println();

            sortArrayDescending(array);
            System.out.println("Сортировка по убыванию: ");
            for (int val : array) System.out.print(val + " ");
            System.out.println();

        } else if (type == 2) {
            System.out.print("Введите минимальное значение: ");
            double minValue = scanner.nextDouble();

            System.out.print("Введите максимальное значение: ");
            double maxValue = scanner.nextDouble();

            if (minValue > maxValue) {
                System.out.println("Ошибка - минимальное значение больше максимального!");
                return;
            }

            if (minValue == maxValue) {
                System.out.println("Ошибка - некорректные значения границ массива!");
                return;
            }

            double[] array = new double[size];
            for (int i = 0; i < size; i++) {
                array[i] = Math.random() * (maxValue - minValue) + minValue;
            }
            System.out.println("Массив: ");
            for (double val : array) System.out.print(val + " ");
            System.out.println();

            System.out.println("Максимальное значение: " + getMax(array));
            System.out.println("Минимальное значение: " + getMin(array));
            System.out.println("Среднее значение: " + getAverage(array));

            sortArrayAscending(array);
            System.out.println("Сортировка по возрастанию: ");
            for (double val : array) System.out.print(val + " ");
            System.out.println();

            sortArrayDescending(array);
            System.out.println("Сортировка по убыванию: ");
            for (double val : array) System.out.print(val + " ");
            System.out.println();

        } else {
            System.out.println("Неверный тип массива!");
        }

        scanner.close();
    }

    public static int getMax(int[] array) {
        int max = array[0];
        for (int value : array) {
            if (value > max) max = value;
        }
        return max;
    }

    public static int getMin(int[] array) {
        int min = array[0];
        for (int value : array) {
            if (value < min) min = value;
        }
        return min;
    }

    public static double getAverage(int[] array) {
        double sum = 0;
        for (int value : array) sum += value;
        return sum / array.length;
    }

    public static void sortArrayDescending(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] < array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void sortArrayAscending(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static double getMax(double[] array) {
        double max = array[0];
        for (double value : array) {
            if (value > max) max = value;
        }
        return max;
    }

    public static double getMin(double[] array) {
        double min = array[0];
        for (double value : array) {
            if (value < min) min = value;
        }
        return min;
    }

    public static double getAverage(double[] array) {
        double sum = 0;
        for (double value : array) sum += value;
        return sum / array.length;
    }

    public static void sortArrayDescending(double[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] < array[j + 1]) {
                    double temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void sortArrayAscending(double[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    double temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
