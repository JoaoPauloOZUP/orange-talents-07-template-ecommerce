package br.com.zupacademy.joao.mercadolivre.controller;

import br.com.zupacademy.joao.mercadolivre.config.security.TokenManager;
import br.com.zupacademy.joao.mercadolivre.controller.dto.request.LoginRequest;
import br.com.zupacademy.joao.mercadolivre.config.security.dto.response.TokenResponse;
import br.com.zupacademy.joao.mercadolivre.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/atenticacao")
public class AutenticacaoController {

    private AuthenticationManager authManager;

    private TokenManager tokenManager;

    private UsuarioRepository repository;


    public AutenticacaoController(AuthenticationManager authManager, TokenManager tokenManager, UsuarioRepository repository) {
        this.authManager = authManager;
        this.tokenManager = tokenManager;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginRequest request) {
        UsernamePasswordAuthenticationToken login = request.toUsernamePassword();

        try {
            Authentication authentication = authManager.authenticate(login);
            String token = tokenManager.gerarToken(authentication);
            return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
