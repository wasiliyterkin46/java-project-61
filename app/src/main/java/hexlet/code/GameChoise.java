package hexlet.code;

class GameChoise {
    public static int run(Cli cli) {
        StringBuilder builder = new StringBuilder("Please enter the game number and press Enter.\n");
        builder.append("1 - Greet\n");
        builder.append("2 - Even\n");
        builder.append("3 - Calc\n");
        builder.append("0 - Exit");
        System.out.println(builder.toString());
        int result = cli.getAnswerIntOnLine("Your choice:");
        return result;
    }

    public static RunnableApp getGame(int choise) {
        RunnableApp result = null;
        switch (choise) {
            case 1:
                result = new GameGreeting();
                break;
            case 2:
                result = new GameEven();
                break;
            case 3:
                result = new GameCalc();
                break;
        }

        return result;
    }
}
