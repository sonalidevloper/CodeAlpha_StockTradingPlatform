import java.util.ArrayList;
import java.util.List;

public class StockMarket {
    private List<Stock> stocks;

    public StockMarket() {
        stocks = new ArrayList<>();
        // Pre-loaded stocks
        stocks.add(new Stock("AAPL", "Apple Inc.", 175.50));
        stocks.add(new Stock("GOOGL", "Alphabet Inc.", 140.30));
        stocks.add(new Stock("TSLA", "Tesla Inc.", 245.80));
        stocks.add(new Stock("AMZN", "Amazon.com Inc.", 185.20));
        stocks.add(new Stock("MSFT", "Microsoft Corp.", 415.60));
        stocks.add(new Stock("META", "Meta Platforms", 505.90));
    }

    public void displayMarket() {
        System.out.println("\n========== LIVE MARKET DATA ==========");
        System.out.printf("%-6s %-20s %s%n", "SYM", "COMPANY", "PRICE");
        System.out.println("--------------------------------------");
        for (Stock s : stocks) {
            System.out.println(s);
        }
        System.out.println("======================================");
    }

    public Stock getStock(String symbol) {
        for (Stock s : stocks)
            if (s.getSymbol().equalsIgnoreCase(symbol)) return s;
        return null;
    }

    public void fluctuateAllPrices() {
        for (Stock s : stocks) s.fluctuatePrice();
    }

    public List<Stock> getStocks() { return stocks; }
}