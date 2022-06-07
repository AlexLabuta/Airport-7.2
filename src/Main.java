import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;

/**
 * @Author Adaye
 * creat 2022-05-10   5:55
 **/
public class Main {
    private static String DATE_FORMAT = "| yyyy/MM/dd | HH:mm |";

    public static void main(String[] args) {
        int intervalTime = 2;

        Airport.getInstance().getTerminals().stream().flatMap(f -> f.getFlights().stream()).filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE)).filter(f -> convertToLocalDate(f.getDate()).
                        isAfter(LocalDateTime.now()) && convertToLocalDate(f.getDate()).isBefore(LocalDateTime.now().plusHours(intervalTime))).sorted(Comparator.comparing(Flight::getDate)).
                forEach(f -> System.out.printf("%s %s aircraft %s%n", f.getType(), convertToLocalDate(f.getDate()).format(DateTimeFormatter.ofPattern(DATE_FORMAT)), f.getAircraft().getModel()));
    }

    private static LocalDateTime convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}