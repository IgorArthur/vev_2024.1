package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import AccountProcessor.Transaction;

public class TransactionTest {

  Transaction transaction = new Transaction("001", LocalDate.parse("2024-06-20"), 15.00);

  @Test
  public void testEqualsSameId() {
    Transaction transaction2 = new Transaction("001", LocalDate.parse("2024-06-20"), 15.00);

    assertEquals(true, transaction2.equals(transaction));
  }

  @Test
  public void testEqualsDifferentId() {
    Transaction transaction2 = new Transaction("002", LocalDate.parse("2024-06-20"), 15.00);

    assertEquals(false, transaction2.equals(transaction));
  }
}
