package hexlet.code;

import hexlet.code.games.*;

import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            // Выбор игры
            int game = getGame(in);
            System.out.println("");

            // Переменная для имени
            String name = "";


            // Запуск игры
            switch (game) {
                case 1:
                    GameGreeting.run(in);
                    break;
                case 2:
                    // Приветствие
                    name = GameGreeting.run(in);
                    // Игра
                    GameEven.run(in, name);
                    break;
                case 3:
                    // Приветствие
                    name = GameGreeting.run(in);
                    // Игра
                    GameCalc.run(in, name);
                    break;
                case 4:
                    // Приветствие
                    name = GameGreeting.run(in);
                    // Игра
                    GameGcd.run(in, name);
                    break;
                case 5:
                    // Приветствие
                    name = GameGreeting.run(in);
                    // Игра
                    GameProgression.run(in, name);
                    break;
                case 6:
                    // Приветствие
                    name = GameGreeting.run(in);
                    // Игра
                    GamePrime.run(in, name);
                    break;
            }

            // Закрываем сканнер
            in.close();

        } catch (RuntimeException e) {
            System.out.println("\nВы ввели некорректное значение!");
            // Закрываем сканнер
            in.close();
        }
    }

    // Функция возвращает номер выбранной игры
    private static int getGame(Scanner in) {
        StringBuilder builder = new StringBuilder("Please enter the game number and press Enter.\n");
        builder.append("1 - Greet\n");
        builder.append("2 - Even\n");
        builder.append("3 - Calc\n");
        builder.append("4 - GCD\n");
        builder.append("5 - Progression\n");
        builder.append("6 - Prime\n");
        builder.append("0 - Exit");
        System.out.println(builder.toString());
        System.out.print("Your choice: ");
        int result = in.nextInt();
        return result;
    }

    /*private static void go() {
        int code = 0;
        RunnableApp game = null;

        Cli cli = new Cli(new Scanner(System.in));

        try {
            game = GameChoise.getGame(1);
            game.run(cli);

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
    }*/

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
