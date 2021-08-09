package br.com.zupacademy.joao.mercadolivre.controller.dto.request;

import br.com.zupacademy.joao.mercadolivre.model.Opiniao;
import br.com.zupacademy.joao.mercadolivre.model.Produto;
import br.com.zupacademy.joao.mercadolivre.model.Usuario;

import javax.validation.constraints.*;

public class OpiniaoRequest {

    @NotNull @Positive @Max(value = 5, message = "Deve ser entre 1 e 5")
    private Long nota;

    @NotBlank
    private String titulo;

    @NotBlank @Size(max = 500)
    private String descricao;

    public OpiniaoRequest(@NotNull @Positive @Max(5) Long nota,
                          @NotBlank String titulo,
                          @NotBlank @Max(1000) String descricao) {

        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toOpiniao(Produto produto, Usuario usuarioOpinante) {
        return new Opiniao(nota, titulo, descricao, produto, usuarioOpinante);
    }
}
