package ada.tech.lms.screen;

import ada.tech.lms.domain.Transaction;
import ada.tech.lms.domain.User;
import ada.tech.lms.service.BankService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

public class DepositExecutedOption implements ExecutedOption {
	private final BankService bankService;
	private final Scanner scanner;
	private final User user;
	public DepositExecutedOption(BankService bankService, Scanner scanner, User user) {
		this.bankService = bankService;
		this.scanner = scanner;
		this.user = user;
	}

	@Override
	public void execute() {
		try {
			System.out.println("Qual o valor que deseja depositar?");
			var value = scanner.nextDouble();
			var account = bankService.findAccountByUser(user);
			account.deposit(value);
			System.out.println("deposito realizado com sucesso");

			Transaction transaction = new Transaction(
					value,
					LocalDateTime.now().toString(),
					"depósito",
					account.getAccountNumber(),
					user.getName()
			);

//			// lista
//			account.getTransactions().add(transaction);

			// arquivo
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/ada/tech/lms/storage/transactions/" + user.getCpf() + ".txt", true));
			writer.write(transaction.toString());
			writer.newLine();
			writer.close();


			BufferedWriter writer2 = new BufferedWriter(new FileWriter("src/main/java/ada/tech/lms/storage/usuario/" + user.getCpf() + ".txt", false));
			writer2.write(user.toString());
		} catch (Exception e) {
			System.out.println("Erro ao realizar o depósito. Verifique os dados informados e tente novamente.");
		}
	}
}
