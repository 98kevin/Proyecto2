package backend.controlators;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import backend.SqlConection;
import exceptions.ErrorCreacionUsuario;

    @MultipartConfig(maxFileSize = 16777215)    //16 Mb
    @WebServlet("/Vistas/carga-revista")
    public class CargaEdicionRevista extends HttpServlet{

        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// obtenemos la parte del arhivo subido  en esta parte multiple del request
        Part filePart = request.getPart("file"); //Retrieves <input type="file" name="file">
        InputStream contenidoRevista = filePart.getInputStream();
    	try {
    	    SqlConection conexion = new SqlConection();
    	    conexion.escribirPublicacion(request, contenidoRevista);
    	    response.sendRedirect("view-editor.jsp");
    	} catch (SQLException | ParseException  e) {
    	    request.setAttribute("error", true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("RegistroUsuario.jsp");
                dispatcher.forward(request, response);
    	    e.printStackTrace();
    	}
        }
}

