package utils;

import utils.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QueryExecutor {
	
	  public static String getDateString(Date date){
		  DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
		  String dateString = dateFormat.format(date);  
		  return dateString;
	  }

	  public static int validateUser(int passcode) {

		  Connection c = null;
		  ResultSet rs;
		  try {
			  c = DBConnector.getInstance();
			  Statement stmt = c.createStatement();
			  rs = stmt.executeQuery("select ORGANIZATION_ID from USR_USERS where PASSWORD = " + passcode);
			  if(rs.next()) {
				  return Integer.parseInt(rs.getString("ORGANIZATION_ID"));
			  }
		  } catch (SQLException e) {
			  e.printStackTrace();
		  } catch (ClassNotFoundException e) {
			  e.printStackTrace();
		  }
		  return -1;
	  }
	
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
  
  public static int getOrganizationId(String username) throws SQLException, ClassNotFoundException {
	    try {
	      Connection c =  DBConnector.getInstance();
	      Statement stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery("select * from USR_USERS");
	      if(rs.next()){
	        return rs.getInt("ORGANIZATION_ID");
	      }
	      return -1;
	    }catch(Exception e){
	      System.out.println(e);}
	    return -2;
	  }
    
  public static String getMerchantSummaries(int organizationId){
	  try{
		  Connection c = DBConnector.getInstance();
		  Statement stmt = c.createStatement();
		  ResultSet rs = stmt.executeQuery("SELECT TXN_COUNT, SUCCESS_RATE " +
				  								"FROM genie.TRANSACTION_SUMMARY " +
				  								"WHERE TXN_DATE = curdate() " +
											    "AND ORGANIZATION_ID = " + organizationId);
		  if(rs.next()){
			  return "today you have total of "
					  + rs.getString("TXN_COUNT")
					  + " transactions with a new approval rate of "
					  + rs.getString("SUCCESS_RATE");
		  }
	  }catch(Exception e){
		  System.out.println(e);
	  }

	  return "your transactions are yet to be reflected in the system please try again later";
  }

	public static String getMerchantSummaries(int organizationId, int day){
		try{
			Connection c = DBConnector.getInstance();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT SUM(TXN_COUNT) AS TXN_COUNT, SUM(SUCCESS_RATE)/COUNT(*) as SUCCESS_RATE " +
					"FROM genie.TRANSACTION_SUMMARY " +
					"WHERE TXN_DATE > curdate() - " + day +
					" AND ORGANIZATION_ID = " + organizationId);
			if(rs.next()){
				return "today you have total of "
						+ rs.getString("TXN_COUNT")
						+ " transactions with a new approval rate of "
						+ (int)Double.parseDouble(rs.getString("SUCCESS_RATE"));
			}
		}catch(Exception e){
			System.out.println(e);
		}

		return "your transactions are yet to be reflected in the system please try again later";
	}

	public static String getAdminSummaries(){
		try{
			Connection c = DBConnector.getInstance();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT SUM(TXN_COUNT) as total,SUM(SUCCESS_RATE)/count(*) as average " +
					"FROM genie.TRANSACTION_SUMMARY " +
					"WHERE TXN_DATE = curdate()");
			if(rs.next()){
				return "today there are "
						+ rs.getString("total")
						+ " transactions with a average approval rate of "
						+ (int)Double.parseDouble(rs.getString("average"));
			}
		}catch(Exception e){
			System.out.println(e);
		}

		return "your transactions are yet to be reflected in the system please try again later";
	}

	public static String getAdminSummaries(int day){
		try{
			Connection c = DBConnector.getInstance();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT SUM(TXN_COUNT) as total,SUM(SUCCESS_RATE)/count(*) as average " +
					"FROM genie.TRANSACTION_SUMMARY " +
					"WHERE TXN_DATE > curdate() - " + day);
			if(rs.next()){
				return "today there are "
						+ rs.getString("total")
						+ " transactions with a average approval rate of "
						+ (int)Double.parseDouble(rs.getString("average"));
			}
		}catch(Exception e){
			System.out.println(e);
		}

		return "your transactions are yet to be reflected in the system please try again later";
	}


	public static String getAdminSummaries(String datestring){
	  try{
		  Connection c = DBConnector.getInstance();
		  Statement stmt = c.createStatement();
		  ResultSet rs = stmt.executeQuery("select sum(TRANSACTION_SUMMARY.TXN_COUNT) as txn_cnt, sum(TRANSACTION_SUMMARY.APPROVED_TXN) as aprv_txns from TRANSACTION_SUMMARY where TRANSACTION_SUMMARY.TXN_DATE>="+datestring);
		  if(rs.next()){
			  return rs.getInt("txn_cnt")+" "+rs.getInt("aprv_txns");
		  }
		  return "invalid user";
	  }catch(Exception e){
		  System.out.println(e);
	  }
	  return "invalid user";  
  }
  
  public static String topXByTxnCount(int numResults){
	  try{
		  StringBuilder result = new StringBuilder();
		  Connection c = DBConnector.getInstance();
		  Statement stmt = c.createStatement();
		  ResultSet rs = stmt.executeQuery("Select ORGANIZATION_ID, sum(TXN_COUNT) as sumTxns from TRANSACTION_SUMMARY group by ORGANIZATION_ID order by TXN_COUNT desc limit "+numResults);
		  while(rs.next()){
			  result.append(rs.getString("ORGANIZATION_ID")+" "+rs.getInt("sumTxns")+"\n");
		  }
		  return result.toString();
	  }catch(Exception e){
		  System.out.println(e);
	  }
	  return "invalid user"; 
  }
  
  public static String worstXByApprovalRate(int numResults){
	  try{
		  StringBuilder result = new StringBuilder();
		  Connection c = DBConnector.getInstance();
		  Statement stmt = c.createStatement();
		  ResultSet rs = stmt.executeQuery("Select ORGANIZATION_ID, sum(APPROVED_TXN)/sum(TXN_COUNT) as rate from TRANSACTION_SUMMARY group by ORGANIZATION_ID order by rate asc limit "+numResults);
		  while(rs.next()){
			  result.append(rs.getString("ORGANIZATION_ID")+" "+rs.getDouble("rate")+"\n");
		  }
		  return result.toString();
	  }catch(Exception e){
		  System.out.println(e);
	  }
	  return "invalid user";  
  }
  
  public static String getNumOpenEvents(){
	  try{
		  Connection c = DBConnector.getInstance();
		  Statement stmt = c.createStatement();
		  ResultSet rs = stmt.executeQuery("select count(*) as count from EVENT where EVENT_STATUS = 'open'");
		  if(rs.next()){
			  return Integer.toString(rs.getInt("count"));
		  }
		  return "invalid user";
	  }catch(Exception e){
		  System.out.println(e);
	  }
	  return "invalid user"; 
  }  
}
