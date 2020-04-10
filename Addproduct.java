

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Service
 */
@WebServlet("/Addproduct")
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 15)

public class Addproduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addproduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String price=request.getParameter("price");
		String product=request.getParameter("product");
		InputStream inputStream = null;
		 Part filePart = request.getPart("file");
	        if (filePart != null) {
	            // prints out some information for debugging
	                   
	            // obtains input stream of the upload file
	            inputStream = filePart.getInputStream();		
		String Status="Waiting";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/laptop","root","Haritha");
			Statement st=con.createStatement();
			PreparedStatement ps=con.prepareStatement(  
					"insert into addproduct values (default,?,?,?)");
					ps.setString(1,product);  
           
			ps.setString(2,price); 
			 if (inputStream != null) {
	                // fetches input stream of the upload file for the blob column
	                ps.setBlob(3, inputStream);
	            }
			

		
			          
			int i=ps.executeUpdate();  
			if(i>0)  {
				
				out.println("<html><head><title></title></head><body><script> window.alert('Admin Added Details successfully');window.location.href='addproduct.jsp';</script></body></html>");
				
            }
			}catch (Exception e2) {System.out.println(e2);}  
			          
			out.close();  
			}  
}}
