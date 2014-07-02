package geosolutions.zoom.da;


//@autor Sergio Medina


import geosolutions.zoom.be.GrupoventaBE;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class GrupoventaDA extends BaseDA {
	String cadenaConexion;
	String DriverConnection;
	String user;
	String password;
public GrupoventaDA(){
	cadenaConexion = super.getConnectionString();
	DriverConnection = super.getDriverConnection();
	user = super.getUser();
	password = super.getPassword();    
}
 public GrupoventaBE listarGrupoventaBE(GrupoventaBE oGrupoventaBE1) throws SQLException {
	GrupoventaBE oGrupoventaBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	oGrupoventaBE=new GrupoventaBE();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
if(oGrupoventaBE1.getIndOpSp()==1){

	String sql = " SELECT codigogrupoventa,grupoventas,canal FROM grupoventa WHERE codigogrupoventa=? and canal=true";
pst = cn.prepareStatement(sql);
pst.setString(1,oGrupoventaBE1.getCodigogrupoventa());
rs = pst.executeQuery();
System.out.println("consulta :" + sql);

}	while(rs.next()){
		oGrupoventaBE.setCodigogrupoventa(rs.getString("codigogrupoventa"));
		oGrupoventaBE.setGrupoventas(rs.getString("grupoventas"));
		oGrupoventaBE.setCanal(rs.getString("canal"));
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
return oGrupoventaBE;
}



public ArrayList<GrupoventaBE> listarRegistroGrupoventaBE(GrupoventaBE oGrupoventaBE1) throws SQLException {
	ArrayList<GrupoventaBE> listaGrupoventaBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	listaGrupoventaBE=new ArrayList<GrupoventaBE>();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
	String sql="";
if (oGrupoventaBE1.getIndOpSp() == 1) {
	sql = " SELECT codigogrupoventa,grupoventas,canal FROM grupoventa WHERE canal=true";
pst = cn.prepareStatement(sql);
rs = pst.executeQuery();
}
if (oGrupoventaBE1.getIndOpSp() == 2) {
	sql = " SELECT codigogrupoventa,grupoventas,canal FROM grupoventa WHERE codigogrupoventa=? and canal=true";
pst = cn.prepareStatement(sql);
pst.setString(1,oGrupoventaBE1.getCodigogrupoventa());
rs = pst.executeQuery();
}

while(rs.next()){
	GrupoventaBE oGrupoventaBE=new GrupoventaBE();
		oGrupoventaBE.setCodigogrupoventa(rs.getString("codigogrupoventa"));
		oGrupoventaBE.setGrupoventas(rs.getString("grupoventas"));
		oGrupoventaBE.setCanal(rs.getString("canal"));
	listaGrupoventaBE.add(oGrupoventaBE);}

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
	oGrupoventaBE1 = null;
}
return listaGrupoventaBE;
}


public  int insertarRegistrosGrupoventaBE(ArrayList<GrupoventaBE> oListaGrupoventaBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

for(GrupoventaBE oGrupoventaBE : oListaGrupoventaBE){
cs=cn.prepareCall("{call uspInsertarGrupoventa(?,?,?)}");
	cs.setString(1, oGrupoventaBE.getGrupoventas());
	cs.setString(2, oGrupoventaBE.getCanal());
	cs.registerOutParameter(3, java.sql.Types.INTEGER);
	cs.execute();
	resultado = cs.getInt(3);
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


public  int insertarGrupoventaBE(GrupoventaBE oGrupoventaBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspInsertarGrupoventa(?,?,?)}");
	cs.setString(1, oGrupoventaBE.getGrupoventas());
	cs.setString(2, oGrupoventaBE.getCanal());
	cs.registerOutParameter(3, java.sql.Types.INTEGER);
	cs.execute();
	resultado = cs.getInt(3);
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


public  int actualizarGrupoventaBE(GrupoventaBE oGrupoventaBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion, user, password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspActualizarGrupoventa(?,?,?)}");
	cs.setString(1, oGrupoventaBE.getCodigogrupoventa());
	cs.setString(2, oGrupoventaBE.getGrupoventas());
	cs.setString(3, oGrupoventaBE.getCanal());
	cs.registerOutParameter(3, java.sql.Types.INTEGER);
	cs.executeUpdate();
	resultado = cs.getInt(3);
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
