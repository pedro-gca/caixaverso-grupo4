package ada.tech.lms.domain;

import java.util.List;

public class SimpleAccount extends BankAccount {

    public SimpleAccount(String accountNumber, User owner, double balance, List<Transaction> transactions) {
        super(accountNumber, owner, balance, transactions);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient balance.");
        }
    }
}