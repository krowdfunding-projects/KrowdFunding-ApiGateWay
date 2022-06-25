package krowdfunding.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class FallbackConfig {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions
            .route(RequestPredicates.GET("/product-fallback"),this::getFallback)
            .andRoute(RequestPredicates.POST("/product-fallback"),this::getPostFallback);
    }

    public Mono<ServerResponse> getFallback(ServerRequest request){
        return ServerResponse.ok().body(Mono.empty(),String.class);
    }

    public Mono<ServerResponse> getPostFallback(ServerRequest request){
        return ServerResponse.status(HttpStatus.valueOf(500)).build();
    }
}
