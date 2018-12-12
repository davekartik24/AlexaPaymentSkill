import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

public class connectionTest {


  public static void main(String[] args) throws SQLException, ClassNotFoundException {
    Instant start = Instant.now();
    String name = QueryHandler.getUserName();
    Instant end = Instant.now();
    Duration timeElapsed = Duration.between(start, end);

    System.out.println(name);
    System.out.println(timeElapsed);

  }


}
