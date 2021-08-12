package br.com.zupacademy.joao.mercadolivre.controller.dto.response;

import br.com.zupacademy.joao.mercadolivre.controller.utility.enums.Status;
import br.com.zupacademy.joao.mercadolivre.controller.utility.gateway.GatewayPagamento;

import br.com.zupacademy.joao.mercadolivre.model.Compra;

public class CompraResponse {

    private Long id;

    private ProdutoResponse produto;

    private Long quantidade;

    private GatewayPagamento gateway;

    private Status status;

    private UsuarioResponse usuario;

    public CompraResponse(Compra compra) {
        this.id = compra.getId();
        this.produto = new ProdutoResponse(compra.getProduto());
        this.quantidade = compra.getQuantidade();
        this.gateway = compra.getGateway();
        this.status = compra.getStatus();
        this.usuario = new UsuarioResponse(compra.getComprador());
    }

    public Long getId() {
        return id;
    }

    public ProdutoResponse getProduto() {
        return produto;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }

    public Status getStatus() {
        return status;
    }

    public UsuarioResponse getUsuario() {
        return usuario;
    }
}
