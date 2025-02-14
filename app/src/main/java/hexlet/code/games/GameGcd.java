package hexlet.code.games;

public class GameGcd {
    // Границы НОД
    private static final int NUM_MIN = 2;
    private static final int NUM_MAX = 30;
    // Сложность
    private static final int LIGHT_COMPLEXITY = 5;
    private static final int HIGH_COMPLEXITY = 10;
    // Хранится правильный ответ
    private static String correctAnswer = "";

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

    // Возвращает правила.
    public static String getRules() {
        return "Find the greatest common divisor of given numbers.";
    }

    // Возвращает текст вопроса.
    public static String getQuestion() {
        // Получаем НОД
        int gcd = getRandomNumber(NUM_MIN, NUM_MAX);
        // Если число двузначное, понижаем сложность
        int curComplexity = Integer.toString(gcd).length() > 1 ? LIGHT_COMPLEXITY : HIGH_COMPLEXITY;
        // Заполняем массив чисел
        int[] space = getSpace(gcd, curComplexity);
        // Подбираем числа
        int num1 = space[getRandomNumber(0, curComplexity - 1)];
        int num2;
        do {
            num2 = space[getRandomNumber(0, curComplexity - 1)];
        } while (num1 == num2);

        //Записываем правильный ответ
        correctAnswer = String.valueOf(gcd);

        return num1 + " " + num2;
    }

    // Возвращает правильный ответ.
    public static String getCorrectAnswer() {
        return correctAnswer;
    }

}
