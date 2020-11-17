import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static java.lang.Thread.sleep;

public class CiclistaInpuntual implements Runnable {
    Etapa phaser;
    Random random = new Random();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public CiclistaInpuntual(Etapa phaser) {
        this.phaser = phaser;
    }


    @Override
    public void run() {
        if (!phaser.isTerminated()) {
            phaser.register();
            salirDeCasa();
            try {
                phaser.awaitAdvanceInterruptibly(phaser.arrive());
            } catch (InterruptedException e) {
                return;
            }
            irGasolineraVenta();
            try {
                phaser.awaitAdvanceInterruptibly(phaser.arrive());
            } catch (InterruptedException e) {
                return;
            }
            irGasolineraVentaGasolinera();
            try {
                phaser.awaitAdvanceInterruptibly(phaser.arrive());
            } catch (InterruptedException e) {
                return;
            }
            irCasa();
        } else {
            System.out.println(Thread.currentThread().getName() + " no le dio tiempo a llegar");
        }
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

    private void irGasolineraVentaGasolinera() {
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

    private void irGasolineraVenta() {
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
