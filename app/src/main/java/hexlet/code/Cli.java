package hexlet.code;

import java.util.Scanner;

public class Cli {

    public static void greeting(Scanner in) {
        System.out.print("Welcome to the Brain Games!\nMay I have your name? ");
        String name = in.next();
        System.out.println("Hello, " + name + "!");
    }

}
