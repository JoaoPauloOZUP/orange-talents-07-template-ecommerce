package br.com.zupacademy.joao.mercadolivre.controller.dto.request;

import br.com.zupacademy.joao.mercadolivre.model.Caracteristica;
import br.com.zupacademy.joao.mercadolivre.model.Categoria;
import br.com.zupacademy.joao.mercadolivre.model.Produto;
import br.com.zupacademy.joao.mercadolivre.model.Usuario;
import br.com.zupacademy.joao.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.joao.mercadolivre.validator.existvalues.generico.ExistIdValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.List;

public class ProdutoRequest {

    @NotBlank
    private String nome;

    @Positive
    private Double preco;

    @PositiveOrZero @NotNull
    private Long quantidade;

    @Size(min = 3) @Valid
    @JsonProperty("caracteristicas")
    private List<CaracteristicaRequest> caracteristicasRequest;

    @NotBlank @Size(max = 1000)
    private String descricao;

    @JsonProperty("categoria")
    @Positive
    @ExistIdValue(dommainClass = Categoria.class, fieldName = "id")
    private Long categoriaId;

    public ProdutoRequest(@NotBlank String nome,
                          @Positive @NotNull Double preco,
                          @PositiveOrZero @NotNull Long quantidade,
                          @NotNull List<CaracteristicaRequest> caracteristicasRequest,
                          @Size(max = 1000) String descricao,
                          @Positive @NotNull Long categoriaId) {

        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.caracteristicasRequest = caracteristicasRequest;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }

    public List<CaracteristicaRequest> getCaracteristicasRequest() {
        return caracteristicasRequest;
    }

    public Produto toProduto(EntityManager manager, Usuario donoDoProduto) {
        Categoria categoria = manager.find(Categoria.class, categoriaId);
        return new Produto(this.nome, this.preco, this.quantidade, this.caracteristicasRequest, this.descricao, categoria, donoDoProduto);
    }

    public int caracteristicasRepetidas() {
        HashSet<String> nomes = new HashSet<>();

        for(CaracteristicaRequest caracteristica : this.caracteristicasRequest) {
            // Forço a ter um padrão
            String nomeUpper = caracteristica.getNome().toUpperCase().trim();
            if(nomes.contains(nomeUpper)) {
                return nomes.size();
            }
            nomes.add(nomeUpper);
        }

        return -1;
    }
}
