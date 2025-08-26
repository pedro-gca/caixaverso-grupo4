package ada.tech.lms.domain;

import java.util.List;

public abstract class BankAccount {
    protected String accountNumber;
    protected User owner;
    protected double balance;
    protected List<Transaction> transactions;

    public BankAccount(String accountNumber, User owner, double balance, List<Transaction> transactions) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
        this.transactions = transactions;
    }

    public abstract void withdraw(double amount);
    public void deposit(double amount) {
        this.balance += amount;
    }
    public double getBalance() {
        return balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public User getOwner() {
        return owner;
    }
    public List<Transaction> getTransactions() {return transactions;}
}