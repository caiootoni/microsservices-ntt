package com.caiootoni.api_gateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GatewayFilter, Ordered {

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Value("${authentication.token:supersecrettoken123}")
    private String validToken;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("DEBUG: AuthenticationFilter executando para: " + exchange.getRequest().getPath());

        String authHeader = exchange.getRequest().getHeaders().getFirst(AUTH_HEADER);

        if(authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            System.out.println("DEBUG: Token ausente ou prefixo incorreto. Retornando 401.");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(BEARER_PREFIX.length());

        if (!validToken.equals(token)) {
            System.out.println("DEBUG: Token inválido. Esperado: " + validToken + ", Recebido: " + token);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        System.out.println("DEBUG: Token válido. Continuando...");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}