package br.com.zupacademy.joao.mercadolivre.controller;

import br.com.zupacademy.joao.mercadolivre.controller.dto.request.CompraRequest;
import br.com.zupacademy.joao.mercadolivre.controller.dto.response.CompraResponse;
import br.com.zupacademy.joao.mercadolivre.controller.utility.email.Disparador;
import br.com.zupacademy.joao.mercadolivre.model.Compra;
import br.com.zupacademy.joao.mercadolivre.model.Produto;
import br.com.zupacademy.joao.mercadolivre.model.Usuario;
import br.com.zupacademy.joao.mercadolivre.model.UsuarioLogado;
import br.com.zupacademy.joao.mercadolivre.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/compra")
public class CompraController {

    private EntityManager manager;

    private UsuarioRepository usuarioRepository;

    private Disparador disparadorEmail;

    public CompraController(EntityManager manager, UsuarioRepository usuarioRepository, Disparador disparadorEmail) {
        this.manager = manager;
        this.usuarioRepository = usuarioRepository;
        this.disparadorEmail = disparadorEmail;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> inserirCompra(@RequestBody @Valid CompraRequest request, UriComponentsBuilder builder) throws BindException  {
        UsuarioLogado logado = (UsuarioLogado) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Usuario usuario = usuarioRepository.findByLogin(logado.getUsername()).get();

        Produto produto = manager.find(Produto.class, request.getIdProduto());

        if(produto.abaterEstoque(request.getQuantidade())) {
            Compra compra = new Compra(produto, request.getQuantidade(), request.getPagamento(), usuario);

            manager.persist(compra);

            String endereco = request.getPagamento().retornaURL(builder, compra);

            URI url = URI.create(endereco);

            disparadorEmail.enviar(produto.getDonoDoProduto().getLogin(), "Produto comprado...");

            return ResponseEntity.status(302).location(url).build();
        }

        BindException exception = new BindException(request, "compraRequest");
        exception.reject(null, "Não foi possivel realizar a compra por conta da quantidade ser maior que estoque");
        throw exception;
    }
}
