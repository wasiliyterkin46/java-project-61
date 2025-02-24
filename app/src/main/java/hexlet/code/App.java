package hexlet.code;

public class App {

    public static void main(String[] args) {
        try {
            // Выбор игры
            String game = Engine.getGame();
            // Запуск игры
            Boolean resultGame = Engine.prepareGame(game);
            // Формируем сообщение о результатах игры
            Engine.closeGame(resultGame);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
