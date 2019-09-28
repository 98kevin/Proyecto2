package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import exceptions.ErrorCreacionUsuario;

public class SqlConection {
    
    private static Connection conexion;
    
    private final String SQL_USER="admin-revistas";
    private final String PASSWORD="12345";
    private final String SQL_PORT="jdbc:mysql://localhost:3306/";
    private final String DATABASE_NAME="Revistas";
    
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
     * @return the conexion
     */
    public static Connection getConexion() {
        return conexion;
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

	public int consultarEditor(String email) {
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
}
