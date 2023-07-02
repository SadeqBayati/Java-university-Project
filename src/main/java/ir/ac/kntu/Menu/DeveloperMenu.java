package ir.ac.kntu.Menu;

import ir.ac.kntu.Print.Print;
import ir.ac.kntu.Scan.ScannerWrapper;
import ir.ac.kntu.manage.ManageDeveloper;
import ir.ac.kntu.presen.Developer;
import ir.ac.kntu.store.Game;

import java.util.concurrent.atomic.AtomicInteger;

public class DeveloperMenu {
    public  void developerMenu(Developer developer) {
        System.out.println("*********************");
        System.out.println("1 create Game");
        System.out.println("2 delete Game");
        System.out.println("3 change Game");
        System.out.println("4 check inbox");
        System.out.println("5 check scheduleEvent");
        System.out.println("6 Share the game with other developers");
        System.out.println("7 check feedback");
        System.out.println("8 Exit");
        System.out.println("*********************");


        switch (ScannerWrapper.getInstance().nextInt()) {
            case 1 -> ManageDeveloper.createGame(developer);
            case 2 -> ManageDeveloper.deleteGame(developer);
            case 3 -> ManageDeveloper.changeGame(developer);
            case 4 -> ManageDeveloper.checkInbox(developer);
            case 5 -> developer.checkScheduleEvent();
            case 6 -> developer.addDeveloper();
            case 7 -> ManageDeveloper.checkFeedback(developer);
            default -> enterDeveloper();
        }
        developerMenu(developer);
    }
    public  void enterDeveloper() {

        Developer developer = null;
        System.out.println("please Choose one  if you want to creat new Enter 0     -1 = back");
        AtomicInteger number = new AtomicInteger();
        ManageDeveloper.developers.forEach(developer1 -> System.out.println(number.incrementAndGet() + " " + developer1.developerName));
        int select = ScannerWrapper.getInstance().nextInt();
        if (select == 0)
            developer = ManageDeveloper.createDeveloper();
        else if (select > 0 && select <= ManageDeveloper.developers.size())
            developer = ManageDeveloper.developers.get(select - 1);
        else Menu.adminUserMenu();
        developerMenu(developer);
    }
}
