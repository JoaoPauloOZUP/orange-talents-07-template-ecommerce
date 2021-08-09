package br.com.zupacademy.joao.mercadolivre.model;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class ImagemProduto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @URL
    private String link;

    @ManyToOne @NotNull
    private Produto produto;

    protected ImagemProduto() {}

    public ImagemProduto(@NotNull @URL String link,
                         @NotNull Produto produto) {

        this.link = link;
        this.produto = produto;
    }

    // Como utilizo um Set de imagemProduto, preciso do equals para comparação.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagemProduto that = (ImagemProduto) o;
        return id.equals(that.id) && link.equals(that.link) && produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link, produto);
    }
}
