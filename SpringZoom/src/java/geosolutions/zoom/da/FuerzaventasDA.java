package geosolutions.zoom.da;


//@autor Sergio Medina


import geosolutions.zoom.be.FuerzaventasBE;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class FuerzaventasDA extends BaseDA {
	String cadenaConexion;
	String DriverConnection;
	String user;
	String password;
public FuerzaventasDA(){
	cadenaConexion = super.getConnectionString();
	DriverConnection = super.getDriverConnection();
	user = super.getUser();
	password = super.getPassword();    
}
 public FuerzaventasBE listarFuerzaventasBE(FuerzaventasBE oFuerzaventasBE1) throws SQLException {
	FuerzaventasBE oFuerzaventasBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	oFuerzaventasBE=new FuerzaventasBE();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
if(oFuerzaventasBE1.getIndOpSp()==1){

	String sql = " SELECT codigofuerzaventas,fuerzaventas FROM fuerzaventas WHERE codigofuerzaventas=? and fuerzaventas=true";
pst = cn.prepareStatement(sql);
pst.setString(1,oFuerzaventasBE1.getCodigofuerzaventas());
rs = pst.executeQuery();
System.out.println("consulta :" + sql);

}	while(rs.next()){
		oFuerzaventasBE.setCodigofuerzaventas(rs.getString("codigofuerzaventas"));
		oFuerzaventasBE.setFuerzaventas(rs.getString("fuerzaventas"));
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
return oFuerzaventasBE;
}



public ArrayList<FuerzaventasBE> listarRegistroFuerzaventasBE(FuerzaventasBE oFuerzaventasBE1) throws SQLException {
	ArrayList<FuerzaventasBE> listaFuerzaventasBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	listaFuerzaventasBE=new ArrayList<FuerzaventasBE>();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
	String sql="";
if (oFuerzaventasBE1.getIndOpSp() == 1) {
	sql = " SELECT codigofuerzaventas,fuerzaventas FROM fuerzaventas WHERE fuerzaventas=true";
pst = cn.prepareStatement(sql);
rs = pst.executeQuery();
}
if (oFuerzaventasBE1.getIndOpSp() == 2) {
	sql = " SELECT codigofuerzaventas,fuerzaventas FROM fuerzaventas WHERE codigofuerzaventas=? and fuerzaventas=true";
pst = cn.prepareStatement(sql);
pst.setString(1,oFuerzaventasBE1.getCodigofuerzaventas());
rs = pst.executeQuery();
}

while(rs.next()){
	FuerzaventasBE oFuerzaventasBE=new FuerzaventasBE();
		oFuerzaventasBE.setCodigofuerzaventas(rs.getString("codigofuerzaventas"));
		oFuerzaventasBE.setFuerzaventas(rs.getString("fuerzaventas"));
	listaFuerzaventasBE.add(oFuerzaventasBE);}

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
	oFuerzaventasBE1 = null;
}
return listaFuerzaventasBE;
}


public  int insertarRegistrosFuerzaventasBE(ArrayList<FuerzaventasBE> oListaFuerzaventasBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

for(FuerzaventasBE oFuerzaventasBE : oListaFuerzaventasBE){
cs=cn.prepareCall("{call uspInsertarFuerzaventas(?,?)}");
	cs.setString(1, oFuerzaventasBE.getFuerzaventas());
	cs.registerOutParameter(2, java.sql.Types.INTEGER);
	cs.execute();
	resultado = cs.getInt(2);
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


public  int insertarFuerzaventasBE(FuerzaventasBE oFuerzaventasBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspInsertarFuerzaventas(?,?)}");
	cs.setString(1, oFuerzaventasBE.getFuerzaventas());
	cs.registerOutParameter(2, java.sql.Types.INTEGER);
	cs.execute();
	resultado = cs.getInt(2);
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


public  int actualizarFuerzaventasBE(FuerzaventasBE oFuerzaventasBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion, user, password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspActualizarFuerzaventas(?,?)}");
	cs.setString(1, oFuerzaventasBE.getCodigofuerzaventas());
	cs.setString(2, oFuerzaventasBE.getFuerzaventas());
	cs.registerOutParameter(2, java.sql.Types.INTEGER);
	cs.executeUpdate();
	resultado = cs.getInt(2);
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
