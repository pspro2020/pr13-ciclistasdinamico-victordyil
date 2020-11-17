import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Phaser;

public class Etapa extends Phaser {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final int CASA_GASOLINERA_PHASE = 0;
    public static final int GASOLINERA_VENTA_PHASE = 1;
    public static final int VENTA_GASOLINERA_PHASE = 2;

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        // The phaser is marked as terminated.
        switch (phase) {
            case CASA_GASOLINERA_PHASE -> System.out.printf("%s -> Los %d ciclistas han llegado a la gasolinera\n",
                    LocalTime.now().format(dateTimeFormatter), registeredParties);
            case GASOLINERA_VENTA_PHASE -> System.out.printf("%s -> Los %d ciclistas llegaron a la venta\n",
                    LocalTime.now().format(dateTimeFormatter), registeredParties);
            case VENTA_GASOLINERA_PHASE -> {
                System.out.printf("%s -> Los %d ciclistas van a la gasolinera para despedirse\n",
                        LocalTime.now().format(dateTimeFormatter), registeredParties);
                return true;
            }
        }
        return super.onAdvance(phase, registeredParties);
    }


}
