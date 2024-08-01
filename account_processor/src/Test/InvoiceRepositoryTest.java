package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import AccountProcessor.InvoiceRepository;
import AccountProcessor.Transaction;

public class InvoiceRepositoryTest {

  InvoiceRepository invoiceRepository = new InvoiceRepository();

  @Test
  void testExistsTransactionTrue() {
    invoiceRepository.registerInvoice("001", LocalDate.parse("2024-06-20"), 15.00, "Cliente Teste", new HashSet<Transaction>());
    assertEquals(true, invoiceRepository.existsInvoice("001"));
  }

  @Test
  void testExistsTransactionFalse() {
    assertEquals(false, invoiceRepository.existsInvoice("001"));
  }
}
