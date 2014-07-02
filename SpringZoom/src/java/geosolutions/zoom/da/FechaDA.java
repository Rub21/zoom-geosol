package geosolutions.zoom.da;


//@autor Sergio Medina


import geosolutions.zoom.be.FechaBE;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class FechaDA extends BaseDA {
	String cadenaConexion;
	String DriverConnection;
	String user;
	String password;
public FechaDA(){
	cadenaConexion = super.getConnectionString();
	DriverConnection = super.getDriverConnection();
	user = super.getUser();
	password = super.getPassword();    
}
 public FechaBE listarFechaBE(FechaBE oFechaBE1) throws SQLException {
	FechaBE oFechaBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	oFechaBE=new FechaBE();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
if(oFechaBE1.getIndOpSp()==1){

	String sql = " SELECT dias_facturados_anio,dias_facturados_mes,fechafacturacion,anio,semestre,trimestre,bimestre,mes_num,mes_desc,mes_anio,semana,semana_anio,dia_nombre,dia_semana,mes_anio_balance,mes_anio_gasto FROM fecha WHERE dias_facturados_anio=? and mes_anio_gasto=true";
pst = cn.prepareStatement(sql);
pst.setInt(1,oFechaBE1.getDias_facturados_anio());
rs = pst.executeQuery();
System.out.println("consulta :" + sql);

}	while(rs.next()){
		oFechaBE.setDias_facturados_anio(rs.getInt("dias_facturados_anio"));
		oFechaBE.setDias_facturados_mes(rs.getInt("dias_facturados_mes"));
		oFechaBE.setFechafacturacion(rs.getString("fechafacturacion"));
		oFechaBE.setAnio(rs.getInt("anio"));
		oFechaBE.setSemestre(rs.getString("semestre"));
		oFechaBE.setTrimestre(rs.getString("trimestre"));
		oFechaBE.setBimestre(rs.getString("bimestre"));
		oFechaBE.setMes_num(rs.getInt("mes_num"));
		oFechaBE.setMes_desc(rs.getString("mes_desc"));
		oFechaBE.setMes_anio(rs.getString("mes_anio"));
		oFechaBE.setSemana(rs.getInt("semana"));
		oFechaBE.setSemana_anio(rs.getString("semana_anio"));
		oFechaBE.setDia_nombre(rs.getString("dia_nombre"));
		oFechaBE.setDia_semana(rs.getInt("dia_semana"));
		oFechaBE.setMes_anio_balance(rs.getString("mes_anio_balance"));
		oFechaBE.setMes_anio_gasto(rs.getString("mes_anio_gasto"));
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
return oFechaBE;
}



public ArrayList<FechaBE> listarRegistroFechaBE(FechaBE oFechaBE1) throws SQLException {
	ArrayList<FechaBE> listaFechaBE=null;
	Connection cn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


try{
	listaFechaBE=new ArrayList<FechaBE>();
	Class.forName(DriverConnection);
	cn = DriverManager.getConnection(cadenaConexion, user, password);
	cn.setAutoCommit(false);
	String sql="";
if (oFechaBE1.getIndOpSp() == 1) {
	sql = " SELECT dias_facturados_anio,dias_facturados_mes,fechafacturacion,anio,semestre,trimestre,bimestre,mes_num,mes_desc,mes_anio,semana,semana_anio,dia_nombre,dia_semana,mes_anio_balance,mes_anio_gasto FROM fecha WHERE mes_anio_gasto=true";
pst = cn.prepareStatement(sql);
rs = pst.executeQuery();
}
if (oFechaBE1.getIndOpSp() == 2) {
	sql = " SELECT dias_facturados_anio,dias_facturados_mes,fechafacturacion,anio,semestre,trimestre,bimestre,mes_num,mes_desc,mes_anio,semana,semana_anio,dia_nombre,dia_semana,mes_anio_balance,mes_anio_gasto FROM fecha WHERE dias_facturados_anio=? and mes_anio_gasto=true";
pst = cn.prepareStatement(sql);
pst.setInt(1,oFechaBE1.getDias_facturados_anio());
rs = pst.executeQuery();
}

while(rs.next()){
	FechaBE oFechaBE=new FechaBE();
		oFechaBE.setDias_facturados_anio(rs.getInt("dias_facturados_anio"));
		oFechaBE.setDias_facturados_mes(rs.getInt("dias_facturados_mes"));
		oFechaBE.setFechafacturacion(rs.getString("fechafacturacion"));
		oFechaBE.setAnio(rs.getInt("anio"));
		oFechaBE.setSemestre(rs.getString("semestre"));
		oFechaBE.setTrimestre(rs.getString("trimestre"));
		oFechaBE.setBimestre(rs.getString("bimestre"));
		oFechaBE.setMes_num(rs.getInt("mes_num"));
		oFechaBE.setMes_desc(rs.getString("mes_desc"));
		oFechaBE.setMes_anio(rs.getString("mes_anio"));
		oFechaBE.setSemana(rs.getInt("semana"));
		oFechaBE.setSemana_anio(rs.getString("semana_anio"));
		oFechaBE.setDia_nombre(rs.getString("dia_nombre"));
		oFechaBE.setDia_semana(rs.getInt("dia_semana"));
		oFechaBE.setMes_anio_balance(rs.getString("mes_anio_balance"));
		oFechaBE.setMes_anio_gasto(rs.getString("mes_anio_gasto"));
	listaFechaBE.add(oFechaBE);}

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
	oFechaBE1 = null;
}
return listaFechaBE;
}


public  int insertarRegistrosFechaBE(ArrayList<FechaBE> oListaFechaBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

for(FechaBE oFechaBE : oListaFechaBE){
cs=cn.prepareCall("{call uspInsertarFecha(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
	cs.setInt(1, oFechaBE.getDias_facturados_mes());
	cs.setString(2, oFechaBE.getFechafacturacion());
	cs.setInt(3, oFechaBE.getAnio());
	cs.setString(4, oFechaBE.getSemestre());
	cs.setString(5, oFechaBE.getTrimestre());
	cs.setString(6, oFechaBE.getBimestre());
	cs.setInt(7, oFechaBE.getMes_num());
	cs.setString(8, oFechaBE.getMes_desc());
	cs.setString(9, oFechaBE.getMes_anio());
	cs.setInt(10, oFechaBE.getSemana());
	cs.setString(11, oFechaBE.getSemana_anio());
	cs.setString(12, oFechaBE.getDia_nombre());
	cs.setInt(13, oFechaBE.getDia_semana());
	cs.setString(14, oFechaBE.getMes_anio_balance());
	cs.setString(15, oFechaBE.getMes_anio_gasto());
	cs.registerOutParameter(16, java.sql.Types.INTEGER);
	cs.execute();
	resultado = cs.getInt(16);
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


public  int insertarFechaBE(FechaBE oFechaBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion,user,password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspInsertarFecha(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
	cs.setInt(1, oFechaBE.getDias_facturados_mes());
	cs.setString(2, oFechaBE.getFechafacturacion());
	cs.setInt(3, oFechaBE.getAnio());
	cs.setString(4, oFechaBE.getSemestre());
	cs.setString(5, oFechaBE.getTrimestre());
	cs.setString(6, oFechaBE.getBimestre());
	cs.setInt(7, oFechaBE.getMes_num());
	cs.setString(8, oFechaBE.getMes_desc());
	cs.setString(9, oFechaBE.getMes_anio());
	cs.setInt(10, oFechaBE.getSemana());
	cs.setString(11, oFechaBE.getSemana_anio());
	cs.setString(12, oFechaBE.getDia_nombre());
	cs.setInt(13, oFechaBE.getDia_semana());
	cs.setString(14, oFechaBE.getMes_anio_balance());
	cs.setString(15, oFechaBE.getMes_anio_gasto());
	cs.registerOutParameter(16, java.sql.Types.INTEGER);
	cs.execute();
	resultado = cs.getInt(16);
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


public  int actualizarFechaBE(FechaBE oFechaBE) throws SQLException {
	int resultado=0;
	Connection cn = null;
	CallableStatement cs = null;


try{
	Class.forName(DriverConnection);
	cn=DriverManager.getConnection(cadenaConexion, user, password);

	cn.setAutoCommit(false);

cs=cn.prepareCall("{call uspActualizarFecha(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
	cs.setInt(1, oFechaBE.getDias_facturados_anio());
	cs.setInt(2, oFechaBE.getDias_facturados_mes());
	cs.setString(3, oFechaBE.getFechafacturacion());
	cs.setInt(4, oFechaBE.getAnio());
	cs.setString(5, oFechaBE.getSemestre());
	cs.setString(6, oFechaBE.getTrimestre());
	cs.setString(7, oFechaBE.getBimestre());
	cs.setInt(8, oFechaBE.getMes_num());
	cs.setString(9, oFechaBE.getMes_desc());
	cs.setString(10, oFechaBE.getMes_anio());
	cs.setInt(11, oFechaBE.getSemana());
	cs.setString(12, oFechaBE.getSemana_anio());
	cs.setString(13, oFechaBE.getDia_nombre());
	cs.setInt(14, oFechaBE.getDia_semana());
	cs.setString(15, oFechaBE.getMes_anio_balance());
	cs.setString(16, oFechaBE.getMes_anio_gasto());
	cs.registerOutParameter(16, java.sql.Types.INTEGER);
	cs.executeUpdate();
	resultado = cs.getInt(16);
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
