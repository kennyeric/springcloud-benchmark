package ai.kennyeric.ms.benchmark.controller;

import ai.kennyeric.ms.benchmark.context.BenchmarkServerContext;
import ai.kennyeric.ms.benchmark.util.RestTemplateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DcController {

    @Autowired
    BenchmarkServerContext benchmarkServerContext;

    /**
     * API，采用注入的loadbalancerclient选择指定的serviceId的服务实例
     * @return
     */
    @GetMapping("/consumer")
    public String dc() {
        String url = benchmarkServerContext.getURL("microservice", "/");
        System.out.println(url);
        return RestTemplateWrapper.getForString(benchmarkServerContext.getRestTemplate(), url);
    }
}
