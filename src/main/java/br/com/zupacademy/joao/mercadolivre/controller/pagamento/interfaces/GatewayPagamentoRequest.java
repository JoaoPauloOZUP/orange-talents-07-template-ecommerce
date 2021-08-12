package br.com.zupacademy.joao.mercadolivre.controller.pagamento.interfaces;

import br.com.zupacademy.joao.mercadolivre.controller.pagamento.model.Transacao;
import br.com.zupacademy.joao.mercadolivre.model.Compra;

public interface GatewayPagamentoRequest {

    // Polimorfismo, ou seja, a implementação desta interface deverá ter o método abaixo!
    Transacao toTransacao(Compra compra);
}
