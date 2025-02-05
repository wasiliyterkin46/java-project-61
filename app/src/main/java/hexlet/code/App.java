package hexlet.code;

import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        go();
    }

    private static void go() {
        int code = 0;
        RunnableApp game = null;

        Cli cli = new Cli(new Scanner(System.in));

        try {
            code = GameChoise.run(cli);
            if (code != 0) {
                game = GameChoise.getGame(code);
                System.out.println("");
                // Запускаем выбранную игру
                game.run(cli);
            }
        } catch (RuntimeException e) {
            System.out.println("\nВы ввели некорректное значение!");
        }


        cli.closeScanner();
    }

    /*private static void go2() {
        int code = 0;
        RunnableApp game = null;

        Cli cli = new Cli(new Scanner(System.in));
        do {
            try {
                code = GameChoise.run(cli);
                if (code == 0) {
                    continue;
                }
                game = GameChoise.getGame(code);
                System.out.println("");
                // Запускаем выбранную игру
                game.run(cli);
                System.out.println("");
            } catch (RuntimeException e) {
                System.out.println("\nВы ввели некорректное значение!");
                break;
            }

        } while (code != 0);

        cli.closeScanner();
        System.out.println("\nСпасибо, что выбрали наши игры! До скорой встречи.");
    }*/

}
