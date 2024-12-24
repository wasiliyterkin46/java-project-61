package hexlet.code;

public class GameGreeting implements RunnableApp {
    private String name = "";

    public void run(Cli cli) {
        StringBuilder builder = new StringBuilder("Hello, ");
        this.name = cli.getAnswerStringOnLine("Welcome to the Brain Games!\nMay I have your name? ");
        builder.append(this.name);
        System.out.println(builder.toString());
    }

    public String getName() {
        return this.name;
    }
}
