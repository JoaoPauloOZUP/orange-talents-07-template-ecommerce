package br.com.zupacademy.joao.mercadolivre.controller.dto.response;

import br.com.zupacademy.joao.mercadolivre.model.Pergunta;

import java.time.Instant;

public class PerguntaResponse {

    private Long id;

    private String titulo;

    private Instant dataCriacao;

    public PerguntaResponse(Pergunta pergunta) {
        this.id = pergunta.getId();
        this.titulo = pergunta.getTitulo();
        this.dataCriacao = pergunta.getDataCriacao();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }
}
