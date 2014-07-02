package geosolutions.zoom.da;


//@autor Sergio Medina


import geosolutions.zoom.be.ProductoBE;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class ProductoDA extends BaseDA {
	String cadenaConexion;
	String DriverConnection;
	String user;
	String password;
public ProductoDA(){
	cadenaConexion = super.getConnectionString();
	DriverConnection = super.getDriverConnection();
	user = super.getUser();
	password = super.getPassword();    
}
 public ProductoBE listarProductoBE(ProductoBE oProductoBE1) throws SQLException {
	ProductoBE oProductoBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	oProductoBE=new ProductoBE();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
if(oProductoBE1.getIndOpSp()==1){

	String sql = " SELECT codigoproducto,grupo,familia,linea,nombremarca,productobase,codigonestle,nombreproducto,pesoneto FROM producto WHERE codigoproducto=? and pesoneto=true";
pst = cn.prepareStatement(sql);
pst.setString(1,oProductoBE1.getCodigoproducto());
rs = pst.executeQuery();
System.out.println("consulta :" + sql);

}	while(rs.next()){
		oProductoBE.setCodigoproducto(rs.getString("codigoproducto"));
		oProductoBE.setGrupo(rs.getString("grupo"));
		oProductoBE.setFamilia(rs.getString("familia"));
		oProductoBE.setLinea(rs.getString("linea"));
		oProductoBE.setNombremarca(rs.getString("nombremarca"));
		oProductoBE.setProductobase(rs.getString("productobase"));
		oProductoBE.setCodigonestle(rs.getString("codigonestle"));
		oProductoBE.setNombreproducto(rs.getString("nombreproducto"));
		oProductoBE.setPesoneto(rs.getDouble("pesoneto"));
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
return oProductoBE;
}



public ArrayList<ProductoBE> listarRegistroProductoBE(ProductoBE oProductoBE1) throws SQLException {
	ArrayList<ProductoBE> listaProductoBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	listaProductoBE=new ArrayList<ProductoBE>();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
	String sql="";
if (oProductoBE1.getIndOpSp() == 1) {
	sql = " SELECT codigoproducto,grupo,familia,linea,nombremarca,productobase,codigonestle,nombreproducto,pesoneto FROM producto WHERE pesoneto=true";
pst = cn.prepareStatement(sql);
rs = pst.executeQuery();
}
if (oProductoBE1.getIndOpSp() == 2) {
	sql = " SELECT codigoproducto,grupo,familia,linea,nombremarca,productobase,codigonestle,nombreproducto,pesoneto FROM producto WHERE codigoproducto=? and pesoneto=true";
pst = cn.prepareStatement(sql);
pst.setString(1,oProductoBE1.getCodigoproducto());
rs = pst.executeQuery();
}

while(rs.next()){
	ProductoBE oProductoBE=new ProductoBE();
		oProductoBE.setCodigoproducto(rs.getString("codigoproducto"));
		oProductoBE.setGrupo(rs.getString("grupo"));
		oProductoBE.setFamilia(rs.getString("familia"));
		oProductoBE.setLinea(rs.getString("linea"));
		oProductoBE.setNombremarca(rs.getString("nombremarca"));
		oProductoBE.setProductobase(rs.getString("productobase"));
		oProductoBE.setCodigonestle(rs.getString("codigonestle"));
		oProductoBE.setNombreproducto(rs.getString("nombreproducto"));
		oProductoBE.setPesoneto(rs.getDouble("pesoneto"));
	listaProductoBE.add(oProductoBE);}

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
	oProductoBE1 = null;
}
return listaProductoBE;
}


public  int insertarRegistrosProductoBE(ArrayList<ProductoBE> oListaProductoBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

for(ProductoBE oProductoBE : oListaProductoBE){
cs=cn.prepareCall("{call uspInsertarProducto(?,?,?,?,?,?,?,?,?)}");
	cs.setString(1, oProductoBE.getGrupo());
	cs.setString(2, oProductoBE.getFamilia());
	cs.setString(3, oProductoBE.getLinea());
	cs.setString(4, oProductoBE.getNombremarca());
	cs.setString(5, oProductoBE.getProductobase());
	cs.setString(6, oProductoBE.getCodigonestle());
	cs.setString(7, oProductoBE.getNombreproducto());
	cs.setDouble(8, oProductoBE.getPesoneto());
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


public  int insertarProductoBE(ProductoBE oProductoBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspInsertarProducto(?,?,?,?,?,?,?,?,?)}");
	cs.setString(1, oProductoBE.getGrupo());
	cs.setString(2, oProductoBE.getFamilia());
	cs.setString(3, oProductoBE.getLinea());
	cs.setString(4, oProductoBE.getNombremarca());
	cs.setString(5, oProductoBE.getProductobase());
	cs.setString(6, oProductoBE.getCodigonestle());
	cs.setString(7, oProductoBE.getNombreproducto());
	cs.setDouble(8, oProductoBE.getPesoneto());
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


public  int actualizarProductoBE(ProductoBE oProductoBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion, user, password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspActualizarProducto(?,?,?,?,?,?,?,?,?)}");
	cs.setString(1, oProductoBE.getCodigoproducto());
	cs.setString(2, oProductoBE.getGrupo());
	cs.setString(3, oProductoBE.getFamilia());
	cs.setString(4, oProductoBE.getLinea());
	cs.setString(5, oProductoBE.getNombremarca());
	cs.setString(6, oProductoBE.getProductobase());
	cs.setString(7, oProductoBE.getCodigonestle());
	cs.setString(8, oProductoBE.getNombreproducto());
	cs.setDouble(9, oProductoBE.getPesoneto());
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
