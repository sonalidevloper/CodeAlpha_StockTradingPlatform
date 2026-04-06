public class Stock {
    private String symbol;
    private String name;
    private double price;

    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public String getSymbol() { return symbol; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    // Simulate market price fluctuation
    public void fluctuatePrice() {
        double change = (Math.random() * 10) - 5; // -5 to +5
        price = Math.max(1, price + change);
        price = Math.round(price * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return String.format("%-6s %-20s $%.2f", symbol, name, price);
    }
}