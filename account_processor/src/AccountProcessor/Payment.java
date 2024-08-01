package AccountProcessor;

import java.time.LocalDate;

public class Payment {

  private String id;
  private LocalDate date;
  private double value;
  private PaymentType type;

  public Payment(String id, LocalDate date, double value, PaymentType type) {
    this.id = id;
    this.date = date;
    this.value = value;
    this.type = type;
  }

  public boolean equals(Payment payment) {
    if (this == payment)
      return true;
    if (payment == null)
      return false;
    if (!(payment instanceof Payment))
      return false;
    Payment other = (Payment) payment;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}
