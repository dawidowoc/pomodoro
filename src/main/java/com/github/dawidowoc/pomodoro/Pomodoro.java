package com.github.dawidowoc.pomodoro;

import java.time.Clock;
import java.time.Duration;
import java.util.Set;

public class Pomodoro {
    private final Timer timer;
    private final ConfigRepository configRepository;
    private final PastPomodoroRepository pastPomodoroRepository;
    private final Clock clock;

    public Pomodoro(Timer timer, ConfigRepository configRepository, PastPomodoroRepository pastPomodoroRepository, Clock clock) {
        this.timer = timer;
        this.configRepository = configRepository;
        this.pastPomodoroRepository = pastPomodoroRepository;
        this.clock = clock;
    }

    public static Pomodoro getRealPomodoro() {
        return new Pomodoro(new TimerImpl(), new InMemoryConfigRepository(Duration.ofMinutes(25)),
                new InMemoryPastPomodoroRepository(), Clock.systemDefaultZone());
    }

    public void start(String name) {
        PomodoroEntity pomodoro = new PomodoroEntity(name, configRepository.getPomodoroDuration());
        timer.countDown(pomodoro.duration(), () -> finishPomodoro(pomodoro));
    }

    public Duration getTimeRemaining() {
        if (!isRunning()) {
            throw new IllegalStateException("Pomodoro is not in progress");
        }
        return timer.getTimeRemaining();
    }

    public boolean isRunning() {
        return timer.isRunning();
    }

    public void cancel() {
        timer.cancel();
    }

    public Set<FinishedPomodoroEntity> getPastPomodoros() {
        return pastPomodoroRepository.getPastPomodoros();

    }

    private void finishPomodoro(PomodoroEntity pomodoro) {
        FinishedPomodoroEntity finishedPomodoro = new FinishedPomodoroEntity(pomodoro, clock.instant());
        pastPomodoroRepository.savePastPomodoro(finishedPomodoro);
    }
}
