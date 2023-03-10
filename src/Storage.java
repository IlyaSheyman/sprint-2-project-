import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Storage {

    LinkedHashMap<Integer, ArrayList<Item>> monthReports = new LinkedHashMap<>();
    public void saveMonthReport(int year, int month, ArrayList<Item> items) {
        monthReports.put(month, items); // добавить сохранение года
    }

    public Item getMaxProfit(int month) {
        ArrayList<Item> items = monthReports.get(month);
        Item maxProfit = null;
        long total = 0;
        for (Item item : items) {
            if (item.expense) {
                continue;
            }
            if (item.getTotal() > total) {
                total = item.getTotal();
                maxProfit = item;
            }
        }

        return maxProfit;
    }

    public Item getMaxExpense(int month) {
        ArrayList<Item> items = monthReports.get(month);
        Item maxExpense = null;
        long total = 0;
        for (Item item : items) {
            if (!item.expense) {
                continue;
            }
            if (item.getTotal() > total) {
                total = item.getTotal();
                maxExpense = item;
            }
        }
        return maxExpense;
    }
}
