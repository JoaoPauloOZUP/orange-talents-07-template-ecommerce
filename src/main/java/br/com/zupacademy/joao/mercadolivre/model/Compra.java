package br.com.zupacademy.joao.mercadolivre.model;

import br.com.zupacademy.joao.mercadolivre.controller.utility.enums.Status;
import br.com.zupacademy.joao.mercadolivre.controller.utility.gateway.GatewayPagamento;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @NotNull
    private Produto produto;

    @Positive
    @NotNull
    private Long quantidade;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayPagamento gateway;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Usuario usuario;

    public Compra(Produto produto, Long quantidade, GatewayPagamento gateway, Usuario usuario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.gateway = gateway;
        this.usuario = usuario;
        this.status = Status.INICIADO;
    }

    public Long getId() {
        return id;
    }

    public void finalizaCompra() {
        status = Status.FINALIZADO;
    }
}
