package com.github.dawidowoc.pomodoro;

import java.util.Set;

interface PastPomodoroRepository {
    void savePastPomodoro(FinishedPomodoroEntity finishedPomodoro);
    Set<FinishedPomodoroEntity> getPastPomodoros();
}
