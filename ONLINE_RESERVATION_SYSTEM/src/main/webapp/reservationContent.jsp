<!-- reservationContent.jsp -->
<!DOCTYPE html>
<%@page import="naveena.DBConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function fetchTrainName() {
            const trainId = $("#train_id").val();
            console.log(`Sending Train ID: ${trainId}`);
            if (trainId) {
                $.ajax({
                    url: `TrainServlet`,
                    type: 'GET',
                    data: { train_id: trainId },
                    success: function(responseText) {
                        console.log(`Response: ${responseText.trim()}`);
                        if (responseText.trim() !== "Train not found") {
                            $("#train_name").val(responseText.trim());
                        } else {
                            alert("Train not found.");
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error(`Error: ${xhr.status} - ${xhr.statusText}`);
                    }
                });
            } else {
                alert("Please select a valid train.");
            }
        }
    </script>
</head>
<body class="bg-gray-100">
    <div class="max-w-lg mx-auto mt-40 mb-20 bg-white p-8 rounded-lg shadow-lg">
        <h2 class="text-2xl font-bold mb-6 text-center">Make a Reservation</h2>
        <form action="ReservationServlet" method="POST" class="space-y-4">
            <div>
                <label for="train_id" class="block text-sm font-medium text-gray-700">Select Train</label>
                <select name="train_id" id="train_id" onchange="fetchTrainName()" class="w-full p-2 border border-gray-300 rounded-md" required>
                    <option value="">Select Train</option>
                    <%
                        Connection conn = null;
                        PreparedStatement pstmt = null;
                        ResultSet rs = null;
                        try {
                            conn = DBConnection.getConnection();
                            String sql = "SELECT train_id, train_number, train_name FROM trains";
                            pstmt = conn.prepareStatement(sql);
                            rs = pstmt.executeQuery();
                            while (rs.next()) {
                                int trainId = rs.getInt("train_id");
                                String trainNumber = rs.getString("train_number");
                                String trainName = rs.getString("train_name");
                    %>
                                <option value="<%= trainId %>"><%= trainNumber %> - <%= trainName %></option>
                    <%
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (rs != null) rs.close();
                            if (pstmt != null) pstmt.close();
                            if (conn != null) conn.close();
                        }
                    %>
                </select>
            </div>
            <div>
                <label for="train_name" class="block text-sm font-medium text-gray-700">Train Name</label>
                <input type="text" name="train_name" id="train_name" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm outline-none" readonly>
            </div>
            <div>
                <label for="class_type" class="block text-sm font-medium text-gray-700">Class Type</label>
                <select name="class_type" id="class_type" class="w-full p-2 border border-gray-300 rounded-md" required>
                    <option value="">Select Class</option>
                    <option value="1A">1A: First AC</option>
                    <option value="2A">2A: AC two tier</option>
                    <option value="3A">3A: AC three tier</option>
                    <option value="SL">SL: Sleeper class</option>
                    <option value="CC">CC: Chair car</option>
                    <option value="2S">2S: Second sitting</option>
                    <option value="EC">EC: Executive chair car</option>
                </select>
            </div>
            <div>
                <label for="date_of_journey" class="block text-sm font-medium text-gray-700">Date of Journey</label>
                <input type="date" name="date_of_journey" id="date_of_journey" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm" required>
            </div>
            <div>
                <label for="from_place" class="block text-sm font-medium text-gray-700">From</label>
                <input type="text" name="from_place" id="from_place" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm" required>
            </div>
            <div>
                <label for="to_place" class="block text-sm font-medium text-gray-700">To</label>
                <input type="text" name="to_place" id="to_place" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm" required>
            </div>
            <button type="submit" class="w-full bg-blue-500 text-white py-2 rounded-md">Reserve</button>
        </form>

        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
        <div class="mt-4 text-red-500">
            <%= message %>
        </div>
        <% } %>
    </div>
</body>
</html>
