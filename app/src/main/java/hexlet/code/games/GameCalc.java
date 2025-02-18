package hexlet.code.games;

public class GameCalc {
    private static final int NUM_MIN = 1;
    private static final int NUM_MAX = 30;
    private static int indexOperations = 0;
    private static char[] cycleOfOperations = {'+', '-', '*'};

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

    // Возвращает текст вопроса и ответ
    public static String[] getQuestion() {
        // Генерируем числа
        int num1 = RandomUtils.getRandomIntegerInRange(NUM_MIN, NUM_MAX);
        int num2 = RandomUtils.getRandomIntegerInRange(NUM_MIN, NUM_MAX);
        StringBuilder builder = new StringBuilder(String.valueOf(num1));
        builder.append(" ");
        builder.append(cycleOfOperations[indexOperations % cycleOfOperations.length]);
        builder.append(" ");
        builder.append(num2);

        String question = builder.toString();
        String correctAnswer = String.valueOf(getResultOper(num1, num2));

        // Увеличиваем индекс после возврата очередного вопроса, чтобы менять знак операции
        indexOperations++;

        return new String[] {question, correctAnswer};
    }
}
