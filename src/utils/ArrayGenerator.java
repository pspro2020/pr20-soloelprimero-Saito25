package utils;

import java.util.Random;

public class ArrayGenerator {

    private final Integer[][] numbers;
    private final Random random = new Random();

    public ArrayGenerator(int numberOfRow, int numberOfColumns) {
        numbers = new Integer[numberOfRow][numberOfColumns];
        fillArray();
    }

    private void fillArray() {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                numbers[i][j] = random.nextInt(10) + 1;
            }
        }
    }

    public Integer[][] getNumbers() {
        return numbers;
    }
}
