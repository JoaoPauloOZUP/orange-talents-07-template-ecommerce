package br.com.zupacademy.joao.mercadolivre.config.security;

import br.com.zupacademy.joao.mercadolivre.model.Usuario;
import br.com.zupacademy.joao.mercadolivre.model.UsuarioLogado;
import br.com.zupacademy.joao.mercadolivre.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoManager implements UserDetailsService {

    private UsuarioRepository repository;

    public AutenticacaoManager(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByLogin(login);

        if (usuario.isPresent()) {
            return new UsuarioLogado(usuario.get().getId(), usuario.get().getLogin(), usuario.get().getSenha());
        }

        throw new UsernameNotFoundException("Usuario nao encontrado: "+login);
    }
}
