package AccountProcessor;

import java.time.LocalDate;
import java.util.HashMap;

public class TransactionRepository {
  private HashMap<String, Transaction> transactionMap = new HashMap<>();

  public boolean existsTransaction(String id) {
    return this.transactionMap.containsKey(id);
  }

  public Transaction getTransaction(String id) {
    return this.transactionMap.get(id);
  }

  public void registerTransaction(String id, LocalDate date, double value) {
    Transaction transaction = new Transaction(id, date, value);
    transactionMap.put(id, transaction);
  }
}
