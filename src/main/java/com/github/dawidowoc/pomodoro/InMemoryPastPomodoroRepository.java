package com.github.dawidowoc.pomodoro;

import java.util.HashSet;
import java.util.Set;

class InMemoryPastPomodoroRepository implements PastPomodoroRepository {
    private final Set<FinishedPomodoroEntity> finishedPomodoros = new HashSet<>();

    @Override
    public void savePastPomodoro(FinishedPomodoroEntity finishedPomodoro) {
        finishedPomodoros.add(finishedPomodoro);
    }

    @Override
    public Set<FinishedPomodoroEntity> getPastPomodoros() {
        return finishedPomodoros;
    }
}
