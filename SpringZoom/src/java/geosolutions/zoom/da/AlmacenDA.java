package geosolutions.zoom.da;

//@autor Sergio Medina
import geosolutions.zoom.be.AlmacenBE;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlmacenDA extends BaseDA {

    String cadenaConexion;
    String DriverConnection;
    String user;
    String password;

    public AlmacenDA() {
        cadenaConexion = super.getConnectionString();
        DriverConnection = super.getDriverConnection();
        user = super.getUser();
        password = super.getPassword();
    }

    public AlmacenBE listarAlmacenBE(AlmacenBE oAlmacenBE1) throws SQLException {
        AlmacenBE oAlmacenBE = null;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        try {
            oAlmacenBE = new AlmacenBE();
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);
            if (oAlmacenBE1.getIndOpSp() == 1) {

                String sql = " SELECT codigoalmacen,nombrealmacen FROM almacen WHERE codigoalmacen=? and nombrealmacen=true";
                pst = cn.prepareStatement(sql);
                pst.setString(1, oAlmacenBE1.getCodigoalmacen());
                rs = pst.executeQuery();
                System.out.println("consulta :" + sql);

            }
            while (rs.next()) {
                oAlmacenBE.setCodigoalmacen(rs.getString("codigoalmacen"));
                oAlmacenBE.setNombrealmacen(rs.getString("nombrealmacen"));
            }

            cn.commit();
            cn.setAutoCommit(true);
        } catch (Exception ex) {
            cn.rollback();
            ex.printStackTrace();
        } finally {

            rs.close();
            rs = null;
            cn.close();
            cn = null;

        }
        return oAlmacenBE;
    }

    public ArrayList<AlmacenBE> listarRegistroAlmacenBE(AlmacenBE oAlmacenBE1) throws SQLException {
        ArrayList<AlmacenBE> listaAlmacenBE = null;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        try {
            listaAlmacenBE = new ArrayList<AlmacenBE>();
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);
            String sql = "";
            if (oAlmacenBE1.getIndOpSp() == 1) {
                sql = " SELECT codigoalmacen,nombrealmacen FROM almacen WHERE nombrealmacen=true";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
            }
            if (oAlmacenBE1.getIndOpSp() == 2) {
                sql = " SELECT codigoalmacen,nombrealmacen FROM almacen WHERE codigoalmacen=? and nombrealmacen=true";
                pst = cn.prepareStatement(sql);
                pst.setString(1, oAlmacenBE1.getCodigoalmacen());
                rs = pst.executeQuery();
            }

            while (rs.next()) {
                AlmacenBE oAlmacenBE = new AlmacenBE();
                oAlmacenBE.setCodigoalmacen(rs.getString("codigoalmacen"));
                oAlmacenBE.setNombrealmacen(rs.getString("nombrealmacen"));
                listaAlmacenBE.add(oAlmacenBE);
            }

            cn.commit();
            cn.setAutoCommit(true);
        } catch (Exception ex) {
            cn.rollback();
            ex.printStackTrace();
        } finally {
            rs.close();
            rs = null;
            cn.close();
            cn = null;
            oAlmacenBE1 = null;
        }
        return listaAlmacenBE;
    }

    public int insertarRegistrosAlmacenBE(ArrayList<AlmacenBE> oListaAlmacenBE) throws SQLException {
        int resultado = 0;
        Connection cn = null;
        CallableStatement cs = null;

        try {
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);

            cn.setAutoCommit(false);

            for (AlmacenBE oAlmacenBE : oListaAlmacenBE) {
                cs = cn.prepareCall("{call uspInsertarAlmacen(?,?)}");
                cs.setString(1, oAlmacenBE.getNombrealmacen());
                cs.registerOutParameter(2, java.sql.Types.INTEGER);
                cs.execute();
                resultado = cs.getInt(2);
                cs.close();
                cs = null;
            }
            cn.commit();
            cn.setAutoCommit(true);
        } catch (Exception ex) {
            cn.rollback();
            ex.printStackTrace();
        } finally {
            cn.close();
            cn = null;

        }
        return resultado;
    }

    public int insertarAlmacenBE(AlmacenBE oAlmacenBE) throws SQLException {
        int resultado = 0;
        Connection cn = null;
        CallableStatement cs = null;

        try {
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);

            cn.setAutoCommit(false);

            cs = cn.prepareCall("{call uspInsertarAlmacen(?,?)}");
            cs.setString(1, oAlmacenBE.getNombrealmacen());
            cs.registerOutParameter(2, java.sql.Types.INTEGER);
            cs.execute();
            resultado = cs.getInt(2);
            cs.close();
            cs = null;
            cn.commit();
            cn.setAutoCommit(true);
        } catch (Exception ex) {
            cn.rollback();
            ex.printStackTrace();
        } finally {
            cn.close();
            cn = null;

        }
        return resultado;
    }

    public int actualizarAlmacenBE(AlmacenBE oAlmacenBE) throws SQLException {
        int resultado = 0;
        Connection cn = null;
        CallableStatement cs = null;

        try {
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);

            cn.setAutoCommit(false);

            cs = cn.prepareCall("{call uspActualizarAlmacen(?,?)}");
            cs.setString(1, oAlmacenBE.getCodigoalmacen());
            cs.setString(2, oAlmacenBE.getNombrealmacen());
            cs.registerOutParameter(2, java.sql.Types.INTEGER);
            cs.executeUpdate();
            resultado = cs.getInt(2);
            cs.close();
            cs = null;
            cn.commit();
            cn.setAutoCommit(true);
        } catch (Exception ex) {
            cn.rollback();
            ex.printStackTrace();
        } finally {
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
