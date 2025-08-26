package ada.tech.lms.domain;

public class Transaction {
//    Registro de Transações
//    Todas as operações de saque e depósito devem ser registradas no sistema.
//    Cada registro de transação deve conter:
    //    Tipo de operação: saque ou depósito;
    //    Valor da operação;
    //    Data e hora exata da realização;
    //    Identificação da conta e do usuário associado.

    private double amount;
    private String dateTime;
    private String type;
    private String idConta;
    private String user;

    public Transaction(double amount, String dateTime, String type, String idConta, String user) {
        this.amount = amount;
        this.dateTime = dateTime;
        this.type = type;
        this.idConta = idConta;
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getType() {
        return type;
    }

    public String getIdConta() {
        return idConta;
    }

    public String getUserCpf() {
        return user;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", dateTime='" + dateTime + '\'' +
                ", type='" + type + '\'' +
                ", idConta='" + idConta + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
