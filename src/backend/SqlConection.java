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
    
    Connection conexion;
    
    private final String SQL_USER="admin-revistas";
    private final String PASSWORD="12345";
    private final String SQL_PORT="jdbc:mysql://localhost:3306/";
    private final String DATABASE_NAME="Revistas";
    
    public SqlConection() {
	try {
	   this.conexion = DriverManager.getConnection(SQL_PORT+DATABASE_NAME, SQL_USER, PASSWORD);
	   System.out.println("Exito");
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
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
		 password= consulta.getString(1);
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    return password;
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
		this.conexion.setAutoCommit(false);
		for (int i = 0; i < sentencias.size(); i++) {
		    Statement sentencia = this.conexion.createStatement();
		    	sentencia.executeUpdate(sentencias.get(i));
		}
		JOptionPane.showMessageDialog(null, "Ingreso con exito");
	    } catch (SQLException e) {
		e.printStackTrace();
		try {
		    this.conexion.rollback();
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "No se completo la transaccion, intente de nuevo");
	    } finally {
		try {
		    this.conexion.setAutoCommit(true);
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
	 * @return the conexion
	 */
	public Connection getConexion() {
	    return conexion;
	}

	/**
	 * @param conexion the conexion to set
	 */
	public void setConexion(Connection conexion) {
	    this.conexion = conexion;
	}
	
	public void escribirRegistro(Perfil perfil)  throws ErrorCreacionUsuario, SQLException{
		int ultimoRegistro= getUltimo("Usuario", "id_usuario");
		int siguienteRegistro = ultimoRegistro++;
		Usuario usuario = (Usuario) perfil;
	        PreparedStatement statementUsuario= usuario.crearSentencia(this, siguienteRegistro);
	        PreparedStatement statementPerfil = perfil.crearSentencia(this, siguienteRegistro);
		try {
		    	getConexion().setAutoCommit(false);
		    	// Envia las sentencias la servidor
		        statementUsuario.executeUpdate();
		        statementPerfil.executeUpdate();
		        getConexion().commit();
		} catch (SQLException ex) {
		    getConexion().rollback();
		    throw new ErrorCreacionUsuario("ERROR: " + ex.getMessage());
		}finally {
		    getConexion().setAutoCommit(true);
	            if (getConexion() != null) {
	                try {
	                    getConexion().close();
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }	
	    }
	    }
}
