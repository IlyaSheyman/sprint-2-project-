import java.util.Scanner;

public class BuhApplication {
    private Scanner scanner;
    public void run() {
        System.out.println("Введите команду");
        scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            String line = scanner.nextLine();
            System.out.println("4 - Вывести информацию о всех месячных отчётах");

            if (line.isEmpty()) {
                return;
            } else if (line.equals("1")) {
                System.out.println("Начали считать все месячные отчёты");
                ReportService service = new ReportService();
                service.loadMonthReports();
                System.out.println("Завершили подсчёт всех месячных отчётов");
            } else {
                System.out.println("Неизвестная команда");
            }
        }
    }

    private void printMenu() {
        System.out.println("Меню:");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("Нажмите Enter для завершения программы");
    }
}
