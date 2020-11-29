package main;

import utils.ArrayGenerator;
import utils.NumberSearcher;
import utils.ResultWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    int numberToSearch = new Random().nextInt(20) + 1;
    private final ArrayGenerator arrayGenerator = new ArrayGenerator(5, 5);
    ThreadPoolExecutor fixedThreadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public Main() {
        showInitialState();
        initTask();
    }

    private void showInitialState() {
        System.out.println("Número a buscar: " + numberToSearch);
        System.out.println("Matriz dada: " + Arrays.deepToString(arrayGenerator.getNumbers()));
    }

    private void initTask(){
        List<NumberSearcher> numberSearchers = new ArrayList<>();
        Integer[][] array = arrayGenerator.getNumbers();
        for (int i = 0; i < 5; i++) {
            numberSearchers.add(new NumberSearcher(i, array[i], numberToSearch));
        }


        try {
            ResultWrapper result = fixedThreadPoolExecutor.invokeAny(numberSearchers);
            System.out.printf("El número %d, se encontraba en en la fila %d - columna %d\n",
                    numberToSearch, result.getRow(), result.getColumn());
        } catch (InterruptedException ignored) {
        } catch (ExecutionException e) {
            System.out.println("No existe el número dentro de las matrices");
        } finally {
            fixedThreadPoolExecutor.shutdown();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
