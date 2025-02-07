package hexlet.code.games;

import java.util.Scanner;

public class GameCalc {
    // Количество побед, до которых продолжается игра
    private static final int COUNT_WIN = 3;
    private static final int NUM_MIN = 1;
    private static final int NUM_MAX = 30;
    private static char[] cycleOfOperations = {'+', '-', '*'};

    public static void run(Scanner in, String name) {

        // Показываем правила
        System.out.println("What is the result of the expression?");

        // Определяем переменные
        boolean play = true;
        int countCorrect = 0;
        int num1 = 0;
        int num2 = 0;
        int resultOper = 0; // Содержит результат операции
        String question = "";
        int answer = 0;

        // Игра
        do {
            // Генерируем числа
            num1 = getRandomNumber(NUM_MIN, NUM_MAX);
            num2 = getRandomNumber(NUM_MIN, NUM_MAX);
            // Получаем результат операции
            resultOper = getResultOper(num1, num2, countCorrect);
            // Генерируем текст вопроса
            question = getQuestionText(num1, num2, countCorrect);
            // Предлагаем вопрос и запрашиваем ответ
            System.out.println(question);
            System.out.print("Your answer: ");
            answer = in.nextInt();
            // Обрабатываем результат
            if (answer == resultOper) {
                System.out.println("Correct!");
                countCorrect++;
            } else {
                System.out.println("'" + answer + "' is wrong answer ;(. Correct answer was '" + resultOper + "'.");
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

    // Возвращает результат операции
    private static int getResultOper(int num1, int num2, int indexOper) {
        int result = 0;
        switch (cycleOfOperations[indexOper]) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            default:
                result = 0;
        }
        return result;
    }

    // Возвращает текст вопроса
    private static String getQuestionText(int num1, int num2, int indexOper) {
        StringBuilder builder = new StringBuilder("Question: ");
        builder.append(num1);
        builder.append(" ");
        builder.append(cycleOfOperations[indexOper]);
        builder.append(" ");
        builder.append(num2 < 0 ? "(" + num2 + ")" : num2);
        return builder.toString();
    }

}
