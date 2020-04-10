	
 
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.*;

import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




/**
 * Servlet implementation class Sregister
 */
@WebServlet("/Sregister")
public class Sregister extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sregister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		PrintWriter out=response.getWriter();
		String user1=request.getParameter("Name");
		String user=user1.replace(" ", "");
		String email=request.getParameter("Email");
		session.setAttribute("id2", email);
		String pass=request.getParameter("Password");
		String conpass=request.getParameter("Confirm Password");
		String address=request.getParameter("Address");
		session.setAttribute("Address", address);
		String ph=request.getParameter("Phone");
		session.setAttribute("Phone", ph);
		//String pass=getSaltString();
		String strt=null,building=null,door=null,city=null,area=null;
		
		
		ResultSet rs7=null;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/laptop","root","Haritha");
			Statement st=con.createStatement();
			PreparedStatement ps=con.prepareStatement("insert into register values(default,?,?,?,?,?)"); 
			ps.setString(1,user);  
			ps.setString(2,pass);  
			ps.setString(3,email);  
			ps.setString(4,ph);  
			ps.setString(5,address); 
			int i=ps.executeUpdate();  
			if(i>0)  {
				
				
				Statement st7 = con.createStatement();	
				//String uid = (String)session.getAttribute("uid");

				String qry3="select * from address";
					rs7=st7.executeQuery(qry3);
					
						while(rs7.next())
					{
					
							door=rs7.getString("door");
							building=rs7.getString("building");
							strt=rs7.getString("strt");
							area=rs7.getString("area");
							city=rs7.getString("city");


			String addr1=door+","+building+","+"\n"+strt+","+area+","+"\n"+city+".";
/*			String addr=address.indexOf().replaceAll(",", "\n") ;
*/
		

				SendEmail sendmail=new SendEmail();
		 		//sendmail.setmailid(email);
		 		sendmail.setpass(user,pass,email,addr1);
		 		sendmail.email();
			 //request.setAttribute("msg","Student Registered");
			//RequestDispatcher rd=request.getServletContext().getRequestDispatcher("/sregister.jsp");
			String message = "Customer Registered";
			//response.sendRedirect("sregister.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
			//String message = "hello";
			request.getSession().setAttribute("message", message);
			response.sendRedirect("index.jsp");
			
		}}
		else
		{
			request.setAttribute("msg","Sorry.Check connections");
		}
	}
	catch(Exception e){
		out.println(e);
		
	}
	
}
protected String getSaltString() {
    String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
    StringBuilder salt = new StringBuilder();
    Random rnd = new Random();
    while (salt.length() < 8) {
        int index = (int) (rnd.nextFloat() * SALTCHARS.length());
        salt.append(SALTCHARS.charAt(index));
    }
    String saltStr = salt.toString();
    return saltStr;

}
}
