package ai.kennyeric.ms.benchmark.schedule;

import ai.kennyeric.ms.benchmark.context.BenchmarkServerContext;
import ai.kennyeric.ms.benchmark.util.RestTemplateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final String BENCH_STRING = "Hello world!";

    @Autowired
    BenchmarkServerContext benchmarkServerContext;

//    @Scheduled(fixedRate = 1000)
    public void rawMS() {
        //记录调用次数
        benchmarkServerContext.incrValue("access#rawms");
        String url = benchmarkServerContext.getURL("microservice", "/");
        String res = RestTemplateWrapper.getForString(benchmarkServerContext.getRestTemplate(), url);
        System.out.println("--------------" + res + "--------------");
        /**
         * 记录调用的成功次数 res == "Hello world!"
         * 记录调用的失败次数 res != "Hello world!"
         */
        if (res.equals(BENCH_STRING))
            benchmarkServerContext.incrValue("success#rawms");
        else
            benchmarkServerContext.incrValue("failed#rawms");
    }

    @Scheduled(fixedRate = 1000)
    public void ribbonMS() {
        benchmarkServerContext.incrValue("access#ribbonms");
        String url = benchmarkServerContext.getRibbonURL("microservice", "/");

        String res = null;
        try {
            res = RestTemplateWrapper.getForString(benchmarkServerContext.getRibbonRestTemplate(), url);
            System.out.println("--------------" + res + "--------------");
            if (res.equals(BENCH_STRING))
                benchmarkServerContext.incrValue("success#ribbonms");
            else
                benchmarkServerContext.incrValue("failed#ribbonms");
        } catch (Exception e) {
            System.out.println("请求服务异常 ");
            benchmarkServerContext.incrValue("failed#ribbonms");
        }
    }
}
