package backend.controlators;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import backend.Usuario;

@WebServlet("/Vistas/Controlador-sesion")
public class ControladorSesion extends HttpServlet{
    /**
     * Serializacion de la clase ControladorSesion
     */
    private static final long serialVersionUID = -1342959523395979651L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Usuario usuario = new Usuario(request);
	boolean acceso = usuario.integro();
	String recurso="error.jsp";
	if(acceso) 
	    recurso= usuario.getRecurso();
	HttpSession sesion = request.getSession();
	sesion.setAttribute("nombre-usuario", usuario.getNombre());
	response.sendRedirect(recurso);
    }
}
