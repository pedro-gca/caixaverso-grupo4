package ada.tech.lms.service;

import ada.tech.lms.domain.BankAccount;
import ada.tech.lms.domain.Transaction;
import ada.tech.lms.domain.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BankService {
    private List<BankAccount> accounts = new ArrayList<>();

    private List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public void deposit(String accountNumber, double amount) {
//        Transaction transaction = new Transaction(
//                amount,
//                java.time.LocalDateTime.now().toString(),
//                "withdraw",
//                accountNumber,
//                findAccount(accountNumber).getOwner().getCpf()
//        );

//        transactions.add(transaction);
        findAccount(accountNumber).withdraw(amount);
    }

    public double checkBalance(String accountNumber) {
        return findAccount(accountNumber).getBalance();
    }

    public BankAccount findAccount(String accountNumber) {
        return accounts.stream()
            .filter(a -> a.getAccountNumber().equals(accountNumber))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }

    public User findUser(String documentNumber){
       try {
           for (BankAccount account : accounts){
                if(account.getOwner().getCpf().equals(documentNumber)){
                   return account.getOwner();
                }
           }
       }
       catch (Exception e) {
           System.out.println("There is no owner with this document number: " + documentNumber);
       }
       return null;
    }

    public BankAccount findAccountByUser(User user) {
        try {
            for (BankAccount account : accounts) {
                if (account.getOwner().getCpf().equals(user.getCpf())) {
                    return account;
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("There is no owner");
        }
        return null;
    }
}