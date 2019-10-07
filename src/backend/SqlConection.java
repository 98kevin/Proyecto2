package backend;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import exceptions.ErrorCreacionUsuario;

public class SqlConection {
    
    static Connection conexion;
    
    private final static String SQL_USER="admin-revistas";
    private final static String PASSWORD="12345";
    private final static String SQL_PORT="jdbc:mysql://localhost:3306/";
    private final static String DATABASE_NAME="Revistas";
    
    public SqlConection() {
	try {
	    if(conexion==null) {
		Class.forName("org.mariadb.jdbc.Driver"); 
		conexion = DriverManager.getConnection(SQL_PORT+DATABASE_NAME, SQL_USER, PASSWORD);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * 
     * @return
     */
    public static ArrayList<Revista> leerRevistas(){
	ArrayList<Revista> revistas= null;
	ResultSet resultado = null;
		String sqlInstruccion = "SELECT * FROM Revista";
		new SqlConection();
		 try {
		    PreparedStatement sentencia = conexion.prepareStatement(sqlInstruccion);
		    resultado = sentencia.executeQuery();
		    revistas = new ArrayList<Revista>();
		    while (resultado.next()) {
			Revista revista = new Revista(resultado);
			revistas.add(revista);
		    }
		} catch (SQLException e) {
		    System.out.println(e.getCause());
		    //e.printStackTrace();
		}
	return revistas;
    }
    
    
    
	 /**
     * Lee todas las revistas que se encuentran en la base de datos segun el codigo de usuario
     * @param codigo
     * @return
     */
    public static ArrayList<Revista> leerRevistasSuscritas(int codigo) {
	ArrayList<Revista> revistas= null;; 
	ResultSet resultado = null;
	Revista revista = new Revista();
		String sqlInstruccion = "SELECT codigo_revista FROM Suscripcion WHERE idUsuario=?";
		 try {
		    PreparedStatement sentencia = conexion.prepareStatement(sqlInstruccion);
		    sentencia.setInt(1, codigo);
		    resultado = sentencia.executeQuery();
		    revistas = new ArrayList<Revista>();
		    while (resultado.next()) {
			Revista rev = revista.leerRevista(resultado.getString(1));
			revistas.add(rev);
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	return revistas;
    }
    
    
    
    /**
     * Lee todas las revistas que se encuentran en la base de datos
     * @param codigo
     * @return
     */
    public static ArrayList<Revista> leerRevistasDeEditor(int codigo) {
	ArrayList<Revista> revistas= null;; 
	ResultSet resultado = null;
		String sqlInstruccion = "SELECT * FROM Revista WHERE id_editor=?";
		new SqlConection();
		 try {
		    PreparedStatement sentencia = SqlConection.conexion.prepareStatement(sqlInstruccion);
		    sentencia.setInt(1, codigo);
		    resultado = sentencia.executeQuery();
		    revistas = new ArrayList<Revista>();
		    while (resultado.next()) {
			Revista revista = new Revista(resultado);
			revistas.add(revista);
		    }
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	return revistas;
    }
    
	/**
	 * Escribe nuevos registros en la base de datos 
	 * @param table
	 * @param columnas
	 * @param registro
	 */
	public  void escribirRegistro(String table, String columnas, String registro) {
		try {
			Statement sentencias = conexion.createStatement();
			sentencias.executeUpdate("INSERT INTO "+ table+columnas + "VALUES" + registro);
			JOptionPane.showMessageDialog(null, "Registro agregado con exito", table+" agregado a la BD", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Consulta preparada para consultar un cliente
	 * @param casillero
	 * @return
	 */
	public ResultSet consultarCliente(int casillero) {
	    ResultSet consulta = null;
	    try {
		 PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM Cliente WHERE casillero= ? ");
		 sentencia.setInt(1, casillero);
		 consulta=sentencia.executeQuery();
	    } catch (SQLException e) {
		e.printStackTrace();
		System.out.println(e.getErrorCode());
	    }
	    return consulta;
	}
	
	/**
	 * Consulta de el password de un cliente
	 * @param casillero
	 * @return
	 * @throws SQLException 
	 */
	public String leerPassword(String email) {
	    ResultSet consulta = null;
	    String password = null;
	    try {
		 PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM Usuario WHERE correo_electronico=?");
		 sentencia.setString(1, email);
		 consulta=sentencia.executeQuery();
		 consulta.next();
		 password= consulta.getString(4);
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    return password;
	}
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	public int leerTipoDeUsuario(String email) {
	    ResultSet consulta = null;
	    int tipoDeUsuario = 0;
	    try {
		 PreparedStatement sentencia = conexion.prepareStatement("SELECT tipo_de_usuario FROM Usuario WHERE correo_electronico=?");
		 sentencia.setString(1, email);
		 consulta=sentencia.executeQuery();
		 consulta.next();
		 tipoDeUsuario= consulta.getInt(1);
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    return tipoDeUsuario;
	}
	
	
	/**
	 * Obtiene el maximo valor de una tabla
	 * @param tabla La tabla a consultar
	 * @param columna La columna a consultar
	 * @return
	 */
	public int getUltimo(String tabla, String columna) {
	    int ultimo = 0 ;
	    try {
		Statement sentencias = conexion.createStatement();
		ResultSet consulta = sentencias.executeQuery("SELECT MAX("+columna+") FROM "+tabla);
		consulta.next();
		ultimo = consulta.getInt(1);
	    } catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "Ocurrio un error de lectura del codigo");
	    }
	    return ultimo;
	}
	
	
	/**
	 * Metodo para ejecutar transacciones en la base de datos
	 * @param sentencias El conjunto de intrucciones que se tiene que ejecutar
	 */
	public void transaccion(ArrayList<String> sentencias) {
	    try {
		conexion.setAutoCommit(false);
		for (int i = 0; i < sentencias.size(); i++) {
		    Statement sentencia = conexion.createStatement();
		    	sentencia.executeUpdate(sentencias.get(i));
		}
		JOptionPane.showMessageDialog(null, "Ingreso con exito");
	    } catch (SQLException e) {
		e.printStackTrace();
		try {
		    conexion.rollback();
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "No se completo la transaccion, intente de nuevo");
	    } finally {
		try {
		    conexion.setAutoCommit(true);
		} catch (SQLException e2) {
		    e2.printStackTrace();
		    JOptionPane.showMessageDialog(null, "Ocurrio un error durante el cirre de la conexion");
		}
	    }
	}
	
	/**
	 * Metodo para generar actualizaciones en la base de datos 
	 * @param tabla La tabla donde se va a modificar
	 * @param modificaciones Las modificaciones a la columnas
	 * @param where Las condiciones de llaves primarias. 
	 */
	public void generarActualizacion(String tabla, String modificaciones, String where) {
	    try {
		Statement sentencias = conexion.createStatement();
		sentencias.executeUpdate("UPDATE "+tabla+" SET "+modificaciones+" "+where+" ");
		JOptionPane.showMessageDialog(null, "Actualizacion realizada con exito");
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}

	/**
	 * Escribe un nuevo perfile en la base de datos 
	 * @param perfil
	 * @throws ErrorCreacionUsuario
	 * @throws SQLException
	 */
	public void escribirNuevoPerfil(Perfil perfil)  throws ErrorCreacionUsuario, SQLException{
		int ultimoRegistro= getUltimo("Usuario", "id_usuario");
		int siguienteUsuario = ultimoRegistro+1;
		int ultimaCuenta = getUltimo("Cuenta", "id_cuenta");
		int siguienteCuenta = ultimaCuenta+1;
		System.out.println(siguienteUsuario);
	        PreparedStatement statementPerfil = perfil.crearSentencia(siguienteUsuario, siguienteCuenta);
		Usuario usuario = (Usuario) perfil;
	        PreparedStatement statementUsuario= usuario.crearSentencia(siguienteUsuario);
	        Cuenta cuenta = new Cuenta();
	        PreparedStatement statementCuenta = cuenta.crearSentencia();
		try {
		    	conexion.setAutoCommit(false);		    	
		    	// Envia las sentencias la servidor
		        statementUsuario.executeUpdate();
		        statementCuenta.executeUpdate();
		        statementPerfil.executeUpdate();
		        conexion.commit();
		} catch (SQLException ex) {
		    conexion.rollback();
		    throw new ErrorCreacionUsuario("ERROR: " + ex.getMessage());
		}finally {
		    conexion.setAutoCommit(true);
	    }
	    }

	/**
	 * Consulta la llave primaria de un editor, en base a su correo electronico
	 * @param email
	 * @return
	 */
	public int consultarCodigoUsuario(String email) {
	    ResultSet consulta = null;
	    int codigo = -1;
	    try {
		 PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM Usuario WHERE correo_electronico=?");
		 sentencia.setString(1, email);
		 consulta=sentencia.executeQuery();
		 consulta.next();
		 codigo= consulta.getInt(1);
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    return codigo;
	}
	
	/**
	 * Escribe una nueva revista en la base de datos 
	 * @param revista
	 * @throws SQLException
	 */
	public void escribirNuevaRevista(Revista revista) throws SQLException {
		int ultimaRevista= getUltimo("Revista", "codigo");
		int siguienteRevista = ultimaRevista+1;
		int ultimosPermisos = getUltimo("Permisos", "id_permisos");
		int siguientesPermisos = ultimosPermisos+1;
	        PreparedStatement statementRevista = revista.crearSentencia(siguienteRevista, siguientesPermisos);
	        Permiso permiso = new Permiso(siguientesPermisos);
	        PreparedStatement statementPermiso = permiso.crearSentencia();
		try {
		    	conexion.setAutoCommit(false);		    	
		    	// Envia las sentencias la servidor
		        statementPermiso.executeUpdate();
		        statementRevista.executeUpdate();
		        conexion.commit();
		        System.out.println("Commit de revistas realizada");
		} catch (SQLException ex) {
		    ex.printStackTrace();
		    conexion.rollback();
		}finally {
		    conexion.setAutoCommit(true);
	    }
	}

	
	/**
	 * Escribe una nueva publicacion en la base de datos 
	 * @param request
	 * @param contenidoRevista
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void escribirPublicacion(HttpServletRequest request, String  path) throws SQLException, ParseException {
	    int ultimaPublicacion= getUltimo("Publicacion", "idPublicacion");
		int siguientePublicacion = ultimaPublicacion+1;
		String sql = "INSERT INTO Publicacion (idPublicacion, path, fecha_de_publicacion, codigo_revista) values (?,?,?,?)";
	        PreparedStatement statementPublicacion = conexion.prepareStatement(sql);
	        statementPublicacion.setInt(1, siguientePublicacion);
	        statementPublicacion.setString(2, path);
	        String startDateStr = request.getParameter("date");
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date fecha = sdf.parse(startDateStr); 
	        statementPublicacion.setDate(3, new Date(fecha.getTime()));
	        int codigoRevista = ((Revista)request.getSession().getAttribute("revista")).getCodigo();
	        statementPublicacion.setInt(4, codigoRevista);
		try {   	
		    	// Envia las sentencias la servidor
		    	statementPublicacion.executeUpdate();
		        System.out.println("Commit de revistas realizada");
		} catch (SQLException ex) {
		    ex.printStackTrace();
		    conexion.rollback();
		}
	    
	}
	/**
	 * Lee el autor de una revista
	 * @param codigo
	 * @return
	 */
	public String leerAutorDeRevista(int codigo) {
	    ResultSet consulta = null;
	    String resultado = null;
	    try {
		String instruccion = "SELECT  nombre FROM Usuario  WHERE id_usuario = ?";
		 PreparedStatement sentencia = conexion.prepareStatement(instruccion);
		 sentencia.setInt(1, codigo);
		 consulta=sentencia.executeQuery();
		 consulta.next();
		 resultado = consulta.getString(1);
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    return resultado;
	}

	public int leerLikesDeRevista(int cod) {
	    ResultSet consulta = null;
	    int codigo = -1;
	    try {
		String instruccion = "SELECT COUNT('id_me_gusta') FROM MeGusta  WHERE codigo_revista = ?";
		 PreparedStatement sentencia = conexion.prepareStatement(instruccion);
		 sentencia.setInt(1, cod);
		 consulta=sentencia.executeQuery();
		 consulta.next();
		 codigo= consulta.getInt(1);
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    return codigo;
	}

	public int leerCodigoSuscripcion(int codRevista, int codUsuario) throws SQLException {
	    ResultSet consulta = null;
	    int codigoSuscripcion=-1;
		String instruccion = "SELECT id_suscripcion FROM Suscripcion WHERE idUsuario=? AND codigo_revista=?";
		//System.out.println(instruccion + " con parametros usr y rev '"+ codUsuario+"', '"+ codRevista+"'");
		 PreparedStatement sentencia = conexion.prepareStatement(instruccion);
		 sentencia.setInt(1, codUsuario);
		 sentencia.setInt(2, codRevista);
		 consulta=sentencia.executeQuery();
		 if(consulta.next())
			 codigoSuscripcion= consulta.getInt(1);
		 return codigoSuscripcion;
	}
	
	public void nuevaSuscripcion(HttpServletRequest request) throws SQLException, ParseException {
		Revista revistaASuscribirse= (Revista) request.getSession().getAttribute("revistaSuscripcion");
		int codigoUsuario = (Integer) request.getSession().getAttribute("codigo");
		int siguienteRegistro = getUltimo("Suscripcion", "id_suscripcion")+1;
		String sql = "INSERT INTO Suscripcion (id_suscripcion, fecha_de_inicio, idUsuario, codigo_revista) values (?,?,?,?)";
		PreparedStatement statement = conexion.prepareStatement(sql);
		statement.setInt(1, siguienteRegistro);
	        String startDateStr = request.getParameter("date");
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date fecha = sdf.parse(startDateStr); 
		statement.setDate(2, new Date(fecha.getTime()));
		statement.setInt(3, codigoUsuario);
		statement.setInt(4, revistaASuscribirse.getCodigo());
		statement.executeUpdate();
	}

	public Date leerUltimoPago(int codigoRevista, int codigoUsuario) throws SQLException {
	    ResultSet consulta = null;
	     int codigoSuscripcion = leerCodigoSuscripcion(codigoRevista, codigoUsuario);
		String instruccion = "SELECT * FROM Pago WHERE id_usuario=? AND id_suscripcion=? ORDER BY fecha_de_pago DESC LIMIT 1";
		 PreparedStatement sentencia = conexion.prepareStatement(instruccion);
		 sentencia.setInt(1, codigoUsuario);
		 sentencia.setInt(2, codigoSuscripcion);
		 consulta=sentencia.executeQuery();
		 if(consulta.next()) {
		     return consulta.getDate(2);
		 }else {
		      String sqlFecha = "SELECT * FROM Suscripcion WHERE idUsuario=? AND id_suscripcion=?";
			 PreparedStatement sentenciaAlterna = conexion.prepareStatement(sqlFecha);
			 sentenciaAlterna.setInt(1, codigoUsuario);
			 sentenciaAlterna.setInt(2, codigoSuscripcion);
			 ResultSet consultaFecha = sentenciaAlterna.executeQuery();
			 consultaFecha.next();
			 System.out.println("Fecha leida en la BD: "+ consultaFecha.getDate(3));
			 return consultaFecha.getDate(3);
		 }
	}
	
	public void pagar(int codigoUsuario, int codigoRevista, int idSuscripcion, Date fechaUltimoPago, int cantidadDePagos,double precioRevista,
			int porcentajeSistema) {
		Date nuevaFecha=null;
		double montoSistema= precioRevista *porcentajeSistema /100;
		double montoEditor = precioRevista - montoSistema;
		try {
		    conexion.setAutoCommit(false);
		    String sqlSentence = "INSERT INTO Pago(fecha_de_pago, id_usuario, id_suscripcion, montoEditor, montoSistema) values (?,?,?,?,?)";
		    for (int i = 0; i < cantidadDePagos; i++) {
	    		nuevaFecha= new Date(fechaUltimoPago.getTime() + (2628000000L * (i+1)));  //sumamos la cantidad en milisegundos por cada mes
			    PreparedStatement stm = conexion.prepareStatement(sqlSentence);
	    			stm.setDate(1, nuevaFecha);
	    			stm.setInt(2, codigoUsuario);
	    			stm.setInt(3, idSuscripcion);
	    			stm.setDouble(4, montoEditor);
	    			stm.setDouble(5, montoSistema);
	    			stm.executeUpdate();
	    		    transferirDinero(codigoRevista);
	    	    }
		        conexion.commit();
		} catch (SQLException e) {
		    try {
			conexion.rollback();
		    } catch (SQLException e1) {
			e1.printStackTrace();
		    }
		    e.printStackTrace();
		} finally {
		    try {
			conexion.setAutoCommit(true);
		    } catch (SQLException e) {
			e.printStackTrace();
		    }
		}
	    
	}

	private void transferirDinero(int codigoRevista) throws SQLException {
	    @SuppressWarnings("unused")
	    int idEditor, idCuentaEditor, porcentajeDeGanancia, idCuentaSistema;
	    double cuotaSuscripcion, abonosEditor, abonosSistema;
	    //instruccion para saber los datos del editor
	    String sqlEditor = "SELECT r.id_editor, r.cuota_suscripcion, p.id_cuenta, c.abonos "+
		    " FROM Revista r, Perfil p, Cuenta c "+
		   " WHERE r.codigo= ? "+
		    " AND r.id_editor = p.id_usuario "+
		    " AND p.id_cuenta = c.id_cuenta";
	    //Instruccion para saber los datos del sistema
	    String sqlSistema = " SELECT s.porcentaje_de_ganancia, s.id_cuenta, c.abonos "
	    	+ " FROM Sistema s, Cuenta c   "
	    	+ " WHERE c.id_cuenta = s.id_cuenta ";
	    PreparedStatement stmEditor = conexion.prepareStatement(sqlEditor);
	    PreparedStatement stmSistema = conexion.prepareStatement(sqlSistema);
	     stmEditor.setInt(1, codigoRevista);
	     ResultSet resultEditor = stmEditor.executeQuery();
	     ResultSet resultSistema = stmSistema.executeQuery();
	    resultEditor.next(); resultSistema.next();
	    
	    idEditor = resultEditor.getInt(1);  
	    cuotaSuscripcion = resultEditor.getDouble(2);
	    idCuentaEditor = resultEditor.getInt(3);
	    abonosEditor = resultEditor.getInt(4);
	  
	    porcentajeDeGanancia = resultSistema.getInt(1);
	    idCuentaSistema = resultSistema.getInt(2);
	    abonosSistema= resultSistema.getDouble(3);
	    
	    double gananciaSistema = cuotaSuscripcion * porcentajeDeGanancia /100;
	    double gananciaEditor = cuotaSuscripcion - gananciaSistema; 
	    
	    String updateEditor = "UPDATE Cuenta SET abonos = ? WHERE id_cuenta = ?";
	    PreparedStatement prepEditor = conexion.prepareStatement(updateEditor); 
	    prepEditor.setDouble(1, (abonosEditor + gananciaEditor));
	    prepEditor.setInt(2, idCuentaEditor);
	    
	    String updateSistema = "UPDATE Cuenta SET abonos = ? WHERE id_cuenta = ?";
	    PreparedStatement prepSistema = conexion.prepareStatement(updateSistema); 
	    prepSistema.setDouble(1, (abonosSistema + gananciaSistema));
	    prepSistema.setInt(2, idCuentaSistema);
	    
	    prepEditor.executeUpdate();
	    prepSistema.executeUpdate();
	}

	public double leerPrecioRevista(int codigoSeleccionado) {
		double precio =0;
		String sql = "SELECT cuota_suscripcion from Revista where codigo=?";
		PreparedStatement stm;
		try {
			stm = conexion.prepareStatement(sql);
			stm.setInt(1, codigoSeleccionado);
			ResultSet resultadoPrecio = stm.executeQuery();
			resultadoPrecio.next();
			precio= resultadoPrecio.getDouble(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return precio;
	}

	public int leerPorcentajeSistema() {
		int porcentaje =0;
		String sql = "SELECT porcentaje_de_ganancia from Sistema";
		PreparedStatement stm;
		try {
			stm = conexion.prepareStatement(sql);
			ResultSet resultadoPrecio = stm.executeQuery();
			resultadoPrecio.next();
			porcentaje= resultadoPrecio.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return porcentaje;
	}
}
