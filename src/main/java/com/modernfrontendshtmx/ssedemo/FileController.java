package com.modernfrontendshtmx.ssedemo;

import com.modernfrontendshtmx.ssedemo.SseBroker.ProgressEvent;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import java.io.IOException;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/upload")
@RequiredArgsConstructor
public class FileController {
    private final FileProcessor fileProcessor;
    private final SseBroker broker;
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @GetMapping
    public String upload(Model model) {
        return "upload";
    }

    @HxRequest
    @PostMapping
    @ResponseBody
    public void runImport(@RequestParam("file") MultipartFile file)
            throws IOException {
        fileProcessor.process(file.getInputStream());
    }

    @GetMapping("/progress")
    public Flux<ServerSentEvent<String>> progress() {
        Flux<ServerSentEvent<String>> heartbeat = Flux.interval(Duration.ofSeconds(5))
                .map(it -> ServerSentEvent.<String>builder()
                        .event("heartbeat")
                        .build());
        Flux<List<ProgressEvent>> updates = broker.subscribeToUpdates();
        return Flux
                .merge(heartbeat,
                        updates.flatMap(events -> Flux.just(createLogEvent(events),
                                createProgressEvent(events))
                                .filter(Objects::nonNull)))
                .doOnSubscribe(
                        subscription -> LOGGER.debug("Subscription: {}", subscription))
                .doOnCancel(() -> LOGGER.debug("cancel"))
                .doOnError(throwable -> LOGGER.debug(throwable.getMessage(), throwable))
                .doFinally(signalType -> LOGGER.debug("finally: {}", signalType));
    }

    private ServerSentEvent<String> createLogEvent(List<ProgressEvent> events) {
        return ServerSentEvent.<String>builder()
                .event("log-event")
                .data(events.stream()
                        .map(progressEvent -> "<div>%s</div>".formatted(
                                replaceNewLines(progressEvent.message())))
                        .collect(Collectors.joining()))
                .build();
    }

    private static ServerSentEvent<String> createProgressEvent(List<ProgressEvent> events) {
        return ServerSentEvent.<String>builder()
                .event("progress-event")
                .data(
                        events.stream()
                                .max(Comparator.comparing(
                                        progressEvent -> progressEvent.progress().value()))
                                .map(
                                        progressEvent -> "<progress class=\"progress w-full h-6\" value=\"%d\" max=\"100\" ></progress>"
                                                .formatted(
                                                        (int) (progressEvent.progress().value() * 100)))
                                .orElse(null))
                .build();
    }

    private String replaceNewLines(String message) {
        return message.replaceAll("\n", "<br>");
    }
}
