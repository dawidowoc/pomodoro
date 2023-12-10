package com.github.dawidowoc.pomodoro;

import java.time.Duration;

interface ConfigRepository {
    Duration getPomodoroDuration();
}
