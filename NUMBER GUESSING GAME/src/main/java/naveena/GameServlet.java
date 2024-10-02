package naveena;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Random;

/**
 * Servlet implementation class GameServlet
 */
@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String action = request.getParameter("action");
		
		if("restart".equals(action))
		{
			restartGame(session);
		}
		else {
			int guess = Integer.parseInt(request.getParameter("guess"));
			
			playGame(session,guess,request);
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	private void restartGame(HttpSession session)
	{
		session.removeAttribute("targetNumber");
		session.removeAttribute("attempts");
	}
	
	private void playGame(HttpSession session, int guess, HttpServletRequest request)
	{
		Random random = new Random();
		
		Integer targetNumber = (Integer) session.getAttribute("targetNumber");
        Integer attempts = (Integer) session.getAttribute("attempts");
        Integer score = (Integer) session.getAttribute("score");

        if (targetNumber == null) {
        	
        	targetNumber = random.nextInt(100);
        	session.setAttribute("targetNumber", targetNumber);
        	attempts = 10;
        	session.setAttribute("attempts", attempts);
        }
        
        if(score==null)
        {
        	score = 0;
            session.setAttribute("score", score);
        }
        
        attempts--;
        
        session.setAttribute("attempts", attempts);
        
        String message;
        
        if(guess == targetNumber)
        {
        	 score = attempts +1 ;
        	 session.setAttribute("score", score);
        	 message = "Congratulations! You guessed the correct number!";
             restartGame(session); 
        }
        else if (guess>targetNumber) {
        	 message = "Too high! Try again.";
        } else {
            message = "Too low! Try again.";
        }
        
        request.setAttribute("message", message);
        request.setAttribute("attemptsLeft", attempts);
        request.setAttribute("score", score);
        
        if(attempts<=0)
        {
        	 message = "Sorry! You've used all your attempts. The correct number was " + targetNumber + ".";
             restartGame(session); 
             request.setAttribute("message", message);
        }
	}
	
	

}
