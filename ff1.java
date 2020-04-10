

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

public class ff1 {
	public static boolean validate(String[] name,int num){  
		boolean status=false;  
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/e-grievance","root","Haritha"); 
			//String str = Arrays.toString(name);
			String str13 = String.join(" ", name);
			//String str2=str.replaceAll(",", " ");
			/*StringBuilder builder = new StringBuilder();
			for(String s : name) {
			    builder.append(s);
			}
			String str1 = builder.toString();*/
			//System.out.println("the name is " +str13);
			for(int i=0;i<num;i++){
				String a=name[i].toString();
				System.out.println(a);
			String sql="select * from words where water ='"+a+"'";
			//rs = statement.executeQuery(Data);
			PreparedStatement ps=con.prepareStatement(sql); 
			ResultSet rs=ps.executeQuery(); 
			/*status=rs.next();*/
			//System.out.println(sql);
			
			if (rs != null && rs.next()) {
				status=true;
				 Statement stmt = con.createStatement();
				String sql1="UPDATE user_query SET type='water',status='validated' WHERE query='"+str13+"'";
				 stmt.executeUpdate(sql1);
				// System.out.println(sql1);
				
            }
		
			/*if(ps.equals(a)){*/
					}
		//	status=rs.next();
		
		      
		/*ResultSet rs=ps.executeQuery();  */
		/*status=rs.next();  */
		          
		}catch(Exception e){System.out.println(e);}  
		return status;  
		}  
}
