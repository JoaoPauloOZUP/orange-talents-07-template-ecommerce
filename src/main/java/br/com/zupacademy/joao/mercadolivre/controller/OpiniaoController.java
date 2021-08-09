package br.com.zupacademy.joao.mercadolivre.controller;

import br.com.zupacademy.joao.mercadolivre.controller.dto.request.OpiniaoRequest;
import br.com.zupacademy.joao.mercadolivre.model.Opiniao;
import br.com.zupacademy.joao.mercadolivre.model.Produto;
import br.com.zupacademy.joao.mercadolivre.model.Usuario;
import br.com.zupacademy.joao.mercadolivre.model.UsuarioLogado;
import br.com.zupacademy.joao.mercadolivre.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class OpiniaoController {

    private EntityManager manager;

    private UsuarioRepository usuarioRepository;

    public OpiniaoController(EntityManager manager, UsuarioRepository usuarioRepository) {
        this.manager = manager;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping(value = "produto/{id}/opiniao")
    @Transactional
    public ResponseEntity<?> inserirOpiniao(@RequestBody @Valid OpiniaoRequest request,
                                            @PathVariable("id") Long idDoProduto) {

        UsuarioLogado logado = (UsuarioLogado) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Produto produto = manager.find(Produto.class, idDoProduto);
        Usuario usuarioOpinante = usuarioRepository.findByLogin(logado.getUsername()).get();

        Opiniao opiniao = request.toOpiniao(produto, usuarioOpinante);

        manager.persist(opiniao);

        return ResponseEntity.ok().build();
    }
}
