package geosolutions.zoom.da;


//@autor Sergio Medina


import geosolutions.zoom.be.Spatial_ref_sysBE;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class Spatial_ref_sysDA extends BaseDA {
	String cadenaConexion;
	String DriverConnection;
	String user;
	String password;
public Spatial_ref_sysDA(){
	cadenaConexion = super.getConnectionString();
	DriverConnection = super.getDriverConnection();
	user = super.getUser();
	password = super.getPassword();    
}
 public Spatial_ref_sysBE listarSpatial_ref_sysBE(Spatial_ref_sysBE oSpatial_ref_sysBE1) throws SQLException {
	Spatial_ref_sysBE oSpatial_ref_sysBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	oSpatial_ref_sysBE=new Spatial_ref_sysBE();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
if(oSpatial_ref_sysBE1.getIndOpSp()==1){

	String sql = " SELECT srid,auth_name,auth_srid,srtext,proj4text FROM spatial_ref_sys WHERE srid=? and proj4text=true";
pst = cn.prepareStatement(sql);
pst.setInt(1,oSpatial_ref_sysBE1.getSrid());
rs = pst.executeQuery();
System.out.println("consulta :" + sql);

}	while(rs.next()){
		oSpatial_ref_sysBE.setSrid(rs.getInt("srid"));
		oSpatial_ref_sysBE.setAuth_name(rs.getString("auth_name"));
		oSpatial_ref_sysBE.setAuth_srid(rs.getInt("auth_srid"));
		oSpatial_ref_sysBE.setSrtext(rs.getString("srtext"));
		oSpatial_ref_sysBE.setProj4text(rs.getString("proj4text"));
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
return oSpatial_ref_sysBE;
}



public ArrayList<Spatial_ref_sysBE> listarRegistroSpatial_ref_sysBE(Spatial_ref_sysBE oSpatial_ref_sysBE1) throws SQLException {
	ArrayList<Spatial_ref_sysBE> listaSpatial_ref_sysBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	listaSpatial_ref_sysBE=new ArrayList<Spatial_ref_sysBE>();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
	String sql="";
if (oSpatial_ref_sysBE1.getIndOpSp() == 1) {
	sql = " SELECT srid,auth_name,auth_srid,srtext,proj4text FROM spatial_ref_sys WHERE proj4text=true";
pst = cn.prepareStatement(sql);
rs = pst.executeQuery();
}
if (oSpatial_ref_sysBE1.getIndOpSp() == 2) {
	sql = " SELECT srid,auth_name,auth_srid,srtext,proj4text FROM spatial_ref_sys WHERE srid=? and proj4text=true";
pst = cn.prepareStatement(sql);
pst.setInt(1,oSpatial_ref_sysBE1.getSrid());
rs = pst.executeQuery();
}

while(rs.next()){
	Spatial_ref_sysBE oSpatial_ref_sysBE=new Spatial_ref_sysBE();
		oSpatial_ref_sysBE.setSrid(rs.getInt("srid"));
		oSpatial_ref_sysBE.setAuth_name(rs.getString("auth_name"));
		oSpatial_ref_sysBE.setAuth_srid(rs.getInt("auth_srid"));
		oSpatial_ref_sysBE.setSrtext(rs.getString("srtext"));
		oSpatial_ref_sysBE.setProj4text(rs.getString("proj4text"));
	listaSpatial_ref_sysBE.add(oSpatial_ref_sysBE);}

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
	oSpatial_ref_sysBE1 = null;
}
return listaSpatial_ref_sysBE;
}


public  int insertarRegistrosSpatial_ref_sysBE(ArrayList<Spatial_ref_sysBE> oListaSpatial_ref_sysBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

for(Spatial_ref_sysBE oSpatial_ref_sysBE : oListaSpatial_ref_sysBE){
cs=cn.prepareCall("{call uspInsertarSpatial_ref_sys(?,?,?,?,?)}");
	cs.setString(1, oSpatial_ref_sysBE.getAuth_name());
	cs.setInt(2, oSpatial_ref_sysBE.getAuth_srid());
	cs.setString(3, oSpatial_ref_sysBE.getSrtext());
	cs.setString(4, oSpatial_ref_sysBE.getProj4text());
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


public  int insertarSpatial_ref_sysBE(Spatial_ref_sysBE oSpatial_ref_sysBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspInsertarSpatial_ref_sys(?,?,?,?,?)}");
	cs.setString(1, oSpatial_ref_sysBE.getAuth_name());
	cs.setInt(2, oSpatial_ref_sysBE.getAuth_srid());
	cs.setString(3, oSpatial_ref_sysBE.getSrtext());
	cs.setString(4, oSpatial_ref_sysBE.getProj4text());
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


public  int actualizarSpatial_ref_sysBE(Spatial_ref_sysBE oSpatial_ref_sysBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion, user, password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspActualizarSpatial_ref_sys(?,?,?,?,?)}");
	cs.setInt(1, oSpatial_ref_sysBE.getSrid());
	cs.setString(2, oSpatial_ref_sysBE.getAuth_name());
	cs.setInt(3, oSpatial_ref_sysBE.getAuth_srid());
	cs.setString(4, oSpatial_ref_sysBE.getSrtext());
	cs.setString(5, oSpatial_ref_sysBE.getProj4text());
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
