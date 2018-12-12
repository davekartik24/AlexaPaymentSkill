import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.GregorianCalendar;

public class connectionTest {
  public static void main(String[] args) throws SQLException, ClassNotFoundException {
      Instant start = Instant.now();
      String name = QueryHandler.getUserName();
      Instant end = Instant.now();
      Duration timeElapsed = Duration.between(start, end);

      System.out.println(name);
      System.out.println(timeElapsed);
	  
	  int org_id = QueryHandler.getOrganizationId("chen");
	  Date d = new GregorianCalendar(2018,12,9).getTime();
	  System.out.println(d);
	  String ds = QueryHandler.getDateString(d);
	  
	  String txn_sum = QueryHandler.getMerchantSummaries(org_id,ds);
	  System.out.println(txn_sum);

	  txn_sum = QueryHandler.getAdminSummaries(ds);
	  System.out.println(txn_sum);
	  
	  System.out.println("************");
	  String topTwo = QueryHandler.topXByTxnCount(2);
	  System.out.println(topTwo);
	  System.out.println("************");
	  String worstTwo = QueryHandler.worstXByApprovalRate(2);
	  System.out.println(worstTwo);
	  
	  System.out.println("************");
	  System.out.println(QueryHandler.getNumOpenEvents());
  }

}
