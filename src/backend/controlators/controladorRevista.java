package backend.controlators;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.Revista;

@WebServlet("/Vistas/controlador-revista")
public class controladorRevista extends HttpServlet{
    
    /**
     * 
     */
    private static final long serialVersionUID = 7610949573158285773L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getParameter("codiogoRevistaSeleccionada");
			System.out.println("Usando el servlet controlador revista");
			Revista revistaSeleccionada = new Revista().leerRevista((request.getParameter("seleccion-revista")));
			System.out.println("Codigo de la revisa en el controladorRevista "+revistaSeleccionada);
			request.getSession().setAttribute("revista", revistaSeleccionada);
			response.sendRedirect("vista-revista-editor.jsp");
    }

}
