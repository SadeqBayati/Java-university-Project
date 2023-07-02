package ir.ac.kntu.Time;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class Time {
    Timer timer = new Timer();

    public void start(TimerTask timerTask,long period) {
        timer.schedule(timerTask, period, period);
    }

    public void stop() {
        timer.cancel();
    }
}

