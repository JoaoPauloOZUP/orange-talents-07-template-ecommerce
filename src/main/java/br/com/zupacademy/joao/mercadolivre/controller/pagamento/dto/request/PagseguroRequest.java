package br.com.zupacademy.joao.mercadolivre.controller.pagamento.dto.request;

import br.com.zupacademy.joao.mercadolivre.controller.pagamento.enums.StatusPagamento;
import br.com.zupacademy.joao.mercadolivre.controller.pagamento.interfaces.GatewayPagamentoRequest;
import br.com.zupacademy.joao.mercadolivre.controller.pagamento.model.Transacao;
import br.com.zupacademy.joao.mercadolivre.model.Compra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PagseguroRequest implements GatewayPagamentoRequest {

    @NotNull @Positive
    private Long idTransacao;

    @NotNull
    private StatusPagamento statusPagamento;

    public PagseguroRequest(@NotNull @Positive Long idTransacao,
                            @NotNull StatusPagamento statusPagamento) {

        this.idTransacao = idTransacao;
        this.statusPagamento = statusPagamento;
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(idTransacao, statusPagamento.normaliza(), compra);
    }
}
