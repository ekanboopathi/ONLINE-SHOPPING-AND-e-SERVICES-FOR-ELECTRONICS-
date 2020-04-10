

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Adminpage
 */
@WebServlet("/Adminpage")
public class Adminpage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Adminpage() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		/*String imagePath = request.getParameter("id");
		//if(!imagePath.equals("Image Stored successfully"))
		//{
		imagePath = imagePath.replace('/','\\');
		String path = application.getRealPath("/");
		path = path+imagePath;
		System.out.println("path : "+ path);*/
		
		try
		{
			String item=request.getParameter("item_name");
			String name=request.getParameter("Name");
			String pass=request.getParameter("Password");
			String address=request.getParameter("Address");
			String image=request.getParameter("image");
			String amount=request.getParameter("amount");
			
			//File file=new File("D:\\.AMY Projects\\project1\\Laptop\\WebContent\\images\\m1.jpg");
			//File file=new File("V:\\Devi Folder\\New Updated Backup\\Laptop\\WebContent\\images\\m1.jpg");
			//V:\Devi Folder\New Updated Backup\Laptop\WebContent\images\m1.jpg
			//FileInputStream fis=new FileInputStream(file);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/laptop","root","Haritha");
			Statement st=con.createStatement();
			PreparedStatement ps=con.prepareStatement(  
					"insert into items values(default,?,?,?,?)"); 
			ps.setString(1,item);  
			ps.setString(2,name);  
			ps.setString(3,amount);  
			ps.setString(4,"Waiting");  
			//ps.setBinaryStream(5,fis,(int)file.length());

			
			          
			int i=ps.executeUpdate();  
			if(i>0)  
				out.println("<html><head><title></title></head><body><script> window.alert('User has successfully placed the Order!!');window.location.href='product.jsp';</script></body></html>");
			 
			          
			}catch (Exception e2) {System.out.println(e2);}  
			          
			out.close();  
			}  
}
