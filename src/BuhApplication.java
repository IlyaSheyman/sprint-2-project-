import java.util.Scanner;

public class BuhApplication {
    private Scanner scanner;
    private ReportService service;
    public void run() {
        System.out.println("Введите команду");
        scanner = new Scanner(System.in);
        service = new ReportService();

        while (true) {
            printMenu();
            String line = scanner.nextLine();
            System.out.println("4 - Вывести информацию о всех месячных отчётах");

            if (line.isEmpty()) {
                return;
            } else if (line.equals("1")) {
                System.out.println("Начали считывать все месячные отчёты");
                service.loadMonthReports();
                System.out.println("Завершили считывание всех месячных отчётов");
            } else if (line.equals("2")) {
                System.out.println("Начали считывать все годовые отчёты");
                service.loadYearReports();
                System.out.println("Завершили считывание всех годовых отчётов");
            } else if (line.equals("3")) {
                System.out.println("Начали сверку отчётов");
                // service.();
                System.out.println("Завершили сверку отчётов");
            } else if (line.equals("4")) {
                System.out.println("Начали выводить информацию о всех месячных отчётах");
                service.printMonthReports();
                System.out.println("Завершили вывод информации о всех месячных отчётах");
            } else if (line.equals("5")) {
                System.out.println("Начали вывод информации о годовом отчёте");
                service.printYearReports();
                System.out.println("Завершили вывод информации о годовом отчёте");
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
