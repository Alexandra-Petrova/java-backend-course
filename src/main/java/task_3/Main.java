package task_3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            run();
        } catch (InvalidVehicleDataException e) {
            System.out.println("Ошибка при создании транспортного средства. " + e.getMessage());
        }
    }

    private static void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Какой тип транспортного средства вы хотите создать (1 - автомобиль, 2 - велосипед, 3 - корабль, 4 - самолет)?");
        System.out.print("Введите соответствующую цифру: ");
        int typeOfTransport = readIntValueInRange(
                scanner, 1, 4, "Некорректный тип транспортного средства!");
        scanner.nextLine();

        System.out.print("Введите модель: ");
        String model = scanner.nextLine();

        System.out.print("Введите скорость (км/ч): ");
        double speed = readPositiveDouble(scanner, "Некорректное значение скорости!");

        Vehicle vehicle;

        if (typeOfTransport == 2) {
            vehicle = new Bicycle(model, speed);
        } else {
            System.out.println("Выберите тип топлива (1 - бензин, 2 - электричество, 3 - дизель, 4 - нет топлива):");
            System.out.print("Введите соответствующую цифру: ");
            int typeOfFuel = readIntValueInRange(
                    scanner, 1, 4, "Некорректный тип топлива!");

            Fuel fuel = switch (typeOfFuel) {
                case 1 -> Fuel.PETROL;
                case 2 -> Fuel.ELECTRICITY;
                case 3 -> Fuel.DIESEL;
                default -> Fuel.NONE;
            };

            System.out.print("Введите мощность двигателя (л.с.): ");
            double power = readPositiveDouble(scanner, "Некорректное значение мощности двигателя!");
            Engine engine = new Engine(fuel, power);

            switch (typeOfTransport) {
                case 1 -> vehicle = new Car(model, speed, engine);
                case 3 -> vehicle = new Ship(model, speed, engine);
                case 4 -> vehicle = new AirPlane(model, speed, engine);
                default -> {
                    System.out.println("Некорректный тип транспорта!");
                    scanner.close();
                    return;
                }
            }
        }

        System.out.println();
        System.out.println("Вот что вы можете сделать с созданным транспортным средством:");
        System.out.println("- получить информацию о транспортном средстве - 1;");
        System.out.println("- начать движение - 2;");
        System.out.println("- изменить скорость - 3;");
        System.out.println("- остановить движение - 4.");
        System.out.println("Чтобы выйти из программы, введите цифру 5\n");
        System.out.print("Введите соответствующую цифру: ");

        boolean running = true;
        while (running) {
            int action = readIntValueInRange(
                    scanner, 1, 5, "Некорректное значение!");

            switch (action) {
                case 1 -> vehicle.infoAboutVehicle();
                case 2 -> vehicle.move();
                case 3 -> {
                    System.out.print("Введите новое значение скорости: ");
                    double delta = readPositiveDouble(scanner, "Некорректное значение скорости!");
                    vehicle.setSpeed(delta);
                    System.out.printf("Новое значение скорости - %s км/ч.", vehicle.getSpeed());
                    System.out.println();
                }
                case 4 -> vehicle.stop();
                case 5 -> {
                    System.out.println("Выход из программы...");
                    running = false;
                }
                default -> System.out.println("Некорректный выбор! Попробуйте снова.");
            }
        }
        scanner.close();
    }

    private static int readIntValueInRange(Scanner scanner, int min, int max, String errorOutOfRangeMessage) {
        int value;
        while (true) {
            try {
                value = scanner.nextInt();
                if (value < min || value > max) {
                    System.out.printf(errorOutOfRangeMessage + " Число должно быть в диапазоне от %s до %s. Попробуйте еще раз.\n", min, max);
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Некорректный тип данных! Попробуйте еще раз.");
                scanner.nextLine();
            }
        }
    }

    private static double readPositiveDouble(Scanner scanner, String errorMessage) {
        double value;
        while (true) {
            try {
                value = scanner.nextDouble();
                if (value < 0) {
                    System.out.println("Значение не может быть отрицательным! Попробуйте еще раз.");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println(errorMessage + " Попробуйте еще раз.");
                scanner.nextLine();
            }
        }
    }
}
