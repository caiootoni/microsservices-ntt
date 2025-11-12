package com.caiootoni.api_gateway.configuration;

import com.caiootoni.api_gateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class GatewayConfiguration {

    @Autowired
    private AuthenticationFilter authenticationFilter;

    // Desabilita o filtro de segurança padrão do Spring Security para permitir o filtro customizado
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().permitAll() //Permitir tudo, pois o filtro customizado fará o trabalho
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCryptPasswordEncoder
    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("produto-service", r -> r.path("/produtos/**")
//                        .filters(f -> f.filter(authenticationFilter))
//                        .uri("lb://produto-service"))
//                .route("pedido-service", r -> r.path("/pedidos/**")
//                        .filters(f -> f.filter(authenticationFilter))
//                        .uri("lb://pedido-service"))
//                .build();
//    }
}
