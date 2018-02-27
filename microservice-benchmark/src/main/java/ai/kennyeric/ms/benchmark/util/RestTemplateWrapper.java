package ai.kennyeric.ms.benchmark.util;

import org.springframework.web.client.RestTemplate;

public class RestTemplateWrapper {

    public static String getForString(RestTemplate restTemplate, String url){
        return restTemplate.getForObject(url, String.class);
    }
}
