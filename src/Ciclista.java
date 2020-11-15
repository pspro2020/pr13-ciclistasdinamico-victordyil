import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static java.lang.Thread.sleep;

public class Ciclista implements Runnable {
    CyclicBarrier barrier;
    Random random = new Random();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Ciclista(CyclicBarrier barrier) {
        this.barrier = barrier;
    }


    @Override
    public void run() {
        try {
            salirDeCasa();
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        try {
            irVenta();
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        try {
            irCasaGasolinera();
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        irCasa();

    }

    private void irCasa() {
        System.out.printf("%s -> %s ha salido de la gasolinera \n",
                LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName());
        int r = random.nextInt(3) + 1;
        try {
            sleep(r * 1000);
        } catch (InterruptedException e) {
            System.out.println("Te has quedado dormido al salir de cas macho..");
        }
        System.out.printf("%s -> %s: Ya en casa\n",
                LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName());

    }

    private void irCasaGasolinera() {
        System.out.printf("%s -> %s ha salido de la venta \n",
                LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName());
        int r = random.nextInt(5) + 5;
        try {
            sleep(r * 1000);
        } catch (InterruptedException e) {
            System.out.println("Te has quedado dormido al salir de cas macho..");
        }
        System.out.printf("%s -> %s ha llegado a la gasolinera\n",
                LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName());
    }

    private void irVenta() {
        System.out.printf("%s -> %s ha salido de la gasolinera \n",
                LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName());
        int r = random.nextInt(5) + 5;
        try {
            sleep(r * 1000);
        } catch (InterruptedException e) {
            System.out.println("Te has quedado dormido al salir de cas macho..");
        }
        System.out.printf("%s -> %s ha llegado a la venta\n",
                LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName());
    }

    private void salirDeCasa() {
        System.out.printf("%s -> %s ha salido de casa\n",
                LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName());
        int r = random.nextInt(3) + 1;
        try {
            sleep(r * 1000);
        } catch (InterruptedException e) {
            System.out.println("Te has quedado dormido al salir de cas macho..");
        }
        System.out.printf("%s -> %s ha llegado a la gasolinera\n",
                LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName());

    }
}
