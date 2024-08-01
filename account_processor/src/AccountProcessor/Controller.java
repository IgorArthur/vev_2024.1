package AccountProcessor;

import java.time.LocalDate;
import java.util.HashSet;

import AccountProcessor.Invoice.InvoiceRepository;
import AccountProcessor.Payment.PaymentRepository;
import AccountProcessor.Payment.PaymentType;
import AccountProcessor.Transaction.Transaction;
import AccountProcessor.Transaction.TransactionRepository;

public class Controller {

  private TransactionRepository transactionRepository = new TransactionRepository();
  private InvoiceRepository invoiceRepository = new InvoiceRepository();
  private PaymentRepository paymentRepository = new PaymentRepository();

  public String registerTransaction(String id, LocalDate date, double value) {
    if (id.equals("") || date == null || value == 0) {
      throw new IllegalArgumentException("ENTRADA INVÁLIDA!");
    }
    if (transactionRepository.existsTransaction(id)) {
      return "CONTA JÁ CADASTRADA!";
    }

    transactionRepository.registerTransaction(id, date, value);
    return "CONTA CADASTRADA!";
  }

  public String registerInvoice(String id, LocalDate date, double value, String clientName,
      HashSet<Transaction> transactions) {
    if (id.equals("") || date == null || value == 0 || clientName.equals("")) {
      throw new IllegalArgumentException("ENTRADA INVÁLIDA!");
    }
    if (invoiceRepository.existsInvoice(id)) {
      return "FATURA JÁ CADASTRADA!";
    }

    invoiceRepository.registerInvoice(id, date, value, clientName, transactions);
    return "FATURA CADASTRADA!";
  }

  public String registerPayment(String id, LocalDate date, double value, PaymentType type, String transactionId) {
    if (id.equals("") || date == null || value == 0 || transactionId.equals("")) {
      throw new IllegalArgumentException("ENTRADA INVÁLIDA!");
    }
    if (paymentRepository.existsPayment(id)) {
      return "PAGAMENTO JÁ CADASTRADO!";
    }
    if (value < 0.01 || value > 5000) {
      return "PAGAMENTOS POR BOLETO NÃO PODEM TER VALOR INFERIOR A R$0,01 OU SUPERIOR A R$5.000,00!";
    }
    Transaction transaction = transactionRepository.getTransaction(transactionId);
    if (date.isAfter(transaction.getDate())) {
      transaction.setValue((transaction.getValue() * 0.1));
    }

    paymentRepository.registerPayment(id, date, value, type, transactionId);
    return "PAGAMENTO CADASTRADO!";
  }
}
