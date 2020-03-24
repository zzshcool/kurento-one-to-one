
package com.example.one2one;

import com.example.one2one.handler.CallHandler;
import com.example.one2one.handler.UserRegistryHandle;
import org.kurento.client.KurentoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@EnableCaching
@EnableWebSocket
@SpringBootApplication
public class One2OneCallApp implements WebSocketConfigurer {

    @Bean
    public CallHandler callHandler() {
        return new CallHandler();
    }

    @Bean
    public UserRegistryHandle registry() {
        return new UserRegistryHandle();
    }

    @Value("${kurento.server.ip}")
    private String kurentoIp;


    @Bean
    public KurentoClient kurentoClient() {
        //return KurentoClient.create();
        return KurentoClient.create(kurentoIp);
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(callHandler(), "/call");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(One2OneCallApp.class, args);
    }
}
