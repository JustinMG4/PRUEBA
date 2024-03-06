package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class cls_conexion {
	String db = "db_parcial";
	String usu= "root";
	String cla= "just1n";
	String url= "jdbc:mysql://localhost/"+db;
	Connection conexion=null;
	public cls_conexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion=DriverManager.getConnection(url, usu, cla);
			if (conexion!=null) {
				System.out.println("Conexion establecida");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public Connection conectar(){
		return conexion;
	}
}
