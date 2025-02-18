package hexlet.code;

import hexlet.code.games.GameCalc;
import hexlet.code.games.GameEven;
import hexlet.code.games.GameGcd;
import hexlet.code.games.GameGreeting;
import hexlet.code.games.GamePrime;
import hexlet.code.games.GameProgression;

import java.util.Objects;
import java.util.Scanner;
import java.util.function.Supplier;

public final class Engine {
    private static String nameUser;
    private static final Scanner IN = new Scanner(System.in);
    private static final int COUNT_WIN = 3;

    // Предлагаем меню игр и возвращаем номер выбранной игры
    public static String getGame() {
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
        String result = IN.next();
        System.out.println("");
        return result;
    }
    // Подготовка игры
    public static Boolean prepareGame(String game) {
        if (game.equals("1")) {
            GameGreeting.run(IN);
            return null;
        } else {
            switch (game) {
                case "2":
                    return playGame(GameEven::getRules, GameEven::getQuestion);
                case "3":
                    return playGame(GameCalc::getRules, GameCalc::getQuestion);
                case "4":
                    return playGame(GameGcd::getRules, GameGcd::getQuestion);
                case "5":
                    return playGame(GameProgression::getRules, GameProgression::getQuestion);
                case "6":
                    return playGame(GamePrime::getRules, GamePrime::getQuestion);
                default:
                    return null;
            }
        }

    }
    // Игра
    private static boolean playGame(Supplier<String> rulesGetter,
                                    Supplier<String[]> questGetter) {

        nameUser = GameGreeting.run(IN);

        System.out.println(rulesGetter.get());
        int curWin = 0;
        while (curWin < COUNT_WIN) {

            String[] questionAndAnswer = questGetter.get();
            String question = questionAndAnswer[0];
            String correctAnswer = questionAndAnswer[1];

            System.out.print("Question: " + question + "\nYour answer: ");
            String answer = IN.next();

            if (correctAnswer.equals(answer)) {
                System.out.println("Correct!");
                curWin++;
            } else {
                System.out.println("'" + answer +  "' is wrong answer ;(. Correct answer was '"
                        + correctAnswer + "'.");
                return false;
            }
        }

        return true;
    }

    // Завершение игры
    public static void closeGame(Boolean win) {
        if (!Objects.isNull(win)) {
            String result = win ? "Congratulations, " + nameUser + "!" : ("Let's try again, " + nameUser + "!");
            System.out.println(result);
        }
    }
}
