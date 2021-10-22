package br.com.fiap.epictask.security;

import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.repository.UserRepository;
import br.com.fiap.epictask.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizarionFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository repository;

    public AuthorizarionFilter(TokenService tokenService, UserRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // pegar o token do header
        String token = extractToken(request);

        // validar o token
        boolean valid = tokenService.valid(token);
        System.out.println(valid);

        // se o token for válido, eu autorizo
        if (valid){
            authorize(token);
        }

        filterChain.doFilter(request, response);
    }

    private void authorize(String token) {
        Long id = tokenService.getUserId(token);
        User user = repository.findById(id).get();

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || header.isEmpty() || !header.startsWith("Bearer "))
            return null;

        return header.substring(7, header.length());
    }
}
