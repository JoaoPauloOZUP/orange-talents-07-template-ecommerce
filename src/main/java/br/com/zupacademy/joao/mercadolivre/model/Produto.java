package br.com.zupacademy.joao.mercadolivre.model;

import br.com.zupacademy.joao.mercadolivre.controller.dto.request.CaracteristicaRequest;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Positive
    private Double preco;

    @PositiveOrZero @NotNull
    private Long quantidade;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "produto") @NotNull
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    @NotBlank @Size(max = 1000)
    @Column(length = 1000)
    private String descricao;

    @ManyToOne @NotNull
    private Categoria categoria;

    @ManyToOne @NotNull
    private Usuario donoDoProduto;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "produto")
    private Set<ImagemProduto> images = new HashSet<>();

    private Instant dataCriacao = Instant.now();

    @Deprecated
    protected Produto() {}

    public Produto(@NotBlank String nome,
                   @Positive @NotBlank Double preco,
                   @PositiveOrZero @NotNull Long quantidade,
                   @NotNull List<CaracteristicaRequest> caracteristicasRequest,
                   @NotBlank @Size(max = 1000) String descricao,
                   @NotNull Categoria categoria,
                   @NotNull Usuario donoDoProduto) {

        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        caracteristicasRequest.forEach(c -> this.caracteristicas.add(new Caracteristica(c.getNome(), c.getDescricao(), this)));
        this.descricao = descricao;
        this.categoria = categoria;
        this.donoDoProduto = donoDoProduto;
    }

    public Usuario getDonoDoProduto() {
        return donoDoProduto;
    }

    // Inseri as imagens ao produto X e recebe um Set de imagem de ImagemRequest.
    public void inserirImagens(Set<String> links) {
        Set<ImagemProduto> imagemProdutos = new HashSet<>();
        links.forEach(link -> imagemProdutos.add(new ImagemProduto(link, this)));
        this.images = imagemProdutos;
    }

    public boolean perceAoUsuarioLogado(Usuario usuario){
        return this.donoDoProduto.equals(usuario);
    }
}
