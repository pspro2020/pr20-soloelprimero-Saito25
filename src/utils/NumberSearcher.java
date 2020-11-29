package utils;

import java.util.concurrent.Callable;

public class NumberSearcher implements Callable<ResultWrapper> {

    private final Integer[] arrayToSearch;
    private final int numberToFind;
    private final int row;

    public NumberSearcher(int row, Integer[] arrayToSearch, int numberToFind) {
        this.row = row;
        this.arrayToSearch = arrayToSearch;
        this.numberToFind = numberToFind;
    }

    @Override
    public ResultWrapper call() throws InterruptedException {
        for (int i = 0; i < arrayToSearch.length; i++) {
            if (numberToFind == arrayToSearch[i]) {
                return new ResultWrapper(row, i);
            }
        }
        throw new RuntimeException("No se ha encontrado el número en la fila " + row);
    }


}
