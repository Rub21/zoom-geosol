package geosolutions.zoom.da;


//@autor Sergio Medina


import geosolutions.zoom.be.VendedorBE;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class VendedorDA extends BaseDA {
	String cadenaConexion;
	String DriverConnection;
	String user;
	String password;
public VendedorDA(){
	cadenaConexion = super.getConnectionString();
	DriverConnection = super.getDriverConnection();
	user = super.getUser();
	password = super.getPassword();    
}
 public VendedorBE listarVendedorBE(VendedorBE oVendedorBE1) throws SQLException {
	VendedorBE oVendedorBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	oVendedorBE=new VendedorBE();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
if(oVendedorBE1.getIndOpSp()==1){

	String sql = " SELECT codigovendedor,nombrevendedor FROM vendedor WHERE codigovendedor=? and nombrevendedor=true";
pst = cn.prepareStatement(sql);
pst.setString(1,oVendedorBE1.getCodigovendedor());
rs = pst.executeQuery();
System.out.println("consulta :" + sql);

}	while(rs.next()){
		oVendedorBE.setCodigovendedor(rs.getString("codigovendedor"));
		oVendedorBE.setNombrevendedor(rs.getString("nombrevendedor"));
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
return oVendedorBE;
}



public ArrayList<VendedorBE> listarRegistroVendedorBE(VendedorBE oVendedorBE1) throws SQLException {
	ArrayList<VendedorBE> listaVendedorBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	listaVendedorBE=new ArrayList<VendedorBE>();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
	String sql="";
if (oVendedorBE1.getIndOpSp() == 1) {
	sql = " SELECT codigovendedor,nombrevendedor FROM vendedor WHERE nombrevendedor=true";
pst = cn.prepareStatement(sql);
rs = pst.executeQuery();
}
if (oVendedorBE1.getIndOpSp() == 2) {
	sql = " SELECT codigovendedor,nombrevendedor FROM vendedor WHERE codigovendedor=? and nombrevendedor=true";
pst = cn.prepareStatement(sql);
pst.setString(1,oVendedorBE1.getCodigovendedor());
rs = pst.executeQuery();
}

while(rs.next()){
	VendedorBE oVendedorBE=new VendedorBE();
		oVendedorBE.setCodigovendedor(rs.getString("codigovendedor"));
		oVendedorBE.setNombrevendedor(rs.getString("nombrevendedor"));
	listaVendedorBE.add(oVendedorBE);}

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
	oVendedorBE1 = null;
}
return listaVendedorBE;
}


public  int insertarRegistrosVendedorBE(ArrayList<VendedorBE> oListaVendedorBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

for(VendedorBE oVendedorBE : oListaVendedorBE){
cs=cn.prepareCall("{call uspInsertarVendedor(?,?)}");
	cs.setString(1, oVendedorBE.getNombrevendedor());
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


public  int insertarVendedorBE(VendedorBE oVendedorBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspInsertarVendedor(?,?)}");
	cs.setString(1, oVendedorBE.getNombrevendedor());
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


public  int actualizarVendedorBE(VendedorBE oVendedorBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion, user, password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspActualizarVendedor(?,?)}");
	cs.setString(1, oVendedorBE.getCodigovendedor());
	cs.setString(2, oVendedorBE.getNombrevendedor());
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
