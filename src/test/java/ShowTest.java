import org.junit.Assert;
import org.junit.Test;

import entities.Lote;
import entities.Show;

public class ShowTest {

    @Test
    public void vendaDeIngressosVipParticaoValida() {
        Lote lote = new Lote("lote02");
        lote.cadastrarIngressos(500, 20, 10.0);
        Show show = new Show("08/05/2025", "GL", 1000.0, 1500.0, false);
        show.addLote(lote);

        // Venda de ingressos dentro da partição válida para VIP
        show.venderIngresso("vip", 100);
        Assert.assertEquals(100, show.getVipsVendidos());
    }

    @Test
    public void vendaDeIngressosVipParticaoInvalida() {
        Lote lote = new Lote("lote02");
        lote.cadastrarIngressos(500, 20, 10.0);
        Show show = new Show("08/05/2025", "GL", 1000.0, 1500.0, false);
        show.addLote(lote);

        // Venda de ingressos fora da partição válida para VIP
        show.venderIngresso("vip", 101);
        Assert.assertEquals(0, show.getVipsVendidos());
    }

    @Test
    public void vendaDeIngressosNormalParticaoValida() {
        Lote lote = new Lote("lote02");
        lote.cadastrarIngressos(500, 20, 10.0);
        Show show = new Show("08/05/2025", "GL", 1000.0, 1500.0, false);
        show.addLote(lote);

        // Venda de ingressos dentro da partição válida para normal
        show.venderIngresso("normal", 350);
        Assert.assertEquals(350, show.getNormaisVendidos());
    }

    @Test
    public void vendaDeIngressosNormalParticaoInvalida() {
        Lote lote = new Lote("lote02");
        lote.cadastrarIngressos(500, 20, 10.0);
        Show show = new Show("08/05/2025", "GL", 1000.0, 1500.0, false);
        show.addLote(lote);

        // Venda de ingressos fora da partição válida para normal
        show.venderIngresso("normal", 351);
        Assert.assertEquals(0, show.getNormaisVendidos());
    }

    @Test
    public void vendaDeIngressosMeiaParticaoValida() {
        Lote lote = new Lote("lote02");
        lote.cadastrarIngressos(500, 20, 10.0);
        Show show = new Show("08/05/2025", "GL", 1000.0, 1500.0, false);
        show.addLote(lote);

        // Venda de ingressos dentro da partição válida para meia
        show.venderIngresso("meia", 50);
        Assert.assertEquals(50, show.getMeiasVendidos());
    }

    @Test
    public void vendaDeIngressosMeiaParticaoInvalida() {
        Lote lote = new Lote("lote02");
        lote.cadastrarIngressos(500, 20, 10.0);
        Show show = new Show("08/05/2025", "GL", 1000.0, 1500.0, false);
        show.addLote(lote);

        // Venda de ingressos fora da partição válida para meia
        show.venderIngresso("meia", 51);
        Assert.assertEquals(0, show.getMeiasVendidos());
    }

    // Testes por tabela de decisão
    
    @Test
    public void calculoDeReceitaLiquidaCaso1() {
        Lote lote = new Lote("lote01");
        lote.cadastrarIngressos(500, 20, 100.0);
        Show show = new Show("19/08/2024", "João", 2000.0, 1500.00, false);
        show.addLote(lote);
        show.venderIngresso("vip", 100);
        show.venderIngresso("normal", 300);
        show.venderIngresso("meia", 50);
        show.calcularReceita();
        Double receitaLiquida = show.getReceitaLiquida();
        
        Assert.assertEquals(49000.0, receitaLiquida, 0);
    }

    @Test
    public void calculoDeReceitaLiquidaCaso2() {
        Show show = new Show("19/08/2024", "João", 2500.0, 1000.00, true);
        Lote lote = new Lote("lote09");
        lote.cadastrarIngressos(500, 20, 100.0);
        lote.oferecerDesconto(10.0); 
        show.addLote(lote);
        show.venderIngresso("normal", 200);
        show.venderIngresso("meia", 50);

        show.calcularReceita();
        Double receitaLiquida = show.getReceitaLiquida();

        System.out.println(receitaLiquida);

        Assert.assertEquals(16600.0, receitaLiquida, 0);
    }

    @Test
    public void calculoDeReceitaLiquidaCaso3() {
        Show show = new Show("19/08/2024", "João", 6000.0, 20000.00, false);
        Lote lote = new Lote("lote03");
        lote.cadastrarIngressos(500, 20, 100.0);
        show.addLote(lote);
        show.venderIngresso("normal", 40);
        show.venderIngresso("vip", 50);
        show.venderIngresso("meia", 50);

        show.calcularReceita();
        Double receitaLiquida = show.getReceitaLiquida();

        Assert.assertEquals(-9500.0, receitaLiquida, 0); // Esperado prejuízo
    }

    @Test
    public void calculoDeReceitaLiquidaCaso4() {
        Show show = new Show("19/08/2024", "João", 30000.0, 5000.00, true);
        Lote lote = new Lote("lote04");
        lote.cadastrarIngressos(500, 20, 100.0);
        show.addLote(lote);
        show.venderIngresso("normal", 300);
        show.venderIngresso("vip", 100);
        show.venderIngresso("meia", 50);

        show.calcularReceita();
        Double receitaLiquida = show.getReceitaLiquida();

        Assert.assertEquals(16750.0, receitaLiquida, 0); 
    }

    @Test
    public void calculoDeReceitaLiquidaCaso5() {
        Show show = new Show("19/08/2024", "João", 25000.0, 22475.00, false);
        Lote lote = new Lote("lote05");
        lote.cadastrarIngressos(500, 20, 100.0);
        lote.oferecerDesconto(5.0);
        show.addLote(lote);
        show.venderIngresso("normal", 300);
        show.venderIngresso("vip", 100);

        show.calcularReceita();
        Double receitaLiquida = show.getReceitaLiquida();

        Assert.assertEquals(0.0, receitaLiquida, 0); 
    }

    @Test
    public void calculoDeReceitaLiquidaCaso6() {
        Show show = new Show("19/08/2024", "João", 25000.0, 10000.00, false);
        Lote lote = new Lote("lote06");
        lote.cadastrarIngressos(500, 20, 100.0);
        show.addLote(lote);
        show.venderIngresso("normal", 100);
        show.venderIngresso("vip", 100);
        show.venderIngresso("meia", 50);

        show.calcularReceita();
        Double receitaLiquida = show.getReceitaLiquida();

        Assert.assertEquals(25000.0, receitaLiquida, 0);
    }

    @Test
    public void calculoDeReceitaLiquidaCaso7() {
        Show show = new Show("19/08/2024", "João", 25000.0, 20000.00, true);
        Lote lote = new Lote("lote07");
        lote.cadastrarIngressos(500, 20, 100.0);
        show.addLote(lote);
        show.venderIngresso("normal", 150);
        show.venderIngresso("vip", 50);
        show.venderIngresso("meia", 50);

        show.calcularReceita();
        Double receitaLiquida = show.getReceitaLiquida();

        Assert.assertEquals(15000.0, receitaLiquida, 0);
    }

    @Test
    public void calculoDeReceitaLiquidaCaso8() {
        Show show = new Show("19/08/2024", "João", 19950.0, 15000.00, true);
        Lote lote = new Lote("lote08");
        lote.cadastrarIngressos(5000, 20, 100.0);
        show.addLote(lote);
        show.venderIngresso("normal", 170);
        show.venderIngresso("vip", 76);
        show.venderIngresso("meia", 100);

        show.calcularReceita();
        Double receitaLiquida = show.getReceitaLiquida();

        Assert.assertEquals(0.0, receitaLiquida, 0);
    }

}