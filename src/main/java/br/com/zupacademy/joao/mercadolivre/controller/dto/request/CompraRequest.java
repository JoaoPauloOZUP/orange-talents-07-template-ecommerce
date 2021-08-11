package br.com.zupacademy.joao.mercadolivre.controller.dto.request;

import br.com.zupacademy.joao.mercadolivre.controller.utility.gateway.GatewayPagamento;
import br.com.zupacademy.joao.mercadolivre.model.Produto;
import br.com.zupacademy.joao.mercadolivre.validator.existvalues.generico.ExistIdValue;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraRequest {

    @Positive
    @NotNull
    @ExistIdValue(dommainClass = Produto.class, fieldName = "id")
    private Long idProduto;

    @Positive @NotNull
    private Long quantidade;

    @NotNull
    private GatewayPagamento pagamento;

    @Deprecated
    protected CompraRequest() {}

    protected CompraRequest(@Positive @NotNull Long idProduto,
                            @Positive @NotNull Long quantidade,
                            @NotNull GatewayPagamento pagamento) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.pagamento = pagamento;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public GatewayPagamento getPagamento() {
        return pagamento;
    }
}
