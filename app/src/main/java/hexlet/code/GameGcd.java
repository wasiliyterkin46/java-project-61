package hexlet.code;

public class GameGcd implements RunnableApp {
    // Количество побед, до которых продолжается игра
    private int countWin = 3;
    // Границы НОД
    private int numMin = 2;
    private int numMax = 30;
    // Сложность
    int complexity = 10;
    int lightComplexity = 5;
    int highComplexity = 10;

    public void run(Cli cli) {
        // Приветствие перед игрой
        GameGreeting greeting = new GameGreeting();
        greeting.run(cli);
        String name = greeting.getName();
        // Показываем правила
        System.out.println("Find the greatest common divisor of given numbers.");
        // Вспомогательные переменные
        int countCorrect = 0; // Количество правильных ответов
        boolean play = true; // Признак продолжения игры
        int answer = 0; // Хранение ответа
        int num1;
        int num2;
        int gcd;

        // Игра
        do {
            // Получаем НОД
            gcd = getRandomNumber(numMin, numMax);
            // Если число двузначное, понижаем сложность
            this.complexity = (int) gcd / 10 > 0 ? lightComplexity : highComplexity;
            // Заполняем массив чисел
            int[] space = getSpace(gcd);
            // Подбираем числа
            num1 = space[getRandomNumber(0, this.complexity - 1)];
            do {
                num2 = space[getRandomNumber(0, this.complexity - 1)];
            } while (num1 == num2);

            // Задаем вопрос
            System.out.println("Question: " + num1 + " " + num2);
            // Получаем ответ
            answer = cli.getAnswerIntOnLine("Your answer: ");
            // Обрабатываем результат
            if (answer == gcd) {
                System.out.println("Correct!");
                countCorrect++;
            } else {
                System.out.println("'" + answer + "' is wrong answer ;(. Correct answer was '" + gcd + "'.");
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

    // Возвращает заполненный массив чисел c числами, у которых общий НОД
    private int[] getSpace(int gcd) {
        int[] result = new int[this.complexity];
        result[0] = gcd;
        int next = 0;
        int countFail = 0;

        for (int x = 1; x < this.complexity; x++) {
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
            } while(countFail > 0);
            result[x] = next;
        }

        return result;
    }
}
