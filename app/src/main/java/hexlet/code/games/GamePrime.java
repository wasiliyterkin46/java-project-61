package hexlet.code.games;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;


public class GamePrime {
    // Переменные класса
    // Количество побед, до которых продолжается игра
    private static final int COUNT_WIN = 3;
    // Верхняя граница чисел
    private static final int MAX_NUM = 99;
    // Экземпляр класса Random для получения 50% вероятности получения простого числа
    private static final Random RANDOM = new Random();

    public static void run(Scanner in, String name) {

        // Показываем правила
        System.out.println("Answer 'yes' if given number is prime. Otherwise answer 'no'.");

        // Вспомогательные переменные
        int countCorrect = 0; // Количество правильных ответов
        boolean play = true; // Признак продолжения игры
        String answer = ""; // Ответ пользователя
        String result = ""; // Правильный ответ
        int number = 0; // Число для вопроса
        int[] primeSpace = getPrimeSpace(); // Массив простых чисел
        int[] cashAnswer = new int[COUNT_WIN]; // Хранение заданных чисел, чтобы не повторялись

        // Игра
        do {
            // Генерируем число
            number = getNumber(primeSpace, cashAnswer);
            result = ArrayUtils.contains(primeSpace, number) ? "yes" : "no";
            // Если число простое, то удаляем его из множества простых чисел, чтобы не повторялось в вопросах
            if (result.equals("yes")) {
                primeSpace = ArrayUtils.removeElement(primeSpace, number);
            }
            // Задаем вопрос
            System.out.println("Question: " + number);
            // Получаем ответ
            System.out.print("Your answer: ");
            answer = in.next().toLowerCase().trim();
            cashAnswer[countCorrect] = number;
            // Обрабатываем результат
            if (answer.equals(result)) {
                System.out.println("Correct!");
                countCorrect++;
            } else {
                System.out.println("'" + answer + "' is wrong answer ;(. Correct answer was '" + result + "'.");
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

    // Возвращает массив простых чисел от 1 до указанного максимума
    private static int[] getPrimeSpace() {
        // Инициализируем массив длиной = максимум / 2, т.к. четные числа заведомо не будут простыми.
        int[] result = new int[(int) MAX_NUM / 2];

        // Двум первым элементам присваиваем простые числа 1 и 2
        result[0] = 1;
        result[1] = 2;
        boolean simple = true; // Признак, что число простое

        int index = result.length; // индекс, в который записываем следующее простое число
        // Заполняем массив
        for (int x = result[result.length - 1] + 1; x < MAX_NUM; x += 2) {
            simple = true;
            for (int y = 1; y < index; y++) {
                if (x % result[y] == 0) {
                    simple = false;
                    break;
                }
            }
            if (simple) {
                result[index] = x;
                index++;
            }
        }

        // Возвращаем новый массив, отсеяв элементы, равные нулю
        return Arrays.copyOf(result, index);
    }

    // Возвращает сгенерированное число. Т.к. простых чисел меньше, чем натуральных, то реализуем алгоритм,
    // который будет предлагать простое число с вероятностью 1/2
    private static int getNumber(int[] primeSpace, int[] cashAnswer) {
        boolean cur = true;
        int n = 0;
        boolean prime = RANDOM.nextBoolean();
        if (prime) {
            // Возвращаем обычное
            do {
                n = getRandomNumber(2, MAX_NUM);
                // Проверяем, чтобы число не было простым и не совпадало с предыдущими вопросами
                cur = ArrayUtils.contains(primeSpace, n) || ArrayUtils.contains(cashAnswer, n);
            } while (cur);
            return n;
        } else {
            // Возвращаем простое
            n = getRandomNumber(0, primeSpace.length - 1);
            return primeSpace[n];
        }
    }
}
