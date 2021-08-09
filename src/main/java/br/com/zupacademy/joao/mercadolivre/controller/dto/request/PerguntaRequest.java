package br.com.zupacademy.joao.mercadolivre.controller.dto.request;

import br.com.zupacademy.joao.mercadolivre.model.Pergunta;
import br.com.zupacademy.joao.mercadolivre.model.Produto;
import br.com.zupacademy.joao.mercadolivre.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class PerguntaRequest {

    @NotBlank @JsonProperty("titulo")
    private String titulo;

    @Deprecated
    protected PerguntaRequest() {}

    public PerguntaRequest(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toPerguta(Produto produto, Usuario usuario) {
        return new Pergunta(titulo, produto, usuario);
    }
}
