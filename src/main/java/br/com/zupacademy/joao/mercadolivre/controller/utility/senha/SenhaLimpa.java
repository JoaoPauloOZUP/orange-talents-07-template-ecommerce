package br.com.zupacademy.joao.mercadolivre.controller.utility.senha;

import br.com.zupacademy.joao.mercadolivre.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SenhaLimpa {

    private String senha;

    /**
     * @param senha deve ser um texto limpo
     * */
    public SenhaLimpa(@NotBlank @Size(min = 6) String senha) {
        Assert.isTrue(senha.length() >= 6, "A senha deve ter mínimo 6 caracteres");

        this.senha = senha;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(this.senha);
    }
}
