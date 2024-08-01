import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import entities.*;
import entities_enum.Financeiro;
import entities_enum.Status;
import entities_enum.TipoDeIngresso;

import java.util.Date;

public class ShowTest {
    
    private Show show;
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
        
        show.addIngresso(ingressoVIP);
        show.addIngresso(ingressoNormal);
        show.addIngresso(ingressoMeiaEntrada);
    }

    @Test
    public void testCalculoReceitaLiquida() {
        double desconto = 0.15;
        double valorIngressos = ingressoVIP.getValorBase() + ingressoNormal.getValorBase() + ingressoMeiaEntrada.getValorBase();
        double receitaEsperada = valorIngressos * (1 - desconto) - show.getDespesas_infraestrutura() * 1.15 - show.getCache();
        
        assertEquals(receitaEsperada, show.calcularReceitaLiquida(), 0.01);
        assertEquals(Financeiro.LUCRO, show.getStatusFinanceiro());
    }
}
