import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class LoadData
{
	public static void main(String[] args) throws Exception
	{
		loadData();
	}
	
	public static void loadData() throws Exception
	{		
		String url = "jdbc:sqlserver://sql04.ok.ubc.ca:1433;DatabaseName=db_jlabossi;";
		String uid = "jlabossi";
		String pw = "23976160";
		
		System.out.println("Connecting to database.");

		Connection con = DriverManager.getConnection(url, uid, pw);
				
		String fileName = "data/project_sql.ddl";
		
	    try
	    {
	        Statement stmt = con.createStatement();
	        
	        Scanner scanner = new Scanner(new File(fileName));
	        scanner.useDelimiter(";");
	        while (scanner.hasNext())
	        {
	            String command = scanner.next();
	            if (command.trim().equals(""))
	                continue;
	            System.out.println(command);       
	            try
	            {
	            	stmt.execute(command);
	            }
	            catch (Exception e)
	            {
	            	System.out.println(e);
	            }
	        }	 
	        scanner.close();
	    }
	    catch (Exception e)
	    {
	        System.out.println(e);
	    }   
	}
}
