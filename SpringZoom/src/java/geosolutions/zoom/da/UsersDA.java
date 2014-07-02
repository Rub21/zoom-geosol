package geosolutions.zoom.da;


//@autor Sergio Medina


import geosolutions.zoom.be.UsersBE;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class UsersDA extends BaseDA {
	String cadenaConexion;
	String DriverConnection;
	String user;
	String password;
public UsersDA(){
	cadenaConexion = super.getConnectionString();
	DriverConnection = super.getDriverConnection();
	user = super.getUser();
	password = super.getPassword();    
}
 public UsersBE listarUsersBE(UsersBE oUsersBE1) throws SQLException {
	UsersBE oUsersBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	oUsersBE=new UsersBE();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
if(oUsersBE1.getIndOpSp()==1){

	String sql = " SELECT id,firstname,lastname FROM users WHERE id=? and lastname=true";
pst = cn.prepareStatement(sql);
pst.setInt(1,oUsersBE1.getId());
rs = pst.executeQuery();
System.out.println("consulta :" + sql);

}	while(rs.next()){
		oUsersBE.setId(rs.getInt("id"));
		oUsersBE.setFirstname(rs.getString("firstname"));
		oUsersBE.setLastname(rs.getString("lastname"));
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
return oUsersBE;
}



public ArrayList<UsersBE> listarRegistroUsersBE(UsersBE oUsersBE1) throws SQLException {
	ArrayList<UsersBE> listaUsersBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	listaUsersBE=new ArrayList<UsersBE>();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
	String sql="";
if (oUsersBE1.getIndOpSp() == 1) {
	sql = " SELECT id,firstname,lastname FROM users WHERE lastname=true";
pst = cn.prepareStatement(sql);
rs = pst.executeQuery();
}
if (oUsersBE1.getIndOpSp() == 2) {
	sql = " SELECT id,firstname,lastname FROM users WHERE id=? and lastname=true";
pst = cn.prepareStatement(sql);
pst.setInt(1,oUsersBE1.getId());
rs = pst.executeQuery();
}

while(rs.next()){
	UsersBE oUsersBE=new UsersBE();
		oUsersBE.setId(rs.getInt("id"));
		oUsersBE.setFirstname(rs.getString("firstname"));
		oUsersBE.setLastname(rs.getString("lastname"));
	listaUsersBE.add(oUsersBE);}

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
	oUsersBE1 = null;
}
return listaUsersBE;
}


public  int insertarRegistrosUsersBE(ArrayList<UsersBE> oListaUsersBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

for(UsersBE oUsersBE : oListaUsersBE){
cs=cn.prepareCall("{call uspInsertarUsers(?,?,?)}");
	cs.setString(1, oUsersBE.getFirstname());
	cs.setString(2, oUsersBE.getLastname());
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


public  int insertarUsersBE(UsersBE oUsersBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspInsertarUsers(?,?,?)}");
	cs.setString(1, oUsersBE.getFirstname());
	cs.setString(2, oUsersBE.getLastname());
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


public  int actualizarUsersBE(UsersBE oUsersBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion, user, password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspActualizarUsers(?,?,?)}");
	cs.setInt(1, oUsersBE.getId());
	cs.setString(2, oUsersBE.getFirstname());
	cs.setString(3, oUsersBE.getLastname());
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
