import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StockMarket market = new StockMarket();
        User user;

        System.out.println(" CodeAlpha Stock Trading Platform ");

        // Load or create user
        if (FileManager.fileExists()) {
            System.out.print("\nSaved data found! Load it? (yes/no): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                String[] info = FileManager.loadUserInfo();
                user = new User(info[0], Double.parseDouble(info[1]));
                FileManager.loadPortfolio(user);
                System.out.println("✅ Welcome back, " + user.getName() + "!");
            } else {
                user = createNewUser(scanner);
            }
        } else {
            user = createNewUser(scanner);
        }

        int choice;
        do {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View My Portfolio");
            System.out.println("5. View Transaction History");
            System.out.println("6. Refresh Market Prices");
            System.out.println("7. Save & Exit");
            System.out.printf("💰 Balance: $%.2f%n", user.getBalance());
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> market.displayMarket();

                case 2 -> {
                    market.displayMarket();
                    System.out.print("Enter stock symbol to buy: ");
                    String symbol = scanner.nextLine().toUpperCase();
                    Stock stock = market.getStock(symbol);
                    if (stock == null) {
                        System.out.println("❌ Stock not found.");
                    } else {
                        System.out.printf("Price: $%.2f | Your balance: $%.2f%n",
                                stock.getPrice(), user.getBalance());
                        System.out.print("Enter quantity: ");
                        int qty = scanner.nextInt();
                        scanner.nextLine();
                        if (user.buy(stock, qty)) {
                            System.out.printf("✅ Bought %d shares of %s!%n", qty, symbol);
                        } else {
                            System.out.println("❌ Insufficient balance!");
                        }
                    }
                }

                case 3 -> {
                    if (user.getPortfolio().isEmpty()) {
                        System.out.println("❌ No stocks in portfolio.");
                    } else {
                        displayPortfolio(user, market);
                        System.out.print("Enter stock symbol to sell: ");
                        String symbol = scanner.nextLine().toUpperCase();
                        Stock stock = market.getStock(symbol);
                        if (stock == null) {
                            System.out.println("❌ Stock not found.");
                        } else {
                            System.out.print("Enter quantity: ");
                            int qty = scanner.nextInt();
                            scanner.nextLine();
                            if (user.sell(stock, qty)) {
                                System.out.printf("✅ Sold %d shares of %s!%n", qty, symbol);
                            } else {
                                System.out.println("❌ Not enough shares.");
                            }
                        }
                    }
                }

                case 4 -> displayPortfolio(user, market);

                case 5 -> {
                    if (user.getTransactions().isEmpty()) {
                        System.out.println("No transactions yet.");
                    } else {
                        System.out.println("\n===== TRANSACTION HISTORY =====");
                        for (Transaction t : user.getTransactions()) {
                            System.out.println(t);
                        }
                    }
                }

                case 6 -> {
                    market.fluctuateAllPrices();
                    System.out.println("✅ Market prices refreshed!");
                    market.displayMarket();
                }

                case 7 -> {
                    FileManager.saveData(user);
                    System.out.println("👋 Goodbye, " + user.getName() + "!");
                }

                default -> System.out.println("❌ Invalid choice.");
            }
        } while (choice != 7);

        scanner.close();
    }

    private static User createNewUser(Scanner scanner) {
        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter starting balance ($): ");
        double balance = scanner.nextDouble();
        scanner.nextLine();
        return new User(name, balance);
    }

    private static void displayPortfolio(User user, StockMarket market) {
        if (user.getPortfolio().isEmpty()) {
            System.out.println("Portfolio is empty.");
            return;
        }
        System.out.println("\n========== MY PORTFOLIO ==========");
        System.out.printf("%-6s %-6s %-10s %-10s %-10s%n",
                "SYM", "QTY", "AVG BUY", "CUR PRICE", "P&L");
        System.out.println("----------------------------------");

        double totalPnL = 0;
        for (var entry : user.getPortfolio().getHoldings().entrySet()) {
            String symbol = entry.getKey();
            int qty = entry.getValue();
            double avgBuy = user.getPortfolio().getAvgBuyPrice(symbol);
            Stock stock = market.getStock(symbol);
            double curPrice = stock != null ? stock.getPrice() : avgBuy;
            double pnl = (curPrice - avgBuy) * qty;
            totalPnL += pnl;

            System.out.printf("%-6s %-6d $%-9.2f $%-9.2f $%-9.2f%n",
                    symbol, qty, avgBuy, curPrice, pnl);
        }
        System.out.println("----------------------------------");
        System.out.printf("Total P&L: $%.2f%n", totalPnL);
        System.out.printf("Balance:   $%.2f%n", user.getBalance());
        System.out.println("==================================");
    }
}