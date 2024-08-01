package entities;

import java.util.ArrayList;
import java.util.List;

public class Lote {
    private String id;
    private List<Ingresso> ingressos = new ArrayList<>();
    private Double desconto;
    
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

}
