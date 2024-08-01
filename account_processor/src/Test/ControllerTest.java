package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.Test;

import AccountProcessor.Controller;
import AccountProcessor.Payment.PaymentType;

public class ControllerTest {

  Controller controller = new Controller();

  @Test
  public void testTransactionExists() {
    controller.registerTransaction("001", LocalDate.parse("2024-06-20"), 15.00);

    assertEquals("CONTA JÁ CADASTRADA!", controller.registerTransaction("001", LocalDate.parse("2024-06-20"), 15.00));
  }

  @Test
  public void testInvoiceExists() {
    controller.registerInvoice("001", LocalDate.parse("2024-06-20"), 15.00, "Cliente Teste",
        new HashSet<String>());

    assertEquals("FATURA JÁ CADASTRADA!", controller.registerInvoice("001", LocalDate.parse("2024-06-20"), 15.00,
        "Cliente Teste", new HashSet<String>()));
  }

  @Test
  public void testPaymentExists() {
    controller.registerTransaction("001", LocalDate.parse("2024-06-20"), 15.00);

    controller.registerPayment("001", LocalDate.parse("2024-06-20"), 15.00, PaymentType.BOLETO, "001");

    assertEquals("PAGAMENTO JÁ CADASTRADO!",
        controller.registerPayment("001", LocalDate.parse("2024-06-20"), 15.00, PaymentType.BOLETO, "001"));
  }

  @Test
  public void testPaymentBoletoWrongValueUnder() {
    controller.registerTransaction("001", LocalDate.parse("2024-06-20"), 15.00);

    assertEquals("PAGAMENTOS POR BOLETO NÃO PODEM TER VALOR INFERIOR A R$0,01 OU SUPERIOR A R$5.000,00!",
        controller.registerPayment("002", LocalDate.parse("2024-06-20"), 0, PaymentType.BOLETO, "001"));
  }

  @Test
  public void testPaymentBoletoWrongValueOver() {
    controller.registerTransaction("001", LocalDate.parse("2024-06-20"), 15.00);

    assertEquals("PAGAMENTOS POR BOLETO NÃO PODEM TER VALOR INFERIOR A R$0,01 OU SUPERIOR A R$5.000,00!",
        controller.registerPayment("002", LocalDate.parse("2024-06-20"), 5001, PaymentType.BOLETO, "001"));
  }

  @Test
  public void testPaymentBoletoSameDateChangesValue() {
    controller.registerTransaction("001", LocalDate.parse("2024-06-20"), 15.00);

    assertEquals("DEVIDO A DATA DO PAGAMENTO DO BOLETO, O VALOR DA CONTA FOI ACRESCIDO EM 10%.\nPAGAMENTO CADASTRADO!",
        controller.registerPayment("001", LocalDate.parse("2024-06-21"), 15, PaymentType.BOLETO, "001"));
    assertEquals(16.5, controller.getTransaction("001").getValue());
  }

  @Test
  public void testInvoiceMapsTransactionInPayment() {
    controller.registerTransaction("001", LocalDate.parse("2024-06-20"), 15.00);

    HashSet<String> transactions = new HashSet<String>();
    transactions.add("001");

    controller.registerInvoice("001", LocalDate.parse("2024-06-20"), 15.00, "Cliente Teste",
        transactions);

    assertEquals("FATURA JÁ CADASTRADA!", controller.registerInvoice("001", LocalDate.parse("2024-06-20"), 15.00,
        "Cliente Teste", new HashSet<String>()));
  }
}
