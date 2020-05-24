package com.keda.jpa.jpademo;

import com.keda.jpa.jpademo.service.UserLogProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;

@EnableJpaAuditing
@SpringBootApplication
public class JpaDemoApplication {

    @Autowired
    private UserLogProducer kafkaSender;

    @PostConstruct
    public void init(){
        for (int i =0;i<10;i++){
            kafkaSender.sendLog(String.valueOf(i));
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

}
