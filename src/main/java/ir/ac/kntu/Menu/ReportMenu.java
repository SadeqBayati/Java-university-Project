package ir.ac.kntu.Menu;

import ir.ac.kntu.Scan.ScannerWrapper;
import ir.ac.kntu.manage.ManageGameAccessory;
import ir.ac.kntu.manage.ManageUser;
import ir.ac.kntu.presen.User;
import ir.ac.kntu.store.GameAccessory;
import ir.ac.kntu.store.Salable;

import java.util.concurrent.atomic.AtomicReference;

public class ReportMenu {
    public void reportMenu(){
        System.out.println("******************");
        System.out.println("1 sum of price");
        System.out.println("2 Best selling product");
        System.out.println("3 The most active user");
        System.out.println("4 back");

        switch (ScannerWrapper.getInstance().nextInt())
        {
            case 1 -> {
                AtomicReference<Float> sum = new AtomicReference<>(0f);
                Salable.getArrayList().stream().forEach(salable -> sum.updateAndGet(v -> v + salable.getPrice()));
                System.out.println("sum of price is" + sum.get());
                reportMenu();
            }
            case 2 -> {
                int[] NSelling = {-1};
                AtomicReference<GameAccessory> gameAccessory1 =new AtomicReference<>(null);
                ManageGameAccessory.getAccessories().forEach(gameAccessory -> {
                    if (gameAccessory.getNumberOfSelling() > NSelling[0]) {
                        NSelling[0] = gameAccessory.getNumberOfSelling();
                        gameAccessory1.set(gameAccessory);
                    }
                });
                if (gameAccessory1.get()==null) {
                    System.out.println("شما محصول پر فروش ندارید");
                    reportMenu();
                }
                reportMenu();
            }
            case 3 -> {
                AtomicReference<User> user = new AtomicReference<>(null);
                final int[] score = {-1};
                ManageUser.getUsers().forEach(user1 ->{
                    if (user1.getScore() > score[0]){
                        score[0] = user1.getScore();
                        user.set(user1);
                    }
                } );
                if (user.get() == null) {
                    System.out.println("شما کاربر فعال ندارید");
                    reportMenu();
                }
                System.out.println("The most active user is "+user.get().toString());
                reportMenu();
            }
            default -> Menu.adminUserMenu();
        }
    }
}
