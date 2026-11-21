# 📈CodeAlpha Stock Trading Platform

![Java](https://img.shields.io/badge/Java-23-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Build-Apache%20Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Interface](https://img.shields.io/badge/Interface-Console-1f6feb?style=for-the-badge)

A **Java + Maven console-based stock trading simulator** for practicing core trading workflows and OOP design.

## 🧾 Overview
This application lets a user simulate stock trading with a menu-driven CLI. It uses predefined market stocks, supports portfolio operations, and persists user data to a local file.

## ✨ Capabilities
- 📊 Display live market stock list
- 🛒 Buy stocks (with balance checks)
- 💸 Sell stocks (with holding checks)
- 💼 Track portfolio holdings and average buy price
- 🧮 View transaction history
- 🔄 Refresh stock prices (simulated fluctuation)
- 💾 Save/load data using file-based persistence (`portfolio_data.txt`)

## 🧱 Tech Stack
- ☕ **Java 23**
- 📦 **Apache Maven**
- 🖥️ **Console interface**
- 📁 **File I/O persistence**

## 📂 Project Structure
```text
CodeAlpha_StockTradingPlatform/
├── pom.xml
└── src/main/java/
    ├── Main.java
    ├── Stock.java
    ├── StockMarket.java
    ├── User.java
    ├── Portfolio.java
    ├── Transaction.java
    └── FileManager.java
```

## 🧩 Core Classes
- **Main**: entry point and menu flow
- **Stock**: stock model and price fluctuation
- **StockMarket**: stock list, lookup, and market display/refresh
- **User**: user profile, balance, buy/sell logic, transactions
- **Portfolio**: holdings and average buy-price tracking
- **Transaction**: buy/sell record with timestamp
- **FileManager**: save/load user and portfolio data

## 🚀 Run Locally
### Prerequisites
- Java 23 installed
- Maven installed

### Commands
```bash
git clone https://github.com/sonalidevloper/CodeAlpha_StockTradingPlatform.git
cd CodeAlpha_StockTradingPlatform
mvn clean compile
java -cp target/classes Main
```

> Optional: you can also run with Maven Exec plugin using `mvn exec:java -Dexec.mainClass=Main`.

## 📌 Notes
- Market symbols are preloaded in `StockMarket`.
- Portfolio data is saved to `portfolio_data.txt` in the project run directory.
