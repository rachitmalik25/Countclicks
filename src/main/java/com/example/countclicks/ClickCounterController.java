package com.example.countclicks;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClickCounterController {

    private final AtomicLong clicks = new AtomicLong();

    @GetMapping("/api/clicks")
    public ClickCount getClicks() {
        return new ClickCount(clicks.get());
    }

    @PostMapping("/api/clicks")
    public ClickCount incrementClicks() {
        return new ClickCount(clicks.incrementAndGet());
    }

    @GetMapping("/api/health")
    public HealthStatus getHealth() {
        return new HealthStatus("UP", "countclicks");
    }

    public record ClickCount(long count) {
    }

    public record HealthStatus(String status, String application) {
    }
}
