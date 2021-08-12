package br.com.zupacademy.joao.mercadolivre.controller.pagamento.service;

import br.com.zupacademy.joao.mercadolivre.controller.pagamento.interfaces.EventoCompraSucesso;
import br.com.zupacademy.joao.mercadolivre.controller.utility.email.Disparador;
import br.com.zupacademy.joao.mercadolivre.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CompraProcessamento {

    @Autowired
    private Set<EventoCompraSucesso> eventoCompraSucessos;

    @Autowired
    private Disparador disparador;

    public void processa(Compra compra) {
        if(compra.concluidaComSucesso()) {
            // Como tenho 2 eventos e ambos implementam a mesma interface e estão anotado @Service,
            // ao chamar a lista de eventos é injetado nela as 2 implementações e assim,
            // para cada evento é possivel executar seu processo!
            eventoCompraSucessos.forEach(evento -> evento.processar(compra));
        } else {
            disparador.enviar(compra.getComprador().getLogin(), "Sua compra não foi realizada com sucesso");
        }
    }
}
