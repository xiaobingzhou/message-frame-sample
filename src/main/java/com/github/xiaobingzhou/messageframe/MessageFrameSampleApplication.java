package com.github.xiaobingzhou.messageframe;


import com.github.xiaobingzhou.messageframe.annotation.EnableHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableHandler
public class MessageFrameSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageFrameSampleApplication.class, args);
    }
}
