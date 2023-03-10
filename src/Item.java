public class Item {
    String name;
    boolean expense;
    int quantity;
    long sum;

    public Item(String name, boolean expense, int quantity, long sum) {
        this.name = name;
        this.expense = expense;
        this.quantity = quantity;
        this.sum = sum;
    }
    // назначение класса - хранить и передавать данные

    long getTotal() {
        return quantity * sum;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", expense=" + expense +
                ", quantity=" + quantity +
                ", sum=" + sum +
                '}'; // toString - для того, чтобы вывести структуру Item на печать
    }
}
