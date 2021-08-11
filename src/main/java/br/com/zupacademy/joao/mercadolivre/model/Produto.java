package br.com.zupacademy.joao.mercadolivre.model;

import br.com.zupacademy.joao.mercadolivre.controller.dto.request.CaracteristicaRequest;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

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

    @NotBlank
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

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Set<ImagemProduto> getImages() {
        return images;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
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

    public boolean abaterEstoque(@Positive Long quantidade) {
        Assert.isTrue(quantidade > 0, "A quantidade deve ser maior que 0");

        if(this.quantidade >= quantidade) {
            this.quantidade -= quantidade;
            return true;
        }

        return false;
    }
}
