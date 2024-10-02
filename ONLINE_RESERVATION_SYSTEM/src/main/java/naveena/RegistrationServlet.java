package naveena;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	Connection conn;
	
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username == null || password == null || username.isEmpty() || password.isEmpty())
		{
			response.getWriter().write("Valid username and password are required");
		}
		
		try {
			conn = DBConnection.getConnection();
			
			String  sql = "INSERT INTO users (username,password) VALUES (?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, username);
            pstmt.setString(2, password);
            
			int rowsInserted = pstmt.executeUpdate();
			
			if (rowsInserted > 0) {
				
				request.setAttribute("message", "Registration successful! Please login.");
				request.getRequestDispatcher("/registration.jsp").forward(request, response);
            } else {
                response.getWriter().write("Registration failed. Please try again.");
            }
			
		} catch (SQLException e) {
			if (e.getSQLState().equals("23505")) {  // SQL state code for unique violation in PostgreSQL
                response.getWriter().write("Username already exists. Please choose a different one.");
            } else {
                e.printStackTrace();
                response.getWriter().write("An error occurred. Please try again later.");
            }
		}
	}

}
