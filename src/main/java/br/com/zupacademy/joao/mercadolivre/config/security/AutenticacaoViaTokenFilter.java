package br.com.zupacademy.joao.mercadolivre.config.security;

import br.com.zupacademy.joao.mercadolivre.model.Usuario;
import br.com.zupacademy.joao.mercadolivre.model.UsuarioLogado;
import br.com.zupacademy.joao.mercadolivre.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenManager tokenManager;

    private UsuarioRepository repository;

    public AutenticacaoViaTokenFilter(TokenManager tokenManager, UsuarioRepository repository) {
        this.tokenManager = tokenManager;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> token = getToken(request);

        if(token.isPresent() && tokenManager.isTokenValido(token.get())) {
            Long idUsuario = tokenManager.getIdUsuario(token.get());
            Usuario usuario = repository.findById(idUsuario).get();
            UsuarioLogado logado = new UsuarioLogado(usuario.getId(), usuario.getLogin(), usuario.getSenha());

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(logado, null, logado.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private Optional<String> getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        return Optional.ofNullable(token);
    }
}
