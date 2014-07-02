package geosolutions.zoom.da;


//@autor Sergio Medina


import geosolutions.zoom.be.AnuladoBE;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class AnuladoDA extends BaseDA {
	String cadenaConexion;
	String DriverConnection;
	String user;
	String password;
public AnuladoDA(){
	cadenaConexion = super.getConnectionString();
	DriverConnection = super.getDriverConnection();
	user = super.getUser();
	password = super.getPassword();    
}
 public AnuladoBE listarAnuladoBE(AnuladoBE oAnuladoBE1) throws SQLException {
	AnuladoBE oAnuladoBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	oAnuladoBE=new AnuladoBE();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
if(oAnuladoBE1.getIndOpSp()==1){

	String sql = " SELECT codigoanulado,motivoanulacion FROM anulado WHERE codigoanulado=? and motivoanulacion=true";
pst = cn.prepareStatement(sql);
pst.setString(1,oAnuladoBE1.getCodigoanulado());
rs = pst.executeQuery();
System.out.println("consulta :" + sql);

}	while(rs.next()){
		oAnuladoBE.setCodigoanulado(rs.getString("codigoanulado"));
		oAnuladoBE.setMotivoanulacion(rs.getString("motivoanulacion"));
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
return oAnuladoBE;
}



public ArrayList<AnuladoBE> listarRegistroAnuladoBE(AnuladoBE oAnuladoBE1) throws SQLException {
	ArrayList<AnuladoBE> listaAnuladoBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	listaAnuladoBE=new ArrayList<AnuladoBE>();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
	String sql="";
if (oAnuladoBE1.getIndOpSp() == 1) {
	sql = " SELECT codigoanulado,motivoanulacion FROM anulado WHERE motivoanulacion=true";
pst = cn.prepareStatement(sql);
rs = pst.executeQuery();
}
if (oAnuladoBE1.getIndOpSp() == 2) {
	sql = " SELECT codigoanulado,motivoanulacion FROM anulado WHERE codigoanulado=? and motivoanulacion=true";
pst = cn.prepareStatement(sql);
pst.setString(1,oAnuladoBE1.getCodigoanulado());
rs = pst.executeQuery();
}

while(rs.next()){
	AnuladoBE oAnuladoBE=new AnuladoBE();
		oAnuladoBE.setCodigoanulado(rs.getString("codigoanulado"));
		oAnuladoBE.setMotivoanulacion(rs.getString("motivoanulacion"));
	listaAnuladoBE.add(oAnuladoBE);}

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
	oAnuladoBE1 = null;
}
return listaAnuladoBE;
}


public  int insertarRegistrosAnuladoBE(ArrayList<AnuladoBE> oListaAnuladoBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

for(AnuladoBE oAnuladoBE : oListaAnuladoBE){
cs=cn.prepareCall("{call uspInsertarAnulado(?,?)}");
	cs.setString(1, oAnuladoBE.getMotivoanulacion());
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


public  int insertarAnuladoBE(AnuladoBE oAnuladoBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspInsertarAnulado(?,?)}");
	cs.setString(1, oAnuladoBE.getMotivoanulacion());
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


public  int actualizarAnuladoBE(AnuladoBE oAnuladoBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion, user, password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspActualizarAnulado(?,?)}");
	cs.setString(1, oAnuladoBE.getCodigoanulado());
	cs.setString(2, oAnuladoBE.getMotivoanulacion());
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
