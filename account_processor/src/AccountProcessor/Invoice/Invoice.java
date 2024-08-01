package AccountProcessor.Invoice;

import java.time.LocalDate;
import java.util.HashSet;

import AccountProcessor.Transaction.Transaction;

public class Invoice {

  private String id;
  private LocalDate date;
  private double value;
  private String clientName;
  private HashSet<Transaction> transactions;

  public Invoice(String id, LocalDate date, double value, String clientName, HashSet<Transaction> transactions) {
    this.id = id;
    this.date = date;
    this.value = value;
    this.clientName = clientName;
    this.transactions = transactions;
  }

  public boolean equals(Invoice invoice) {
    if (this == invoice)
      return true;
    if (invoice == null)
      return false;
    if (!(invoice instanceof Invoice))
      return false;
    Invoice other = (Invoice) invoice;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}
