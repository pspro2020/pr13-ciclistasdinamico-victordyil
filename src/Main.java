import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int NUMBER_OF_CYCLES = 10;

    public static void main(String[] args) {
        Etapa phaser = new Etapa();
        for (int i = 0; i < 10; i++) {
            new Thread(new Ciclista(phaser), "Ciclista " + i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            return;
        }
        new Thread(new CiclistaInpuntual(phaser), "Ciclista " + 10).start();
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            return;
        }
        new Thread(new CiclistaInpuntual(phaser), "Ciclista " + 11).start();
    }
}
