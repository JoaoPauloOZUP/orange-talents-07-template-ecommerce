package br.com.zupacademy.joao.mercadolivre.controller.pagamento.model;

import br.com.zupacademy.joao.mercadolivre.controller.pagamento.enums.StatusPagamento;
import br.com.zupacademy.joao.mercadolivre.controller.utility.relogio.RelogioSistema;
import br.com.zupacademy.joao.mercadolivre.model.Compra;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.Instant;
import java.util.Objects;

@Entity
public class Transacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Positive
    private Long idTransacao;

    @NotNull @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    @ManyToOne
    private Compra compra;

    @NotNull
    private Instant instanteTransacao = new RelogioSistema().hoje();

    @Deprecated
    protected Transacao() {
    }

    public Transacao(Long idTransacao, StatusPagamento statusPagamento, Compra compra) {
        this.idTransacao = idTransacao;
        this.statusPagamento = statusPagamento;
        this.compra = compra;
    }

    public Long getId() {
        return id;
    }

    public Long getIdTransacao() {
        return idTransacao;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public Instant getInstanteTransacao() {
        return instanteTransacao;
    }

    public boolean concluidaComSucesso() {
        return this.statusPagamento.equals(StatusPagamento.sucesso);
    }

    // é utilizado em hashSet

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transacao)) return false;
        Transacao transacao = (Transacao) o;
        return idTransacao.equals(transacao.idTransacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransacao);
    }
}
