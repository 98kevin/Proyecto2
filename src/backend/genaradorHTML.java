package backend;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class genaradorHTML {

    public static String convertirTabla(ResultSet resutladoConsulta)  {
		String sourceTable="";
		try {
		    sourceTable = sourceTable +("<p align='center'><table class=\"table table-striped\">");
		     ResultSetMetaData rsmd = resutladoConsulta.getMetaData();
			 int columnCount = rsmd.getColumnCount();
			 // table header
			 sourceTable = sourceTable +("<thead><tr>");
			 for (int i = 0; i < columnCount; i++) {
			     sourceTable = sourceTable + ("<th>" + rsmd.getColumnLabel(i + 1) + "</th>");
			   }
			 sourceTable = sourceTable +("</tr></thead>");
			 // the data
			 while (resutladoConsulta.next()) {
			     sourceTable = sourceTable +("<tr>");
			  for (int i = 0; i < columnCount; i++) {
			      sourceTable = sourceTable +("<td>" + resutladoConsulta.getString(i + 1) + "</td>");
			    }
			  sourceTable = sourceTable +("</tr>");
			  }
			 sourceTable = sourceTable +("</table></p>");
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		 return sourceTable;
    }
	
}
