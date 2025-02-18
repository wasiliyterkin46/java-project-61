package hexlet.code.games;

import java.util.Random;

public class GameProgression {
    // Длина последовательности
    private static final int MIN_LEN = 5;
    private static final int MAX_LEN = 10;
    // Диапазон шага прогрессии
    private static final int STEP_PROGRESSION_MAX = 20;
    // Диапазон стартового числа
    private static final int START_NUM_MAX = 20;

    // Возвращает прогрессию по заданным начальному числу, количеству элементов и шагу
    private static int[] getProgression(int startNum, int countElem, int step) {
        int[] result = new int[countElem];
        result[0] = startNum;
        for (int x = 1; x < countElem; x++) {
            result[x] = result[x - 1] + step;
        }

        return result;
    }

    // Возвращает рандомный множитель 1 или -1. Это надо, если хотим игру с отрицательными числами
    private static int getSign() {
        Random rnd = new Random();
        boolean res = rnd.nextBoolean();
        return res ? (-1) : 1;
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


    // Возвращает правила
    public static String getRules() {
        return "What number is missing in the progression?";
    }

    // Возвращает текст вопроса
    public static String[] getQuestion() {
        // Генерируем шаг прогрессии
        int stepProgression = RandomUtils.getRandomIntegerInRange(1, STEP_PROGRESSION_MAX) * getSign();
        // Генерируем длину прогрессии
        int lenProg = RandomUtils.getRandomIntegerInRange(MIN_LEN, MAX_LEN);
        // Генерируем начальное число прогрессии
        int startNum = RandomUtils.getRandomIntegerInRange(0, START_NUM_MAX) * getSign();
        // Генерируем прогрессию
        int[] progression = getProgression(startNum, lenProg, stepProgression);
        // Получаем индекс скрываемого элемента
        int hideElem = RandomUtils.getRandomIntegerInRange(0, lenProg - 1);

        String correctAnswer = String.valueOf(progression[hideElem]);
        String question = getArrayToString(progression, hideElem);

        return new String[] {question, correctAnswer};
    }

}
