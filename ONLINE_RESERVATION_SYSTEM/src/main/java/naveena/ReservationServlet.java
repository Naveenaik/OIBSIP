package naveena;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    Connection conn;

    public ReservationServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	HttpSession session = request.getSession(false);
    	
    	if(session!=null)
    	{
    		Integer userId = (Integer) session.getAttribute("userId");
    		System.out.println(userId);
    		
    		if(userId!=null)
    		{
    			String trainIdStr = request.getParameter("train_id");
    			int trainId = Integer.parseInt(trainIdStr);
    	        String classType = request.getParameter("class_type");
    	        String dateOfJourney = request.getParameter("date_of_journey");
    	        String fromPlace = request.getParameter("from_place");
    	        String toPlace = request.getParameter("to_place");
    	        String pnrNumber = generatePNR(); // Simulate PNR generation logic
    	        
    	        LocalDate date = LocalDate.parse(dateOfJourney);

    	        try {
    	            conn = DBConnection.getConnection();
    	            String sql = "INSERT INTO reservations (train_id,user_id,class_type, date_of_journey, from_place, to_place, pnr_number) " +
    	                         "VALUES (?,?, ?, ?, ?, ?, ?)";
    	            PreparedStatement pstmt = conn.prepareStatement(sql);
    	            pstmt.setInt(1, trainId);
    	            pstmt.setInt(2, userId);
    	            pstmt.setString(3, classType);
    	            pstmt.setDate(4, java.sql.Date.valueOf(date));
    	            pstmt.setString(5, fromPlace);
    	            pstmt.setString(6, toPlace);
    	            pstmt.setString(7, pnrNumber);

    	            int rowsInserted = pstmt.executeUpdate();
    	            if (rowsInserted > 0) {
    	                request.setAttribute("message", "Reservation successful! Your PNR: " + pnrNumber);
    	                request.getRequestDispatcher("thankyou.jsp").forward(request, response);
    	            } else {
    	                request.setAttribute("message", "Reservation failed. Please try again.");
    	                request.getRequestDispatcher("reservation.jsp").forward(request, response);
    	            }
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	            request.setAttribute("message", "An error occurred while making the reservation.");
    	            request.getRequestDispatcher("reservation.jsp").forward(request, response);
    	        }
    		}
    		else {
    			System.out.println("from else");
    			request.getRequestDispatcher("reservation.jsp").forward(request, response);
			}
    	}
    	else {
    		response.sendRedirect("login.jsp");
		}
        
    }

    private String generatePNR() {
        // Simulate PNR generation (use random logic)
        return "PNR" + (int)(Math.random() * 1000000);
    }
}
