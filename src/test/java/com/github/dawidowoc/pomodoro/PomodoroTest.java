package com.github.dawidowoc.pomodoro;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class PomodoroTest {
    private final Instant instant = Instant.now();
    private final TimerMock timerMock = new TimerMock();
    private final Pomodoro pomodoro = new Pomodoro(timerMock, new InMemoryConfigRepository(Duration.ofMinutes(25)),
            new InMemoryPastPomodoroRepository(), Clock.fixed(instant, ZoneId.systemDefault()));

    @Test
    void shouldSaveFinishedPomodoro() {
        Assertions.assertThat(pomodoro.getPastPomodoros()).isEmpty();
        pomodoro.start("mypomodoro");
        Assertions.assertThat(pomodoro.getPastPomodoros()).isEmpty();
        timerMock.finish();
        Assertions.assertThat(pomodoro.getPastPomodoros()).containsExactly(new FinishedPomodoroEntity(
                new PomodoroEntity("mypomodoro", Duration.ofMinutes(25)), instant));
    }

    @Test
    void shouldAllowToCancelPomodoroWithoutSaving() {
        Assertions.assertThat(pomodoro.getPastPomodoros()).isEmpty();
        pomodoro.start("mypomodoro");
        Assertions.assertThat(pomodoro.getPastPomodoros()).isEmpty();
        pomodoro.cancel();
        assertThat(pomodoro.isRunning()).isFalse();
        Assertions.assertThat(pomodoro.getPastPomodoros()).isEmpty();
    }

    @Test
    void shouldAnswerIfPomodoroIsRunning() {
        assertThat(pomodoro.isRunning()).isFalse();
        pomodoro.start("mypomodoro");
        assertThat(pomodoro.isRunning()).isTrue();
        timerMock.finish();
        assertThat(pomodoro.isRunning()).isFalse();
    }

    @Test
    void shouldReturnRemainingTimeForRunningPomodoro() {
        pomodoro.start("mypomodoro");
        assertThat(pomodoro.getTimeRemaining()).isEqualTo(Duration.ofMinutes(25));
        timerMock.elapseTime(Duration.ofMinutes(5));
        assertThat(pomodoro.getTimeRemaining()).isEqualTo(Duration.ofMinutes(20));
    }
}
