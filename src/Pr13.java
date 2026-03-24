import java.io.*;
import java.util.Scanner;

public class Pr13 {

    static Scanner scanner = new Scanner(System.in);
    static String fileName = "text.txt";

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1 - Записати до файлу");
            System.out.println("2 - Прочитати файл");
            System.out.println("3 - Показати діапазон");
            System.out.println("4 - Вставити рядок");
            System.out.println("5 - Вийти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                writeToFile();
            }
            else if (choice == 2) {
                readFile();
            }
            else if (choice == 3) {
                showRange();
            }
            else if (choice == 4) {
                insertLine();
            }
            else if (choice == 5) {
                System.out.println("Вихід...");
                break;
            }
        }
    }

    public static void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            System.out.print("Скільки рядків додати: ");
            int n = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < n; i++) {
                System.out.print("Введіть рядок: ");
                String text = scanner.nextLine();
                writer.write(text);
                writer.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("Помилка запису у файл");
        }
    }

    public static void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int num = 1;
            System.out.println("\nВміст файлу:");

            while ((line = reader.readLine()) != null) {
                System.out.println(num + ": " + line);
                num++;
            }
        }
        catch (IOException e) {
            System.out.println("Помилка читання файлу");
        }
    }

    public static void showRange() {
        System.out.print("Початок: ");
        int start = scanner.nextInt();
        System.out.print("Кінець: ");
        int end = scanner.nextInt();
        scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int num = 1;

            while ((line = reader.readLine()) != null) {
                if (num >= start && num <= end) {
                    System.out.println(num + ": " + line);
                }
                num++;
            }
        }
        catch (IOException e) {
            System.out.println("Помилка");
        }
    }

    public static void insertLine() {
        System.out.print("У який рядок вставити: ");
        int pos = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введіть текст: ");
        String newLine = scanner.nextLine();

        String[] lines = new String[1000];
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines[count++] = line;
            }
        }
        catch (IOException e) {
            System.out.println("Помилка читання");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < count + 1; i++) {
                if (i == pos - 1) {
                    writer.write(newLine);
                    writer.newLine();
                }
                if (i < count) {
                    writer.write(lines[i]);
                    writer.newLine();
                }
            }
        }
        catch (IOException e) {
            System.out.println("Помилка запису");
        }
    }
}