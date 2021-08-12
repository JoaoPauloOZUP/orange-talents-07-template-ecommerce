package br.com.zupacademy.joao.mercadolivre.controller.pagamento.service;

import br.com.zupacademy.joao.mercadolivre.controller.pagamento.interfaces.EventoCompraSucesso;
import br.com.zupacademy.joao.mercadolivre.model.Compra;
import io.jsonwebtoken.lang.Assert;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal implements EventoCompraSucesso {

    @Override
    public void processar(Compra compra) {
        Assert.isTrue(compra.concluidaComSucesso(), "Compra não concluida");

        RestTemplate template = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getComprador().getId());
        template.postForEntity("http://localhost:8080/notas-ficais", request, String.class);
    }
}
