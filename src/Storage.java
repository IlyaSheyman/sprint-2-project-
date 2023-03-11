import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Storage {

    LinkedHashMap<Integer, ArrayList<Item>> monthReports = new LinkedHashMap<>();
    LinkedHashMap<Integer, ArrayList<Year>> yearReports = new LinkedHashMap<>();

    public void saveMonthReport(int year, int month, ArrayList<Item> items) {
        monthReports.put(month, items); // добавить учет года
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

    public void saveYearReport(int year, ArrayList<Year> info) {
        yearReports.put(year, info);
    }

    public Long getMonthProfit(int month, boolean expense, long amount) {

        long profit = 0;
        long income = 0;
        long consumption = 0;

        if (expense) {
            consumption = amount;
        } else {
            income = amount;
        }

        profit = income - consumption;

        return profit;
    }

    public Long getAveragedIncome(ArrayList<Year> info) {
        ArrayList<Long> incomes = new ArrayList<>();
        long income = 0;
        for (Year year1 : info) {
            if (!year1.expense) {
                income += year1.amount;
                incomes.add(year1.amount);
            }
        }
        return income / incomes.size();
    }

    public Long getAveragedConsumption(ArrayList<Year> info) {
        ArrayList<Long> consump = new ArrayList<>();
        long consumption = 0;
        for (Year year1 : info) {
            if (year1.expense) {
                consumption += year1.amount;
                consump.add(year1.amount);
            }
        }
        return consumption / consump.size();
    }
}

