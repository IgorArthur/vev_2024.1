import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import entities.*;
import entities_enum.Financeiro;
import entities_enum.Status;
import entities_enum.TipoDeIngresso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowTest {

    private Show show;
    private Lote lote;
    private Ingresso ingressoVIP;
    private Ingresso ingressoNormal;
    private Ingresso ingressoMeiaEntrada;

    @Before
    public void setUp() {
        show = new Show(
            new Date(),
            "GL",
            1000.0,
            2000.0,
            true,
            Financeiro.LUCRO
        );

        ingressoVIP = new Ingresso("1", Status.VENDIDO, TipoDeIngresso.VIP, 20.0);
        ingressoNormal = new Ingresso("2", Status.VENDIDO, TipoDeIngresso.NORMAL, 10.0);
        ingressoMeiaEntrada = new Ingresso("3", Status.VENDIDO, TipoDeIngresso.MEIA, 5.0);

        List<Ingresso> ingressos = new ArrayList<>();
        ingressos.add(ingressoVIP);
        ingressos.add(ingressoNormal);
        ingressos.add(ingressoMeiaEntrada);

        lote = new Lote();
        lote.setId("Lote1");
        lote.setDesconto(0.15);
        lote.setIngressos(ingressos);

        show.addLote(lote);
    }

    @Test
    public void testCalculoReceitaLiquida() {
        double valorIngressos = ingressoVIP.getValorBase() + ingressoNormal.getValorBase() + ingressoMeiaEntrada.getValorBase();
        double receitaEsperada = valorIngressos * (1 - lote.getDesconto()) - show.getDespesas_infraestrutura() * 1.15 - show.getCache();
        
        assertEquals(receitaEsperada,  -3270.25, 0.01);
    }

    @Test
    public void testContarIngressosVendidosPorTipo() {
        int vendidosVIP = show.contarIngressosVendidosPorTipo(TipoDeIngresso.VIP);
        int vendidosNormal = show.contarIngressosVendidosPorTipo(TipoDeIngresso.NORMAL);
        int vendidosMeiaEntrada = show.contarIngressosVendidosPorTipo(TipoDeIngresso.MEIA);

        assertEquals(1, vendidosVIP);
        assertEquals(1, vendidosNormal);
        assertEquals(1, vendidosMeiaEntrada);
    }

    @Test
    public void testGerarRelatorio() {
        try {
            show.gerarRelatorio();
        } catch (Exception e) {
            fail("Ocorreu uma exceção ao gerar o relatório: " + e.getMessage());
        }
    }

    @Test
    public void testAdicionarLote() {
        Lote novoLote = new Lote();
        novoLote.setId("Lote2");
        novoLote.setDesconto(0.10);
        novoLote.setIngressos(new ArrayList<>());
        
        show.addLote(novoLote);
        
        List<Lote> lotes = show.getLotes();
        assertEquals(2, lotes.size());
        assertEquals("Lote2", lotes.get(1).getId());
    }

    @Test
    public void testRemoverLote() {
        show.removeLote(lote);
        
        List<Lote> lotes = show.getLotes();
        assertEquals(0, lotes.size());
    }


}
