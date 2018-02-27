package ai.kennyeric.ms.benchmark.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("microservice")
public interface DcClient {

    @GetMapping("/")
    String consumer();
}
