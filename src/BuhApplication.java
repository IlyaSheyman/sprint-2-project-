import java.util.Scanner;

public class BuhApplication {
    private Scanner scanner;
    private ReportService service;
    public void run() { // реализация меню
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
                service.loadMonthReports();
                System.out.println("Считывание всех месячных отчётов завершено!");
            } else if (line.equals("2")) {
                service.loadYearReports();
                System.out.println("Считывание всех годовых отчётов завершено!");
            } else if (line.equals("3")) {
                System.out.println("Сверка отчетов: ");
                service.checkReports();
            } else if (line.equals("4")) {
                System.out.println("Информация о всех месячных отчётах:");
                service.printMonthReports();
            } else if (line.equals("5")) {
                System.out.println("Информация о годовом отчёте:");
                service.printYearReports();
            } else {
                System.out.println("Неизвестная команда");
            }
        }
    }

    private void printMenu() { // меню
        System.out.println("Меню:");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("Нажмите Enter для завершения программы");
    }
}
