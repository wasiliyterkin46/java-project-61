package hexlet.code.games;

import java.util.Arrays;

public class GameGcd {
    // Границы НОД
    private static final int NUM_MIN = 2;
    private static final int NUM_MAX = 30;
    // Сложность
    private static final int LIGHT_COMPLEXITY = 5;
    private static final int HIGH_COMPLEXITY = 10;

    // Возвращает заполненный массив чисел c числами, у которых общий НОД
    private static int[] getSpace(int gcd, int complexity) {
        int[] result = new int[complexity];
        result[0] = gcd;
        /*int countFail = 0;*/

        for (int x = 1; x < complexity; x++) {
            int next = result[x - 1] + gcd;
            int[] verifiableNumbers = Arrays.copyOfRange(result, 1, x);

            while (!nextHasGcdIsOnlyFirstElement(verifiableNumbers, next)) {
                next += gcd;
            }
            result[x] = next;
        }

        return result;
    }

    private static boolean nextHasGcdIsOnlyFirstElement(int[] verifiableNumbers, int potentialNumber) {
        for (int nextNumber : verifiableNumbers) {
            if (potentialNumber % nextNumber == 0) {
                return false;
            }
        }
        return true;
    }

    // Возвращает правила.
    public static String getRules() {
        return "Find the greatest common divisor of given numbers.";
    }

    // Возвращает текст вопроса.
    public static String[] getQuestion() {
        // Получаем НОД
        int gcd = RandomUtils.getRandomIntegerInRange(NUM_MIN, NUM_MAX);
        // Если число двузначное, понижаем сложность
        int curComplexity = Integer.toString(gcd).length() > 1 ? LIGHT_COMPLEXITY : HIGH_COMPLEXITY;
        // Заполняем массив чисел
        int[] space = getSpace(gcd, curComplexity);
        // Подбираем числа
        int num1 = space[RandomUtils.getRandomIntegerInRange(0, curComplexity - 1)];
        int num2;
        do {
            num2 = space[RandomUtils.getRandomIntegerInRange(0, curComplexity - 1)];
        } while (num1 == num2);

        String question = num1 + " " + num2;
        String correctAnswer = String.valueOf(gcd);

        return new String[] {question, correctAnswer};
    }

}
