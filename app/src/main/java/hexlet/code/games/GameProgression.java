package hexlet.code.games;

import java.util.Scanner;

public class GameProgression {
    // Переменные класса
    // Количество побед, до которых продолжается игра
    private static int countWin = 3;
    // Длина последовательности
    private static int minLen = 5;
    private static int maxLen = 10;
    // Диапазон шага прогрессии
    private static int  stepProgressionMax = 20;
    // Диапазон стартового числа
    private static int startNumMax = 20;

   public static void run(Scanner in, String name) {

       // Показываем правила
        System.out.println("What number is missing in the progression?");

        // Вспомогательные переменные
        int countCorrect = 0; // Количество правильных ответов
        boolean play = true; // Признак продолжения игры
        int answer = 0; // Хранение ответа
        int stepProgression = 0; // Шаг прогрессии
        int lenProg = 0; // Длина прогрессии
        int startNum = 0; // Начальное число прогрессии
        int hideElem = 0; // Индекс скрываемого элемента

        // Игра
        do {
            // Генерируем шаг прогрессии
            stepProgression = getRandomNumber(1, stepProgressionMax) * getSign();
            // Генерируем длину прогрессии
            lenProg = getRandomNumber(minLen, maxLen);
            // Генерируем начальное число прогрессии
            startNum = getRandomNumber(0, startNumMax) * getSign();
            // Генерируем прогрессию
            int[] progression = getProgression(startNum, lenProg, stepProgression);
            // Получаем индекс скрываемого элемента
            hideElem = getRandomNumber(0, lenProg - 1);

            //Задаем вопрос
            System.out.println("Question: " + getArrayToString(progression, hideElem));
            // Получаем ответ
            System.out.print("Your answer: ");
            answer = in.nextInt();

            // Обрабатываем результат
            if (answer == progression[hideElem]) {
                System.out.println("Correct!");
                countCorrect++;
            } else {
                System.out.println("'" + answer + "' is wrong answer ;(. Correct answer was '" + progression[hideElem] + "'.");
                play = false;
            }

        } while (play && countCorrect < countWin);

        // Завершаем игру
        if (countCorrect == countWin) {
            System.out.println("Congratulations, " + name + "!");
        } else {
            System.out.println("Let's try again, " + name + "!");
        }
    }

    // Возвращает прогрессию по заданным начальному числу, количеству элементов и шагу
    private static int[] getProgression(int startNum, int countElem, int step) {
        int[] result = new int[countElem];
        result[0] = startNum;
        for (int x = 1; x < countElem; x++) {
            result[x] = result[x - 1] + step;
        }

        return result;
    }

    // Возвращает случайное число в заданном диапазоне
    private static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max + 1 - min) + min);
    }

    // Возвращает рандомный множитель 1 или -1. Это надо, если хотим игру с отрицательными числами
    private static int getSign() {
        int result = (int) (Math.random() * 10);
        return result <= 5 ? (-1) : 1;
    }

    // Возвращает сроку элементов массива с разделителем пробел и пропуском указанного элемента
    private static String getArrayToString(int[] array, int index) {
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < array.length; x++) {
            builder.append(x == index ? ".." : array[x]);
            builder.append(" ");
        }
        return builder.toString();
    }
}
