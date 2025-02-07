package hexlet.code.games;

import java.util.Scanner;

public class GameEven {
    // Количество побед, до которых продолжается игра. Нужно для гибкости, если будет необходимость играть дольше/меньше
    private static final int COUNT_WIN = 3;
    // Диапазон генерируемых чисел
    private static final int MAX_NUM = 100;

    public static void run(Scanner in, String name) {

        // Показываем правила
        System.out.println("Answer 'yes' if the number is even, otherwise answer 'no'.");

        // Определяем переменные
        boolean play = true;
        boolean even = false;
        int number = 0;
        String answer = "";
        String result = "";
        int countCorrect = 0;

        // Игра
        do {
            number = (int) (Math.random() * MAX_NUM);
            even = number % 2 == 0;
            System.out.println("Question: " + number);
            System.out.print("Your answer: ");
            answer = in.next().toLowerCase();
//            answer = cli.getAnswerStringOnLine("Your answer: ").toLowerCase();

            // Проверяем ответ
            if (!answer.equals("yes") && !answer.equals("no")) {
                result = "'" + answer + "' - is wrong answer ;(. " +
                        "Must be 'yes' if the number is even, otherwise answer 'no'.";
                play = false;
                continue;
            }
            if ((answer.equals("yes") && even) || (answer.equals("no") && !even)) {
                System.out.println("Correct!");
                countCorrect++;
            } else {
                result = answer.equals("yes") ? "'yes' is wrong answer ;(. Correct answer was 'no'." :
                        "'no' is wrong answer ;(. Correct answer was 'yes'.";
                play = false;
            }
        } while (play && countCorrect < COUNT_WIN);

        // Завершаем игру
        if (countCorrect == COUNT_WIN) {
            System.out.println("Congratulations, " + name + "!");
        } else {
            System.out.println(result);
            System.out.println("Let's try again, " + name + "!");
        }
    }

}
