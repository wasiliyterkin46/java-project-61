package hexlet.code.games;

import java.util.List;
import java.util.LinkedList;
import java.util.Random;

public class GamePrime {
    // Верхняя граница чисел
    private static final int MAX_NUM = 99;

    // Массив простых чисел
    private static Integer[] arrayOfPrimeNumbers;
    private static Integer currentAvailableIndexArrayOfPrimeNumbers = 0;
    // Массив не простых чисел
    private static Integer[] arrayOfNotPrimeNumbers;
    private static Integer currentAvailableIndexArrayOfNotPrimeNumbers = 0;

    static {
        createSetsOfNumbers();
    }

    // Возвращает правила
    public static String getRules() {
        return "Answer 'yes' if given number is prime. Otherwise answer 'no'.";
    }

    // Возвращает текст вопроса
    public static String[] getQuestion() {
        String question;
        String correctAnswer;

        Random rnd = new Random();
        boolean isPrime = rnd.nextBoolean();
        if (isPrime) {
            question = nextNumber(arrayOfPrimeNumbers, currentAvailableIndexArrayOfPrimeNumbers);
            correctAnswer = "yes";
        } else {
            question = nextNumber(arrayOfNotPrimeNumbers, currentAvailableIndexArrayOfNotPrimeNumbers);
            correctAnswer = "no";
        }


        return new String[] {question, correctAnswer};
    }

    private static void createSetsOfNumbers() {
        List<Integer> primeNumbers = new LinkedList<>();
        List<Integer> noPrimeNumbers = new LinkedList<>();

        for (int x = 1; x <= MAX_NUM; x++) {
            if (numberIsPrime(primeNumbers, x)) {
                primeNumbers.add(x);
            } else {
                noPrimeNumbers.add(x);
            }
        }

        arrayOfPrimeNumbers = primeNumbers.toArray(new Integer[primeNumbers.size()]);
        arrayOfNotPrimeNumbers = noPrimeNumbers.toArray(new Integer[noPrimeNumbers.size()]);
    }

    private static boolean numberIsPrime(List<Integer> currentPrimeList, int number) {
        if (number == 1) {
            return false;
        }
        for (var i : currentPrimeList) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static String nextNumber(Integer[] arrayOfNumbers, Integer currentIndex) {
        int nextIndex = RandomUtils.getRandomIntegerInRange(currentIndex, arrayOfNumbers.length - 1);
        int valueNextIndex = arrayOfNumbers[nextIndex];

        if (nextIndex != currentIndex) {
            changeElementOfArrray(arrayOfNumbers, currentIndex, nextIndex);
        }

        currentIndex++;
        if (currentIndex == arrayOfNumbers.length) {
            currentIndex = 0;
        }

        return Integer.toString(valueNextIndex);
    }

    private static void changeElementOfArrray(Integer[] arrayOfNumbers, int indexFirst, int indexSecond) {
        int valueNextIndex = arrayOfNumbers[indexSecond];
        arrayOfNumbers[indexSecond] = arrayOfNumbers[indexFirst];
        arrayOfNumbers[indexFirst] = valueNextIndex;
    }

}
