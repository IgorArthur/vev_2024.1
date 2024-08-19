import org.junit.Assert;
import org.junit.Test;

import entities.Lote;

public class LoteTest {
    
    @Test
    public void testeCadastroDeVipsLimiteSuperior() {
        Lote lote = new Lote("Lote01");
        
        // Cadastro de ingressos com 30% VIP (limite superior)
        String resultado = lote.cadastrarIngressos(500, 30, 10.0);
        Assert.assertEquals("Lote de ingressos cadastrados com sucesso!", resultado);

        // Verificação da quantidade de ingressos VIPs cadastrados
        double ingressosVips = lote.getIngressos_vips();
        Assert.assertEquals(150.0, ingressosVips, 0);

        // Verificação da quantidade de ingressos meias
        double ingressos_meia = lote.getIngressos_meia();
        Assert.assertEquals(50.0, ingressos_meia, 0);

        // Verificação da quantidade de ingressos normais
        double ingressos_normais = lote.getIngressos_normais();
        Assert.assertEquals(300.0, ingressos_normais, 0);
    }

    @Test
    public void testeCadastroDeVipsAcimaDoLimite() {
        Lote lote = new Lote("Lote01");
        
        // Cadastro de ingressos com 31% VIP (Acima do limite superior)
        String resultado = lote.cadastrarIngressos(500, 31, 10.0);
        Assert.assertEquals("Capacidade maxima de ingressos VIP de 30%", resultado);

        // Verificação da quantidade de ingressos VIPs cadastrados
        double ingressosVips = lote.getIngressos_vips();
        Assert.assertEquals(0.0, ingressosVips, 0);

        // Verificação da quantidade de ingressos meias
        double ingressos_meia = lote.getIngressos_meia();
        Assert.assertEquals(0.0, ingressos_meia, 0);

        // Verificação da quantidade de ingressos normais
        double ingressos_normais = lote.getIngressos_normais();
        Assert.assertEquals(0.0, ingressos_normais, 0);
    }

    @Test
    public void testeCadastroDeVipsLimiteInferior() {
        Lote lote = new Lote("Lote01");
        
        // Cadastro de ingressos com 20% VIP (Limite inferior)
        String resultado = lote.cadastrarIngressos(500, 20, 10.0);
        Assert.assertEquals("Lote de ingressos cadastrados com sucesso!", resultado);

        // Verificação da quantidade de ingressos VIPs cadastrados
        double ingressosVips = lote.getIngressos_vips();
        Assert.assertEquals(100.0, ingressosVips, 0);

        // Verificação da quantidade de ingressos meias
        double ingressos_meia = lote.getIngressos_meia();
        Assert.assertEquals(50.0, ingressos_meia, 0);

        // Verificação da quantidade de ingressos normais
        double ingressos_normais = lote.getIngressos_normais();
        Assert.assertEquals(350.0, ingressos_normais, 0);
    }

    @Test
    public void testeCadastroDeVipsAbaixoDoLimiteInferior() {
        Lote lote = new Lote("Lote01");
        
        // Cadastro de ingressos com 19% VIP (Abaixo do limite inferior)
        String resultado = lote.cadastrarIngressos(500, 19, 10.0);
        Assert.assertEquals("Ingressos vips devem ser no minimo 20%", resultado);

        // Verificação da quantidade de ingressos VIPs cadastrados
        double ingressosVips = lote.getIngressos_vips();
        Assert.assertEquals(0.0, ingressosVips, 0);

        // Verificação da quantidade de ingressos meias
        double ingressos_meia = lote.getIngressos_meia();
        Assert.assertEquals(0.0, ingressos_meia, 0);

        // Verificação da quantidade de ingressos normais
        double ingressos_normais = lote.getIngressos_normais();
        Assert.assertEquals(0.0, ingressos_normais, 0);
    }

    @Test
    public void testeCadastroDeIngressosAbaixoDoLimiteInferior() {
        Lote lote = new Lote("Lote01");
        
        // Cadastro da quantidade ingressos como 0 (Abaixo do limite inferior)
        String resultado = lote.cadastrarIngressos(0, 20, 10.0);
        Assert.assertEquals("Pelo menos 1 ingresso deve ser cadastrado!", resultado);

        // Verificação da quantidade de ingressos VIPs cadastrados
        double ingressosVips = lote.getIngressos_vips();
        Assert.assertEquals(0.0, ingressosVips, 0);

        // Verificação da quantidade de ingressos meias
        double ingressos_meia = lote.getIngressos_meia();
        Assert.assertEquals(0.0, ingressos_meia, 0);

        // Verificação da quantidade de ingressos normais
        double ingressos_normais = lote.getIngressos_normais();
        Assert.assertEquals(0.0, ingressos_normais, 0);
    }

}
