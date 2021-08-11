package br.com.zupacademy.joao.mercadolivre.controller.utility.gateway;

import br.com.zupacademy.joao.mercadolivre.model.Compra;
import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

    paypal{
        @Override
        public String retornaURL(UriComponentsBuilder builder, Compra compra) {
            String uriBuilder = builder.path("/retorno-paypal/{id}")
                    .buildAndExpand(compra.getId()).toString();

            return "paypal.com?buyerId="+compra.getId()+"&redirectUrl="+uriBuilder;
        }
    },
    pagseguro {
        @Override
        public String retornaURL(UriComponentsBuilder builder, Compra compra) {
            String uriBuilder = builder.path("/retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toString();

            return "pagseguro.com?buyerId="+compra.getId()+"&redirectUrl="+uriBuilder;
        }
    },
    ;

    public abstract String retornaURL(UriComponentsBuilder builder, Compra compra);
}
