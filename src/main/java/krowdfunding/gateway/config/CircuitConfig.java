package krowdfunding.gateway.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitConfig {

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> customizer(){
        return f-> f.configureDefault(id -> new Resilience4JConfigBuilder(id)
            .circuitBreakerConfig(CircuitBreakerConfig.custom()
                                      .slidingWindowSize(20).permittedNumberOfCallsInHalfOpenState(5)
                                      .failureRateThreshold(60)
                                      .waitDurationInOpenState(Duration.ofSeconds(30)).build())
                                          .timeLimiterConfig(TimeLimiterConfig.custom()
                                                                 .timeoutDuration(Duration.ofSeconds(1)).build()).build());
    }
}
