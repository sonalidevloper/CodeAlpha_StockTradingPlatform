import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String type; // BUY or SELL
    private String symbol;
    private int quantity;
    private double price;
    private String dateTime;

    public Transaction(String type, String symbol, int quantity, double price) {
        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.dateTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getType() { return type; }
    public String getSymbol() { return symbol; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public String getDateTime() { return dateTime; }

    public double getTotalValue() { return quantity * price; }

    @Override
    public String toString() {
        return String.format("[%s] %s %d shares of %s @ $%.2f = $%.2f",
                dateTime, type, quantity, symbol, price, getTotalValue());
    }
}