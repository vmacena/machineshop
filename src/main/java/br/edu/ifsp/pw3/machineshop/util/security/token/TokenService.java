package br.edu.ifsp.pw3.machineshop.util.security.token;

import br.edu.ifsp.pw3.machineshop.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${default.password.token}")
    private String secret;

    public String gerarToken(Usuario usuario) throws JWTCreationException {
        Algorithm algoritmo = Algorithm.HMAC256(secret);

        return JWT.create()
                .withIssuer("DISCIPLINA PW3")
                .withSubject(usuario.getLogin())
                .withExpiresAt(dataExpiracao())
                .sign(algoritmo);
    }

    public String getSubject(String tokenJWT) throws JWTVerificationException {
        Algorithm algoritmo = Algorithm.HMAC256(secret);
        JWTVerifier jwtv = JWT.require(algoritmo)
                .withIssuer("DISCIPLINA PW3")
                .build();
        return jwtv.verify(tokenJWT).getSubject();
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}