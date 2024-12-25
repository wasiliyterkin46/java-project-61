package hexlet.code;

class GameEven implements RunnableApp {
    // Количество побед, до которых продолжается игра. Нужно для гибкости, если будет необходимость играть дольше/меньше
    private int countWin = 3;

    public void run(Cli cli) {
        // Приветствие перед игрой
        GameGreeting greeting = new GameGreeting();
        greeting.run(cli);
        String name = greeting.getName();

        // Показываем правила
        System.out.println("Answer 'yes' if the number is even, otherwise answer 'no'.");

        // Определяем переменные
        boolean play = true;
        boolean even = false;
        int number = 0;
        String answer = "";
        String result = "";
        int countCorrect = 0;

        // Игра
        do {
            number = (int) (Math.random() * 100);
            even = number % 2 == 0 ? true : false;
            System.out.println("Question: " + number);
            answer = cli.getAnswerStringOnLine("Your answer: ").toLowerCase();

            // Проверяем ответ
            if (!answer.equals("yes") && !answer.equals("no")) {
                result = "'" + answer + "' - is wrong answer ;(. Must be 'yes' if the number is even, otherwise answer 'no'.";
                play = false;
                continue;
            }
            if ((answer.equals("yes") && even) || (answer.equals("no") && !even)) {
                System.out.println("Correct!");
                countCorrect++;
            } else {
                result = answer.equals("yes") ? "'yes' is wrong answer ;(. Correct answer was 'no'." : "'no' is wrong answer ;(. Correct answer was 'yes'.";
                play = false;
            }
        } while (play && countCorrect < countWin);

        // Завершаем игру
        if (countCorrect == countWin) {
            System.out.println("Congratulations, " + name + "!");
        } else {
            System.out.println(result);
            System.out.println("Let's try again, " + name + "!");
        }
    }
}
