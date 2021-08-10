package br.com.zupacademy.joao.mercadolivre.controller.dto.response;

import br.com.zupacademy.joao.mercadolivre.model.Opiniao;
import br.com.zupacademy.joao.mercadolivre.model.Pergunta;
import br.com.zupacademy.joao.mercadolivre.model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDetalhadoResponse {

    private ProdutoResponse produto;

    private List<ImagemProdutoResponse> imagens = new ArrayList<>();

    private List<CaracteristicaResponse> caracteristicas = new ArrayList<>();

    private List<OpiniaoResponse> opinioes = new ArrayList<>();

    private OpiniaoOperacoesResponse mediaETotalDeAvaliacoes;

    private List<PerguntaResponse> perguntas = new ArrayList<>();

    private UsuarioResponse usuario;

    @Deprecated
    protected ProdutoDetalhadoResponse() {}

    public ProdutoDetalhadoResponse(Produto produto, List<Opiniao> opinioes, List<Pergunta> perguntas) {
        this.produto = new ProdutoResponse(produto);

        this.imagens.addAll(produto.getImages().stream()
                .map(img -> new ImagemProdutoResponse(img)).collect(Collectors.toList()));

        this.caracteristicas.addAll(produto.getCaracteristicas().stream()
                .map(c -> new CaracteristicaResponse(c)).collect(Collectors.toList()));

        this.opinioes.addAll(opinioes.stream()
                .map(op -> new OpiniaoResponse(op)).collect(Collectors.toList()));

        this.mediaETotalDeAvaliacoes = new OpiniaoOperacoesResponse(calculaMedia(), calculaQuantidade());

        this.perguntas.addAll(perguntas.stream()
                .map(pergunta -> new PerguntaResponse(pergunta)).collect(Collectors.toList()));

        this.usuario = new UsuarioResponse(produto.getDonoDoProduto());
    }

    public ProdutoResponse getProduto() {
        return produto;
    }

    public List<ImagemProdutoResponse> getImagens() {
        return imagens;
    }

    public List<CaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public List<OpiniaoResponse> getOpinioes() {
        return opinioes;
    }

    public OpiniaoOperacoesResponse getMediaETotalDeAvaliacoes() {
        return mediaETotalDeAvaliacoes;
    }

    public List<PerguntaResponse> getPerguntas() {
        return perguntas;
    }

    public UsuarioResponse getUsuario() {
        return usuario;
    }

    private Long calculaMedia() {
        if(opinioes.size() == 0) {
            return 0L;
        }

        return opinioes.stream().mapToLong(op -> op.getNota()).sum() / opinioes.size();
    }

    private Long calculaQuantidade() {
        return opinioes.stream().count();
    }
}
