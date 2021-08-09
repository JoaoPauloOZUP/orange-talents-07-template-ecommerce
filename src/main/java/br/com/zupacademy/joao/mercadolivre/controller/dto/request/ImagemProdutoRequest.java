package br.com.zupacademy.joao.mercadolivre.controller.dto.request;

import br.com.zupacademy.joao.mercadolivre.controller.utility.EnviarImagem;
import br.com.zupacademy.joao.mercadolivre.model.ImagemProduto;
import br.com.zupacademy.joao.mercadolivre.model.Produto;
import br.com.zupacademy.joao.mercadolivre.model.Usuario;
import br.com.zupacademy.joao.mercadolivre.model.UsuarioLogado;
import br.com.zupacademy.joao.mercadolivre.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImagemProdutoRequest {

    @Size(min = 1)
    @NotNull(message = "Deve conter no mínimo uma imagem")
    List<MultipartFile> images;

    /**
     * O método setImagem se faz nescessário pois a requisição não pelo o corpo e sim por um forms.
     * */
    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }

    public Produto produtoComImagem(EnviarImagem enviar, EntityManager manager, Long idDoProduto, UsuarioRepository repository) {
        Produto produto = manager.find(Produto.class, idDoProduto);

        UsuarioLogado logado = (UsuarioLogado) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = repository.findByLogin(logado.getUsername()).get();

        if(!produto.perceAoUsuarioLogado(usuario)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Set<ImagemProduto> imagemProdutos = new HashSet<>();
        Set<String> links = enviar.upload(this.images);
        links.forEach(link -> imagemProdutos.add(new ImagemProduto(link, produto)));
        produto.inserirImagens(imagemProdutos);

        return produto;
    }
}
