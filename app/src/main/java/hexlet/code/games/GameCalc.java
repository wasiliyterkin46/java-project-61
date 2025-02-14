package hexlet.code.games;

public class GameCalc {
    private static final int NUM_MIN = 1;
    private static final int NUM_MAX = 30;
    private static int indexOperations = 0;
    private static char[] cycleOfOperations = {'+', '-', '*'};
    // Хранится правильный ответ
    private static String correctAnswer = "";

    // Возвращает случайное число в заданном диапазоне
    private static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max + 1 - min) + min);
    }

    // Возвращает результат операции
    private static int getResultOper(int num1, int num2) {
        int result = 0;
        switch (cycleOfOperations[indexOperations % cycleOfOperations.length]) {
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

    // Возвращает правила
    public static String getRules() {
        return "What is the result of the expression?";
    }

    // Возвращает текст вопроса
    public static String getQuestion() {
        // Генерируем числа
        int num1 = getRandomNumber(NUM_MIN, NUM_MAX);
        int num2 = getRandomNumber(NUM_MIN, NUM_MAX);
        correctAnswer = String.valueOf(getResultOper(num1, num2));
        StringBuilder builder = new StringBuilder(String.valueOf(num1));
        builder.append(" ");
        builder.append(cycleOfOperations[indexOperations % cycleOfOperations.length]);
        builder.append(" ");
        builder.append(num2);
        // Увеличиваем индекс после возврата очередного вопроса, чтобы менять знак операции
        indexOperations++;

        return builder.toString();
    }

    // Возвращает правильный ответ
    public static String getCorrectAnswer() {
        return correctAnswer;
    }

}
