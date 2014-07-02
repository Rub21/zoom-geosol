package geosolutions.zoom.da;


//@autor Sergio Medina


import geosolutions.zoom.be.VehiculoBE;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class VehiculoDA extends BaseDA {
	String cadenaConexion;
	String DriverConnection;
	String user;
	String password;
public VehiculoDA(){
	cadenaConexion = super.getConnectionString();
	DriverConnection = super.getDriverConnection();
	user = super.getUser();
	password = super.getPassword();    
}
 public VehiculoBE listarVehiculoBE(VehiculoBE oVehiculoBE1) throws SQLException {
	VehiculoBE oVehiculoBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	oVehiculoBE=new VehiculoBE();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
if(oVehiculoBE1.getIndOpSp()==1){

	String sql = " SELECT codigovehiculo,placa,nombrevehiculo,nombreconductor,nombretransportista FROM vehiculo WHERE codigovehiculo=? and nombretransportista=true";
pst = cn.prepareStatement(sql);
pst.setString(1,oVehiculoBE1.getCodigovehiculo());
rs = pst.executeQuery();
System.out.println("consulta :" + sql);

}	while(rs.next()){
		oVehiculoBE.setCodigovehiculo(rs.getString("codigovehiculo"));
		oVehiculoBE.setPlaca(rs.getString("placa"));
		oVehiculoBE.setNombrevehiculo(rs.getString("nombrevehiculo"));
		oVehiculoBE.setNombreconductor(rs.getString("nombreconductor"));
		oVehiculoBE.setNombretransportista(rs.getString("nombretransportista"));
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
return oVehiculoBE;
}



public ArrayList<VehiculoBE> listarRegistroVehiculoBE(VehiculoBE oVehiculoBE1) throws SQLException {
	ArrayList<VehiculoBE> listaVehiculoBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	listaVehiculoBE=new ArrayList<VehiculoBE>();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
	String sql="";
if (oVehiculoBE1.getIndOpSp() == 1) {
	sql = " SELECT codigovehiculo,placa,nombrevehiculo,nombreconductor,nombretransportista FROM vehiculo WHERE nombretransportista=true";
pst = cn.prepareStatement(sql);
rs = pst.executeQuery();
}
if (oVehiculoBE1.getIndOpSp() == 2) {
	sql = " SELECT codigovehiculo,placa,nombrevehiculo,nombreconductor,nombretransportista FROM vehiculo WHERE codigovehiculo=? and nombretransportista=true";
pst = cn.prepareStatement(sql);
pst.setString(1,oVehiculoBE1.getCodigovehiculo());
rs = pst.executeQuery();
}

while(rs.next()){
	VehiculoBE oVehiculoBE=new VehiculoBE();
		oVehiculoBE.setCodigovehiculo(rs.getString("codigovehiculo"));
		oVehiculoBE.setPlaca(rs.getString("placa"));
		oVehiculoBE.setNombrevehiculo(rs.getString("nombrevehiculo"));
		oVehiculoBE.setNombreconductor(rs.getString("nombreconductor"));
		oVehiculoBE.setNombretransportista(rs.getString("nombretransportista"));
	listaVehiculoBE.add(oVehiculoBE);}

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
	oVehiculoBE1 = null;
}
return listaVehiculoBE;
}


public  int insertarRegistrosVehiculoBE(ArrayList<VehiculoBE> oListaVehiculoBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

for(VehiculoBE oVehiculoBE : oListaVehiculoBE){
cs=cn.prepareCall("{call uspInsertarVehiculo(?,?,?,?,?)}");
	cs.setString(1, oVehiculoBE.getPlaca());
	cs.setString(2, oVehiculoBE.getNombrevehiculo());
	cs.setString(3, oVehiculoBE.getNombreconductor());
	cs.setString(4, oVehiculoBE.getNombretransportista());
	cs.registerOutParameter(5, java.sql.Types.INTEGER);
	cs.execute();
	resultado = cs.getInt(5);
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


public  int insertarVehiculoBE(VehiculoBE oVehiculoBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspInsertarVehiculo(?,?,?,?,?)}");
	cs.setString(1, oVehiculoBE.getPlaca());
	cs.setString(2, oVehiculoBE.getNombrevehiculo());
	cs.setString(3, oVehiculoBE.getNombreconductor());
	cs.setString(4, oVehiculoBE.getNombretransportista());
	cs.registerOutParameter(5, java.sql.Types.INTEGER);
	cs.execute();
	resultado = cs.getInt(5);
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


public  int actualizarVehiculoBE(VehiculoBE oVehiculoBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion, user, password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspActualizarVehiculo(?,?,?,?,?)}");
	cs.setString(1, oVehiculoBE.getCodigovehiculo());
	cs.setString(2, oVehiculoBE.getPlaca());
	cs.setString(3, oVehiculoBE.getNombrevehiculo());
	cs.setString(4, oVehiculoBE.getNombreconductor());
	cs.setString(5, oVehiculoBE.getNombretransportista());
	cs.registerOutParameter(5, java.sql.Types.INTEGER);
	cs.executeUpdate();
	resultado = cs.getInt(5);
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
