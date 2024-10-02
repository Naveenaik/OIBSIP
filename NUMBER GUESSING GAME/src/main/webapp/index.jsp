<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Guess the Number Game</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen flex flex-col justify-center items-center">

    <div class="mb-10">
        <h1 class="font-extrabold text-4xl text-gray-800">Welcome to the Guess the Number Game!</h1>
    </div>


    <div class="bg-white mx-4 p-10 border border-gray-300 shadow-lg rounded-lg w-full max-w-md">

        <div class="mb-8">
            <form action="GameServlet" method="post" class="flex flex-col gap-4">
                <label for="guess" class="text-xl font-medium text-gray-700">Enter your guess (1-100):</label>
                <input id="guess" name="guess" type="number" min="1" max="100"
                    class="text-lg px-4 py-2 border border-gray-300 rounded-md w-full shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                    required>
                <button type="submit"
                    class="py-2 px-4 bg-blue-600 text-white rounded-md hover:bg-blue-500 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                    Enter
                </button>
            </form>
        </div>


        <div class="mb-8">
            <form action="GameServlet" method="post">
                <input type="hidden" name="action" value="restart">
                <button type="submit"
                    class="w-full py-2 px-4 bg-red-600 text-white rounded-md hover:bg-red-500 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500">
                    Restart
                </button>
            </form>
        </div>


        <div class="text-center">
            <h3 class="text-lg font-semibold text-gray-800">
                <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
            </h3>
            <h3 class="text-lg font-semibold text-gray-800">
            	Your score is : 
                <%= request.getAttribute("score") != null ? request.getAttribute("score") : "" %>
            </h3>
            <h4 class="text-md text-gray-600">
                Attempts Left: <%= request.getAttribute("attemptsLeft") != null ? request.getAttribute("attemptsLeft") : "" %>
            </h4>
        </div>
    </div>
</body>
</html>
