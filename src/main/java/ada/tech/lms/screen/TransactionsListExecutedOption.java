package ada.tech.lms.screen;

import ada.tech.lms.domain.BankAccount;
import ada.tech.lms.domain.Transaction;
import ada.tech.lms.domain.User;
import ada.tech.lms.service.BankService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class TransactionsListExecutedOption implements ExecutedOption {
    private final BankService bankService;
    private final List<Transaction> transactions;
    private final User user;



    public TransactionsListExecutedOption(BankService bankService, List<Transaction> transactions, User user) {
        this.bankService = bankService;
        this.user = user;
        this.transactions = transactions;
    }

    @Override
    public void execute() {
        try {
//            // listas
//            BankAccount account = bankService.findAccountByUser(user);
//            account.getTransactions().forEach(transaction -> System.out.println(transaction.toString()));

            // arquivos
            try {
                BufferedReader reader = new BufferedReader(new FileReader("src/main/java/ada/tech/lms/storage/transactions/" + user.getCpf() + ".txt"));
//                reader.lines()
                System.out.println("as transações são: ");
                reader.lines().forEach(line -> System.out.println(line));
            } catch (Exception e) {
                System.out.println("Erro ao ler o arquivo de transações. Verifique se o arquivo existe.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar transações. Verifique os dados informados e tente novamente.");
        }
    }
}
