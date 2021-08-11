package br.com.zupacademy.joao.mercadolivre.controller;

import br.com.zupacademy.joao.mercadolivre.controller.dto.response.ProdutoDetalhadoResponse;
import br.com.zupacademy.joao.mercadolivre.model.Opiniao;
import br.com.zupacademy.joao.mercadolivre.model.Pergunta;
import br.com.zupacademy.joao.mercadolivre.model.Produto;
import br.com.zupacademy.joao.mercadolivre.repository.OpiniaoRepository;
import br.com.zupacademy.joao.mercadolivre.repository.PerguntaRepository;
import br.com.zupacademy.joao.mercadolivre.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProdutoDetalhadoController {

    private ProdutoRepository produtoRepository;

    private PerguntaRepository perguntaRepository;

    private OpiniaoRepository opiniaoRepository;

    public ProdutoDetalhadoController(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository, OpiniaoRepository opiniaoRepository) {
        this.produtoRepository = produtoRepository;
        this.perguntaRepository = perguntaRepository;
        this.opiniaoRepository = opiniaoRepository;
    }

    @GetMapping("/produto/{id}/detalhe")
    public ResponseEntity<?> exibirDetalhes(@PathVariable("id") Long idDoProduto) {
        Optional<Produto> produtoOPT = produtoRepository.findById(idDoProduto);
        if(produtoOPT.isPresent()) {
            Produto produto = produtoOPT.get();
            List<Opiniao> opinioes = opiniaoRepository.findByProduto(produto);
            List<Pergunta> perguntas = perguntaRepository.findByProduto(produto);

            ProdutoDetalhadoResponse response = new ProdutoDetalhadoResponse(produto, opinioes,  perguntas);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.notFound().build();
    }
}
