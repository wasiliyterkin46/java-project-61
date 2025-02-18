package hexlet.code.games;

public final class RandomUtils {

    public static int getRandomIntegerInRange(int min, int max) {
        return (int) (Math.random() * (max + 1 - min) + min);
    }

    public static int getRandomInteger(int maxNum) {
        int number = (int) (Math.random() * maxNum);
        return number;
    }
}
