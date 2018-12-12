import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryHandler {
  public static String getUserName() throws SQLException, ClassNotFoundException {
    try {
      // add mysql-connector-java library
      Connection c =  DBConnector.getInstance();
      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery("select * from USR_USERS");
      if(rs.next()){
        return rs.getString("USERNAME");
      }
      return "invalid user";
    }catch(Exception e){
      System.out.println(e);}
    return "invalid user";
  }
}
