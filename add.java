

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class add
 */
@WebServlet("/add")
public class add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public add() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		String no=request.getParameter("no");
		String name=request.getParameter("name");
		String strt=request.getParameter("strt");
		String area=request.getParameter("area");
		String city=request.getParameter("city");

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/laptop","root","Haritha");
			Statement st=con.createStatement();
			PreparedStatement ps=con.prepareStatement(  
					"insert into address values (default,?,?,?,?,?)");
					ps.setString(1,no); 
					ps.setString(2,name); 
					ps.setString(3,strt); 
					ps.setString(4,area); 
					ps.setString(5,city); 
           
			
		
			int i=ps.executeUpdate();  
			if(i>0)  {
				
				out.println("<html><head><title></title></head><body><script> window.alert('Your Address to be Register successfully');window.location.href='Admin.jsp';</script></body></html>");
				
            }
			}catch (Exception e2) {System.out.println(e2);}  
			          
			out.close();  
			}  
}