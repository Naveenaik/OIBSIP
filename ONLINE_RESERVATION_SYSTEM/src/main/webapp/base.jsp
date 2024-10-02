<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><%= request.getAttribute("title") != null ? request.getAttribute("title") : "Default Title" %></title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body class="bg-gray-100">
    <header>
        <nav class="bg-white p-4 shadow-md fixed top-0 w-full">
            <ul class="flex space-x-4">
                <li><a href="reservation.jsp" class="text-blue-500">Reservation Form</a></li>
                <li><a href="cancellation.jsp" class="text-blue-500"> Cancellation Form</a></li>
            </ul>
        </nav>
    </header>

    <main class="mt-6">
      <jsp:include page="${contentPage}" />
    </main>

    <footer class="fixed bottom-0 left-0 w-full text-center py-4 bg-white">
    	<p>&copy; 2024 Developed by Naveena. All rights reserved.</p>
	</footer>

</body>
</html>
