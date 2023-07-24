package projet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
         
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		try {
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_jee", "root", "");
			 String n=request.getParameter("txtname");
			 String p=request.getParameter("txtmdp");
			 PreparedStatement ps=con.prepareStatement("select username from table_login where username=? and mdp =?");
			 ps.setString(1, n);
			 ps.setString(2, p);
			 ResultSet rs =ps.executeQuery();
			 if (rs.next()) {
				 RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
				 rd.forward(request, response);
				 
			}else {
				out.println("<font color=red size=18>Login Failed!!<br>");
				out.println("<a href=login.jsp>Try AGAIN!!</a>");
				
			}
			 
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

} 
