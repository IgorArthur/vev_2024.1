package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import AccountProcessor.Payment;
import AccountProcessor.PaymentType;

public class PaymentTest {

  Payment payment = new Payment("001", LocalDate.parse("2024-06-20"), 15.00, PaymentType.BOLETO, "001");

  @Test
  public void testEqualsSameId() {
    Payment payment2 = new Payment("001", LocalDate.parse("2024-06-20"), 15.00, PaymentType.BOLETO, "001");

    assertEquals(true, payment2.equals(payment));
  }

  @Test
  public void testEqualsDifferentId() {
    Payment payment2 = new Payment("002", LocalDate.parse("2024-06-20"), 15.00, PaymentType.BOLETO, "001");

    assertEquals(false, payment2.equals(payment));
  }
}
