import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class ReportService {

    Storage storage = new Storage();

    public void loadMonthReports() {
        String path = "./sprint-2-project-/resources/m.20210";
        for (int i = 1; i < 4; i++) {
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

    public void printMonthReports() {
        for (int i = 1; i < storage.monthReports.size(); i++) {
            System.out.println("Месяц: " + getMonthName(i));
            Item maxProfit = storage.getMaxProfit(i);
            System.out.println("Максимальный доход. Товар: " + maxProfit.name + ". Сумма: " + maxProfit.getTotal());
            Item maxExpense = storage.getMaxExpense(i);
            System.out.println("Максимальная расход. Статья расхода: " + maxExpense.name + ". Сумма расходов: " + maxExpense.getTotal());
        }
    }

    private String getMonthName(int month) {
        String monthName = "";
        if (month == 1) {
            monthName = "Январь";
        } else if (month == 2) {
            monthName = "Февраль";
        } else if (month == 3) {
            monthName = "Март";
        }
        return monthName;
    }

    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
}
