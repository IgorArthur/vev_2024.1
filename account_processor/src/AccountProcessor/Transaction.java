package AccountProcessor;

import java.time.LocalDate;

public class Transaction {

  private String id;
  private LocalDate date;
  private double value;

  public Transaction(String id, LocalDate date, double value) {
    this.id = id;
    this.date = date;
    this.value = value;
  }

  public boolean equals(Transaction transaction) {
    if (this == transaction)
      return true;
    if (transaction == null)
      return false;
    if (!(transaction instanceof Transaction))
      return false;
    Transaction other = (Transaction) transaction;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  public LocalDate getDate() {
    return this.date;
  }

  public double getValue() {
    return this.value;
  }

  public void setValue(double newValue) {
    this.value = newValue;
  }
}
