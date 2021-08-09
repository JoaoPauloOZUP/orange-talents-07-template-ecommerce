package br.com.zupacademy.joao.mercadolivre.controller;

import br.com.zupacademy.joao.mercadolivre.controller.dto.request.PerguntaRequest;
import br.com.zupacademy.joao.mercadolivre.controller.utility.email.Disparador;
import br.com.zupacademy.joao.mercadolivre.model.Pergunta;
import br.com.zupacademy.joao.mercadolivre.model.Produto;
import br.com.zupacademy.joao.mercadolivre.model.Usuario;
import br.com.zupacademy.joao.mercadolivre.model.UsuarioLogado;
import br.com.zupacademy.joao.mercadolivre.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class PerguntaController {

    private EntityManager manager;

    private UsuarioRepository usuarioRepository;

    private Disparador disparador;

    public PerguntaController(EntityManager manager, UsuarioRepository usuarioRepository, Disparador disparador) {
        this.manager = manager;
        this.usuarioRepository = usuarioRepository;
        this.disparador = disparador;
    }

    @PostMapping(value = "/produto/{id}/pergunta")
    @Transactional
    public ResponseEntity adicionarPergunta(@RequestBody @Valid PerguntaRequest request,
                                            @PathVariable("id") Long idDoProduto) {

        UsuarioLogado logado = (UsuarioLogado) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Produto produto = manager.find(Produto.class, idDoProduto);
        Usuario usuario = usuarioRepository.findByLogin(logado.getUsername()).get();

        Pergunta pergunta = request.toPerguta(produto, usuario);

        manager.persist(pergunta);

        String emailDonoProduto = produto.getDonoDoProduto().getLogin();
        String perguntaDoUsuario = pergunta.getTitulo();

        disparador.enviar(emailDonoProduto, perguntaDoUsuario);

        return ResponseEntity.ok().build();
    }
}
