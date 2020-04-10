

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
        PrintWriter out = response.getWriter(); 
        HttpSession session = request.getSession(false);

      //ss  HttpSession session1 = request.getSession();
        String fid=request.getParameter("Name");
		 session.setAttribute("Name", fid);
        String n=request.getParameter("Name");  
        session.setAttribute("Name", n);
        String p=request.getParameter("Password"); 
        String address=request.getParameter("Address"); 
        session.setAttribute("Address", address);
        String p1=request.getParameter("username"); 
        session.setAttribute("username", p1);

        System.out.println("the address is "+address);
         session = request.getSession(false);
        if(session!=null)
        session.setAttribute("name", n);
        if(n.equals("admin@gmail.com")&&(p.equals("admin"))){  
			out.println("<html><head><title></title></head><body><script> window.alert('Admin login successful');window.location.href='Admin.jsp';</script></body></html>");

        	
        }  
        else if(LoginDao.validate(n, p)){  
			out.println("<html><head><title></title></head><body><script> window.alert('User login successful');window.location.href='Home1.jsp';</script></body></html>");

        	
        }  
         
        else{  
			out.println("<html><head><title></title></head><body><script> window.alert('login unsuccessful');window.location.href='index.jsp';</script></body></html>");

        	
        }  

        out.close();  
    }  
} 