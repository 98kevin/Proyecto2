package backend.controlators;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.Usuario;

@WebServlet("/Controlador-sesion")
public class ControladorSesion extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Usuario usuario = new Usuario(request);
	RequestDispatcher dispatcher = request.getRequestDispatcher("resultadoInicioDeSesion.jsp");
	//TODO implementar el inicio de sesion
	dispatcher.forward(request, response);
    }

}
