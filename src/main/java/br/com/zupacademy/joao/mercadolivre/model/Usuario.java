package br.com.zupacademy.joao.mercadolivre.model;

import br.com.zupacademy.joao.mercadolivre.controller.utility.SenhaLimpa;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email @NotBlank
    @Column(unique = true) // Garanto que existirá apenas 1 e somente 1 usuário
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

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    // Utilizo para comparação de usuários quando fazer inserções. Por este motivo foi criado!
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id) && login.equals(usuario.login) && senha.equals(usuario.senha) && instant.equals(usuario.instant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, senha, instant);
    }
}
