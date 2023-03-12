import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Storage {

    LinkedHashMap<Integer, ArrayList<Item>> monthReports = new LinkedHashMap<>();
    LinkedHashMap<Integer, ArrayList<Year>> yearReports = new LinkedHashMap<>();
    LinkedHashMap<Integer, LinkedHashMap<Integer, ArrayList<Item>>> monthReportsThroughYears = new LinkedHashMap<>();

    public String getMonthName(int month) { // метод для получения названия месяца
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

    public void saveMonthReport(int year, int month, ArrayList<Item> items) {
        monthReports.put(month, items);
        monthReportsThroughYears.put(year, monthReports);
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

    public HashMap<Integer, Long> getMonthProfit(ArrayList<Year> info) {
        HashMap<Integer, Long> income = new HashMap<>();
        HashMap<Integer, Long> consumption = new HashMap<>();
        HashMap<Integer, Long> profit = new HashMap<>(); // Integer - номер месяца, Long - сумма

        for (Year year1 : info) {
            if (year1.expense) {
                consumption.put(year1.month, year1.amount);
            } else {
                income.put(year1.month, year1.amount);
            }
        }

        for (Integer month : income.keySet()) {
            profit.put(month, income.get(month) - consumption.get(month));
        }
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

    public Long getMonthIncome(int month) {
        ArrayList<Item> items = monthReports.get(month);
        long sum = 0;
        for (Item item : items) {
            if (!item.expense) {
                sum += item.getTotal();
            }
        }
        return sum;
    }

    public Long getMonthConsumption(int month) {
        ArrayList<Item> items = monthReports.get(month);
        long sum = 0;
        for (Item item : items) {
            if (item.expense) {
                sum += item.getTotal();
            }
        }
        return sum;
    }

    public void compare(HashMap<Integer, Long> monthsIncome, HashMap<Integer, Long> monthConsumption,
                        ArrayList<Long> incomes, ArrayList<Long> consump) {
        for (int i = 0; i < monthsIncome.size(); i++) {
            // сравниваем доходы:
            if (monthsIncome.get(i+1).equals(incomes.get(i))) {
                System.out.println("Доходы в месяце " + getMonthName(i+1) + " совпадают.");
            } else {
                System.out.println("Ошибка: несоответствие в доходах месяца " + getMonthName(i+1));
                continue;
            }
            // сравниваем расходы:
            if (monthConsumption.get(i+1).equals(consump.get(i))) {
                System.out.println("Расходы в месяце " + getMonthName(i+1) + " совпадают.");
            } else {
                System.out.println("Ошибка: несоответствие в расходах месяца " + getMonthName(i+1));
            }
        }
    }
}

