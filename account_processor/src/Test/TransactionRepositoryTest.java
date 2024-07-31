package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import AccountProcessor.TransactionRepository;

public class TransactionRepositoryTest {

  TransactionRepository transactionRepository = new TransactionRepository();

  @Test
  void testExistsTransactionTrue() {
    transactionRepository.registerTransaction("001", LocalDate.parse("2024-06-20"), 15.00);
    assertEquals(true, transactionRepository.existsTransaction("001"));
  }

  @Test
  void testExistsTransactionFalse() {
    assertEquals(false, transactionRepository.existsTransaction("001"));
  }
}
