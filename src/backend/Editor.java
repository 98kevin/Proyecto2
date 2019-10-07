package backend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Editor extends Perfil{
    public void publicarEdicion() {
	
    }
    
    public void crearRevista() {
	
    }
    
    public void modificarEstadoMeGusta() {
    	
    }
    public void modificarEstadoComentarios() {
    	
    }
    public void bloquearSuscriptor() {
    	
    }
    public void reporteDeComentarios() {
    	
    }
    public ResultSet reporteDeSuscripciones(int codigo) {
    	new SqlConection();
    	ResultSet resultados=null;
    	String sql =" SELECT s.id_suscripcion as 'Codigo de suscripcion', r.nombre as 'Nombre de la revista', s.fecha_de_inicio as 'Fecha Inicial', u.nombre as 'Nombre del suscriptor'"+
    		"	FROM Suscripcion s, Revista r, Usuario u "+
    		"	WHERE s.codigo_revista=r.codigo "+
    		"	AND r.id_editor=u.id_usuario "+
    		"	AND u.id_usuario=? ";
    	try {
    		PreparedStatement stm =SqlConection.conexion.prepareStatement(sql);
			stm.setInt(1, codigo);
			System.out.println("SQL:"+sql+" con: "+codigo);
			resultados = stm.executeQuery();
			System.out.println("Consulta generada");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return resultados;
    }
    
    public void reporteDeMeGustas() {
    	
    }
    
    public ResultSet reporteDeGanancias() {
    	new SqlConection();
    	ResultSet resultados=null;
    	String sql =" SELECT s.id_suscripcion as 'Codigo de suscripcion', s.estado as 'Estado', s.fecha_de_inicio as 'Fecha de Inicio', u.nombre as 'Nombre', "+
    		   " (SELECT sum(montoEditor) "+
    		    "        FROM Pago  " +
    		     "       WHERE id_suscripcion = s.id_suscripcion) Ganancias "+
    		  "  FROM Suscripcion s, Usuario u "+
    		 "   WHERE s.idUsuario= u.id_usuario ";
    	try {
    		PreparedStatement stm =SqlConection.conexion.prepareStatement(sql);
			resultados = stm.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return resultados;
    }
    
    public ResultSet reporteDeGananciasGeneral(int codigo) {
    	new SqlConection();
    	ResultSet resultados=null;
    	String sql ="SELECT sum(montoEditor) from Pago where id_usuario=?";
    	try {
    		PreparedStatement stm =SqlConection.conexion.prepareStatement(sql);
    		stm.setInt(1, codigo);
			resultados = stm.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return resultados;
    }

}
