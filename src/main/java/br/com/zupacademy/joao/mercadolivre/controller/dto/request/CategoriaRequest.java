package br.com.zupacademy.joao.mercadolivre.controller.dto.request;

import br.com.zupacademy.joao.mercadolivre.model.Categoria;
import br.com.zupacademy.joao.mercadolivre.validator.existvalues.categoria.ExistSuperCategoriaValue;
import br.com.zupacademy.joao.mercadolivre.validator.uniquevalue.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CategoriaRequest {

    @NotBlank
    @UniqueValue(dommainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @JsonProperty("super")
    @ExistSuperCategoriaValue
    private Long superCategoriaId;

    /**
     * Este construtor é para quando a superCategoriaId for nula
     * */
    protected CategoriaRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    protected CategoriaRequest(@NotBlank String nome,
                               @NotNull Long superCategoriaId) {

        this.nome = nome;
        this.superCategoriaId = superCategoriaId;
    }

    public Categoria toCategoria(EntityManager manager) {
        if(superCategoriaId != null) {
            Categoria categoria = manager.find(Categoria.class, superCategoriaId);
            return new Categoria(this.nome, categoria);
        }

        return new Categoria(this.nome);
    }
}
