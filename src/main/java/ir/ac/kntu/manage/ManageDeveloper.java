package ir.ac.kntu.manage;

import ir.ac.kntu.Enum.GameType;
import ir.ac.kntu.Print.Print;
import ir.ac.kntu.Scan.ScannerWrapper;
import ir.ac.kntu.presen.Developer;
import ir.ac.kntu.presen.User;
import ir.ac.kntu.report.ReprotGameProblemToDev;
import ir.ac.kntu.store.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class ManageDeveloper {
    public static ArrayList<Developer> developers = new ArrayList<>();

    public static Developer createDeveloper() {
        System.out.println("please enter DeveloperName");
        String developerName = ScannerWrapper.getInstance().next();
        System.out.println("please Enter pass");
        String pass = ScannerWrapper.getInstance().next();
        Developer developer = new Developer(pass, developerName);
        if (!developers.contains(developer))
            developers.add(developer);
        return developer;
    }

    public static void removeDeveloper(Developer developer) {
        developers.remove(developer);
    }

    public static void changeDev(Developer changedDev) {
        System.out.println("please enter DeveloperName");
        changedDev.developerName = ScannerWrapper.getInstance().next();
        System.out.println("please Enter pass");
        changedDev.pass = ScannerWrapper.getInstance().next();
    }

    public static void reportProblem() {

        try {
            System.out.println("please choose a Game   -1 = back");
            ManageGame.printGame();
            int select = ScannerWrapper.getInstance().nextInt();
            if (select > 0 && select <= ManageGame.games.size())
                new ReprotGameProblemToDev(ManageDeveloper.developers, ManageGame.games.get(select - 1));
            else if (select != -1)throw new  Exception("خارج از محدوده انتخاب کردید");
            else return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void createGame(Developer developer){
        Game game = Game.read();
        developer.addGame(game);
    }

    public static void deleteGame(Developer developer){
        int select;
        System.out.println("please choose one Game   -1 = back");
        Print.printArrayGame(developer.getMyGame(), "");
        select = ScannerWrapper.getInstance().nextInt();
        if (select <= -1 || select > developer.getMyGame().size()) return;
        developer.removeGame(developer.getMyGame().get(select - 1));
    }
    public static void changeGame(Developer developer){
        int select;
        System.out.println("please choose one Game   -1 = back");
        Print.printArrayGame(developer.getMyGame(), "");
        select = ScannerWrapper.getInstance().nextInt();
        if (select == -1)
            return;
        developer.changeGame(developer.getMyGame().get(select - 1));
    }

    public static void checkInbox(Developer developer) {
        Game game = null;

        ArrayList<String> stringArrayList = developer.getInbox().stream().map(Game::getName).collect(Collectors.toCollection(ArrayList::new));
        if (stringArrayList.size() != 0) {
            out.println("Choose the desired option    back  = -1");
            Print.printArrayOfString(stringArrayList, "");
        } else {
            out.println("your inbox is empty");
            return;
        }
        int select = ScannerWrapper.getInstance().nextInt();

        if (select > 0 && select <= developer.getInbox().size()) {
            game = developer.getInbox().get(select - 1);
        } else return;

        developer.getInbox().remove(game);
        ArrayList<Game> arrayList = developer.getScheduleEvent();
        arrayList.add(game);
        developer.setScheduleEvent(arrayList);
    }
    public static void checkFeedback(Developer developer){
        String userName = null;
        ArrayList<String> stringArrayList = new ArrayList<>();
        Map<Game,String> gameStringMap = null;

        if (developer.getUserMapMap().size() == 0){
            out.println("بازخوردی برای شما ثبت نشده است");
            return;
        }

        for (Map.Entry<User,Map<Game,String>> map : developer.getUserMapMap().entrySet()){
            gameStringMap = map.getValue();
            userName = map.getKey().getUserName();
            for (Map.Entry<Game,String> map1 : gameStringMap.entrySet())
                stringArrayList.add("userName : " + userName + " for "+map1.getKey().getName()+" seid "+map1.getValue());
        }





        Print.printArrayOfString(stringArrayList,"");
    }
}
