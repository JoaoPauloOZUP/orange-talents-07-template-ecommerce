package br.com.zupacademy.joao.mercadolivre.controller.pagamento.dto.request;

import br.com.zupacademy.joao.mercadolivre.controller.pagamento.enums.StatusPagamento;
import br.com.zupacademy.joao.mercadolivre.controller.pagamento.interfaces.GatewayPagamentoRequest;
import br.com.zupacademy.joao.mercadolivre.controller.pagamento.model.Transacao;
import br.com.zupacademy.joao.mercadolivre.model.Compra;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PaypalRequest implements GatewayPagamentoRequest {

    @Positive @NotNull
    private Long idTransacao;

    @Min(0) @Max(1)
    private int status;

    @Deprecated
    public PaypalRequest() {
    }

    protected PaypalRequest(Long idTransacao, int status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    public Long getIdTransacao() {
        return idTransacao;
    }

    public int getStatus() {
        return status;
    }

    public Transacao toTransacao(Compra compra) {
        StatusPagamento statusConvertido = this.status == 0 ? StatusPagamento.erro : StatusPagamento.sucesso;
        return new Transacao(idTransacao, statusConvertido, compra);
    }
}
