package com.javatechie.config;

import io.grpc.protobuf.services.ProtoReflectionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.grpc.BindableService;


@Configuration
public class GrpcConfig {

    @Bean
    public BindableService reflectionService() {
        return ProtoReflectionService.newInstance();
    }
}