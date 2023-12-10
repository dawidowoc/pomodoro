package com.github.dawidowoc.pomodoro;

import java.time.Duration;

class TimerMock implements Timer {
    private Runnable callback;
    private Duration timeRemaining;

    @Override
    public void countDown(Duration duration, Runnable callback) {
        this.timeRemaining = duration;
        this.callback = callback;
    }

    @Override
    public void cancel() {
        this.callback = null;
    }

    @Override
    public boolean isRunning() {
        return callback != null;
    }

    @Override
    public Duration getTimeRemaining() {
        return timeRemaining;
    }

    public void finish() {
        callback.run();
        callback = null;
    }

    public void elapseTime(Duration duration) {
        timeRemaining = timeRemaining.minus(duration);
    }
}
