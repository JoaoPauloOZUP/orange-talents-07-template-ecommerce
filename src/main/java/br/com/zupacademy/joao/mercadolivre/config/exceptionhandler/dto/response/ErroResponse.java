package br.com.zupacademy.joao.mercadolivre.config.exceptionhandler.dto.response;

public class ErroResponse {

    private String campo;

    private String erro;

    public ErroResponse(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
