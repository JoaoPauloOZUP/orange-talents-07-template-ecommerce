package br.com.zupacademy.joao.mercadolivre.model;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true) // Garanto que existirá apenas 1 e somente 1 categoria
    private String nome;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Categoria superCategoria;

    @Deprecated
    protected Categoria() {}

    /**
     * @param nome o nome não deve ser vazio
     * */
    public Categoria(@NotBlank String nome) {
        Assert.isTrue(StringUtils.hasLength(nome), "O nome nao deve ser vazio");

        this.nome = nome;
    }

    /**
     * @param nome o nome não deve ser vazio
     * @param superCategoria não deve ser nula
     * */
    public Categoria(@NotBlank String nome,
                     @NotNull Categoria superCategoria) {

        Assert.isTrue(StringUtils.hasLength(nome), "O nome nao deve ser vazio");
        Assert.notNull(superCategoria, "A super categoria nao deve ser nula");

        this.nome = nome;
        this.superCategoria = superCategoria;
    }
}
