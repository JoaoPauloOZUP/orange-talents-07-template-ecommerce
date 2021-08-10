package br.com.zupacademy.joao.mercadolivre.controller.dto.response;

public class OpiniaoOperacoesResponse {

    private Long media;

    private Long totalDeAvaliacoes;

    public OpiniaoOperacoesResponse(Long media, Long totalDeAvaliacoes) {
        this.media = media;
        this.totalDeAvaliacoes = totalDeAvaliacoes;
    }

    public Long getMedia() {
        return media;
    }

    public Long getTotalDeAvaliacoes() {
        return totalDeAvaliacoes;
    }
}
