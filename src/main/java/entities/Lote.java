package entities;

import java.util.ArrayList;
import java.util.List;

import entities_enum.Status;
import entities_enum.TipoDeIngresso;

public class Lote {
    private String id;
    private List<Ingresso> ingressos = new ArrayList<>();
    private Boolean elegivelDesconto;
    Double maxDesconto;
    Double maxIngressosVip;
    Double minIngressosVip;
    double ingressos_meia;
    double ingressos_vips;
    double ingressos_normais;


    public Lote(String id) {
        this.id = id;
        this.maxDesconto = 25.0;
        this.maxIngressosVip  = 30.0; // %
        this.minIngressosVip = 20.0; // problema localizado através dos testes AVL
    }

    // Desconto em % por ingresso 
    public String oferecerDesconto(Double desconto, String id) {
        
        String msg = "Ingresso não elegível a desconto";

        for (int i = 0; i < ingressos.size(); i++) {
            
            if (ingressos.get(i).getId() == id && ingressos.get(i).getTipo() != TipoDeIngresso.MEIA) {
                
                if (desconto <= 25.0) {
                    ingressos.get(i).setDesconto(desconto);
                    msg = "Desconto de " + desconto + "%" + " aplicado";
                } else {
                    msg = "O desconto máximo é de apenas 25%";
                }
            }
        }
        return msg;
    }

    // Aplicar desconto a todos os ingressos elegíveis do lote
    public String oferecerDesconto(Double desconto) {
        
        String msg = "Não foi possível aplicar os descontos!";

        for (int i = 0; i < ingressos.size(); i++) {
            
            if (ingressos.get(i).getTipo() != TipoDeIngresso.MEIA) {
                
                if (desconto <= 25.0) {
                    ingressos.get(i).setDesconto(desconto);
                    msg = "Desconto de " + desconto + "%" + " aplicado";
                } else {
                    msg = "O desconto máximo é de apenas 25%";
                }
            }
        }
        return msg;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public List<Ingresso> getIngressos() {
        return ingressos;
    }


    public void addIngressos(Ingresso ingresso) {
        ingressos.add(ingresso);
    }

    public void removeIngressos(Ingresso ingresso) {
        ingressos.remove(ingresso);
    }

    public Boolean getElegivelDesconto() {
        return elegivelDesconto;
    }


    public void setElegivelDesconto(Boolean elegivelDesconto) {
        this.elegivelDesconto = elegivelDesconto;
    }

    public double getIngressos_meia() {
        return ingressos_meia;
    }

    public double getIngressos_vips() {
        return ingressos_vips;
    }

    public double getIngressos_normais() {
        return ingressos_normais;
    }

    public String cadastrarIngressos(int n_ingressos, double porcentagemVip, double valor_base) {
        
        String msg_ingressos = "";
        if (porcentagemVip > maxIngressosVip) {
            msg_ingressos = "Capacidade maxima de ingressos VIP de 30%";
        } else if (n_ingressos <= 0) {
            msg_ingressos = "Pelo menos 1 ingresso deve ser cadastrado!"; // Notado durante os testes
        }
         else if (porcentagemVip < minIngressosVip) {
            msg_ingressos = "Ingressos vips devem ser no minimo 20%";
        } else {
            ingressos_meia = n_ingressos * 0.10;
            ingressos_vips = n_ingressos * (porcentagemVip/100);
            ingressos_normais = n_ingressos - ingressos_vips - ingressos_meia;

            for (int i = 0; i < ingressos_meia; i++) {
                String id = i + "M";
                Ingresso ingresso = new Ingresso(id, entities_enum.Status.DISPONIVEL, TipoDeIngresso.MEIA, valor_base / 2);
                ingressos.add(ingresso);
            }

            for (int i = 0; i < ingressos_vips; i++) {
                String id = i + "V";
                Ingresso ingresso = new Ingresso(id, entities_enum.Status.DISPONIVEL, TipoDeIngresso.VIP, valor_base * 2);
                ingressos.add(ingresso);
            }

            for (int i = 0; i < ingressos_normais; i++) {
                String id = i + "N";
                Ingresso ingresso = new Ingresso(id, entities_enum.Status.DISPONIVEL, TipoDeIngresso.NORMAL, valor_base);
                ingressos.add(ingresso);
            }

            msg_ingressos = "Lote de ingressos cadastrados com sucesso!";
        }

        return msg_ingressos;
    }

    public boolean verificaDisponibilidade(String tipo) {
        for (int i = 0; i < ingressos.size(); i++) {
            if (ingressos.get(i).getTipo().toString().equals(tipo.toUpperCase()) && ingressos.get(i).getStatus().equals(Status.DISPONIVEL)) {
                return true;
            }
        }
        return false;
    }
}
