package br.com.zupacademy.joao.mercadolivre.controller.utility.email;

import org.springframework.stereotype.Component;

// O nome disparado é devido o fato que posssa ter futuramente um processo de envio via SMS.
@Component
public interface Disparador {

    public void enviar(String caminho, String titulo);
}
