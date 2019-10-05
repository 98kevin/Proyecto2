package backend.controlators;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.Archivos;
import backend.Revista;
import backend.SqlConection;

@WebServlet("/Vistas/controladorNuevaRevista")
public class controladorNuevaRevista extends HttpServlet{
    /**
     * 
     */
    private static final long serialVersionUID = 3226936797994870385L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Revista revista = new Revista(request);
	try {
	    SqlConection conexion = new SqlConection();
	    conexion.escribirNuevaRevista(revista);
	    Archivos.generarJspRevista(revista);
	    request.setAttribute("exito", true);
	    response.sendRedirect("view-editor.jsp");
	} catch (SQLException  e) {
	    request.setAttribute("error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
	    e.printStackTrace();
	}
    }

}