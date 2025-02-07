package hexlet.code.games;

import java.util.Scanner;

public class GameGcd {
    // Количество побед, до которых продолжается игра
    private static final int COUNT_WIN = 3;
    // Границы НОД
    private static final int NUM_MIN = 2;
    private static final int NUM_MAX = 30;
    // Сложность
    private static final int LIGHT_COMPLEXITY = 5;
    private static final int HIGH_COMPLEXITY = 10;

    public static void run(Scanner in, String name) {

        // Показываем правила
        System.out.println("Find the greatest common divisor of given numbers.");
        // Вспомогательные переменные
        int countCorrect = 0; // Количество правильных ответов
        boolean play = true; // Признак продолжения игры
        int answer = 0; // Хранение ответа
        int num1;
        int num2;
        int gcd;

        // Игра
        do {
            // Получаем НОД
            gcd = getRandomNumber(NUM_MIN, NUM_MAX);
            // Если число двузначное, понижаем сложность
            int curComplexity = (int) gcd / 10 > 0 ? LIGHT_COMPLEXITY : HIGH_COMPLEXITY;
            // Заполняем массив чисел
            int[] space = getSpace(gcd, curComplexity);
            // Подбираем числа
            num1 = space[getRandomNumber(0, curComplexity - 1)];
            do {
                num2 = space[getRandomNumber(0, curComplexity - 1)];
            } while (num1 == num2);

            // Задаем вопрос
            System.out.println("Question: " + num1 + " " + num2);
            // Получаем ответ
            System.out.print("Your answer: ");
            answer = in.nextInt();
            // Обрабатываем результат
            if (answer == gcd) {
                System.out.println("Correct!");
                countCorrect++;
            } else {
                System.out.println("'" + answer + "' is wrong answer ;(. Correct answer was '" + gcd + "'.");
                play = false;
            }
        } while (play && countCorrect < COUNT_WIN);

        // Завершаем игру
        if (countCorrect == COUNT_WIN) {
            System.out.println("Congratulations, " + name + "!");
        } else {
            System.out.println("Let's try again, " + name + "!");
        }
    }

    // Возвращает случайное число в заданном диапазоне
    private static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max + 1 - min) + min);
    }

    // Возвращает заполненный массив чисел c числами, у которых общий НОД
    private static int[] getSpace(int gcd, int complexity) {
        int[] result = new int[complexity];
        result[0] = gcd;
        int next = 0;
        int countFail = 0;

        for (int x = 1; x < complexity; x++) {
            next = result[x - 1] + gcd;
            do {
                countFail = x - 1;
                for (int y = 1; y < x; y++) {
                    if (next % result[y] == 0) {
                        next += gcd;
                    }  else {
                        countFail--;
                    }
                }
            } while (countFail > 0);
            result[x] = next;
        }

        return result;
    }
}
