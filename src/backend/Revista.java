package backend;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class Revista implements Serializable {
    int codigo;

    private String nombre; 
    private String descripcion; 
    private String etiquetas; 
    private double cuotaSuscripcion; 
    private double costoDiario;
    private int categoria; 
    private int editor;
     
    
    public Revista() {
	super();
    }
    
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    /**
     * Crea una nueva revista a partir de un formulario de campos
     * @param req
     */
    public Revista (HttpServletRequest req) {
	super();
	this.nombre = req.getParameter("nombre");
	this.descripcion = req.getParameter("descripcion");
	this.etiquetas = req.getParameter("etiquetas");
	this.cuotaSuscripcion = Double.parseDouble(req.getParameter("cuotaDeSuscripcion"));
	this.categoria = Integer.parseInt(req.getParameter("categoria"));
	this.editor = getCodigoEditor((String) req.getSession().getAttribute("email"));
    }
    
    /**
     * Crea una revista a partir de un resultado sql
     * @param resultado
     */
    public Revista(ResultSet resultado) {
	try {
	    this.codigo = resultado.getInt(1);
	    this.nombre= resultado.getString(2);
	    this.descripcion= resultado.getString(3);
	    this.etiquetas = resultado.getString(4);
	    this.cuotaSuscripcion = resultado.getDouble(5);
	    this.costoDiario= resultado.getDouble(6);
	    this.categoria= resultado.getInt(7);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }


    public Revista leerRevista(String idRevista) {
	int id = getNumeros(idRevista);
	ResultSet resultado;
	Revista revista= null;
	String sql = "SELECT * FROM Revista WHERE codigo=?";
	try {
	    PreparedStatement statementRevista = SqlConection.getConexion().prepareStatement(sql);
	    statementRevista.setInt(1, id);
	    resultado = statementRevista.executeQuery();
	    resultado.next();
	    revista = new Revista(resultado);
	} catch (SQLException |NumberFormatException e) {
	    e.printStackTrace();
	}
	System.out.println("Revista leida en la base de datos: "+ revista);
	return revista;
    }

    private int getNumeros(String idRevista) {
	char [] cadena = idRevista.toCharArray();
	String n ="";
	for (int i = 0; i < cadena.length; i++) {
	    if(Character.isDigit(cadena[i]))
		n+=cadena[i];
	}
	return Integer.parseInt(n);
    }

    public int getCodigoEditor(String email) {
	SqlConection conexion = new SqlConection();
	return conexion.consultarCodigoUsuario(email);
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
