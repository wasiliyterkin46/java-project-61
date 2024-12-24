package hexlet.code;

public class GameGreeting implements RunnableApp {
    public void run(Cli cli) {
        StringBuilder builder = new StringBuilder("Hello, ");
        String name = cli.getAnswerStringOnLine("Welcome to the Brain Games!\nMay I have your name? ");
        builder.append(name);
        System.out.println(builder.toString());
    }
}
