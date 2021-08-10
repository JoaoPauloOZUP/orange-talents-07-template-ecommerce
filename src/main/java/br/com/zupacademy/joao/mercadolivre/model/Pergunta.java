package br.com.zupacademy.joao.mercadolivre.model;

import br.com.zupacademy.joao.mercadolivre.controller.utility.relogio.RelogioSistema;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
public class Pergunta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotNull
    private Instant dataCriacao = new RelogioSistema().hoje();

    @ManyToOne
    @NotNull
    private Usuario usuario;

    @ManyToOne @NotNull
    private Produto produto;

    @Deprecated
    protected Pergunta() {}

    public Pergunta(@NotBlank String titulo,
                    @NotNull Produto produto,
                    @NotNull Usuario usuario) {

        this.titulo = titulo;
        this.produto = produto;
        this.usuario = usuario;
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
