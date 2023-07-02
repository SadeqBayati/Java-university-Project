package ir.ac.kntu.Menu;

import ir.ac.kntu.Print.Print;
import ir.ac.kntu.Scan.ScannerWrapper;
import ir.ac.kntu.manage.ManageDeveloper;
import ir.ac.kntu.manage.ManageGameAccessory;
import ir.ac.kntu.manage.ManageSeller;
import ir.ac.kntu.presen.Admin;
import ir.ac.kntu.presen.Seller;
import ir.ac.kntu.presen.User;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SellerMenu {

    public Seller enterSeller() throws Exception {
        ManageSeller manageSeller = new ManageSeller();
        Seller seller = null;
        System.out.println("please Choose one  if you want to creat new Enter 0     -1 = back");

        Print.printArrayOfString(ManageSeller.getSellerArrayList().stream().map(Seller::getSellerName).collect(Collectors.toCollection(ArrayList::new)),"");
        int select = ScannerWrapper.getInstance().nextInt();
        if (select == 0)
            seller = manageSeller.read();
        else if (select > 0 && select <= ManageSeller.getSellerArrayList().size())
            seller = ManageSeller.getSellerArrayList().get(select-1);
        else throw new Exception("ورودی اشتباه");
        return seller;
    }
    public void sellerOption(Seller seller){
        System.out.println("*********************");
        System.out.println("1 see GameAccessory");
        System.out.println("2 delete GameAccessory");
        System.out.println("3 change GameAccessory");
        System.out.println("4 find GameAccessory");
        System.out.println("5 add GameAccessory");
        System.out.println("6 back");

        switch (ScannerWrapper.getInstance().nextInt()) {
            case 1 -> {
                ManageGameAccessory.printAccessoriesOfSeller(seller);
                sellerOption(seller);
            }
            case 2 -> {
                ManageGameAccessory.deleteGameAccessory(seller);
                sellerOption(seller);
            }
            case 3 -> {
                ManageGameAccessory.changeGameAccessory(seller);
                sellerOption(seller);
            }
            case 4 -> {
                ManageGameAccessory.printAccessoriesWithSearch(seller);
                sellerOption(seller);
            }
            case 5 -> {
                ManageGameAccessory.createGameAccessory(seller);
                sellerOption(seller);
            }
            default -> Menu.adminUserMenu();
        }
    }
    public void manageGameAccessoryMenu(Admin admin,Seller seller) {

        System.out.println("*********************");
        System.out.println("1 see GameAccessory");
        System.out.println("2 delete GameAccessory");
        System.out.println("3 change GameAccessory");
        System.out.println("4 find GameAccessory");
        System.out.println("5 add GameAccessory");
        System.out.println("6 back");

        switch (ScannerWrapper.getInstance().nextInt()) {
            case 1 -> {
                ManageGameAccessory.printAccessoriesOfSeller(seller);
                manageGameAccessoryMenu(admin,seller);
            }
            case 2 -> {
                ManageGameAccessory.deleteGameAccessory(seller);
                manageGameAccessoryMenu(admin,seller);
            }
            case 3 -> {
                ManageGameAccessory.changeGameAccessory(seller);
                manageGameAccessoryMenu(admin,seller);
            }
            case 4 -> {
                ManageGameAccessory.printAccessoriesWithSearch(seller);
                manageGameAccessoryMenu(admin,seller);
            }
            case 5 -> {
                ManageGameAccessory.createGameAccessory(seller);
                manageGameAccessoryMenu(admin,seller);
            }
            default -> Menu.adminOption(admin);
        }
    }


}
