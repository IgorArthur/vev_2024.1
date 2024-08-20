import org.junit.Assert;
import org.junit.Test;

import entities.Ingresso;
import entities.Lote;
import entities_enum.Status;
import entities_enum.TipoDeIngresso;

public class VeVTest {

    @Test
    public void cadastrarLote1Negativo() {
        Lote lote = new Lote("Lote01");
        String resultado = lote.cadastrarIngressos(100, -1, 10.0);
        Assert.assertEquals("Pelo menos 1 ingresso deve ser cadastrado!", resultado);
    }

    @Test
    public void cadastrarLote0() {
        Lote lote = new Lote("Lote01");
        String resultado = lote.cadastrarIngressos(100, 0, 10.0);
        Assert.assertEquals("Pelo menos 1 ingresso deve ser cadastrado!", resultado);
    }

    @Test
    public void cadastrarLote19() {
        Lote lote = new Lote("Lote01");
        String resultado = lote.cadastrarIngressos(100, 19, 10.0);
        Assert.assertEquals("Ingressos vips devem ser no mínimo 20%", resultado);
    }

    @Test
    public void cadastrarLote20() {
        Lote lote = new Lote("Lote01");
        String resultado = lote.cadastrarIngressos(100, 20, 10.0);
        Assert.assertEquals("Lote de ingressos cadastrados com sucesso!", resultado);
    }

    @Test
    public void cadastrarLote29() {
        Lote lote = new Lote("Lote01");
        String resultado = lote.cadastrarIngressos(100, 29, 10.0);
        Assert.assertEquals("Lote de ingressos cadastrados com sucesso!", resultado);
    }

    @Test
    public void cadastrarLote30() {
        Lote lote = new Lote("Lote01");
        String resultado = lote.cadastrarIngressos(100, 30, 10.0);
        Assert.assertEquals("Lote de ingressos cadastrados com sucesso!", resultado);
    }

    @Test
    public void cadastrarLote31() {
        Lote lote = new Lote("Lote01");
        String resultado = lote.cadastrarIngressos(100, 31, 10.0);
        Assert.assertEquals("Capacidade máxima de ingressos VIP de 30%", resultado);
    }

    @Test
    public void oferecerDesconto1Negativo() {
        Lote lote = new Lote("Lote01");
        lote.cadastrarIngressos(100, 25, 10.0);
        String resultado = lote.oferecerDesconto(-1);
        Assert.assertEquals("O desconto mínimo é de 0%", resultado);
    }

    @Test
    public void oferecerDesconto0() {
        Lote lote = new Lote("Lote01");
        lote.cadastrarIngressos(100, 25, 0);
        String resultado = lote.oferecerDesconto(0);
        Assert.assertEquals("Desconto de 0% aplicado", resultado);
    }

    @Test
    public void oferecerDesconto24() {
        Lote lote = new Lote("Lote01");
        lote.cadastrarIngressos(100, 25, 10.0);
        String resultado = lote.oferecerDesconto(24.0);
        Assert.assertEquals("Desconto de 24% aplicado", resultado);
    }

    @Test
    public void oferecerDesconto25() {
        Lote lote = new Lote("Lote01");
        lote.cadastrarIngressos(100, 25, 10.0);
        String resultado = lote.oferecerDesconto(25.0);
        Assert.assertEquals("Desconto de 25% aplicado", resultado);
    }

    @Test
    public void oferecerDesconto26() {
        Lote lote = new Lote("Lote01");
        lote.cadastrarIngressos(100, 25, 10.0);
        String resultado = lote.oferecerDesconto(26.0);
        Assert.assertEquals("O desconto máximo é de apenas 25%", resultado);
    }

    //

    @Test
    public void cadastrarLoteNegativo() {
        Lote lote = new Lote("Lote01");
        String resultado = lote.cadastrarIngressos(100, -5, 10.0);
        Assert.assertEquals("Pelo menos 1 ingresso deve ser cadastrado!", resultado);
    }

    @Test
    public void cadastrarLoteMenor20() {
        Lote lote = new Lote("Lote01");
        String resultado = lote.cadastrarIngressos(100, 10, 10.0);
        Assert.assertEquals("Ingressos vips devem ser no mínimo 20%", resultado);
    }

    @Test
    public void cadastrarLoteMaior20Menor30() {
        Lote lote = new Lote("Lote01");
        String resultado = lote.cadastrarIngressos(100, 25, 10.0);
        Assert.assertEquals("Lote de ingressos cadastrados com sucesso!", resultado);
    }

    @Test
    public void cadastrarLoteMaior30() {
        Lote lote = new Lote("Lote01");
        String resultado = lote.cadastrarIngressos(100, 35, 10.0);
        Assert.assertEquals("Capacidade máxima de ingressos VIP de 30%", resultado);
    }

    @Test
    public void marcarIngressoVendido() {
        Ingresso ingresso = new Ingresso("Ingresso01", Status.VENDIDO, TipoDeIngresso.NORMAL, 10);
    }

    @Test
    public void marcarIngressoDisponivel() {
        Ingresso ingresso = new Ingresso("Ingresso01", Status.DISPONIVEL, TipoDeIngresso.NORMAL, 10);
    }

    @Test
    public void oferecerDescontoIngressoNormal() {
        Ingresso ingresso = new Ingresso("Ingresso01", Status.DISPONIVEL, TipoDeIngresso.NORMAL, 10);
        ingresso.setDesconto(10);
    }

    @Test
    public void oferecerDescontoIngressoMeia() {
        Ingresso ingresso = new Ingresso("Ingresso01", Status.DISPONIVEL, TipoDeIngresso.MEIA, 10);
        ingresso.setDesconto(10);
    }

    @Test
    public void oferecerDescontoIngressoVIP() {
        Ingresso ingresso = new Ingresso("Ingresso01", Status.DISPONIVEL, TipoDeIngresso.VIP, 10);
        ingresso.setDesconto(10);
    }
}