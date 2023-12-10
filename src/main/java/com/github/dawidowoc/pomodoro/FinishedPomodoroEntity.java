package com.github.dawidowoc.pomodoro;

import java.time.Instant;

record FinishedPomodoroEntity(PomodoroEntity pomodoroEntity, Instant finishInstant) {
}
