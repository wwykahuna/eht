package com.eht.evetrade.config;


import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.jackson.JacksonMsgConvertor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OkhttpsConfiguration {

    @Bean
    public HTTP createHttp(ObjectMapper objectMapper){
        HTTP http = HTTP.builder()
                .baseUrl("https://esi.evepc.163.com")
                .addMsgConvertor(new JacksonMsgConvertor(objectMapper))
                .build();
        return http;
    }
}
