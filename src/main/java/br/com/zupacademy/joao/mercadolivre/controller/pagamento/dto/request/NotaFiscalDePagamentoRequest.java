package br.com.zupacademy.joao.mercadolivre.controller.pagamento.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NotaFiscalDePagamentoRequest {

    @Positive @NotNull
    private Long idCompra;

    @Positive @NotNull
    private Long getIdComprador;

    public NotaFiscalDePagamentoRequest(@NotNull @Positive Long idCompra,
                                        @Positive @NotNull Long getIdComprador) {
        this.idCompra = idCompra;
        this.getIdComprador = getIdComprador;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getGetIdComprador() {
        return getIdComprador;
    }
}
