import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import entities.Lote;
import entities.Ingresso;
import entities_enum.TipoDeIngresso;
import entities_enum.Status;

public class LoteTest {

    private Lote loteComDescontoValido;
    private Lote loteComDescontoExcedente;

    @Before
    public void setUp() {
        loteComDescontoValido = new Lote("Lote1", 0.20); // Desconto de 20%
        loteComDescontoExcedente = new Lote("Lote2", 0.30); // Desconto de 30% (deve ser ajustado para 25%)

        // Adiciona alguns ingressos ao lote
        loteComDescontoValido.addIngresso(new Ingresso("1", Status.VENDIDO, TipoDeIngresso.NORMAL, 10.0));
        loteComDescontoExcedente.addIngresso(new Ingresso("2", Status.VENDIDO, TipoDeIngresso.NORMAL, 10.0));
    }

    @Test
    public void testDescontoMaximoPermitido() {
        assertEquals(0.20, loteComDescontoValido.getDesconto(), 0.01); // Verifica se o desconto está correto

        // Verifica se o desconto excedente é ajustado para o máximo permitido de 25%
        assertEquals(0.25, loteComDescontoExcedente.getDesconto(), 0.01);
    }

    @Test
    public void testAplicacaoDesconto() {
        double valorOriginal = 10.0;

        // Verifica o valor após aplicar o desconto
        double valorComDescontoValido = valorOriginal - valorOriginal * loteComDescontoValido.getDesconto();
        double valorComDescontoExcedente = valorOriginal - valorOriginal * loteComDescontoExcedente.getDesconto();

        assertEquals(8.0, valorComDescontoValido, 0.01); // 10 - 20% de 10
        assertEquals(7.5, valorComDescontoExcedente, 0.01); // 10 - 25% de 10 (desconto ajustado)
    }
}
