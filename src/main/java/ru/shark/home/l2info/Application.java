package ru.shark.home.l2info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.shark.home.l2info")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
