package ai.kennyeric.ms.benchmark;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class MSBenchmarkApplication {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 采用ribbon拦截resttemplate的请求，默认采用轮询的方式获取服务实例
     * @return
     */
    @Bean(name = "ribbonRestTemplate")
    @LoadBalanced
    public RestTemplate ribbonRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MSBenchmarkApplication.class, args);
    }
}
