import org.junit.Assert;
import org.junit.Test;

import entities.Ingresso;
import entities.Lote;
import entities_enum.Status;
import entities_enum.TipoDeIngresso;

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
    
    @Test
    public void aplicandoDescontosLimiteSuperior() {
        Lote lote = new Lote("lote02");
        lote.cadastrarIngressos(100, 20, 10.0);
        
        // Desconto de 25% para ingressos VIPs e Normais (Limite superior)
        String resultado = lote.oferecerDesconto(25.0);
        double valor = 0.0;
        for (Ingresso ingressos : lote.getIngressos()) {
            if (ingressos.getTipo() != TipoDeIngresso.MEIA) {
                valor += ingressos.getValor();
            }
        }
        Assert.assertEquals(825.0, valor, 0.0);
        Assert.assertEquals("Desconto de 25.0% aplicado", resultado);
    }

    @Test
    public void aplicandoDescontosAcimaDoLimiteSuperior() {
        Lote lote = new Lote("lote02");
        lote.cadastrarIngressos(100, 20, 10.0);
        
        // Desconto acima do limite superior para ingressos VIPs e Normais
        String resultado =  lote.oferecerDesconto(26.0);
        
        Assert.assertEquals("O desconto máximo é de apenas 25%", resultado);
    }

    @Test
    public void aplicandoDescontoEmIngressoMeiaLimiteSuperior() {
        Lote lote = new Lote("lote02");
        Ingresso ingresso = new Ingresso("0M", Status.DISPONIVEL, TipoDeIngresso.MEIA, 5.0);
        lote.addIngressos(ingresso);
        
        // Desconto para ingressos que não são elegíveis
        String resultado = lote.oferecerDesconto(25.0, "0M");
        Assert.assertEquals("Ingresso não elegível a desconto", resultado);
    }
}
