package geosolutions.zoom.da;


//@autor Sergio Medina


import geosolutions.zoom.be.Cuotas_vendedorBE;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class Cuotas_vendedorDA extends BaseDA {
	String cadenaConexion;
	String DriverConnection;
	String user;
	String password;
public Cuotas_vendedorDA(){
	cadenaConexion = super.getConnectionString();
	DriverConnection = super.getDriverConnection();
	user = super.getUser();
	password = super.getPassword();    
}
 public Cuotas_vendedorBE listarCuotas_vendedorBE(Cuotas_vendedorBE oCuotas_vendedorBE1) throws SQLException {
	Cuotas_vendedorBE oCuotas_vendedorBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	oCuotas_vendedorBE=new Cuotas_vendedorBE();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
if(oCuotas_vendedorBE1.getIndOpSp()==1){

	String sql = " SELECT codigovendedorc,nombrevendedorc,fecha_cuota,anio_cuota,mes_cuota,cuota_lacteos,cuota_cafe,cuota_bebidas,cuota_culinarios FROM cuotas_vendedor WHERE codigovendedorc=? and cuota_culinarios=true";
pst = cn.prepareStatement(sql);
pst.setString(1,oCuotas_vendedorBE1.getCodigovendedorc());
rs = pst.executeQuery();
System.out.println("consulta :" + sql);

}	while(rs.next()){
		oCuotas_vendedorBE.setCodigovendedorc(rs.getString("codigovendedorc"));
		oCuotas_vendedorBE.setNombrevendedorc(rs.getString("nombrevendedorc"));
		oCuotas_vendedorBE.setFecha_cuota(rs.getString("fecha_cuota"));
		oCuotas_vendedorBE.setAnio_cuota(rs.getInt("anio_cuota"));
		oCuotas_vendedorBE.setMes_cuota(rs.getString("mes_cuota"));
		oCuotas_vendedorBE.setCuota_lacteos(rs.getDouble("cuota_lacteos"));
		oCuotas_vendedorBE.setCuota_cafe(rs.getDouble("cuota_cafe"));
		oCuotas_vendedorBE.setCuota_bebidas(rs.getDouble("cuota_bebidas"));
		oCuotas_vendedorBE.setCuota_culinarios(rs.getDouble("cuota_culinarios"));
}

	cn.commit();
	cn.setAutoCommit(true);
}
catch (Exception ex) {
	cn.rollback();
	ex.printStackTrace();
}
finally {
	
	rs.close();
	rs = null;
	cn.close();
	cn = null;

}
return oCuotas_vendedorBE;
}



public ArrayList<Cuotas_vendedorBE> listarRegistroCuotas_vendedorBE(Cuotas_vendedorBE oCuotas_vendedorBE1) throws SQLException {
	ArrayList<Cuotas_vendedorBE> listaCuotas_vendedorBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	listaCuotas_vendedorBE=new ArrayList<Cuotas_vendedorBE>();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
	String sql="";
if (oCuotas_vendedorBE1.getIndOpSp() == 1) {
	sql = " SELECT codigovendedorc,nombrevendedorc,fecha_cuota,anio_cuota,mes_cuota,cuota_lacteos,cuota_cafe,cuota_bebidas,cuota_culinarios FROM cuotas_vendedor WHERE cuota_culinarios=true";
pst = cn.prepareStatement(sql);
rs = pst.executeQuery();
}
if (oCuotas_vendedorBE1.getIndOpSp() == 2) {
	sql = " SELECT codigovendedorc,nombrevendedorc,fecha_cuota,anio_cuota,mes_cuota,cuota_lacteos,cuota_cafe,cuota_bebidas,cuota_culinarios FROM cuotas_vendedor WHERE codigovendedorc=? and cuota_culinarios=true";
pst = cn.prepareStatement(sql);
pst.setString(1,oCuotas_vendedorBE1.getCodigovendedorc());
rs = pst.executeQuery();
}

while(rs.next()){
	Cuotas_vendedorBE oCuotas_vendedorBE=new Cuotas_vendedorBE();
		oCuotas_vendedorBE.setCodigovendedorc(rs.getString("codigovendedorc"));
		oCuotas_vendedorBE.setNombrevendedorc(rs.getString("nombrevendedorc"));
		oCuotas_vendedorBE.setFecha_cuota(rs.getString("fecha_cuota"));
		oCuotas_vendedorBE.setAnio_cuota(rs.getInt("anio_cuota"));
		oCuotas_vendedorBE.setMes_cuota(rs.getString("mes_cuota"));
		oCuotas_vendedorBE.setCuota_lacteos(rs.getDouble("cuota_lacteos"));
		oCuotas_vendedorBE.setCuota_cafe(rs.getDouble("cuota_cafe"));
		oCuotas_vendedorBE.setCuota_bebidas(rs.getDouble("cuota_bebidas"));
		oCuotas_vendedorBE.setCuota_culinarios(rs.getDouble("cuota_culinarios"));
	listaCuotas_vendedorBE.add(oCuotas_vendedorBE);}

	cn.commit();
	cn.setAutoCommit(true);
}
catch (Exception ex) {
	cn.rollback();
	ex.printStackTrace();
}finally {
	rs.close();
	rs = null;
	cn.close();
	cn = null;
	oCuotas_vendedorBE1 = null;
}
return listaCuotas_vendedorBE;
}


public  int insertarRegistrosCuotas_vendedorBE(ArrayList<Cuotas_vendedorBE> oListaCuotas_vendedorBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

for(Cuotas_vendedorBE oCuotas_vendedorBE : oListaCuotas_vendedorBE){
cs=cn.prepareCall("{call uspInsertarCuotas_vendedor(?,?,?,?,?,?,?,?,?)}");
	cs.setString(1, oCuotas_vendedorBE.getNombrevendedorc());
	cs.setString(2, oCuotas_vendedorBE.getFecha_cuota());
	cs.setInt(3, oCuotas_vendedorBE.getAnio_cuota());
	cs.setString(4, oCuotas_vendedorBE.getMes_cuota());
	cs.setDouble(5, oCuotas_vendedorBE.getCuota_lacteos());
	cs.setDouble(6, oCuotas_vendedorBE.getCuota_cafe());
	cs.setDouble(7, oCuotas_vendedorBE.getCuota_bebidas());
	cs.setDouble(8, oCuotas_vendedorBE.getCuota_culinarios());
	cs.registerOutParameter(9, java.sql.Types.INTEGER);
	cs.execute();
	resultado = cs.getInt(9);
	cs.close();
	cs=null;
}
	cn.commit();
	cn.setAutoCommit(true);
}
catch (Exception ex) {
	cn.rollback();
	ex.printStackTrace();
}
finally {
	cn.close();
	cn = null;
	
}
return resultado;
}


public  int insertarCuotas_vendedorBE(Cuotas_vendedorBE oCuotas_vendedorBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspInsertarCuotas_vendedor(?,?,?,?,?,?,?,?,?)}");
	cs.setString(1, oCuotas_vendedorBE.getNombrevendedorc());
	cs.setString(2, oCuotas_vendedorBE.getFecha_cuota());
	cs.setInt(3, oCuotas_vendedorBE.getAnio_cuota());
	cs.setString(4, oCuotas_vendedorBE.getMes_cuota());
	cs.setDouble(5, oCuotas_vendedorBE.getCuota_lacteos());
	cs.setDouble(6, oCuotas_vendedorBE.getCuota_cafe());
	cs.setDouble(7, oCuotas_vendedorBE.getCuota_bebidas());
	cs.setDouble(8, oCuotas_vendedorBE.getCuota_culinarios());
	cs.registerOutParameter(9, java.sql.Types.INTEGER);
	cs.execute();
	resultado = cs.getInt(9);
	cs.close();
	cs=null;
	cn.commit();
	cn.setAutoCommit(true);
}
catch (Exception ex) {
	cn.rollback();
	ex.printStackTrace();
}
finally {
	cn.close();
	cn = null;
	
}
return resultado;
}


public  int actualizarCuotas_vendedorBE(Cuotas_vendedorBE oCuotas_vendedorBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion, user, password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspActualizarCuotas_vendedor(?,?,?,?,?,?,?,?,?)}");
	cs.setString(1, oCuotas_vendedorBE.getCodigovendedorc());
	cs.setString(2, oCuotas_vendedorBE.getNombrevendedorc());
	cs.setString(3, oCuotas_vendedorBE.getFecha_cuota());
	cs.setInt(4, oCuotas_vendedorBE.getAnio_cuota());
	cs.setString(5, oCuotas_vendedorBE.getMes_cuota());
	cs.setDouble(6, oCuotas_vendedorBE.getCuota_lacteos());
	cs.setDouble(7, oCuotas_vendedorBE.getCuota_cafe());
	cs.setDouble(8, oCuotas_vendedorBE.getCuota_bebidas());
	cs.setDouble(9, oCuotas_vendedorBE.getCuota_culinarios());
	cs.registerOutParameter(9, java.sql.Types.INTEGER);
	cs.executeUpdate();
	resultado = cs.getInt(9);
	cs.close();
	cs=null;
	cn.commit();
	cn.setAutoCommit(true);
}
catch (Exception ex) {
	cn.rollback();
	ex.printStackTrace();
}
finally {
	cn.close();
	cn = null;
	
}
return resultado;
}


 public ResultSet listarRS(String query) throws SQLException {
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        try {
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);
            String sql = query;
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            cn.commit();
            cn.setAutoCommit(true);
        } catch (Exception ex) {
            cn.rollback();
            ex.printStackTrace();
        } finally {
            cn.close();
            cn = null;
       }
        return rs;
    }
}
