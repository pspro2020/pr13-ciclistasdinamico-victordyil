import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Etapa implements Runnable {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void run() {
        System.out.printf("%s -> Comienza etapa\n",
                LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName());

    }

}
