package AccountProcessor.Payment;

public enum PaymentType {
  BOLETO,
  CARTAO_CREDITO,
  TRANSFERENCIA_BANCARIA;

  public static PaymentType getById(int id) {
    switch (id) {
      case 0:
        return PaymentType.BOLETO;
      case 1:
        return PaymentType.CARTAO_CREDITO;
      case 2:
        return PaymentType.TRANSFERENCIA_BANCARIA;
    }
    return null;
  }
}
