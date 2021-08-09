package br.com.zupacademy.joao.mercadolivre.controller;

import br.com.zupacademy.joao.mercadolivre.controller.dto.request.ImagemProdutoRequest;
import br.com.zupacademy.joao.mercadolivre.controller.dto.request.ProdutoRequest;
import br.com.zupacademy.joao.mercadolivre.controller.utility.EnviarImagem;
import br.com.zupacademy.joao.mercadolivre.model.Produto;
import br.com.zupacademy.joao.mercadolivre.model.Usuario;
import br.com.zupacademy.joao.mercadolivre.model.UsuarioLogado;
import br.com.zupacademy.joao.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.joao.mercadolivre.validator.uniquevalue.caracteristica.UniqueCaracteristica;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private EntityManager manager;

    private UsuarioRepository repository;

    private EnviarImagem enviaImagem;

    // O value abaixo aplica o fitro apenas ao ProdutoRequest
    @InitBinder(value = "ProdutoRequest")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new UniqueCaracteristica());
    }

    public ProdutoController(EntityManager manager, UsuarioRepository repository, EnviarImagem enviaImagem) {
        this.manager = manager;
        this.repository = repository;
        this.enviaImagem = enviaImagem;
    }

    @PostMapping
    @Transactional // Preciso de um contexto de transação pois irei fazer no total 4 inserts. 3 caracteristicas e 1 produto.
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest request) {
        UsuarioLogado logado = (UsuarioLogado) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Usuario donoDoProduto = repository.findByLogin(logado.getUsername()).get();
        Produto produto = request.toProduto(manager, donoDoProduto);

        manager.persist(produto);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{id}/imagem")
    @Transactional
    public void inserirImagem(@PathVariable("id") Long idDoProduto, @Valid ImagemProdutoRequest request) {
        UsuarioLogado logado = (UsuarioLogado) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Produto produto = manager.find(Produto.class, idDoProduto);
        Usuario usuario = repository.findByLogin(logado.getUsername()).get();

        if(!produto.perceAoUsuarioLogado(usuario)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        produto.inserirImagens(request.produtoComImagem(enviaImagem));

        manager.merge(produto);
    }
}
