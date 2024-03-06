package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Conexion.cls_conexion;

public class cls_Autos {
	String marca;
	String anio;
	String placa;
	String color;
	String mecanico;
	String km;
	String ingreso;
	int estado;
	
	public cls_Autos() {
		this.marca = "";
		this.anio = "";
		this.placa = "";
		this.color = "";
		this.estado=0;
		this.mecanico="";
		this.km="";
		this.ingreso="";
	}
	public String getMecanico() {
		return mecanico;
	}

	public void setMecanico(String mecanico) {
		this.mecanico = mecanico;
	}

	public String getKm() {
		return km;
	}

	public void setKm(String km) {
		this.km = km;
	}

	public String getIngreso() {
		return ingreso;
	}

	public void setIngreso(String ingreso) {
		this.ingreso = ingreso;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	String sentencias;
	cls_conexion conex = new cls_conexion();


	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean insertar() {
		int respuesta = 0;
		try {
			sentencias = "INSERT INTO autos (Marca, Anio,  Placa, Color, KM, Fecha_Ingreso, Mecanico, Estado) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement st = conex.conectar().prepareStatement(sentencias);
			st.setString(1, this.getMarca());
			st.setString(2, this.getAnio());
			st.setString(3, this.getPlaca());
			st.setString(4, this.getColor());
			st.setString(5, this.getKm());
			st.setString(6, this.getIngreso());
			st.setString(7, this.getMecanico());
			st.setInt(8, this.getEstado());
			
			respuesta = st.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		if (respuesta > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Object[][] consultar(String Mecanico) {
		Object obj[][] = null;
		try {
			if (Mecanico.equals("")) {
				this.sentencias = "SELECT * FROM autos";
			} else {
				this.sentencias = "SELECT * FROM autos WHERE mecanico LIKE '"+"%"+Mecanico+"%"+"'";
			}
			Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet datos = st.executeQuery(this.sentencias);
			datos.last();
			int nf = datos.getRow();
			obj = new Object[nf][9];
			datos.beforeFirst();
			int f = 0;
			while (datos.next()) {
				obj[f][0] = datos.getObject(1);
				obj[f][1] = datos.getObject(2);
				obj[f][2] = datos.getObject(3);
				obj[f][3] = datos.getObject(4);
				obj[f][4] = datos.getObject(5);
				obj[f][5] = datos.getObject(6);
				obj[f][6] = datos.getObject(7);
				obj[f][7] = datos.getObject(8);
				obj[f][8] = datos.getObject(9);
				f++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return obj;
	}

	public Object[][] consultar2(String fecha) {
		Object obj[][] = null;
		try {
			if (fecha.equals("")) {
				this.sentencias = "SELECT * FROM autos";
			} else {
				this.sentencias = "SELECT * FROM autos WHERE Fecha_Ingreso LIKE '"+"%"+fecha+"%"+"'";
			}
			Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet datos = st.executeQuery(this.sentencias);
			datos.last();
			int nf = datos.getRow();
			obj = new Object[nf][9];
			datos.beforeFirst();
			int f = 0;
			while (datos.next()) {
				obj[f][0] = datos.getObject(1);
				obj[f][1] = datos.getObject(2);
				obj[f][2] = datos.getObject(3);
				obj[f][3] = datos.getObject(4);
				obj[f][4] = datos.getObject(5);
				obj[f][5] = datos.getObject(6);
				obj[f][6] = datos.getObject(7);
				obj[f][7] = datos.getObject(8);
				obj[f][8] = datos.getObject(9);
				f++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return obj;
	}
	public Object[][] consultar3(boolean terminado) {
		Object obj[][] = null;
		try {
			if (terminado==false) {
				this.sentencias = "SELECT * FROM autos";
			} else {
				this.sentencias = "SELECT * FROM autos WHERE Estado = 1";
			}
			Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet datos = st.executeQuery(this.sentencias);
			datos.last();
			int nf = datos.getRow();
			obj = new Object[nf][9];
			datos.beforeFirst();
			int f = 0;
			while (datos.next()) {
				obj[f][0] = datos.getObject(1);
				obj[f][1] = datos.getObject(2);
				obj[f][2] = datos.getObject(3);
				obj[f][3] = datos.getObject(4);
				obj[f][4] = datos.getObject(5);
				obj[f][5] = datos.getObject(6);
				obj[f][6] = datos.getObject(7);
				obj[f][7] = datos.getObject(8);
				obj[f][8] = datos.getObject(9);
				f++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return obj;
	}
	
	public boolean login(String usuario, String contra) {
		try {
			sentencias= "SELECT Cedula,Clave FROM mecanicos WHERE Cedula= '"+usuario+"' AND Clave='"+contra+"' AND Estado= 1 ";
			
			PreparedStatement st = conex.conectar().prepareStatement(sentencias); 
			ResultSet datos = st.executeQuery(this.sentencias);
			
			if(datos.next()) {
				datos.close();
				st.close();
				return true;
			} else {
				datos.close();
				st.close();
				return false;
			}
		} catch (Exception e) {
			System.out.println("Errror al ejecular la consulta:"+ e.getMessage());	
		    return false;
		}
	}
}