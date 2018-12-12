package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
  private static Connection c = null;

  public DBConnector(){};

  public static Connection getInstance() throws SQLException, ClassNotFoundException {
    if(c == null){
      getConnector();
    }
    return c;
  }

  private static void getConnector() throws SQLException, ClassNotFoundException {
    if(c == null) {
      Class.forName("com.mysql.jdbc.Driver");
      c = DriverManager.getConnection(
          "jdbc:mysql://genie.cyfbv6juemos.us-east-1.rds.amazonaws.com/genie", "genie",
          "noface2face");
    }
  }

}
