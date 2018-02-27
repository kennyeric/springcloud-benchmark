package ai.kennyeric.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableAutoConfiguration
@EnableEurekaClient
@RestController
public class MSApplication {

    @RequestMapping("/")
    public String home() {
        return "Hello world!";
    }

    public static void main(String[] args) {
        SpringApplication.run(MSApplication.class, args);
    }
}
