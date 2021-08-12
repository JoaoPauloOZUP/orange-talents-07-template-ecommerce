package br.com.zupacademy.joao.mercadolivre.model;

import br.com.zupacademy.joao.mercadolivre.controller.pagamento.dto.request.PagseguroRequest;
import br.com.zupacademy.joao.mercadolivre.controller.pagamento.interfaces.GatewayPagamentoRequest;
import br.com.zupacademy.joao.mercadolivre.controller.pagamento.model.Transacao;
import br.com.zupacademy.joao.mercadolivre.controller.utility.enums.Status;
import br.com.zupacademy.joao.mercadolivre.controller.utility.gateway.GatewayPagamento;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Compra {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @NotNull
    private Produto produto;

    @Positive
    @NotNull
    private Long quantidade;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayPagamento gateway;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Usuario comprador;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    protected Compra() {
    }

    public Compra(Produto produto, Long quantidade, GatewayPagamento gateway, Usuario usuario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.gateway = gateway;
        this.comprador = usuario;
        this.status = Status.INICIADO;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }

    public Status getStatus() {
        return status;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void finalizaCompra() {
        status = Status.FINALIZADO;
    }

    public Set<Transacao> isProcessada() {
        return this.transacoes.stream().filter(Transacao::concluidaComSucesso).collect(Collectors.toSet());
    }

    public boolean concluidaComSucesso() {
        return !this.isProcessada().isEmpty();
    }

    public void tentativaPagamento(GatewayPagamentoRequest request) {
        // Na tentativa é passado a req e assim retorna uma nova transação
        Transacao transacao = request.toTransacao(this);

        // Se houve alguma transação no set de transações, lança o erro
        Assert.isTrue(!this.transacoes.contains(transacao), "Já existe uma transacao");

        // verifica se esta transação foi concluida
        // Caso tenha sido, lança um erro
        Assert.isTrue(isProcessada().isEmpty(), "Esta transacao já foi concluida");

        // Caso contrátio, adiciona no set de transações
        this.transacoes.add(transacao);
    }
}
