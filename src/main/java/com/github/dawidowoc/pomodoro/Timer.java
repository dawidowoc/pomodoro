package com.github.dawidowoc.pomodoro;

import java.time.Duration;

interface Timer {
    void countDown(Duration duration, Runnable callback);

    void cancel();

    boolean isRunning();

    Duration getTimeRemaining();
}
