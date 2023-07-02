package ir.ac.kntu.presen;

import ir.ac.kntu.Print.Print;
import ir.ac.kntu.Scan.ScannerWrapper;
import ir.ac.kntu.manage.ManageDeveloper;
import ir.ac.kntu.manage.ManageGame;
import ir.ac.kntu.store.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.System.*;

public class Developer {
    ArrayList<Game> myGame = new ArrayList<>();
    ArrayList<Game> inbox = new ArrayList<>();
    ArrayList<Game> scheduleEvent = new ArrayList<>();
    public String developerName, pass;

    Map<User,Map<Game,String>> userMapMap = new HashMap<>();

    public Developer(String pass, String developerName) {
        this.pass = pass;
        this.developerName = developerName;
    }

    public ArrayList<Game> getMyGame() {
        return myGame;
    }

    public Map<User, Map<Game, String>> getUserMapMap() {
        return userMapMap;
    }
    public void setUserMapMap(Map<User, Map<Game, String>> userMapMap) {
        this.userMapMap = userMapMap;
    }

    public void addGame(Game game) {
        myGame.add(game);
    }

    public void changeGame(Game game) {
        ManageGame.changeGame(game);
    }

    public void removeGame(Game game) {
        myGame.remove(game);
    }

    public void addDeveloper() {
        Developer addDev = null;
        ArrayList<Game> addableGames = new ArrayList<>();
        Game addGame = null;
        ArrayList<Developer> developers = new ArrayList<>(ManageDeveloper.developers);
        developers.remove(this);
        ArrayList<String> stringArrayList = developers.stream().
                filter(developer -> !developer.equals(this)).map(Developer::getDeveloperName).collect(Collectors.toCollection(ArrayList::new));

        if (stringArrayList.size() == 0) {
            out.println(" به غیر از شما هیچ توسعه دهنده ی دیگری نیست");
            return;
        }
        out.println("-1 = بازگشت      یک توسعه دهنده را انتخاب کنید");
        Print.printArrayOfString(stringArrayList, " ");
        if (stringArrayList.size() == 0) {
            out.println("هیچ بازی قابل اشتراکی ندارید");
            return;
        }


        int select = ScannerWrapper.getInstance().nextInt();
        if (select > 0 && select <= stringArrayList.size()) {
            addDev = developers.get(select - 1);
        }else return;


        stringArrayList.clear();

        for (Game game : myGame) if (!addDev.myGame.contains(game)) addableGames.add(game);

        out.println("-1 = بازگشت     کدام بازی تان را می خواهید به اشتراک بگذارید");
        stringArrayList = addableGames.stream().map(Game::getName).collect(Collectors.toCollection(ArrayList::new));
        Print.printArrayOfString(stringArrayList," ");
        select = ScannerWrapper.getInstance().nextInt();
        if (select > 0 && select <= stringArrayList.size())
            addGame = addableGames.get(select-1);
        else return;

        addDev.myGame.add(addGame);

    }

    public String getDeveloperName() {
        return developerName;
    }




    public ArrayList<Game> getScheduleEvent() {
        return new ArrayList<>(scheduleEvent);
    }

    public void setScheduleEvent(ArrayList<Game> scheduleEvent) {
        this.scheduleEvent = scheduleEvent;
    }

    public void checkScheduleEvent() {
        Game game = null;

        ArrayList<String> stringArrayList = scheduleEvent.stream().map(Game::getName).collect(Collectors.toCollection(ArrayList::new));

        if (stringArrayList.size() != 0) {
            out.println("Choose the desired option    back  = -1");
            Print.printArrayOfString(stringArrayList, "");
        } else {
            out.println("your scheduleEvent is empty");
            return;
        }

        int select = ScannerWrapper.getInstance().nextInt();

        if (select > 0 && select <= scheduleEvent.size()) {
            ManageGame.games.add(scheduleEvent.get(select - 1));
            scheduleEvent.remove(select - 1);
            out.println("your choose successfully removed from scheduleEvent List");
        } else return;

    }

    public ArrayList<Game> getInbox() {
        return inbox;
    }

    public void addInbox(Game game) {
        inbox.add(game);
    }

    public int getInboxSize() {
        return inbox.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return developerName.equals(developer.developerName) && pass.equals(developer.pass);
    }
    @Override
    public int hashCode() {
        return Objects.hash(developerName, pass);
    }

}

