package ai.kennyeric.ms.benchmark.controller;

import ai.kennyeric.ms.benchmark.context.BenchmarkServerContext;
import ai.kennyeric.ms.benchmark.util.RestTemplateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonDcController {

    @Autowired
    BenchmarkServerContext benchmarkServerContext;

    @GetMapping("/r/consumer")
    public String dc(){
        return RestTemplateWrapper.getForString(benchmarkServerContext.getRibbonRestTemplate(), benchmarkServerContext.getRibbonURL("microservice", "/"));
    }
}
