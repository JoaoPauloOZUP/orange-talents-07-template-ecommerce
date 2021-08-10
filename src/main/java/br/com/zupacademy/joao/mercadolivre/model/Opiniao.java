package br.com.zupacademy.joao.mercadolivre.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Opiniao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Positive @Max(5)
    private Long nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Column(length = 1000)
    private String descricao;

    @ManyToOne @NotNull
    private Produto produto;

    @ManyToOne @NotNull
    private Usuario usuario;

    @Deprecated
    protected Opiniao() {}

    public Opiniao(@NotNull @Positive @Max(5) Long nota,
                   @NotBlank String titulo,
                   @NotBlank String descricao,
                   @NotNull Produto produto,
                   @NotNull Usuario usuario) {

        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public Long getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
