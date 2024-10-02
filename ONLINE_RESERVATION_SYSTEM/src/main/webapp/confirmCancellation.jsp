<%@page import="naveena.Reservation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Confirm Cancellation</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
    <div class="max-w-lg mx-auto mt-10 bg-white p-8 rounded-lg shadow-lg">
        <h2 class="text-2xl font-bold mb-6 text-center">Confirm Cancellation</h2>
        
        <%
            Reservation reservation = (Reservation) request.getAttribute("reservation");
            if (reservation != null) {
        %>
            <p><strong>Train Number:</strong> <%= reservation.getTrainNumber() %></p>
            <p><strong>Train Name:</strong> <%= reservation.getTrainName() %></p>
            <p><strong>Class Type:</strong> <%= reservation.getClassType() %></p>
            <p><strong>Date of Journey:</strong> <%= reservation.getDateOfJourney() %></p>
            <p><strong>PNR Number:</strong> <%= reservation.getPnrNumber() %></p>
            
            <form action="CancelReservationServlet" method="post">
                <input type="hidden" name="reservationId" value="<%= reservation.getReservationId() %>">
                <button type="submit" class="mt-4 w-full bg-red-500 text-white py-2 rounded-md">Confirm Cancellation</button>
            </form>
        <%
            } else {
        %>
            <p>No reservation details available.</p>
        <%
            }
        %>
        <p class="mt-4 text-center text-sm text-gray-600"><a href="cancellation.jsp" class="text-indigo-600 hover:text-indigo-500">Go Back</a></p>
    </div>
</body>
</html>
