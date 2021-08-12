package br.com.zupacademy.joao.mercadolivre.controller.pagamento.enums;

public enum StatusPagamento {
    sucesso, erro;

    public StatusPagamento normaliza() {
        if(this.equals(sucesso)) {
            return StatusPagamento.sucesso;
        }

        return StatusPagamento.erro;
    }
}
