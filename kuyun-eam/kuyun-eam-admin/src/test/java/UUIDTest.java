import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.UUID;

/**
 * Created by user on 2017-06-10.
 */
public class UUIDTest {

    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.printf("uuid=" + uuid.toString());
    }
}
