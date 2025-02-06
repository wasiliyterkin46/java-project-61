package hexlet.code;

import java.util.Scanner;

public class Cli {
    private final Scanner in;

    Cli(Scanner in) {
        this.in = in;
    }

    public static void greeting(Scanner in) {
        System.out.print("Welcome to the Brain Games!\nMay I have your name? ");
//        StringBuilder builder = new StringBuilder("Hello, ");
        String name = in.next();
        System.out.println("Hello, " + name + "!");
    }

    public void closeScanner() {
        this.in.close();
    }

//    public void sendMessage(String message) {
//        System.out.println(message);
//    }

    // Запрос текстового ответа в строке после вопроса
    public String getAnswerString(String question) {
        String answer;
        System.out.println(question);
        answer = this.in.next();
        return answer;
    }

    // Запрос текстового ответа в строке вопроса
    public String getAnswerStringOnLine(String question) {
        String answer;
        System.out.print(question + " ");
        answer = this.in.next();
        return answer;
    }

    // Запрос числового ответа в строке после вопроса
    public Integer getAnswerInt(String question) {
        Integer answer;
        System.out.println(question);
        answer = this.in.nextInt();
        return answer;
    }

    // Запрос числового ответа в строке после вопроса
    public Integer getAnswerIntOnLine(String question) {
        Integer answer;
        System.out.print(question + " ");
        answer = this.in.nextInt();
        return answer;
    }
}
