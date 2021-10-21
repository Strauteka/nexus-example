package edu.strauteka.nexus;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public class ReactiveController {
    @GetMapping()
    Flux<HelloDto> greetStranger(@RequestParam(value = "count", required = false) Optional<Integer> count) {
        return Flux.generate(() -> 0, (state, sink) -> {
                    final var localState = state + 1;
                    if(localState > count.orElse(3)) {
                        sink.complete();
                    }
                    sink.next(localState);
                    return localState;
                })
                .delayElements(Duration.ofSeconds(1))
                .map(i -> new HelloDto("Hello " + i));
    }
}
