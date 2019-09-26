package backend.controlators;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.Usuario;


@WebServlet("/Inicio")
public class Login extends HttpServlet{
	/**
     * Serializacion de la clase Login
     */
    private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    Usuario user = new Usuario(email, password);
	    if(user.integro()) {
	            try {
	        	 RequestDispatcher dispatcher = request.getRequestDispatcher("incio.jsp");
	        	 dispatcher.forward(request, response);
		    } catch (ServletException | IOException e) {
			e.printStackTrace();
		    }
	    }
		
	}
}
