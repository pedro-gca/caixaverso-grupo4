package ada.tech.lms.screen;

import ada.tech.lms.domain.BankAccount;
import ada.tech.lms.domain.Transaction;
import ada.tech.lms.domain.User;
import ada.tech.lms.service.BankService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

public class WithdrawExecutedOption implements ExecutedOption {

	private BankService bankService;
	private Scanner scanner;
	private User userAccount;
	public WithdrawExecutedOption(BankService bankService, Scanner scanner, User userAccount) {
		this.bankService = bankService;
		this.userAccount = userAccount;
		this.scanner = scanner;
	}

	@Override
	public void execute() {
		try {
			System.out.println("Valor informado para o saque?");
			var value = scanner.nextDouble();
			BankAccount account = bankService.findAccountByUser(userAccount);
			account.getBalance();
			account.withdraw(value);

			Transaction transaction = new Transaction(
					value,
					LocalDateTime.now().toString(),
					"saque",
					account.getAccountNumber(),
					userAccount.getName()
			);
//			// lista
//			account.getTransactions().add(transaction);

			// arquivo
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/ada/tech/lms/storage/transactions/" + userAccount.getCpf() + ".txt", true));
			writer.write(transaction.toString());
			writer.newLine();
			writer.close();

			BufferedWriter writer2 = new BufferedWriter(new FileWriter("src/main/java/ada/tech/lms/storage/usuario/" + userAccount.getCpf() + ".txt", false));
			writer2.write(userAccount.toString());
		} catch (Exception e) {
			System.out.println("Erro ao realizar o saque. Verifique os dados informados e tente novamente.");
		}
	}
}
