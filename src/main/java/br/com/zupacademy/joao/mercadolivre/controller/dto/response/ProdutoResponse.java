package br.com.zupacademy.joao.mercadolivre.controller.dto.response;

import br.com.zupacademy.joao.mercadolivre.model.Produto;

import javax.validation.constraints.NotNull;

public class ProdutoResponse {

    private Long id;

    private String nome;

    private Double preco;

    private Long quantidade;

    private String descricao;

    public ProdutoResponse(@NotNull Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.quantidade = produto.getQuantidade();
        this.descricao = produto.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }
}
