import org.junit.Test;

import entities.Lote;
import entities.Show;

public class inicialTest {
    
    @Test
    public void inicialTeste() {
        Show show = new Show("08/05/2001", "Nattanzinho", 1000.00, 2000.00, true);
        Lote lote = new Lote("lote05");
        lote.cadastrarIngressos(500, 20, 10);
        lote.oferecerDesconto(15.0); // para todos os ingressos vips e normais

        show.addLote(lote);

        show.venderIngresso("vip", 100);
        show.venderIngresso("meia", 50);
        show.venderIngresso("normal", 350);
        System.out.println(show.gerarRelatorio());
    }

}
