package br.com.zupacademy.joao.mercadolivre.controller.utility.relogio;

import java.time.Instant;

public class RelogioSistema implements Relogio {
    @Override
    public Instant hoje() {
        return Instant.now();
    }
}
