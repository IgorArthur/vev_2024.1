package entities;

import java.util.ArrayList;
import java.util.List;
import entities_enum.Financeiro;
import entities_enum.Status;

public class Show {
    private String data;
    private String artista;
    private Double cache;
    private Double despesas_infraestrutura;
    private List<Lote> lotes = new ArrayList<>();
    private Boolean isDataEspecial;
    private Financeiro statusFinanceiro;
    private Double receita_bruta;
    private int vips_vendidos;
    private int normais_vendidos;
    private int meia_vendidos;
    private double receita_liquida;
    
    public Show(String data, String artista, Double cache, Double despesas_infraestrutura, Boolean data_especial) {
        this.data = data;
        this.artista = artista;
        this.cache = cache;
        this.despesas_infraestrutura = despesas_infraestrutura;
        this.isDataEspecial = data_especial;
        this.receita_bruta = 0.0;
        this.receita_liquida = 0.0;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
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

    public int getVipsVendidos() {
        return vips_vendidos;
    }

    public int getNormaisVendidos() {
        return normais_vendidos;
    }

    public int getMeiasVendidos() {
        return meia_vendidos;
    }

    public void setDespesas_infraestrutura(Double despesas_infraestrutura) {
        this.despesas_infraestrutura = despesas_infraestrutura;
    }

    public Boolean getIsDataEspecial() {
        return isDataEspecial;
    }

    public void setIsDataEspecial(Boolean data_especial) {
        this.isDataEspecial = data_especial;
    }

    public Financeiro getStatusFinanceiro() {
        return statusFinanceiro;
    }

    public void setStatusFinanceiro(Financeiro statusFinanceiro) {
        this.statusFinanceiro = statusFinanceiro;
    }

    public void venderIngresso(String tipo, int quantidade) {
        for (Lote lote : lotes) {
            if (tipo.toLowerCase() == "vip" && quantidade > lote.getIngressos_vips()) {
                break;
            } else if (tipo.toLowerCase() == "normal" && quantidade > lote.getIngressos_normais()) {
                break;
            } else if (tipo.toLowerCase() == "meia" && quantidade > lote.getIngressos_meia()) {
                break;
            }
            for (Ingresso ingresso : lote.getIngressos()) {
                if (ingresso.getStatus().equals(Status.DISPONIVEL)) {
                    receita_bruta += ingresso.getValor();
                    ingresso.setStatus(Status.VENDIDO);
                    
                    switch (tipo.toLowerCase()) {
                        case "vip":
                            vips_vendidos++;
                            break;
                        case "meia":
                            meia_vendidos++;
                            break;
                        case "normal":
                            normais_vendidos++;
                            break;
                    }
                    quantidade--;
                    if (quantidade == 0) {
                        calcularReceita();
                        return;
                    } 
                }
            }
        }
        calcularReceita(); 
    }

    public void addLote(Lote lote) {
        lotes.add(lote);
    }

    public List<Lote> getLotes() {
        return lotes;
    }

    public void calcularReceita() {
        if (isDataEspecial) {
           receita_liquida = receita_bruta - ((despesas_infraestrutura * 1.15) + cache);
        } else {
            receita_liquida = receita_bruta - (despesas_infraestrutura + cache);
        }

        if (receita_liquida > 0) {
            statusFinanceiro = Financeiro.LUCRO;
        } else if (receita_liquida == 0) {
            statusFinanceiro = Financeiro.ESTAVEL;
        } else {
            statusFinanceiro = Financeiro.PREJUIZO;
        }
    }

    public String gerarRelatorio() {
        return " <<Relatorio>> \n" + 
                "Ingressos VIPs vendidos: " + vips_vendidos +
                "\nIngressos meias vendidos: " + meia_vendidos +
                "\nIngressos normais vendidos: " + normais_vendidos +
                "\nReceita liquida: " + receita_liquida +
                "\nStatus financeiro: " + statusFinanceiro;
    }
}
