package AccountProcessor;

import java.time.LocalDate;
import java.util.HashSet;

public class Controller {

  private TransactionRepository transactionRepository = new TransactionRepository();
  private InvoiceRepository invoiceRepository = new InvoiceRepository();

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
}
