package ada.tech.lms.domain;

import java.util.List;

public class SpecialAccount extends BankAccount {
    private double limit;
    protected List<Transaction> transactions;

    public SpecialAccount(String accountNumber, User owner, double balance, double limit, List<Transaction> transactions) {
        super(accountNumber, owner, balance, transactions);
        this.limit = limit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance + limit) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Amount exceeds balance and limit.");
        }
    }

    public double getLimit() {
        return limit;
    }
}