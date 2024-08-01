package AccountProcessor.Invoice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

public class InvoiceRepository {
  private HashMap<String, Invoice> invoiceMap = new HashMap<>();

  public boolean existsInvoice(String id) {
    return this.invoiceMap.containsKey(id);
  }

  public Invoice geInvoice(String id) {
    return this.invoiceMap.get(id);
  }

  public void registerInvoice(String id, LocalDate date, double value, String clientName, HashSet<String> transactions) {
    Invoice invoice = new Invoice(id, date, value, clientName, transactions);
    
    invoiceMap.put(id, invoice);
  }
}