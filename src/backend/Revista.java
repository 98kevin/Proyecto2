package backend;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class Revista {
    String nombre; 
    String descripcion; 
    String etiquetas; 
    double cuotaSuscripcion; 
    double costoDiario;
    int categoria; 
    int editor;


    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }



    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }



    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    /**
     * @return the etiquetas
     */
    public String getEtiquetas() {
        return etiquetas;
    }



    /**
     * @param etiquetas the etiquetas to set
     */
    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }



    /**
     * @return the cuotaSuscripcion
     */
    public double getCuotaSuscripcion() {
        return cuotaSuscripcion;
    }



    /**
     * @param cuotaSuscripcion the cuotaSuscripcion to set
     */
    public void setCuotaSuscripcion(double cuotaSuscripcion) {
        this.cuotaSuscripcion = cuotaSuscripcion;
    }



    /**
     * @return the costoDiario
     */
    public double getCostoDiario() {
        return costoDiario;
    }



    /**
     * @param costoDiario the costoDiario to set
     */
    public void setCostoDiario(double costoDiario) {
        this.costoDiario = costoDiario;
    }



    /**
     * @return the categoria
     */
    public int getCategoria() {
        return categoria;
    }



    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }



    /**
     * @return the editor
     */
    public int getEditor() {
        return editor;
    }



    /**
     * @param editor the editor to set
     */
    public void setEditor(int editor) {
        this.editor = editor;
    }

    
    public Revista (HttpServletRequest req) {
	super();
	this.nombre = req.getParameter("nombre");
	this.descripcion = req.getParameter("descripcion");
	this.etiquetas = req.getParameter("etiquetas");
	this.cuotaSuscripcion = Double.parseDouble(req.getParameter("cuotaDeSuscripcion"));
	this.categoria = Integer.parseInt(req.getParameter("categoria"));
	this.editor = getCodigoEditor((String) req.getSession().getAttribute("email"));
    }
    
    public int getCodigoEditor(String email) {
	SqlConection conexion = new SqlConection();
	return conexion.consultarEditor(email);
    }



    public PreparedStatement crearSentencia(int siguienteRevista, int siguientesPermisos) throws SQLException {
	    String sqlUser = "INSERT INTO Revista (codigo, nombre, descripcion, etiquetas, cuota_suscripcion, id_permisos, costo_por_dia, id_Categoria, id_editor) "
	    	+ "values (?,?,?,?,?,?,?,?,?)";
	    PreparedStatement statementRevista = SqlConection.getConexion().prepareStatement(sqlUser);
	    	//Siguiente registro en la base de datos
	        statementRevista.setInt(1, siguienteRevista);
	        statementRevista.setString(2, this.getNombre());
	        statementRevista.setString(3, this.getDescripcion());
	        statementRevista.setString(4, this.getEtiquetas());
	        statementRevista.setDouble(5, this.getCuotaSuscripcion());
	        statementRevista.setInt(6, siguientesPermisos);
	        statementRevista.setDouble(7, 1);  //costo sin asingar
	        statementRevista.setInt(8, this.getCategoria());
	        statementRevista.setInt(9, this.getEditor());
	        System.out.println("Sentencia de revista creada");
	   return statementRevista;
    }
}
