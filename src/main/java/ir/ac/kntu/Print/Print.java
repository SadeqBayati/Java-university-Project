package ir.ac.kntu.Print;

import ir.ac.kntu.store.Game;
import ir.ac.kntu.Scan.ScannerWrapper;
import ir.ac.kntu.store.Salable;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.lang.System.*;

public class Print {

    public static void printArrayGame(ArrayList<Game> arrayList, String extra) {
        AtomicInteger number = new AtomicInteger(1);

        int lineNumber = 0;
        boolean exit = false;
        boolean getPermission = false;

        for (Game value : arrayList) {
            if ((lineNumber > 20) && !getPermission) {
                out.println("Can you see the continuation of the content?  1 = yes   2 = no");
                if (ScannerWrapper.getInstance().nextInt() != 1) exit = true;
                getPermission = true;
            }

            if (exit) break;

            String stringPrint = number.getAndIncrement() + " " + value.getName() + " " + extra;

            for (int j = 0; j < stringPrint.length() + 2; j++) out.print("*");

            out.println("\n" + "*" + stringPrint + "*");

            for (int j = 0; j < stringPrint.length() + 2; j++) out.print("*");

            lineNumber += 3;

            out.println();
        }
    }


    public static void printArrayGame(ArrayList<String> arrayList) {
        AtomicInteger number = new AtomicInteger(0);

        int lineNumber = 0;
        boolean exit = false;
        boolean getPermission = false;

        for (String value : arrayList) {
            if ((lineNumber > 20) && !getPermission) {
                out.println("Can you see the continuation of the content?  1 = yes   2 = no");
                if (ScannerWrapper.getInstance().nextInt() != 1) {
                    exit = true;
                }
                getPermission = true;

            }

            if (exit) break;

            for (int j = 0; j < value.length() + 2; j++) out.print("*");

            out.println("\n" + "*" + value + "*");

            for (int j = 0; j < value.length() + 2; j++) out.print("*");
            lineNumber += 3;

            out.println();
        }
    }

    public static void printArrayGameAccessories(ArrayList<String> arrayList) {
        AtomicInteger number = new AtomicInteger(1);
        int lineNumber = 0;
        boolean exit = false;
        boolean getPermission = false;

        for (String value : arrayList) {
            if ((lineNumber > 20) && !getPermission) {
                out.println("Can you see the continuation of the content?  1 = yes   2 = no");
                if (ScannerWrapper.getInstance().nextInt() != 1) {
                    exit = true;
                }
                getPermission = true;

            }

            if (exit) break;

            for (int j = 0; j < value.length() + 2 + String.valueOf(number.get()).length(); j++) out.print("-");

            out.println("\n" + "|"+number.getAndIncrement() + value + "|");

            for (int j = 0; j < value.length() + 2 + String.valueOf(number.get()).length(); j++) out.print("-");


            lineNumber += 3;

            out.println();
        }
    }


    public static void printArrayOfString(ArrayList<String> arrayList, String extra) {
        AtomicInteger number = new AtomicInteger(1);
        arrayList.forEach(s -> out.println(number.getAndIncrement()+ " " + s + " " + extra));
    }


    public static void printSalable(Predicate<Salable> predicate){
        AtomicInteger number = new AtomicInteger(0);
        int lineNumber = 0;
        boolean exit = false;
        boolean getPermission = false;
        ArrayList<String> arrayList = Salable.getArrayList().stream().filter(predicate).map(Salable::getName).collect(Collectors.toCollection(ArrayList::new));
        for (int i = 0; i < arrayList.size() ; i++){
            if ((lineNumber > 20) && !getPermission){
                out.println("Can you see the continuation of the content?  1 = yes   2 = no");
                if (ScannerWrapper.getInstance().nextInt() != 1) {
                    exit = true;
                }
                getPermission = true;

            }
            if (exit) break;


            if (Salable.getArrayList().stream().filter(predicate).toList().get(i).getClass() == Game.class) {
                for (int j = 0; j < arrayList.get(i).length() + 2; j++) out.print("*");
                out.println("\n" + "*" + arrayList.get(i) + "*");
                for (int j = 0; j < arrayList.get(i).length() + 2; j++) out.print("*");

            } else{
                for (int j = 0; j < arrayList.get(i).length() + 2; j++) out.print("-");

                out.println("\n" + "|" + arrayList.get(i) + "|");

                for (int j = 0; j < arrayList.get(i).length() + 2; j++) out.print("-");

            }
            lineNumber += 3;
            out.println();
        }
    }

}
