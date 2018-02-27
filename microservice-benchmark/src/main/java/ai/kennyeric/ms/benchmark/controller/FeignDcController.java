package ai.kennyeric.ms.benchmark.controller;

import ai.kennyeric.ms.benchmark.feignclient.DcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignDcController {
    @Autowired
    DcClient dcClient;

    @GetMapping("/f/consumer")
    public String dc() {
        return dcClient.consumer();
    }
}
