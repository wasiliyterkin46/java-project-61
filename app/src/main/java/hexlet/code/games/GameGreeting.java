package hexlet.code.games;

import java.util.Scanner;

public class GameGreeting {
    private String name = "";

    public static String run(Scanner in) {
        System.out.print("Welcome to the Brain Games!\nMay I have your name? ");
        String name = in.next();
        System.out.println("Hello, " + name + "!");
        return name;
    }

}
