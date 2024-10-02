package naveena;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/TrainServlet")
public class TrainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setHeader("Access-Control-Allow-Methods", "GET, POST");
    	String trainIdStr = request.getParameter("train_id");
        System.out.println("Received train ID:" + trainIdStr);
        response.setContentType("text/plain");
        
        try {
            // Convert the train ID from String to Integer
            int trainId = Integer.parseInt(trainIdStr); 
            System.out.println("Id is" + trainId);

            Connection conn = DBConnection.getConnection();
            String sql = "SELECT train_name FROM trains WHERE train_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, trainId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String trainName = rs.getString("train_name");
                response.getWriter().write(trainName);
            } else {
                response.getWriter().write("Train not found");
            }
        } catch (NumberFormatException e) {
            response.getWriter().write("Invalid train ID");
        } catch (SQLException e) {
            e.printStackTrace();	
            response.getWriter().write("Error retrieving train name");
        }
    }

}
