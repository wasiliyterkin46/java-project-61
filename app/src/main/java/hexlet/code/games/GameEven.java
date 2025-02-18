package hexlet.code.games;

public class GameEven {
    // Диапазон генерируемых чисел
    private static final int MAX_NUM = 100;

    // Возвращает правила
    public static String getRules() {
        return "Answer 'yes' if the number is even, otherwise answer 'no'.";
    }

    // Возвращает текст вопроса
    public static String[] getQuestion() {
        int number = RandomUtils.getRandomInteger(MAX_NUM);
        String question = String.valueOf(number);
        String correctAnswer = number % 2 == 0 ? "yes" : "no";

        return new String[] {question, correctAnswer};
    }

}
