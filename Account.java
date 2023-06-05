package atm;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountId;
    private double balance;
    private List<String> transactionHistory;

    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit - Amount: " + amount);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrawal - Amount: " + amount);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void transfer(Account recipient, double amount) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add("Transfer to " + recipient.getAccountId() + " - Amount: " + amount);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History for Account: " + accountId);
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}
