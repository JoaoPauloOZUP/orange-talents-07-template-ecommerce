package br.com.zupacademy.joao.mercadolivre.controller.dto.request;

import br.com.zupacademy.joao.mercadolivre.controller.utility.SenhaLimpa;
import br.com.zupacademy.joao.mercadolivre.model.Usuario;
import br.com.zupacademy.joao.mercadolivre.validator.uniquevalue.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    @Email @NotBlank
    @UniqueValue(dommainClass = Usuario.class, fieldName = "login")
    private String login;

    @NotBlank @Size(min = 6)
    private final String senha;

    protected UsuarioRequest(@Email @NotBlank String login,
                             @NotBlank @Size(min = 6) String senha) {

        this.login = login;
        this.senha = senha;
    }

    public Usuario toUsuario() {
        return new Usuario(this.login, new SenhaLimpa(this.senha));
    }
}
