package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities_enum.Financeiro;

public class Show {
    private Date data;
    private String artista;
    private Double cache;
    private Double despesas_infraestrutura;
    private List<Ingresso> lotes = new ArrayList<>();
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

    public List<Ingresso> getIngressos(){
        return lotes;
    }

    public void addIngresso(Ingresso ingresso){
        lotes.add(ingresso);
    }

    public void removeIngresso(Ingresso ingresso){
        lotes.remove(ingresso);
    }

}
