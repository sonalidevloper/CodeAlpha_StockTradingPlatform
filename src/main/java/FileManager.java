import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_NAME = "portfolio_data.txt";

    public static void saveData(User user) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println("USER:" + user.getName());
            writer.println("BALANCE:" + user.getBalance());

            // Save holdings
            for (var entry : user.getPortfolio().getHoldings().entrySet()) {
                String symbol = entry.getKey();
                int qty = entry.getValue();
                double avgPrice = user.getPortfolio().getAvgBuyPrice(symbol);
                writer.println("HOLDING:" + symbol + ":" + qty + ":" + avgPrice);
            }

            // Save transactions
            for (Transaction t : user.getTransactions()) {
                writer.println("TRANSACTION:" + t.getType() + ":" +
                        t.getSymbol() + ":" + t.getQuantity() + ":" +
                        t.getPrice() + ":" + t.getDateTime());
            }

            System.out.println("✅ Portfolio saved successfully!");
        } catch (IOException e) {
            System.out.println("❌ Error saving data: " + e.getMessage());
        }
    }

    public static boolean fileExists() {
        return new File(FILE_NAME).exists();
    }

    public static String[] loadUserInfo() {
        List<String> lines = readLines();
        for (String line : lines) {
            if (line.startsWith("USER:")) {
                String name = line.split(":")[1];
                String balance = "";
                for (String l : lines) {
                    if (l.startsWith("BALANCE:")) {
                        balance = l.split(":")[1];
                        break;
                    }
                }
                return new String[]{name, balance};
            }
        }
        return null;
    }

    public static void loadPortfolio(User user) {
        List<String> lines = readLines();
        for (String line : lines) {
            if (line.startsWith("HOLDING:")) {
                String[] parts = line.split(":");
                user.getPortfolio().addHolding(parts[1],
                        Integer.parseInt(parts[2]),
                        Double.parseDouble(parts[3]));
            }
        }
    }

    private static List<String> readLines() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) lines.add(line);
        } catch (IOException e) {
            System.out.println("❌ Error loading data: " + e.getMessage());
        }
        return lines;
    }
}