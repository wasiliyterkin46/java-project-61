package hexlet.code.games;

import java.util.Scanner;

public class GameEven {
    // Диапазон генерируемых чисел
    private static final int MAX_NUM = 100;
    // Хранится правильный ответ
    private static String correctAnswer = "";

    // Возвращает правила
    public static String getRules() {
        return "Answer 'yes' if the number is even, otherwise answer 'no'.";
    }

    // Возвращает текст вопроса
    public static String getQuestion() {
        int number = (int) (Math.random() * MAX_NUM);
        correctAnswer = number % 2 == 0 ? "yes" : "no";
        return String.valueOf(number);
    }

    // Возвращает правильный ответ.
    public static String getCorrectAnswer() {
        return correctAnswer;
    }

}
