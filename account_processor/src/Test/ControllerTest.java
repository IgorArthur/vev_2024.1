package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.Test;

import AccountProcessor.Controller;
import AccountProcessor.PaymentType;
import AccountProcessor.Transaction;

public class ControllerTest {

  Controller controller = new Controller();

  @Test
  public void testTransactionExists() {
    controller.registerTransaction("001", LocalDate.parse("2024-06-20"), 15.00);

    assertEquals("CONTA JÁ CADASTRADA!", controller.registerTransaction("001", LocalDate.parse("2024-06-20"), 15.00));
  }

  @Test
  public void testInvoiceExists() {
    controller.registerInvoice("001", LocalDate.parse("2024-06-20"), 15.00, "Cliente Teste", new HashSet<Transaction>());

    assertEquals("FATURA JÁ CADASTRADA!", controller.registerInvoice("001", LocalDate.parse("2024-06-20"), 15.00, "Cliente Teste", new HashSet<Transaction>()));
  }

  @Test
  public void testPaymentExists() {
    controller.registerTransaction("001", LocalDate.parse("2024-06-20"), 15.00);
    
    controller.registerPayment("001", LocalDate.parse("2024-06-20"), 15.00, PaymentType.BOLETO, "001");

    assertEquals("PAGAMENTO JÁ CADASTRADO!", controller.registerPayment("001", LocalDate.parse("2024-06-20"), 15.00, PaymentType.BOLETO, "001"));
  }
}
