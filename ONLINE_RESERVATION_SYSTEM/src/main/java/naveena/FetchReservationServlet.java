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

@WebServlet("/FetchReservationServlet")
public class FetchReservationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pnrNumber = request.getParameter("pnr");
        Reservation reservation = null;

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT r.reservation_id, t.train_number, t.train_name, r.class_type, r.date_of_journey " +
                         "FROM reservations r JOIN trains t ON r.train_id = t.train_id " +
                         "WHERE r.pnr_number = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pnrNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Populate the Reservation object with the fetched data
                reservation = new Reservation();
                reservation.setReservationId(rs.getInt("reservation_id"));
                reservation.setTrainNumber(rs.getString("train_number"));
                reservation.setTrainName(rs.getString("train_name"));
                reservation.setClassType(rs.getString("class_type"));
                reservation.setDateOfJourney(rs.getDate("date_of_journey"));
                reservation.setPnrNumber(pnrNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Forward to confirmation page if reservation is found, else show an error
        if (reservation != null) {
            request.setAttribute("reservation", reservation);
            request.getRequestDispatcher("confirmCancellation.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "No reservation found for the provided PNR number.");
            request.getRequestDispatcher("/cancellation.jsp").forward(request, response);
        }
    }
}
