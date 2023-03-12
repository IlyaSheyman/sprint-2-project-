import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class ReportService {

    Storage storage = new Storage();

    public void loadMonthReports() { // считывание месячных отчетов
        String path = "./sprint-2-project-/resources/m.20210";
        for (int i = 1; i < 5; i++) {
            ArrayList<Item> items = loadMonthReport(path + i + ".csv");
            storage.saveMonthReport(2021, i, items);
        }
    }

    ArrayList<Item> loadMonthReport (String path) {
        List<String> lines = readFileContents(path);
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] rows = line.split(",");
            Item item = new Item(rows[0], Boolean.parseBoolean(rows[1]), Integer.parseInt(rows[2]), Long.parseLong(rows[3]));
            items.add(item);
        }
        return items;
    }

    public void printMonthReports() { // печать информации о месячных отчетах
        for (int i = 1; i < storage.monthReports.size(); i++) {
            System.out.println("Месяц: " + storage.getMonthName(i));
            Item maxProfit = storage.getMaxProfit(i);
            System.out.println("Максимальный доход. Товар: " + maxProfit.name + ". Сумма: " + maxProfit.getTotal());
            Item maxExpense = storage.getMaxExpense(i);
            System.out.println("Максимальная расход. Статья расхода: " + maxExpense.name + ". Сумма расходов: " + maxExpense.getTotal());
        }
    }

// работа с месяцем окончена. Далее идет работа с годом.

    public void loadYearReports() { // метод для считывания годовых отчетов
        String path = "./sprint-2-project-/resources/y.202";
        for (int i = 1; i < 2; i++) {
            ArrayList<Year> info = loadYearReport(path + i + ".csv");
            storage.saveYearReport(i, info);
        }
    }

    ArrayList<Year> loadYearReport (String path) {
        List<String> lines = readFileContents(path);
        ArrayList<Year> info = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] rows = line.split(",");
            Year year = new Year(Integer.parseInt(rows[0]), Long.parseLong(rows[1]), Boolean.parseBoolean(rows[2]));
            info.add(year);
        }
        return info;
    }


    public void printYearReports() { // метод для печати информации по годовым отчетам
        for (Integer year : storage.yearReports.keySet()) {
            ArrayList<Year> info = storage.yearReports.get(year);
            HashMap<Integer, Long> profit = storage.getMonthProfit(info);
            for (Integer month : profit.keySet()) {
                System.out.println("Месяц: " + storage.getMonthName(month) + ". Прибыль за месяц: " + profit.get(month));
            }
            System.out.println("Год: " + getYearName(year) + ". Средний доход за все месяцы в году: " + storage.getAveragedIncome(info));
            System.out.println("Год: " + getYearName(year) + ". Средний расход за все месяцы в году: " + storage.getAveragedConsumption(info));
        }
    }

    private String getYearName(int year) { // метод для получения названия года
        String yearName = "202" + year;
        return yearName;
    }

    public void checkReports() { // метод для сверки отчетов
        for (Integer year : storage.yearReports.keySet()) {
            ArrayList<Year> info = storage.yearReports.get(year); // передали конкретный месяц
            HashMap<Integer, Long> monthsIncome = new HashMap<>();
            HashMap<Integer, Long> monthConsumption = new HashMap<>(); // Integer - номер месяца, Long - сумма

            System.out.println("Исходя из отчетов по месяцам: ");

            for (int i = 1; i < storage.monthReports.size(); i++) {
                System.out.println("Доход за " + storage.getMonthName(i) + " составил " + storage.getMonthIncome(i));
                monthsIncome.put(i, storage.getMonthIncome(i));
                System.out.println("Расход за " + storage.getMonthName(i) + " составил " + storage.getMonthConsumption(i));
                monthConsumption.put(i, storage.getMonthConsumption(i));
            }

            ArrayList<Long> incomes = new ArrayList<>();
            ArrayList<Long> consump = new ArrayList<>();

            for (Year year1 : info) {
                if (!year1.expense) {
                    incomes.add(year1.amount);
                } else {
                    consump.add(year1.amount);
                }
            }
            System.out.println("Исходя из отчетов по году: ");

            for (int i = 0; i < incomes.size(); i++) {
                System.out.println("Доход за " + storage.getMonthName(i+1) + " составил " + incomes.get(i));
                System.out.println("Расход за " + storage.getMonthName(i+1) + " составил " + consump.get(i));
            }
            storage.compare(monthsIncome, monthConsumption, incomes, consump);
        }
    }

    List<String> readFileContents(String path) { // метод для считывания файлов
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            //System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
}
