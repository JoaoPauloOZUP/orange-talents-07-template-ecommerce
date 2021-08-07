package br.com.zupacademy.joao.mercadolivre.controller;

import br.com.zupacademy.joao.mercadolivre.controller.dto.request.ProdutoRequest;
import br.com.zupacademy.joao.mercadolivre.model.Produto;
import br.com.zupacademy.joao.mercadolivre.model.Usuario;
import br.com.zupacademy.joao.mercadolivre.model.UsuarioLogado;
import br.com.zupacademy.joao.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.joao.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.joao.mercadolivre.validator.uniquevalue.caracteristica.UniqueCaracteristica;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private EntityManager manager;

    private UsuarioRepository repository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new UniqueCaracteristica());
    }

    public ProdutoController(EntityManager manager, UsuarioRepository repository) {
        this.manager = manager;
        this.repository = repository;
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
}
