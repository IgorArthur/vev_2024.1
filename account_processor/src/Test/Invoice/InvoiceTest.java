package Test.Invoice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.Test;

import AccountProcessor.Invoice.Invoice;

public class InvoiceTest {
  Invoice invoice = new Invoice("001", LocalDate.parse("2024-06-20"), 15.00, "Cliente teste", new HashSet<String>());

  @Test
  public void testEqualsSameId() {
    Invoice invoice2 = new Invoice("001", LocalDate.parse("2024-06-20"), 15.00, "Cliente teste", new HashSet<String>());

    assertEquals(true, invoice2.equals(invoice));
  }

  @Test
  public void testEqualsDifferentId() {
    Invoice invoice2 = new Invoice("002", LocalDate.parse("2024-06-20"), 15.00, "Cliente teste", new HashSet<String>());

    assertEquals(false, invoice2.equals(invoice));
  }
}
