package ada.tech.lms.screen;

import ada.tech.lms.domain.SimpleAccount;
import ada.tech.lms.domain.Transaction;
import ada.tech.lms.domain.User;
import ada.tech.lms.service.BankService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CreateAccountExecutedOption implements ExecutedOption {
	private BankService bankService;
	private Scanner scanner;

	public CreateAccountExecutedOption(BankService bankService, Scanner scanner) {
		this.bankService = bankService;
		this.scanner = scanner;
	}

	@Override
	public void execute() {
		try {
//			// utilizando listas
//			System.out.println("Informe o CPF");
//			var cpf = scanner.next();
//			System.out.println("Informe o Nome do usuário");
//			var name = scanner.next();
//			var generatedAccountNumber = generateAccountNumber();
//			List<Transaction> transactions = new ArrayList<>();
//			bankService.addAccount(new SimpleAccount(generatedAccountNumber, new User(cpf, name), 0.0, transactions));
//			System.out.println("Conta criada com sucesso");

			// utilizando arquivos no storage
			System.out.println("Informe o CPF");
			var cpf = scanner.next();
			System.out.println("Informe o Nome do usuário");
			var name = scanner.next();
			var generatedAccountNumber = generateAccountNumber();
			List<Transaction> transactions = new ArrayList<>();
			bankService.addAccount(new SimpleAccount(generatedAccountNumber, new User(cpf, name), 0.0, transactions));

			// lógica para não escrever o msmo número de conta mais de uma vez
			try (BufferedWriter writer = new BufferedWriter(
					new FileWriter("src/main/java/ada/tech/lms/storage/conta/" + cpf + ".txt", true))) {
				writer.write(generatedAccountNumber.toString());
				writer.newLine(); // for readability
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("Conta criada com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao criar a conta. Verifique os dados informados e tente novamente.");
		}
	}

	private String generateAccountNumber() {
		var random = new Random();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			stringBuilder.append(random.nextInt(0,9));
		}
		return stringBuilder.toString();
	}
}
