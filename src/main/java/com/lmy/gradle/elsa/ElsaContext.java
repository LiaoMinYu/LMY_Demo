package com.lmy.gradle.elsa;


import com.lmy.gradle.elsa.buffer.DoubleBuffer;
import com.lmy.gradle.elsa.buffer.FillBufferFunction;
import com.lmy.gradle.elsa.dto.IdBatchResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

import java.time.Duration;

/**
 * @author LMinY
 * @since 2020/7/8
 */
public class ElsaContext implements Elsa {

    private static final Logger logger = LoggerFactory.getLogger(ElsaContext.class);
    private final DoubleBuffer buffer;

    public ElsaContext(ElsaProperties properties) {
        WebClient webClient = WebClient.builder().build();
        Scheduler scheduler = Schedulers.single();

        String url;
        switch (properties.getEnv()) {
            case test:
                url = "http://elsa.test.be";
                break;
            default:
                url = "http://elsa.test.hyqfx.com";
                break;
        }

        UriComponentsBuilder server = UriComponentsBuilder.fromHttpUrl(url)
            .path("/uids")
            .queryParam("n", properties.getBuffer());

        FillBufferFunction f = (buffer, fetching) ->
            webClient
                .get()
                .uri(server.build().toUri())
                .retrieve()
                .bodyToMono(IdBatchResp.class)
                .subscribeOn(scheduler)
                .map(IdBatchResp::getUids)
                // TODO: only retry network error
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(1)))
                .doOnNext(ids -> ids.forEach(buffer::offer))
                .subscribe(l -> fetching.set(false));

        buffer = new DoubleBuffer(properties.getBuffer(), f);
    }

    @Override
    public String getUid() {
        return buffer.get();
    }
}
