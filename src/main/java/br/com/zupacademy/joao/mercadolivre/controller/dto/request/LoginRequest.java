package br.com.zupacademy.joao.mercadolivre.controller.dto.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    protected LoginRequest(@NotBlank String login,
                           @NotBlank String senha) {

        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public UsernamePasswordAuthenticationToken toUsernamePassword() {
        return new UsernamePasswordAuthenticationToken(login, senha);
    }
}
