package com.Zacke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final private HashMap<String, String> myNamnsdagarMap = new HashMap<>();
    private static final String NAMNSDAGAR_TXT = "namnsdagar.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        prepareNamnsdagMap();
        while (true) {
            System.out.println("Vill du kolla någons namnsdag eller kolla en speciell dag?");
            System.out.println("Skriv dag eller namn");
            String namnEllerDagInput = scanner.next().toLowerCase();
            if (namnEllerDagInput.equals("dag")) {
                checkNameFromDay(namnEllerDagInput);
            }
            if (namnEllerDagInput.equals("namn")) {
                checkDayFromName(namnEllerDagInput);
            }
        }
    }

    private static void checkDayFromName(String namnEllerDagInput) {
        System.out.println("Du valde " + namnEllerDagInput);
        System.out.println("Vilket namn?");
        Scanner namnInput = new Scanner(System.in);
        String namn = namnInput.nextLine().toLowerCase();
        String datum = checkName(namn);
        System.out.println(datum);
    }

    private static void checkNameFromDay(String namnEllerDagInput) {
        System.out.println("Du valde " + namnEllerDagInput);
        System.out.println("Vilken dag?");
        Scanner dagInput = new Scanner(System.in);
        String dag = dagInput.nextLine().toLowerCase();
        System.out.println(myNamnsdagarMap.get(dag));
        Main.main(null);
    }

    private static void prepareNamnsdagMap() {
        List<String> namnsdagar = null;

        try {
            namnsdagar = Files.readAllLines(Paths.get(NAMNSDAGAR_TXT));

        } catch (IOException e) {
            System.out.println("Error: kunde inte läsa från filen " + NAMNSDAGAR_TXT);
            System.exit(1);
        }
        for(String rad:namnsdagar){

            String[] a= rad.split("\t");
            //System.out.println(a[0]+" - "+a[1]);
            myNamnsdagarMap.put(a[0] , a[1]);
        }
    }

    private static String checkName(String namn) {
        for (String key:myNamnsdagarMap.keySet()) {
            if(myNamnsdagarMap.get(key).toLowerCase().contains(namn)) {
                return key;
            }
        }
        return null;
    }
}