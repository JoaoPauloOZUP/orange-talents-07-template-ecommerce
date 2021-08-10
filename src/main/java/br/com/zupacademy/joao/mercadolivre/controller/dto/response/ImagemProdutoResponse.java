package br.com.zupacademy.joao.mercadolivre.controller.dto.response;

import br.com.zupacademy.joao.mercadolivre.model.ImagemProduto;

import java.util.List;

public class ImagemProdutoResponse {

    private Long id;

    private String link;

    public ImagemProdutoResponse(ImagemProduto imagemProduto) {
        this.id = imagemProduto.getId();
        this.link = imagemProduto.getLink();
    }

    public Long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }
}
