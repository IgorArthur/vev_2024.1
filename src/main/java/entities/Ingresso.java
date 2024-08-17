package entities;

import entities_enum.Status;
import entities_enum.TipoDeIngresso;

public class Ingresso {
    
    private String id;
    private Status status;
    private TipoDeIngresso tipo;
    private Double valor;

    
    
    public Ingresso(String id, Status status, TipoDeIngresso tipo, Double valor) {
        this.id = id;
        this.status = status;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }
    
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TipoDeIngresso getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeIngresso tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setDesconto(Double desconto) {
        this.valor -= valor * (desconto / 100);
    }

}
