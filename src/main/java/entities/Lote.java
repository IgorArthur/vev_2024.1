package entities;

import java.util.ArrayList;
import java.util.List;

public class Lote {
    private String id;
    private List<Ingresso> ingressos = new ArrayList<>();
    private Double desconto;

    
    
    public Lote(String id, double desconto) {
        // Garantir que o desconto não exceda 25%
        this.desconto = Math.min(desconto, 0.25);
        this.id = id;
    }

    public Lote() {
       
    }

    public String getId() {
        return id;
    }
   
    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addIngresso(Ingresso ingresso) {
        if (ingresso != null) {
            ingressos.add(ingresso);
        } else {
            throw new IllegalArgumentException("Ingresso não pode ser nulo");
        }
    }

    public double calcularValorComDesconto(double valorOriginal) {
        return valorOriginal - valorOriginal * desconto;
    }

}
