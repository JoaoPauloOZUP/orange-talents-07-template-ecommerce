package br.com.zupacademy.joao.mercadolivre.model;

import br.com.zupacademy.joao.mercadolivre.controller.utility.SenhaLimpa;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;

@Entity
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email @NotBlank
    private String login;

    @NotBlank @Size(min = 6)
    private String senha;

    @PastOrPresent @NotNull
    private Instant instant = Instant.now();

    @Deprecated
    protected Usuario() {}

    /**
     * @param login não pode ser vazia
     * @param senhaLimpa deve ser um instâcia de senha limpa
     * */
    public Usuario(@Email @NotBlank String login,
                   @NotNull SenhaLimpa senhaLimpa) {

        Assert.isTrue(StringUtils.hasLength(login), "Login não pode ser vazio");
        Assert.notNull(senhaLimpa, "Senha limpa não pode ser nula");

        this.login = login;
        this.senha = senhaLimpa.hash();
    }
}
