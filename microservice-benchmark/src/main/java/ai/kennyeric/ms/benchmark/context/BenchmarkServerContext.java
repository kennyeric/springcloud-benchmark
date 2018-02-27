package ai.kennyeric.ms.benchmark.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 定义与server相关的context
 */

@Component
public class BenchmarkServerContext {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    @Qualifier("ribbonRestTemplate")
    RestTemplate ribbonRestTemplate;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public RestTemplate getRibbonRestTemplate() {
        return ribbonRestTemplate;
    }

    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public Long incrValue(String key) {
        return redisTemplate.opsForValue().increment(key, 1L);
    }

    public String getURL(String serviceId, String route) {
        ServiceInstance serviceInstance = loadBalancerClient.choose(serviceId);
        return "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + route;
    }

    public String getRibbonURL(String serviceId, String route) {
        return "http://" + serviceId + route;
    }
}
