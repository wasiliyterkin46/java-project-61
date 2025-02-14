package hexlet.code;

import hexlet.code.games.GameCalc;
import hexlet.code.games.GameEven;
import hexlet.code.games.GameGcd;
import hexlet.code.games.GameGreeting;
import hexlet.code.games.GamePrime;
import hexlet.code.games.GameProgression;

import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;


public class App {

    private static String nameUser;
    private static final Scanner in = new Scanner(System.in);
    private static final int COUNT_WIN = 3;

    public static void main(String[] args) {
        try {
            // Выбор игры
            String game = getGame(in);
            // Запуск игры
            Boolean resultGame = prepareGame(game);
            // Формируем сообщение о результатах игры
            closeGame(resultGame);
            // Закрываем сканнер
            in.close();

        } catch (RuntimeException e) {
            System.out.println("\nВы ввели некорректное значение!");
            // Закрываем сканнер
            in.close();
        }
    }

    // Предлагаем меню игр и возвращаем номер выбранной игры
    private static String getGame(Scanner in) {
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
        String result = in.next();
        System.out.println("");
        return result;
    }
    // Подготовка игры
    private static Boolean prepareGame(String game) {
        if (game.equals("1")) {
            GameGreeting.run(in);
            return null;
        } else {
            switch (game) {
                case "2":
                    return playGame(GameEven::getRules, GameEven::getQuestion, GameEven::getCorrectAnswer);
                case "3":
                    return playGame(GameCalc::getRules, GameCalc::getQuestion, GameCalc::getCorrectAnswer);
                case "4":
                    return playGame(GameGcd::getRules, GameGcd::getQuestion, GameGcd::getCorrectAnswer);
                case "5":
                    return playGame(GameProgression::getRules, GameProgression::getQuestion,
                            GameProgression::getCorrectAnswer);
                case "6":
                    return playGame(GamePrime::getRules, GamePrime::getQuestion, GamePrime::getCorrectAnswer);
                default:
                    return null;
            }
        }

    }
    // Игра
    private static boolean playGame(Supplier<String> rulesGetter,
                                    Supplier<String> questGetter,
                                    Supplier<String> correctAnswerGetter) {

        nameUser = GameGreeting.run(in);

        System.out.println(rulesGetter.get());
        int curWin = 0;
        while (curWin < COUNT_WIN) {
            System.out.print("Question: " + questGetter.get() + "\nYour answer: ");
            String answer = in.next();

            String correctAnswer = correctAnswerGetter.get();

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
    private static void closeGame(Boolean win) {
        if (!Objects.isNull(win)) {
            String result = win ? "Congratulations, " + nameUser + "!" : ("Let's try again, " + nameUser + "!");
            System.out.println(result);
        }
    }

}
