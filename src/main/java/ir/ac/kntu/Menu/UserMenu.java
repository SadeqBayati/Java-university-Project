package ir.ac.kntu.Menu;

import ir.ac.kntu.Enum.GameType;
import ir.ac.kntu.Scan.ScannerWrapper;
import ir.ac.kntu.manage.*;
import ir.ac.kntu.presen.Developer;
import ir.ac.kntu.presen.User;
import ir.ac.kntu.store.Game;
import ir.ac.kntu.store.GameAccessory;
import ir.ac.kntu.manage.ManageLibrary;
import ir.ac.kntu.store.Monitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

import static ir.ac.kntu.Menu.Menu.userTime;
import static java.lang.System.err;
import static java.lang.System.out;


public class UserMenu {
    public void enterUser() {
        System.out.println("*********************");
        System.out.println("1 Sign up");
        System.out.println("2 Sign in");
        System.out.println("3 Back");
        switch (ScannerWrapper.getInstance().nextInt()) {
            case 1 -> signUp();
            case 2 -> singIn();
            default -> {
                Menu.adminUserMenu();
                userTime.stop();
            }
        }
    }

    public void signUp() {
        User user = User.read();
        ManageUser.users.add(user);
        userTime.start(new TimerTask() {
            @Override
            public void run() {
                user.score++;
            }
        }, 5000L);
        userOption(user);
    }

    public void singIn() {
        System.out.println("please Enter UserName");
        String userName = ScannerWrapper.getInstance().next();
        System.out.println("please Enter Password");
        String pass = ScannerWrapper.getInstance().next();

        User user = ManageUser.findUserWithUserNameAndPass(userName, pass);
        while (user == null) {
            System.out.println("please Enter Correctly");
            System.out.println("please Enter UserName");
            userName = ScannerWrapper.getInstance().next();
            System.out.println("please Enter Password");
            pass = ScannerWrapper.getInstance().next();
            user = ManageUser.findUserWithUserNameAndPass(userName, pass);
        }

        User finalUser = user;
        userTime.start(new TimerTask() {
            @Override
            public void run() {
                finalUser.score++;
            }
        }, 5000L);
        userOption(user);
    }

    public void userOption(User user) {
        System.out.println("*********************");
        System.out.println("1 Freind");
        System.out.println("2 ManageLibrary");
        System.out.println("3 Game Store");
        System.out.println("4 GameAccessory Store");
        System.out.println("5 Profile");
        System.out.println("6 Back");
        switch (ScannerWrapper.getInstance().nextInt()) {
            case 1 -> freindMenu(user);
            case 2 -> libraryMenu(user);
            case 3 -> storeGameMenu(user);
            case 4 -> gameAccessoryStoreMenu(user);
            case 5 -> profileMenu(user);
            case 6 -> enterUser();
            default -> userOption(user);
        }
    }

    public void gameAccessoryStoreMenu(User user){
        System.out.println("*********************");
        System.out.println("1 See All GameAccessory");
        System.out.println("2 Search GameAccessory");
        System.out.println("3 Filter with price");
        System.out.println("4 Back");

        GameAccessory gameAccessory1 = null;
        int select = 0;


        switch (ScannerWrapper.getInstance().nextInt()){
            case 1 -> {
                ManageGameAccessory.printAccessories(gameAccessory -> true);
                out.println("  -1 = بازگشت         لطفا یک گزینه را انتخاب کنید");
                select = ScannerWrapper.getInstance().nextInt();
                if (select > 0 && select <= ManageGameAccessory.getAccessories().size()) {
                    gameAccessory1 = ManageGameAccessory.getAccessories().get(select - 1);
                    out.println(gameAccessory1);
                    out.println("1 = بله     آیا شما می خواهید این تجهیز را بخرید");
                    select = ScannerWrapper.getInstance().nextInt();
                    if (select == 1)
                        ManageGameAccessory.buyGameAccessory(user,gameAccessory1);
                    else gameAccessoryStoreMenu(user);
                }
                gameAccessoryStoreMenu(user);
            }
            case 2 -> {
                out.println("لطفا متن که می خواهید جست وجو کنید را وارد کنید");
                String search = ScannerWrapper.getInstance().next();
                out.println("بین کدام دو گروه می خواهید جست و جو کنید");
                out.println("   1 = monitor      2 = GameControl");
                Predicate<GameAccessory> predicate1;
                if (ScannerWrapper.getInstance().nextInt() == 1) {
                    predicate1 = gameAccessory -> gameAccessory.getClass() == Monitors.class;
                }else predicate1 = gameAccessory -> gameAccessory.getClass() != Monitors.class;

                Predicate<GameAccessory> predicate = gameAccessory -> gameAccessory.toString().contains(search);
                ManageGameAccessory.printAccessories(predicate,predicate1);
                out.println("  -1 = بازگشت         لطفا یک گزینه را انتخاب کنید");
                select = ScannerWrapper.getInstance().nextInt();

                if (select > 0 && select <= ManageGameAccessory.getAccessories().stream().filter(predicate).filter(predicate1).toList().size()) {
                    gameAccessory1 = ManageGameAccessory.getAccessories().stream().filter(predicate).filter(predicate1).toList().get(select - 1);
                    out.println(gameAccessory1);
                    out.println("1 = بله     آیا شما می خواهید این تجهیز را بخرید");
                    select = ScannerWrapper.getInstance().nextInt();
                    if (select == 1)
                        ManageGameAccessory.buyGameAccessory(user,gameAccessory1);
                    else gameAccessoryStoreMenu(user);
                }
                gameAccessoryStoreMenu(user);
            }
            case 3 -> {
                out.println("حداقل قیمت را وارد کنید");
                int min = ScannerWrapper.getInstance().nextInt();
                out.println("حداکثر قیمت را وارد کنید");
                int max = ScannerWrapper.getInstance().nextInt();
                Predicate<GameAccessory> predicate = gameAccessory -> gameAccessory.getPrice() > min && gameAccessory.getPrice() <max;
                ManageGameAccessory.printAccessories(predicate);
                if (select > 0 && select <= ManageGameAccessory.getAccessories().stream().filter(predicate).toList().size()) {
                    gameAccessory1 = ManageGameAccessory.getAccessories().stream().filter(predicate).toList().get(select - 1);
                    out.println(gameAccessory1);
                    out.println("1 = بله     آیا شما می خواهید این تجهیز را بخرید");
                    select = ScannerWrapper.getInstance().nextInt();
                    if (select == 1)
                        ManageGameAccessory.buyGameAccessory(user,gameAccessory1);
                    else gameAccessoryStoreMenu(user);
                }
                gameAccessoryStoreMenu(user);
            }
            default -> userOption(user);
        }
    }

    public void storeGameMenu(User user) {
        System.out.println("*********************");
        System.out.println("1 See All Game");
        System.out.println("2 Search Game");
        System.out.println("3 Filter with price");
        System.out.println("4 Back");

        int numberOfGame;

        switch (ScannerWrapper.getInstance().nextInt()) {
            case 1 -> {
                ManageGame.printGame(user);
                System.out.println("Please Select    Back = -1");
                numberOfGame = ScannerWrapper.getInstance().nextInt() - 1;
                if (numberOfGame == -2) storeGameMenu(user);
                System.out.println(ManageGame.games.get(numberOfGame).toString());
                System.out.println("Do you want to buy this game?  1 = yes  And  2 = no");
                if (ScannerWrapper.getInstance().nextInt() == 1)
                    ManageGame.buyGame(user, ManageGame.games.get(numberOfGame));
                storeGameMenu(user);
            }
            case 2 -> {
                System.out.println("Please Enter String");
                ArrayList<Game> searchedGame = ManageGame.searchGame(ScannerWrapper.getInstance().next());
                if (searchedGame.size() != 0) {

                    System.out.println("Please Enter Integer   -1 = Back");

                    numberOfGame = ScannerWrapper.getInstance().nextInt() - 1;

                    if (numberOfGame == -2) storeGameMenu(user);

                    System.out.println(searchedGame.get(numberOfGame).toString());
                    System.out.println("Do you want to buy this game?  1 = yes  And  2 = no");

                    if (ScannerWrapper.getInstance().nextInt() == 1)
                        ManageGame.buyGame(user, ManageGame.games.get(numberOfGame));

                } else System.out.println("Search has no Result");
                storeGameMenu(user);
            }
            case 3 -> {
                ManageGame.printByFilterWithPrice();
                storeGameMenu(user);
            }
            case 4 -> userOption(user);
        }

    }

    public void profileMenu(User user) {
        System.out.println("*********************");
        System.out.println("1 See Info");
        System.out.println("2 Change Info");
        System.out.println("3 Charge Wallet");
        System.out.println("4 Back");

        switch (ScannerWrapper.getInstance().nextInt()) {
            case 1 -> {
                ManageUser.printUser(user);
                profileMenu(user);
            }
            case 2 -> {
                ManageUser.changeUser(user);
                profileMenu(user);
            }
            case 3 -> {
                ManageUser.chargeWallet(user);
                profileMenu(user);
            }
            case 4 -> userOption(user);
            default -> profileMenu(user);
        }
    }

    public void freindMenu(User user) {

        System.out.println("*********************");
        System.out.println("1 See All FreindUserName");
        System.out.println("2 Find Friend");
        System.out.println("3 Send Request");
        System.out.println("4 Request");
        System.out.println("5 Back");
        int arrayElement;

        switch (ScannerWrapper.getInstance().nextInt()) {
            case 1 -> {
                user.seeAllFreind();
                freindMenu(user);
            }
            case 2 -> {
                System.out.println("Please Enter SearchString");
                user.findFreind(ScannerWrapper.getInstance().next());
                freindMenu(user);
            }
            case 3 -> {
                System.out.println("Pleas Choose    -1 = Back");
                ArrayList<User> userThatNotFreind = ManageUser.printAndReturnUserThatNotFreind(user);
                arrayElement = ScannerWrapper.getInstance().nextInt() - 1;
                if (arrayElement == -2)
                    freindMenu(user);
                userThatNotFreind.get(arrayElement).addRequest(user);
                freindMenu(user);
            }
            case 4 -> {
                user.printRequest();
                user.acceptRequest(user.getRequestFromFreind().get(ScannerWrapper.getInstance().nextInt() - 1));
                freindMenu(user);
            }
            default -> userOption(user);
        }
    }


    public void libraryMenu(User user) {
        System.out.println("*********************");
        System.out.println("1 See All Game");
        System.out.println("2 See All GameAccessory");
        System.out.println("3 Search Game");
        System.out.println("4 Search GameAccessory");
        System.out.println("5 Back");

        int numberOfGame;
        switch (ScannerWrapper.getInstance().nextInt()) {
            case 1 -> {
                ManageGame.printGameOfUser(user);
                System.out.println("Please Select One   Back = -1");
                numberOfGame = ScannerWrapper.getInstance().nextInt() - 1;
                if (numberOfGame == -2) libraryMenu(user);
                optionGameInLibraryMenu(user, user.getGames().get(numberOfGame));
            }
            case 2 ->{
                ManageGameAccessory.printAccessories(gameAccessory -> user.getGameAccessoryArrayList().contains(gameAccessory));
                out.println("-1 = بازگشت      تجهیز موزد نظر خود را انتخاب کنید");
                numberOfGame = ScannerWrapper.getInstance().nextInt();
                if (numberOfGame == -1) libraryMenu(user);
                if (numberOfGame > 0 && numberOfGame <= ManageGameAccessory.getAccessories().stream().filter(gameAccessory -> user.getGameAccessoryArrayList().contains(gameAccessory)).toList().size())
                    optionGameAccessoryInLibraryMenu(user,ManageGameAccessory.getAccessories().stream().filter(gameAccessory -> user.getGameAccessoryArrayList().contains(gameAccessory)).toList().get(numberOfGame-1));
                else libraryMenu(user);
            }
            case 3 -> {
                System.out.println("Please Enter String For Search");
                ArrayList<Game> searchedGame = ManageGame.printGameOfUser(user, ScannerWrapper.getInstance().next());
                System.out.println("Please Chose One");
                optionGameInLibraryMenu(user, searchedGame.get(ScannerWrapper.getInstance().nextInt() - 1));
            }
            case 4 -> {
                out.println("لطفا متن که می خواهید جست وجو کنید را وارد کنید");
                String search = ScannerWrapper.getInstance().next();
                ArrayList<GameAccessory> accessoryArrayList = ManageGameAccessory.printAccessoriesOfUser(user,search);
                out.println(" -1= بازگشت      لطفا گزینه مورد نظر خود را وارد کنید");
                numberOfGame = ScannerWrapper.getInstance().nextInt();
                if (numberOfGame <= accessoryArrayList.size() && numberOfGame > 0)
                    optionGameAccessoryInLibraryMenu(user,accessoryArrayList.get(numberOfGame-1));
                else libraryMenu(user);
            }
            default -> userOption(user);
        }
    }

    public void optionGameAccessoryInLibraryMenu(User user,GameAccessory gameAccessory){
        System.out.println("*********************");
        System.out.println("1 Community");
        System.out.println("2 Rating");
        System.out.println("3 Feedback");
        System.out.println("4 Back");

        int number;
        switch (ScannerWrapper.getInstance().nextInt()){
            case 1 -> {
                for (Map.Entry<User,String> map : gameAccessory.getCommunity().entrySet())
                    out.println("User :" + map.getKey().getUserName() + "  seid  :" +map.getValue());
                out.println(" -1 = بازگشت    1 = بله     آیا شما می خواهید نظر را وارد کنید");
                if (ScannerWrapper.getInstance().nextInt() != -1)
                    ManageLibrary.addGameAccessoryCommunity(user,gameAccessory);
                    else optionGameAccessoryInLibraryMenu(user,gameAccessory);
            }
            case 2 -> {
                System.out.println("the rate of this game is " + gameAccessory.getAverageRate());
                System.out.println("are you want to add Rate     1 = yes   2 = no  -1 = Back");
                number = ScannerWrapper.getInstance().nextInt();
                if (number == 1) ManageLibrary.addRateGameAccessory(user, gameAccessory);
                if (number == -1) libraryMenu(user);
                optionGameAccessoryInLibraryMenu(user, gameAccessory);
            }
            case 3 -> {
                sendGameAccessoryFeedback(user,gameAccessory);
                optionGameAccessoryInLibraryMenu(user,gameAccessory);
            }
            default -> libraryMenu(user);
        }
    }
    public void optionGameInLibraryMenu(User user, Game game) {
        System.out.println("\n you chose " + game.getName());
        System.out.println("*********************");
        System.out.println("1 Community");
        System.out.println("2 Rating");
        System.out.println("3 Feedback");
        System.out.println("4 Back");
        int number;

        switch (ScannerWrapper.getInstance().nextInt()) {
            case 1 -> {
                ManageLibrary.printCommunity(game);
                System.out.println("are you want to add Community     1 = yes   2 = no");
                if (ScannerWrapper.getInstance().nextInt() == 1) ManageLibrary.addGameCommunity(user, game);
                optionGameInLibraryMenu(user, game);
            }
            case 2 -> {
                System.out.println("the rate of this game is " + game.getRate());
                System.out.println("are you want to add Rate     1 = yes   2 = no  -1 = Back");
                number = ScannerWrapper.getInstance().nextInt();
                if (number == 1) ManageLibrary.addRateGame(user, game);
                if (number == -1) libraryMenu(user);
                optionGameInLibraryMenu(user, game);
            }
            case 3 -> sendGameFeedback(user, game);
            default -> libraryMenu(user);
        }
    }
    public void sendGameFeedback(User user, Game game) {
        String feedback;
        AtomicBoolean send = new AtomicBoolean(false);

        if (game.getGameType() != GameType.BETA) {
            out.println("این قسمت برای بازی های نسخه بتا است");
            optionGameInLibraryMenu(user, game);
        }

        out.println("لطفا بازخوردتان را وارد کنید");
        feedback = ScannerWrapper.getInstance().next();

        ManageDeveloper.developers.stream().filter(dev -> dev.getMyGame().contains(game)).map(Developer::getUserMapMap).forEach(userMapMap -> {
            Map<Game, String> gameStringMap = userMapMap.get(user);

            if (gameStringMap == null) {
                gameStringMap = new HashMap<>();
                gameStringMap.put(game, feedback);
            } else gameStringMap.put(game, feedback);

            userMapMap.put(user, gameStringMap);
            if (!send.get())
                out.println("بازخورد شما برای توسعه دهنده ارسال شد");
            send.set(true);
        });
        if (!send.get()) out.println("هیچ توسعه دهنده ای این بازی را ندارد");

        optionGameInLibraryMenu(user, game);
    }

    public void sendGameAccessoryFeedback(User user,GameAccessory gameAccessory){
        out.println("لطفا باز خورد خود را وارد کنید");
        String feedback = ScannerWrapper.getInstance().next();
        ManageSeller.getSellerArrayList().stream().filter( seller ->  gameAccessory.getSeller().equals(seller)).forEach(seller -> seller.getUserStringMap().put(user,feedback));
    }

}
