package br.java.mvp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class MvpClienteJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvpClienteJavaApplication.class, args);
    }

}
