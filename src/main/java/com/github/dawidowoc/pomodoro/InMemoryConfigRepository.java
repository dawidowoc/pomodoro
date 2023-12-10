package com.github.dawidowoc.pomodoro;

import java.time.Duration;

class InMemoryConfigRepository implements ConfigRepository {
    private final Duration pomodoroDuration;

    InMemoryConfigRepository(Duration pomodoroDuration) {
        this.pomodoroDuration = pomodoroDuration;
    }

    @Override
    public Duration getPomodoroDuration() {
        return pomodoroDuration;
    }
}
