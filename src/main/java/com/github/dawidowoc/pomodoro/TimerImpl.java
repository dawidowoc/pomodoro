package com.github.dawidowoc.pomodoro;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class TimerImpl implements Timer {
    private final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
    private Future<?> timerFinisher = null;
    private Instant pomodoroFinishInstant = null;

    @Override
    public void countDown(Duration duration, Runnable callback) {
        Instant now = Instant.now();
        pomodoroFinishInstant = now.plusMillis(duration.toMillis());
        timerFinisher = scheduledThreadPoolExecutor.schedule(() -> {
            timerFinisher = null;
            callback.run();
        }, duration.toMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void cancel() {
        timerFinisher.cancel(true);
        timerFinisher = null;
    }

    @Override
    public boolean isRunning() {
        return timerFinisher != null;
    }

    @Override
    public Duration getTimeRemaining() {
        return Duration.between(Instant.now(), pomodoroFinishInstant);
    }
}
