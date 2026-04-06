import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<String, Integer> holdings; // symbol -> quantity
    private Map<String, Double> avgBuyPrice; // symbol -> avg price paid

    public Portfolio() {
        holdings = new HashMap<>();
        avgBuyPrice = new HashMap<>();
    }

    public void addHolding(String symbol, int quantity, double price) {
        int currentQty = holdings.getOrDefault(symbol, 0);
        double currentAvg = avgBuyPrice.getOrDefault(symbol, 0.0);

        double newAvg = ((currentQty * currentAvg) + (quantity * price))
                / (currentQty + quantity);

        holdings.put(symbol, currentQty + quantity);
        avgBuyPrice.put(symbol, Math.round(newAvg * 100.0) / 100.0);
    }

    public boolean removeHolding(String symbol, int quantity) {
        int current = holdings.getOrDefault(symbol, 0);
        if (current < quantity) return false;
        if (current == quantity) {
            holdings.remove(symbol);
            avgBuyPrice.remove(symbol);
        } else {
            holdings.put(symbol, current - quantity);
        }
        return true;
    }

    public int getQuantity(String symbol) {
        return holdings.getOrDefault(symbol, 0);
    }

    public double getAvgBuyPrice(String symbol) {
        return avgBuyPrice.getOrDefault(symbol, 0.0);
    }

    public Map<String, Integer> getHoldings() { return holdings; }

    public boolean isEmpty() { return holdings.isEmpty(); }
}