package hexlet.code;

import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;


public class GamePrime implements RunnableApp {
    // Переменные класса
    // Количество побед, до которых продолжается игра
    private int countWin = 3;
    // Верхняя граница чисел
    private int maxNum = 99;

    @Override
    public void run(Cli cli) {
        // Приветствие перед игрой
        GameGreeting greeting = new GameGreeting();
        greeting.run(cli);
        String name = greeting.getName();
        // Показываем правила
        System.out.println("Answer 'yes' if given number is prime. Otherwise answer 'no'.");

        // Вспомогательные переменные
        int countCorrect = 0; // Количество правильных ответов
        boolean play = true; // Признак продолжения игры
        String answer = ""; // Ответ пользователя
        String result = ""; // Правильный ответ
        int number = 0; // Число для вопроса
        int[] primeSpace = getPrimeSpace(this.maxNum);
        int[] cashAnswer = new int[countWin]; // Хранение заданных чисел, чтобы не повторялись

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
            answer = cli.getAnswerStringOnLine("Your answer: ").toLowerCase().trim();
            cashAnswer[countCorrect] = number;
            // Обрабатываем результат
            if (answer.equals(result)) {
                System.out.println("Correct!");
                countCorrect++;
            } else {
                System.out.println("'" + answer + "' is wrong answer ;(. Correct answer was '" + result + "'.");
                play = false;
            }
        } while (play && countCorrect < this.countWin);

        // Завершаем игру
        if (countCorrect == countWin) {
            System.out.println("Congratulations, " + name + "!");
        } else {
            System.out.println("Let's try again, " + name + "!");
        }
    }

    // Возвращает случайное число в заданном диапазоне
    private int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max + 1 - min) + min);
    }

    // Возвращает массив простых чисел от 1 до указанного максимума
    private int[] getPrimeSpace(int maxNum) {
        int[] result = new int[(int) maxNum / 2]; // Инициализируем массив длиной = максимум / 2, т.к. четные числа заведомо не будут простыми.
        // Двум первым элементам присваиваем простые числа 1 и 2
        result[0] = 1;
        result[1] = 2;
        boolean simple = true; // Признак, что число простое

        int index = 2; // индекс, в который записываем очередное простое число
        // Заполняем массив
        for (int x = 3; x < maxNum; x += 2) {
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

    // Возвращает сгенерированное число. Т.к. простых чисел меньше, чем натуральных, то реализуем алгоритм, который будет предлагать простое число с вероятностью 1/2
    private int getNumber(int[] primeSpace, int[] cashAnswer) {
        boolean cur = true;
        // Получаем случайное число
        int n = getRandomNumber(1, 10);
        if (n <= 5) {
            // Возвращаем обычное
            do {
                n = getRandomNumber(2, this.maxNum);
                // Проверяем, чтобы число не было простым и не совпадало с предыдущими вопросами
                cur = ArrayUtils.contains(primeSpace, n) || ArrayUtils.contains(cashAnswer, n);
            } while(cur);
            return n;
        } else {
            // Возвращаем простое
            n = getRandomNumber(0, primeSpace.length - 1);
            return primeSpace[n];
        }
    }
}
