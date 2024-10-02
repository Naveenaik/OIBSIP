package naveena;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/CancelReservationServlet")
public class CancelReservationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reservationId = Integer.parseInt(request.getParameter("reservationId"));

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM reservations WHERE reservation_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reservationId);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/cancellation.jsp").forward(request, response);
    }
}
