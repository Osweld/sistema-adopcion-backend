package ues.dsi.sistemaadopcionbackend.auth.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ues.dsi.sistemaadopcionbackend.auth.models.AuthUser;
import ues.dsi.sistemaadopcionbackend.auth.services.JWTService;
import ues.dsi.sistemaadopcionbackend.auth.services.JWTServiceImpl;
import ues.dsi.sistemaadopcionbackend.models.entity.Usuario;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/v1/auth/login","POST"));
    }

    //Hablando un poco de interfaces como yo se quien es el que implementa la interfaz osea a quien mando a llamar'
    //Como voy a saber yo cual clase es la que manda a llamar?
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        if(username == null && password == null){
            Usuario user;
            try{
                user = new ObjectMapper().readValue(request.getInputStream(),Usuario.class);
                username = user.getUsername();
                password = user.getPassword();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username.trim(),password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        try {
            String token = jwtService.create(authResult);
            response.addHeader(JWTServiceImpl.HEADER_STRING,JWTServiceImpl.TOKEN_PREFIX+token);
            AuthUser user = (AuthUser) authResult.getPrincipal();
            Map<String,Object> body = new HashMap<>();
            body.put("token",token);
            body.put("expiration",jwtService.getExpiration(JWTServiceImpl.TOKEN_PREFIX+token));
            body.put("user",user);
            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(200);
            response.setContentType("application/json");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String,Object> body = new HashMap<>();
        body.put("error",failed.getMessage());
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType("application/json");
    }

}
