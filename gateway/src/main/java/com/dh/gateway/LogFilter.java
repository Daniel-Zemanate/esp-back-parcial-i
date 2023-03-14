package com.dh.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.logging.Logger;

@Component
public class LogFilter extends AbstractGatewayFilterFactory<LogFilter.Config> {

    private static Logger log = Logger.getLogger(LogFilter.class.getName());

    public LogFilter(){super(Config.class);}

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("Path requested: " + exchange.getRequest().getPath() + " at: " + Calendar.getInstance().getTime());

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("Path requested: " + exchange.getRequest().getPath() + " time response: " + Calendar.getInstance().getTime());


        }));
        };
    }

    public static class Config {
    }
}
