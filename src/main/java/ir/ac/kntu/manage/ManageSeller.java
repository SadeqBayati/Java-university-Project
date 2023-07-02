package ir.ac.kntu.manage;

import ir.ac.kntu.Scan.ScannerWrapper;
import ir.ac.kntu.presen.Seller;
import ir.ac.kntu.store.GameAccessory;

import java.util.ArrayList;

public class ManageSeller {
    static ArrayList<Seller> sellerArrayList = new ArrayList<>();

    public Seller read(){
        System.out.println("لطفا نام فروشنده را وارد نمایید");
        String sellerName = ScannerWrapper.getInstance().next();
        System.out.println("لطفا رمز را وارد کنید");
        String pass = ScannerWrapper.getInstance().next();
        Seller seller = new Seller(sellerName,pass);
        sellerArrayList.add(seller);
        return seller;
    }

    public static ArrayList<Seller> getSellerArrayList() {
        return new ArrayList<>(sellerArrayList);
    }

}
