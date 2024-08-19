import org.junit.Assert;
import org.junit.Test;

import entities.Lote;
import entities.Show;

public class ShowTest {
     @Test
    public void vendaDeIngressosVipLimiteSuperior() {
        Lote lote = new Lote("lote02");
        lote.cadastrarIngressos(500, 20, 10.0);
        Show show = new Show("08/05/2025", "GL", 1000.0, 1500.0, false);
        show.addLote(lote);

        // Venda de ingressos até o limite superior da quantidade, dado o tipo de um ingresso
        show.venderIngresso("vip", 100);
        Assert.assertEquals(100, show.getVipsVendidos());
    }

    @Test
    public void vendaDeIngressosVipAcimaLimiteSuperior() {
        Lote lote = new Lote("lote02");
        lote.cadastrarIngressos(500, 20, 10.0);
        Show show = new Show("08/05/2025", "GL", 1000.0, 1500.0, false);
        show.addLote(lote);

        // Venda de ingressos acima do limite superior da quantidade, dado o tipo de um ingresso
        show.venderIngresso("vip", 101);
        Assert.assertEquals(0, show.getVipsVendidos());
    }

    @Test
    public void vendaDeIngressosNormalLimiteSuperior() {
        Lote lote = new Lote("lote02");
        lote.cadastrarIngressos(500, 20, 10.0);
        Show show = new Show("08/05/2025", "GL", 1000.0, 1500.0, false);
        show.addLote(lote);

        // Venda de ingressos até o limite superior da quantidade restante para os ingressos normais
        show.venderIngresso("normal", 350);
        Assert.assertEquals(350, show.getNormaisVendidos());
    }

    @Test
    public void vendaDeIngressosNormalAcimaDoLimiteSuperior() {
        Lote lote = new Lote("lote02");
        lote.cadastrarIngressos(500, 20, 10.0);
        Show show = new Show("08/05/2025", "GL", 1000.0, 1500.0, false);
        show.addLote(lote);

        // Venda de ingressos até o limite superior da quantidade restante para os ingressos normais
        show.venderIngresso("normal", 351);
        Assert.assertEquals(0, show.getNormaisVendidos());
    }

    @Test
    public void vendaDeIngressosMeiaLimiteSuperior() {
        Lote lote = new Lote("lote02");
        lote.cadastrarIngressos(500, 20, 10.0);
        Show show = new Show("08/05/2025", "GL", 1000.0, 1500.0, false);
        show.addLote(lote);

        // Venda de ingressos até o limite superior da quantidade restante para os ingressos normais
        show.venderIngresso("meia", 50);
        Assert.assertEquals(50, show.getMeiasVendidos());
    }

    @Test
    public void vendaDeIngressosMeiaAcimaDoLimiteSuperior() {
        Lote lote = new Lote("lote02");
        lote.cadastrarIngressos(500, 20, 10.0);
        Show show = new Show("08/05/2025", "GL", 1000.0, 1500.0, false);
        show.addLote(lote);

        // Venda de ingressos até o limite superior da quantidade restante para os ingressos normais
        show.venderIngresso("meia", 51);
        Assert.assertEquals(0, show.getMeiasVendidos());
    }
}