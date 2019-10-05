package backend.controlators;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.SqlConection;

@WebServlet("/Vistas/nueva-suscripcion")
public class NuevaSuscripcion extends HttpServlet{

    /**
     * Codigo de serializacion
     */
    private static final long serialVersionUID = -8396485132459009949L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
	    SqlConection conexion = new SqlConection();
	    conexion.nuevaSuscripcion(request);
	    response.sendRedirect("view-editor.jsp");
	} catch (SQLException | ParseException  e) {
	    request.setAttribute("error", true);
	    e.printStackTrace();
	}
    }
}