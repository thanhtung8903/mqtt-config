package com.lab.mqttconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MqttConfigApplication {

    @Autowired
    private MqttScheduler mqttScheduler;

    public static void main(String[] args) {
        SpringApplication.run(MqttConfigApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            mqttScheduler.sendDeleteRequest();
        };
    }
}
