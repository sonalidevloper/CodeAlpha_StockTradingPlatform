import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private double balance;
    private Portfolio portfolio;
    private List<Transaction> transactions;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.portfolio = new Portfolio();
        this.transactions = new ArrayList<>();
    }

    public String getName() { return name; }
    public double getBalance() { return balance; }
    public Portfolio getPortfolio() { return portfolio; }
    public List<Transaction> getTransactions() { return transactions; }

    public boolean buy(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;
        if (cost > balance) return false;

        balance -= cost;
        balance = Math.round(balance * 100.0) / 100.0;
        portfolio.addHolding(stock.getSymbol(), quantity, stock.getPrice());
        transactions.add(new Transaction("BUY", stock.getSymbol(), quantity, stock.getPrice()));
        return true;
    }

    public boolean sell(Stock stock, int quantity) {
        if (portfolio.getQuantity(stock.getSymbol()) < quantity) return false;

        double earned = stock.getPrice() * quantity;
        balance += earned;
        balance = Math.round(balance * 100.0) / 100.0;
        portfolio.removeHolding(stock.getSymbol(), quantity);
        transactions.add(new Transaction("SELL", stock.getSymbol(), quantity, stock.getPrice()));
        return true;
    }
}