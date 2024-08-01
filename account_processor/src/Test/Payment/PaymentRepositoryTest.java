package Test.Payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import AccountProcessor.Payment.PaymentRepository;
import AccountProcessor.Payment.PaymentType;

public class PaymentRepositoryTest {

  PaymentRepository paymentRepository = new PaymentRepository();

  @Test
  void testExistsPaymentTrue() {
    paymentRepository.registerPayment("001", LocalDate.parse("2024-06-20"), 15.00, PaymentType.BOLETO, "001");
    assertEquals(true, paymentRepository.existsPayment("001"));
  }

  @Test
  void testExistsPaymentFalse() {
    assertEquals(false, paymentRepository.existsPayment("001"));
  }
}
