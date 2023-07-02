package ir.ac.kntu.presen;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Seller {

    String sellerName, pass;

    Map<User,String> userStringMap = new HashMap<>();
    public Seller(String sellerName,String pass){
        this.sellerName = sellerName;
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public String getSellerName() {
        return sellerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(sellerName, seller.sellerName) && Objects.equals(pass, seller.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sellerName, pass);
    }

    @Override
    public String toString() {
        return "Seller{" +
                "sellerName='" + sellerName + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
    public void checkInbox(){
        for (Map.Entry<User,String> map : userStringMap.entrySet())
            System.out.println("userName  :  " + map.getKey().getUserName() + "  seid   : " + map.getValue());
    }

    public Map<User, String> getUserStringMap() {
        return userStringMap;
    }
}
