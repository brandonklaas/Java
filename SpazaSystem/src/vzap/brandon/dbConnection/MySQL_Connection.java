package vzap.brandon.dbConnection;
import java.io.*;
import java.sql.*;
import java.util.Properties;

public class MySQL_Connection {
	
	private Connection connection = null;
	private String psw = null;
	private String url = null;
	private String user = null;
	private String dbName = null;
	
	public MySQL_Connection()
	{
		try
		{
			InputStream input = new FileInputStream("resources/spazaDB.properties");
			Properties prop = new Properties();
			
			//Load a properties
			prop.load(input);
			
			psw = prop.getProperty("password");
			url = prop.getProperty("url");
			user = prop.getProperty("username");
			dbName = prop.getProperty("dbName");
			
			url = url+dbName;
			
			//1 Register the driver
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Registered.....>>>>>>");
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public boolean closeConnection()
	{
		try {
			if (connection != null) {
				connection.close();
			}
			System.out.println("Connection closed.......>>>>>>");
			return true;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public Connection getConnection() throws SQLException
	{
		connection = DriverManager.getConnection(url,user,psw);
		return connection;
	}
	
	public static void main(String [] args)
	{
		MySQL_Connection mc = new MySQL_Connection();
		
		try {
			Connection con = mc.getConnection();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		mc.closeConnection();
	}
	
}
