/*
    Made by Zacke (with help from others)
    Check out my Github: https://github.com/Zacke04
 */

package com.Zacke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final private HashMap<String, String> myNamnsdagarMap = new HashMap<>(); //Creates a hashmap to store all namnsdagar
    private static final String NAMNSDAGAR_TXT = "namnsdagar.txt";  //Defines the text file

    public static void main(String[] args) {
        prepareNamnsdagMap();   //Load in all namnsdagar into a hashmap
        chooseDayOrName();      //Waits for a user input, "dag" or "namn".
        return;
    }

    private static void chooseDayOrName() {
        Scanner scanner = new Scanner(System.in);   //Start scanning the terminal for user input
        while (true) {
            System.out.println("Vill du kolla någons namnsdag eller kolla en speciell dag?");
            System.out.println("Skriv dag, namn eller lista");
            String namnEllerDagInput = scanner.next().toLowerCase();    //The user input get into a string in lowercase.
            if (namnEllerDagInput.equals("dag")) {
                checkNameFromDay(namnEllerDagInput); //Waits for the user to input a date so the program can search for the name in the hashmap.
            }
            if (namnEllerDagInput.equals("namn")) {
                checkDayFromName(namnEllerDagInput);    //Waits for the user to input a name so the program can search for the date in the hashmap.
            }
            if (namnEllerDagInput.equals("lista")) {
                for (String i : myNamnsdagarMap.keySet()) {
                    System.out.println("Datum: " + i + " Namn: " + myNamnsdagarMap.get(i));
                }
            }
            if (namnEllerDagInput.equals("exit")) {
                System.exit(0);     //Exit the program
            }
        }
    }

    private static void checkDayFromName(String namnEllerDagInput) {
        System.out.println("Du valde " + namnEllerDagInput);
        System.out.println("Vilket namn?");
        Scanner namnInput = new Scanner(System.in); //Start scanning for user input
        String namn = namnInput.nextLine().toLowerCase();   //Make user input to lowercase and put it in a string
        String datum = checkName(namn);     //The program takes the input and send it to another function to get the name from the hashmap
        System.out.println(datum);          //The function sends it back here and outputs it
    }

    private static void checkNameFromDay(String namnEllerDagInput) {
        System.out.println("Du valde " + namnEllerDagInput);
        System.out.println("Vilken dag?");
        Scanner dagInput = new Scanner(System.in);  //Start scanning for user input
        String dag = dagInput.nextLine().toLowerCase();
        System.out.println(myNamnsdagarMap.get(dag));
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