package com.modernfrontendshtmx.contact.service;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public final class ArchiveProcessInfo {
    private final AtomicInteger progress = new AtomicInteger(0);
    private Status status = Status.RUNNING;
    private Future<String> future;

    public void setProgress(int progress) {
        Assert.isTrue(progress >= 0 && progress <= 100,
                "Progress should be between 0 and 100");
        this.progress.set(progress);
    }

    enum Status {
        RUNNING, COMPLETE, FAILED
    }
}
