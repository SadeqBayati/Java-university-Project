package ir.ac.kntu.Menu;

import ir.ac.kntu.Scan.ScannerWrapper;
import ir.ac.kntu.Time.Time;
import ir.ac.kntu.manage.*;
import ir.ac.kntu.presen.Admin;
import ir.ac.kntu.presen.Seller;
import ir.ac.kntu.store.Game;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Menu {
    static Time userTime = new Time();

    public static ArrayList<Admin> admins = new ArrayList<>();

    public static void adminUserMenu() {
        DeveloperMenu developerMenu = new DeveloperMenu();
        SellerMenu sellerMenu = new SellerMenu();
        UserMenu userMenu = new UserMenu();
        System.out.println("*********************");
        System.out.println("1 Admin");
        System.out.println("2 User");
        System.out.println("3 Developer");
        System.out.println("4 Seller");
        System.out.println("5 Report");
        System.out.println("6 Exit");
        System.out.println("*********************");

        switch (ScannerWrapper.getInstance().nextInt()) {
            case 1 -> admin();
            case 2 -> userMenu.enterUser();
            case 3 -> developerMenu.enterDeveloper();
            case 4 -> {
                try {
                    Seller seller = sellerMenu.enterSeller();
                    sellerMenu.sellerOption(seller);
                }catch (Exception e){

                    System.out.println(e.getMessage());
                    adminUserMenu();
                }
            }
            case 5 -> {
                ReportMenu reportMenu = new ReportMenu();
                reportMenu.reportMenu();
            }
            default -> System.exit(0);
        }
    }

    public static void manageGameMenu(Admin admin) {
        System.out.println("*********************");
        System.out.println("1 create Game");
        System.out.println("2 delete Game");
        System.out.println("3 change Game");
        System.out.println("4 back");
        int arrayElement;
        switch (ScannerWrapper.getInstance().nextInt()) {
            case 1 -> {
                ManageGame.creatGame();
                manageGameMenu(admin);
            }
            case 2 -> {
                System.out.println("Please enter the desired number  -1  = Back");
                ManageGame.printGame();
                arrayElement = ScannerWrapper.getInstance().nextInt() - 1;
                if (arrayElement == -2)
                    manageGameMenu(admin);
                if (ManageGame.deleteGame(ManageGame.games.get(arrayElement))) {
                    System.out.println("successfully removed");
                } else System.out.println("not removed");
                manageGameMenu(admin);
            }
            case 3 -> {
                System.out.println("Please enter the desired number   -1 = Back");
                ManageGame.printGame();
                arrayElement = ScannerWrapper.getInstance().nextInt() - 1;
                if (arrayElement == -2)
                    manageGameMenu(admin);
                ManageGame.changeGame(ManageGame.games.get(arrayElement));
                manageGameMenu(admin);
            }
            case 4 -> adminOption(admin);
            default -> manageGameMenu(admin);
        }
    }

    public static void admin() {
        //admins.stream().forEach(admin -> admins.remove(admin));
        admins.add(new Admin("sadeq", "123"));
        admins.add(new Admin("amin", "123"));
        admins.add(new Admin("ali", "123"));

        AtomicInteger number = new AtomicInteger(0);
        admins.forEach(admin -> System.out.println(number.incrementAndGet() + " " + admin.userName));
        System.out.println("please Choose UserName");
        int select = ScannerWrapper.getInstance().nextInt();
        Admin admin = admins.get(select - 1);
        String pass;

        do {
            System.out.println("*********************");
            System.out.println("please Enter Password");
            pass = ScannerWrapper.getInstance().next();
        } while (!pass.equals(admin.pass));

        adminOption(admin);
    }

    public static void adminOption(Admin admin) {
        SellerMenu sellerMenu = new SellerMenu();
        System.out.println("*********************");
        System.out.println("1 Games");
        System.out.println("2 Users");
        System.out.println("3 GameAccessors");
        System.out.println("4 Profile");
        System.out.println("5 Report GameProblem");
        System.out.println("6 Back");

        Game game = null;

        switch (ScannerWrapper.getInstance().nextInt()) {
            case 1 -> manageGameMenu(admin);
            case 2 -> manageUserMenu(admin);
            case 3 -> {
                try {
                    Seller seller = sellerMenu.enterSeller();
                    sellerMenu.manageGameAccessoryMenu(admin,seller);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    adminOption(admin);
                }
            }
            case 4 -> {
                System.out.println("username : " + admin.userName + "   pass : " + admin.pass);
                adminOption(admin);
            }
            case 5 -> {
                ManageDeveloper.reportProblem();
                adminOption(admin);
            }
            default -> {
                admins.clear();
                adminUserMenu();
            }

        }
    }



    public static void manageUserMenu(Admin admin) {
        System.out.println("*********************");
        System.out.println("1 see User");
        System.out.println("2 delete User");
        System.out.println("3 change User");
        System.out.println("4 find user");
        System.out.println("5 back");
        int arrayElement;

        switch (ScannerWrapper.getInstance().nextInt()) {
            case 1 -> {
                ManageUser.printAllUserName();
                manageUserMenu(admin);
            }
            case 2 -> {
                System.out.println("please choice   Back = -1");
                ManageUser.printAllUserName();
                arrayElement = ScannerWrapper.getInstance().nextInt() - 1;
                if (arrayElement == -2)
                    manageUserMenu(admin);
                ManageUser.deleteUser(ManageUser.users.get(arrayElement));
                manageUserMenu(admin);
            }
            case 3 -> {
                System.out.println("please choice Back = -1");
                ManageUser.printAllUserName();
                arrayElement = ScannerWrapper.getInstance().nextInt() - 1;
                if (arrayElement == -2)
                    manageUserMenu(admin);
                ManageUser.changeUser(ManageUser.users.get(arrayElement));
                manageUserMenu(admin);
            }
            case 4 -> {
                System.out.println("please Enter String");
                String search = ScannerWrapper.getInstance().next();
                ManageUser.findUser(search);
                manageUserMenu(admin);
            }
            default -> adminOption(admin);
        }
    }


}