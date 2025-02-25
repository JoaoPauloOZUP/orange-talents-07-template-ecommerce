package br.com.zupacademy.joao.mercadolivre.controller.utility.email;

import org.springframework.stereotype.Component;

@Component
public class EnviarEmail implements Disparador {
    @Override
    public void enviar(String caminho, String mensagem) {
        System.out.println("Email enviado para: "+caminho);
        System.out.println("Mensagem: "+mensagem);
    }
}
