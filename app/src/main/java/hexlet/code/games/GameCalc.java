package hexlet.code.games;

import java.util.Scanner;

public class GameCalc {
    // Количество побед, до которых продолжается игра
    private static int countWin = 3;
    private static int numMin = 1;
    private static int numMax = 30;
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
            num1 = getRandomNumber(numMin, numMax) * getSign();
            num2 = getRandomNumber(numMin, numMax) * getSign();
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

        } while (play && countCorrect < countWin);

        // Завершаем игру
        if (countCorrect == countWin) {
            System.out.println("Congratulations, " + name + "!");
        } else {
            System.out.println("Let's try again, " + name + "!");
        }
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
