package com.github.dawidowoc.cli;

import com.github.dawidowoc.pomodoro.Pomodoro;

import java.io.IOException;

public class PomodoroCLI {
    public static void main(String[] args) throws InterruptedException, IOException {
        Pomodoro pomodoro = Pomodoro.getRealPomodoro();
        pomodoro.start("mypomodoro");
        System.out.println("Pomodoro started");
        while (pomodoro.isRunning()) {
            Runtime.getRuntime().exec("clear");
            System.out.println(pomodoro.getTimeRemaining());
            Thread.sleep(500);
        }
        System.out.println("Pomodoro finished");
    }
}
