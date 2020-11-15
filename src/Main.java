import java.util.concurrent.CyclicBarrier;

public class Main {
    private static final int NUMBER_OF_CYCLES = 10;

    public static void main(String[] args) {
        CyclicBarrier cycle = new CyclicBarrier(NUMBER_OF_CYCLES, new Etapa());
        for (int i = 0; i < 10; i++) {
            new Thread(new Ciclista(cycle), "Ciclista " + i).start();
        }
    }
}
