package AccountProcessor;

import java.time.LocalDate;
import java.util.HashMap;

public class PaymentRepository {
  private HashMap<String, Payment> paymentMap = new HashMap<>();

  public boolean existsPayment(String id) {
    return this.paymentMap.containsKey(id);
  }

  public Payment getPayment(String id) {
    return this.paymentMap.get(id);
  }

  public void registerPayment(String id, LocalDate date, double value, PaymentType type) {
    Payment payment = new Payment(id, date, value, type);
    paymentMap.put(id, payment);
  }
}
