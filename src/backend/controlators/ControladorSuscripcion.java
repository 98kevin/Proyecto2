package backend.controlators;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.Revista;
import backend.SqlConection;

@WebServlet("/Vistas/controlador-suscripcion")
public class ControladorSuscripcion extends HttpServlet{
    
    /**
     * 
     */
    private static final long serialVersionUID = -2429548979049043139L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Revista revistaSeleccionada = new Revista().leerRevista((request.getParameter("codigo-revista")));
	request.getSession().setAttribute("revistaSuscripcion", revistaSeleccionada);
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Vistas/previsualizar-revista.jsp");
	  SqlConection sqlConn = new SqlConection();
	 String author  = sqlConn.leerAutorDeRevista(revistaSeleccionada.getCodigo());
	 request.setAttribute("autor", author);
	 int cantLikes = sqlConn.leerLikesDeRevista(revistaSeleccionada.getCodigo());
	 request.setAttribute("cant", cantLikes);
	    dispatcher.forward(request, response);
    }

}
