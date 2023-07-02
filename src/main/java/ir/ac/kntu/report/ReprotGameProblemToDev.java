package ir.ac.kntu.report;

import ir.ac.kntu.Time.Time;
import ir.ac.kntu.manage.ManageDeveloper;
import ir.ac.kntu.manage.ManageGame;
import ir.ac.kntu.presen.Developer;
import ir.ac.kntu.store.Game;

import java.util.ArrayList;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class ReprotGameProblemToDev {
    int round = 1;

    final long period = 30000L;

    Time inboxTime = new Time();

    ArrayList<Developer> developers;

    public ReprotGameProblemToDev(ArrayList<Developer> developers, Game game) throws Exception {
        this.developers = developers;
        reportProblem(game);
        ManageGame.games.remove(game);
    }

    ArrayList<Developer> allDevThatHaveOneGame = new ArrayList<>();

    Developer developer = null;

    private void reportProblem(Game game) throws Exception {
        if (developers.size() == 0) {
            throw new Exception("you haven't any developer");
        }

        allDevThatHaveOneGame = getAllDevThatHaveOneGame(game);
        developer = getSuitableDev(game);
        developer.addInbox(game);

        inboxTime.start(new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(round * period);
                    if (developer.getInbox().contains(game)){
                        developer.getInbox().remove(game);
                        allDevThatHaveOneGame.remove(developer);
                        developer = getSuitableDev(game);
                        developer.getInbox().add(game);
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }, 100L);

    }

    private Developer getSuitableDev(Game game) throws Exception {
        int size = Integer.MAX_VALUE;
        Developer developer = null;

        if (allDevThatHaveOneGame.size() == 0){
            allDevThatHaveOneGame = getAllDevThatHaveOneGame(game);
            round++;
        }


        for (Developer dev : allDevThatHaveOneGame)
            if (size > dev.getInboxSize()) {
                size = dev.getInboxSize();
                developer = dev;
            }


        return developer;
    }

    private ArrayList<Developer> getAllDevThatHaveOneGame(Game game) throws Exception {
        ArrayList<Developer> arrayList = developers.stream().filter(dev -> dev.getMyGame().contains(game)).collect(Collectors.toCollection(ArrayList::new));
        if (arrayList.size() == 0) throw new Exception("هیچ توسعه دهنده ای این بازی را ندارد");
        return arrayList;
    }

    public void stopTime() {
        inboxTime.stop();
    }

}
