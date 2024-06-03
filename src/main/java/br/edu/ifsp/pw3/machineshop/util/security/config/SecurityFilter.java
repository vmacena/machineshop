package br.edu.ifsp.pw3.machineshop.util.security.config;

import br.edu.ifsp.pw3.machineshop.repository.UsuarioRepository;
import br.edu.ifsp.pw3.machineshop.util.security.token.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService pw3tokenservice;
    private final UsuarioRepository repository;

    public SecurityFilter(TokenService pw3tokenservice, UsuarioRepository repository) {
        this.pw3tokenservice = pw3tokenservice;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            var authorizationHeader = request.getHeader("Authorization");

            if (authorizationHeader != null) {
                authorizationHeader = authorizationHeader.replace("Bearer ", "");
                var subject = pw3tokenservice.getSubject(authorizationHeader);
                var usuario = repository.findByLogin(subject);

                var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            if (response.getStatus() == 403) {
                System.out.println("Erro 403 ocorreu: " + e.getMessage());
            }
            throw e;
        }
    }
}