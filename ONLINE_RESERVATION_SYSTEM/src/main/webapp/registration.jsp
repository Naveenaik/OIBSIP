<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
    <script src="https://cdn.tailwindcss.com?plugins=forms,typography,aspect-ratio,line-clamp,container-queries"></script>
	<script>
		function showAlert(message)
		{
			if(message)
			{
				alert(message);
				window.loction.href = "login.jsp";
			}
		}
	</script>
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
    <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
        <h2 class="text-2xl font-semibold mb-6">Registration</h2>
        <form action="RegistrationServlet" method="post" class="space-y-4">
            <div>
                <label for="username" class="block text-sm font-medium text-gray-700">Username</label>
                <input type="text" id="username" name="username" required
                    class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
            </div>
            <div>
                <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
                <input type="password" id="password" name="password" required
                    class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
            </div>
            <button type="submit"
                class="w-full py-2 px-4 bg-gray-800 text-white rounded-md hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500">
                Registration
            </button>
        </form>
        <p class="mt-4 text-center text-sm text-gray-600">Already have an account? <a href="login.jsp" class="text-indigo-600 hover:text-indigo-500">Login here</a></p>
    </div>
    
    <% 
    String message = (String) request.getAttribute("message");
    if(message!=null)
    {
    %>
    	<script>
    		showAlert("<%= message %>");
    	</script>
    <%	
    }
    %>
</body>
</html>