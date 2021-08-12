package br.com.zupacademy.joao.mercadolivre.controller.pagamento.interfaces;

import br.com.zupacademy.joao.mercadolivre.model.Compra;

public interface EventoCompraSucesso {

    public void processar(Compra compra);
}
