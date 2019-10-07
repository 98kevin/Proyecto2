package backend.controlators;

import java.io.IOException;
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

    @MultipartConfig(maxFileSize = 16777215, location="/home/kevin/eclipse-workspace/Proyecto2/WebContent/RevistasCargadas")    //16 Mb
    @WebServlet("/Vistas/carga-revista")
    public class CargaEdicionRevista extends HttpServlet{

        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// obtenemos la parte del arhivo subido  en esta parte multiple del request
	    SqlConection conexion = new SqlConection();
        	try {
        	    response.setContentType("text/html;charset=UTF-8");
                    Part part = request.getPart("file");
                    part.write(getFileName(part));
                    conexion.escribirPublicacion(request, getFileName(part)); //aca se escribe el registro del path en la BD
        	    response.sendRedirect("view-editor.jsp");
        	} catch (SQLException | ParseException  e) {
        	    request.setAttribute("error", true);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("RegistroUsuario.jsp");
                    dispatcher.forward(request, response);
        	    e.printStackTrace();
        	}
        }
	
	
	/**
	 * Devuleve el nombre del archivo de la parte que se esta subiendo
	 * @param part
	 * @return
	 */
	 public String getFileName(Part part) {
	        String contentHeader = part.getHeader("content-disposition");
	        String[] subHeaders = contentHeader.split(";");
	        for(String current : subHeaders) {
	            if(current.trim().startsWith("filename")) {
	                int pos = current.indexOf('=');
	                String fileName = current.substring(pos+1);
	                return fileName.replace("\"", "");
	            }
	        }
	        return null;
	    }
}

