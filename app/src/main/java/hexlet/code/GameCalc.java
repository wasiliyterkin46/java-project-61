package hexlet.code;

public class GameCalc implements RunnableApp {
    // Количество побед, до которых продолжается игра
    private int countWin = 3;
    private int numMin = 1;
    private int numMax = 30;
    private char[] cycleOfOperations = {'+', '-', '*'};

    public void run(Cli cli) {
        // Приветствие перед игрой
        GameGreeting greeting = new GameGreeting();
        greeting.run(cli);
        String name = greeting.getName();

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
            num1 = getRandomNumber(this.numMin, this.numMax) * getSign();
            num2 = getRandomNumber(this.numMin, this.numMax) * getSign();
            // Получаем результат операции
            resultOper = getResultOper(num1, num2, countCorrect);
            // Генерируем текст вопроса
            question = getQuestionText(num1, num2, countCorrect);
            // Предлагаем вопрос и запрашиваем ответ
            System.out.println(question);
            answer = cli.getAnswerIntOnLine("Your answer: ");
            // Обрабатываем результат
            if (answer == resultOper) {
                System.out.println("Correct!");
                countCorrect++;
            } else {
                System.out.println("'" + answer + "' is wrong answer ;(. Correct answer was '" + resultOper + "'.");
                play = false;
            }

        } while (play && countCorrect < this.countWin);

        // Завершаем игру
        if (countCorrect == 3) {
            System.out.println("Congratulations, " + name + "!");
        } else {
            System.out.println("Let's try again, " + name + "!");
        }
    }

    // Возвращает случайное число в заданном диапазоне
    private int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max + 1 - min) + min);
    }

    // Возвращает рандомный множитель 1 или -1. Это надо, если хотим игру с отрицательными числами
    private int getSign() {
        int result = (int) (Math.random() * 10);
        return result <= 5 ? (-1) : 1;
    }

    // Возвращает результат операции
    private int getResultOper(int num1, int num2, int indexOper) {
        int result = 0;
        switch (this.cycleOfOperations[indexOper]) {
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
    private String getQuestionText(int num1, int num2, int indexOper) {
        StringBuilder builder = new StringBuilder("Question: ");
        builder.append(num1);
        builder.append(" ");
        builder.append(cycleOfOperations[indexOper]);
        builder.append(" ");
        builder.append(num2 < 0 ? "(" + num2 + ")" : num2);
        return builder.toString();
    }

}
