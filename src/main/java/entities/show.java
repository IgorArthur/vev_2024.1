package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities_enum.Financeiro;
import entities_enum.Status;
import entities_enum.TipoDeIngresso;

public class Show {
    private Date data;
    private String artista;
    private Double cache;
    private Double despesas_infraestrutura;
    private List<Lote> lotes = new ArrayList<>();
    private Boolean data_especial;
    private Financeiro statusFinanceiro;

    public Show(Date data, String artista, Double cache, Double despesas_infraestrutura, Boolean data_especial, Financeiro statusFinanceiro) {
        this.data = data;
        this.artista = artista;
        this.cache = cache;
        this.despesas_infraestrutura = despesas_infraestrutura;
        this.data_especial = data_especial;
        this.statusFinanceiro = statusFinanceiro;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public Double getCache() {
        return cache;
    }

    public void setCache(Double cache) {
        this.cache = cache;
    }

    public Double getDespesas_infraestrutura() {
        return despesas_infraestrutura;
    }

    public void setDespesas_infraestrutura(Double despesas_infraestrutura) {
        this.despesas_infraestrutura = despesas_infraestrutura;
    }

    public Boolean getData_especial() {
        return data_especial;
    }

    public void setData_especial(Boolean data_especial) {
        this.data_especial = data_especial;
    }

    public Financeiro getStatusFinanceiro() {
        return statusFinanceiro;
    }

    public void setStatusFinanceiro(Financeiro statusFinanceiro) {
        this.statusFinanceiro = statusFinanceiro;
    }

    public List<Lote> getLotes() {
        return lotes;
    }

    public void addLote(Lote lote) {
        lotes.add(lote);
    }

    public void removeLote(Lote lote) {
        lotes.remove(lote);
    }

    public double calcularReceitaLiquida() {
        double receitaBruta = 0.0;

        for (Lote lote : lotes) {
            double desconto = lote.getDesconto();
            for (Ingresso ingresso : lote.getIngressos()) {
                if (ingresso.getStatus() == Status.VENDIDO) {
                    double valor = ingresso.getValorBase();
                    if (ingresso.getTipo() == TipoDeIngresso.VIP || ingresso.getTipo() == TipoDeIngresso.NORMAL) {
                        valor -= valor * desconto; // Aplicando desconto
                    }
                    receitaBruta += valor;
                }
            }
        }

        double despesas = despesas_infraestrutura;
        if (data_especial) {
            despesas += despesas * 0.15; // 15% a mais para data especial
        }

        double receitaLiquida = receitaBruta - despesas - cache;

        if (receitaLiquida > 0) {
            statusFinanceiro = Financeiro.LUCRO;
        } else if (receitaLiquida == 0) {
            statusFinanceiro = Financeiro.ESTAVEL;
        } else {
            statusFinanceiro = Financeiro.PREJUIZO;
        }

        return receitaLiquida;
    }

    public int contarIngressosVendidosPorTipo(TipoDeIngresso tipo) {
        int count = 0;
        for (Lote lote : lotes) {
            for (Ingresso ingresso : lote.getIngressos()) {
                if (ingresso.getTipo() == tipo && ingresso.getStatus() == Status.VENDIDO) {
                    count++;
                }
            }
        }
        return count;
    }

    public double calcularTotalReceita() {
        double total = 0.0;
        for (Lote lote : lotes) {
            for (Ingresso ingresso : lote.getIngressos()) {
                if (ingresso.getStatus() == Status.VENDIDO) {
                    total += ingresso.getValorBase();
                }
            }
        }
        return total;
    }

    public void gerarRelatorio() {
        int vendidosVIP = contarIngressosVendidosPorTipo(TipoDeIngresso.VIP);
        int vendidosNormal = contarIngressosVendidosPorTipo(TipoDeIngresso.NORMAL);
        int vendidosMeiaEntrada = contarIngressosVendidosPorTipo(TipoDeIngresso.MEIA);

        double receitaLiquida = calcularReceitaLiquida();

        System.out.println("Relatório do Show:");
        System.out.println("Ingressos vendidos VIP: " + vendidosVIP);
        System.out.println("Ingressos vendidos NORMAL: " + vendidosNormal);
        System.out.println("Ingressos vendidos MEIA ENTRADA: " + vendidosMeiaEntrada);
        System.out.println("Receita líquida: R$ " + receitaLiquida);
        System.out.println("Status financeiro: " + statusFinanceiro);
    }
}
