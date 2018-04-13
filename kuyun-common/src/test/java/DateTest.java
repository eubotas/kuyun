import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by user on 2018-04-13.
 */
public class DateTest {

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        String formattedDate = formatter.format(LocalDate.now());
        System.out.println(formattedDate);
    }
}
