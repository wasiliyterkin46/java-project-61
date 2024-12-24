package hexlet.code;

import java.util.Scanner;

// Интерфейс нужен (не использовал интерфейс Runnable), т.к. Поток System.in нельзя закрывать в процессе работы приложения.
// Если хочу, чтобы не перезапуская можно было играть в разные игры, то надо инициировать System.in в главном классе и далее передавать System.in в запускаемые игры.

public interface RunnableApp {
    void run(Cli cli);
}
