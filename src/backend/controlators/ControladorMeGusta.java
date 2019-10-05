package backend.controlators;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Vistas/suscriptor/") //la hubicacion de la vista jsp que llamara al servlet
public class ControladorMeGusta extends HttpServlet{

    
    /**
     * 
     */
    private static final long serialVersionUID = 7054338174917218934L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
	
    }
}
