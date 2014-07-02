package geosolutions.zoom.da;

//@autor Sergio Medina
import geosolutions.zoom.be.ClienteBE;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ClienteDA extends BaseDA {

    String cadenaConexion;
    String DriverConnection;
    String user;
    String password;

    public ClienteDA() {
        cadenaConexion = super.getConnectionString();
        DriverConnection = super.getDriverConnection();
        user = super.getUser();
        password = super.getPassword();
    }

    public ClienteBE listarClienteBE(ClienteBE oClienteBE1) throws SQLException {
        ClienteBE oClienteBE = null;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sql = "";

        try {
            oClienteBE = new ClienteBE();
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);
            if (oClienteBE1.getIndOpSp() == 1) {

                sql = " SELECT codigocliente,nombrecliente,direccion,distrito,categoriacliente,gironegocio,coordenada_y,coordenada_x,visual FROM cliente WHERE codigocliente=? and visual=true";

            }
            pst = cn.prepareStatement(sql);
            pst.setString(1, oClienteBE1.getCodigocliente());
            rs = pst.executeQuery();
            System.out.println("consulta :" + sql);
            while (rs.next()) {
                oClienteBE.setCodigocliente(rs.getString("codigocliente"));
                oClienteBE.setNombrecliente(rs.getString("nombrecliente"));
                oClienteBE.setDireccion(rs.getString("direccion"));
                oClienteBE.setDistrito(rs.getString("distrito"));
                oClienteBE.setCategoriacliente(rs.getString("categoriacliente"));
                oClienteBE.setGironegocio(rs.getString("gironegocio"));
                oClienteBE.setCoordenada_y(rs.getDouble("coordenada_y"));
                oClienteBE.setCoordenada_x(rs.getDouble("coordenada_x"));
                oClienteBE.setVisual(rs.getInt("visual"));
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
        return oClienteBE;
    }

    public ArrayList<ClienteBE> listarRegistroClienteBE(ClienteBE oClienteBE1) throws SQLException {
        ArrayList<ClienteBE> listaClienteBE = null;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        try {
            listaClienteBE = new ArrayList<ClienteBE>();
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);
            String sql = "";
            if (oClienteBE1.getIndOpSp() == 1) {
                sql = " SELECT codigocliente,nombrecliente,direccion,distrito,categoriacliente,gironegocio,coordenada_y,coordenada_x,visual FROM cliente WHERE visual=true";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
            }
        
            if (oClienteBE1.getIndOpSp() == 2) {

                sql = " SELECT codigocliente,nombrecliente,direccion,distrito,categoriacliente,gironegocio,coordenada_y,coordenada_x,visual FROM cliente where nombrecliente ilike '%" + oClienteBE1.getNombrecliente() + "%' limit 10 ";

            }

            pst = cn.prepareStatement(sql);
            System.out.println("sql:"+sql);
            //pst.setString(1, oClienteBE1.getCodigocliente());
            rs = pst.executeQuery();

            while (rs.next()) {
                ClienteBE oClienteBE = new ClienteBE();
                oClienteBE.setCodigocliente(rs.getString("codigocliente"));
                oClienteBE.setNombrecliente(rs.getString("nombrecliente"));
                oClienteBE.setDireccion(rs.getString("direccion"));
                oClienteBE.setDistrito(rs.getString("distrito"));
                oClienteBE.setCategoriacliente(rs.getString("categoriacliente"));
                oClienteBE.setGironegocio(rs.getString("gironegocio"));
                oClienteBE.setCoordenada_y(rs.getDouble("coordenada_y"));
                oClienteBE.setCoordenada_x(rs.getDouble("coordenada_x"));
                oClienteBE.setVisual(rs.getInt("visual"));
                listaClienteBE.add(oClienteBE);
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
            oClienteBE1 = null;
        }
        return listaClienteBE;
    }

    /*Lisntado los cantidad de clientes que pertenecesn a un layer*/
    public List listaLayers(ClienteBE oClienteBE1) throws SQLException {
        List list = new LinkedList();
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        try {
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);
            String sql = "";
            if (oClienteBE1.getIndOpSp() == 1) {
                sql = " select \n"
                        + "	distinct case when codigodia is null then 'sin dia' else codigodia end\n"
                        + "	,count(codigodia)n"
                        + "	from cliente a\n"
                        + "	where \n"
                        + "	nombrecliente ilike case when '" + oClienteBE1.getNombrecliente() + "'='null'  then nombrecliente else '%" + oClienteBE1.getNombrecliente().replace(" ", "%") + "%' end \n"
                        + "	and direccion ilike case when '" + oClienteBE1.getDireccion() + "'='null'  then direccion else '%" + oClienteBE1.getDireccion().replace(" ", "%") + "%' end \n"
                        + "	and distrito ilike case when '" + oClienteBE1.getDistrito() + "'='null'  then distrito else '%" + oClienteBE1.getDistrito() + "%' end \n"
                        + "	and categoriacliente= case when '" + oClienteBE1.getCategoriacliente() + "'='null'  then categoriacliente else '" + oClienteBE1.getCategoriacliente() + "' end \n"
                        + "	and gironegocio = case when '" + oClienteBE1.getGironegocio() + "'='null'  then gironegocio else '" + oClienteBE1.getGironegocio() + "' end\n"
                        + "     and coordenada_y is not null\n"
                        + "	group by codigodia";

            }
            if (oClienteBE1.getIndOpSp() == 2) {
                sql = " select \n"
                        + "	distinct case when a.codigomesa is null then 'sin mesa' else a.codigomesa end \n"
                        + "	,count(a.codigomesa)n"
                        + "	from cliente a\n"
                        + "	where \n"
                        + "	nombrecliente ilike case when '" + oClienteBE1.getNombrecliente() + "'='null'  then nombrecliente else '%" + oClienteBE1.getNombrecliente().replace(" ", "%") + "%' end \n"
                        + "	and direccion ilike case when '" + oClienteBE1.getDireccion() + "'='null'  then direccion else '%" + oClienteBE1.getDireccion().replace(" ", "%") + "%' end \n"
                        + "	and a.distrito ilike case when '" + oClienteBE1.getDistrito() + "'='null'  then a.distrito else '%" + oClienteBE1.getDistrito() + "%' end \n"
                        + "	and categoriacliente= case when '" + oClienteBE1.getCategoriacliente() + "'='null'  then categoriacliente else '" + oClienteBE1.getCategoriacliente() + "' end \n"
                        + "	and gironegocio = case when '" + oClienteBE1.getGironegocio() + "'='null'  then gironegocio else '" + oClienteBE1.getGironegocio() + "' end\n"
                        + "     and coordenada_y is not null\n"
                        + "	group by a.codigomesa";
            }
            if (oClienteBE1.getIndOpSp() == 3) {

                sql = " select \n"
                        + "	distinct case when a.codigoruta is null then 'sin ruta' else a.codigoruta end  \n"
                        + "	,count(codigoruta)\n"
                        + "	from cliente a\n"
                        + "	where \n"
                        + "	nombrecliente ilike case when '" + oClienteBE1.getNombrecliente() + "'='null'  then nombrecliente else '%" + oClienteBE1.getNombrecliente().replace(" ", "%") + "%' end \n"
                        + "	and direccion ilike case when '" + oClienteBE1.getDireccion() + "'='null'  then direccion else '%" + oClienteBE1.getDireccion().replace(" ", "%") + "%' end \n"
                        + "	and a.distrito ilike case when '" + oClienteBE1.getDistrito() + "'='null'  then a.distrito else '%" + oClienteBE1.getDistrito() + "%' end \n"
                        + "	and categoriacliente= case when '" + oClienteBE1.getCategoriacliente() + "'='null'  then categoriacliente else '" + oClienteBE1.getCategoriacliente() + "' end \n"
                        + "	and gironegocio = case when '" + oClienteBE1.getGironegocio() + "'='null'  then gironegocio else '" + oClienteBE1.getGironegocio() + "' end\n"
                        + "     and coordenada_y is not null\n"
                        + "	group by a.codigoruta";
            }

            System.out.println("sql: " + sql);
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Object[] obj = {rs.getString(1), rs.getString(2)};
                list.add(obj);
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
            oClienteBE1 = null;
        }
        return list;
    }

    public List consultarCliente(ClienteBE oClienteBE1) throws SQLException {
        List list = new LinkedList();
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        try {

            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);
            String sql = "";
            if (oClienteBE1.getIndOpSp() == 1) {
                sql = " select \n"
                        + "	codigocliente,nombrecliente,direccion\n"
                        + "	,distrito,categoriacliente,gironegocio\n"
                        + "	,coordenada_y,coordenada_x,visual \n"
                        + "	from cliente\n"
                        + "	where \n"
                        + "	nombrecliente ilike case when '" + oClienteBE1.getNombrecliente() + "'='null'  then nombrecliente else '%" + oClienteBE1.getNombrecliente().replace(" ", "%") + "%' end \n"
                        + "	and direccion ilike case when '" + oClienteBE1.getDireccion() + "'='null'  then direccion else '%" + oClienteBE1.getDireccion().replace(" ", "%") + "%' end \n"
                        + "	and distrito ilike case when '" + oClienteBE1.getDistrito() + "'='null'  then distrito else '%" + oClienteBE1.getDistrito() + "%' end \n"
                        + "	and categoriacliente= case when '" + oClienteBE1.getCategoriacliente() + "'='null'  then categoriacliente else '" + oClienteBE1.getCategoriacliente() + "' end \n"
                        + "	and gironegocio = case when '" + oClienteBE1.getGironegocio() + "'='null'  then gironegocio else '" + oClienteBE1.getGironegocio() + "' end\n"
                        + "     and coordenada_y is not null\n"
                        + "	and coordenada_x is not null";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
            }
            if (oClienteBE1.getIndOpSp() == 2) {
                sql = " SELECT codigocliente,nombrecliente,direccion,distrito,categoriacliente,gironegocio,coordenada_y,coordenada_x,visual FROM cliente WHERE codigocliente=? and visual=true";
                pst = cn.prepareStatement(sql);
                pst.setString(1, oClienteBE1.getCodigocliente());
                rs = pst.executeQuery();
            }
            System.out.println("sql: " + sql);

            while (rs.next()) {
                Object[] obj = {rs.getString("codigocliente"), rs.getString("nombrecliente"), rs.getString("direccion"),
                    rs.getString("distrito"), rs.getString("categoriacliente"), rs.getString("gironegocio"),
                    rs.getString("coordenada_y"), rs.getString("coordenada_x"), rs.getString("visual")};
                list.add(obj);
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
            oClienteBE1 = null;
        }
        return list;
    }

    public ArrayList<ClienteBE> consultarClienteObject(ClienteBE oClienteBE1) throws SQLException {
        ArrayList<ClienteBE> list = new ArrayList<ClienteBE>();
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        try {

            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);
            String sql = "";
            if (oClienteBE1.getIndOpSp() == 1) {
                sql = " select \n"
                        + "	codigocliente,nombrecliente,direccion\n"
                        + "	,distrito,categoriacliente,gironegocio\n"
                        + "	,coordenada_y,coordenada_x,visual \n"
                        + "	from cliente\n"
                        + "	where \n"
                        + "	nombrecliente ilike case when '" + oClienteBE1.getNombrecliente() + "'='null'  then nombrecliente else '%" + oClienteBE1.getNombrecliente().replace(" ", "%") + "%' end \n"
                        + "	and direccion ilike case when '" + oClienteBE1.getDireccion() + "'='null'  then direccion else '%" + oClienteBE1.getDireccion().replace(" ", "%") + "%' end \n"
                        + "	and distrito ilike case when '" + oClienteBE1.getDistrito() + "'='null'  then distrito else '%" + oClienteBE1.getDistrito() + "%' end \n"
                        + "	and categoriacliente= case when '" + oClienteBE1.getCategoriacliente() + "'='null'  then categoriacliente else '" + oClienteBE1.getCategoriacliente() + "' end \n"
                        + "	and gironegocio = case when '" + oClienteBE1.getGironegocio() + "'='null'  then gironegocio else '" + oClienteBE1.getGironegocio() + "' end\n"
                        + "     and coordenada_y is not null\n"
                        + "	and coordenada_x is not null";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
            }
            if (oClienteBE1.getIndOpSp() == 2) {
                sql = " SELECT codigocliente,nombrecliente,direccion,distrito,categoriacliente,gironegocio,coordenada_y,coordenada_x,visual FROM cliente WHERE codigocliente=? and visual=true";
                pst = cn.prepareStatement(sql);
                pst.setString(1, oClienteBE1.getCodigocliente());
                rs = pst.executeQuery();
            }
            System.out.println("sql: " + sql);

            while (rs.next()) {

                ClienteBE oClienteBE = new ClienteBE();
                oClienteBE.setCodigocliente(rs.getString("codigocliente"));
                oClienteBE.setNombrecliente(rs.getString("nombrecliente"));
                oClienteBE.setDireccion(rs.getString("direccion"));
                oClienteBE.setDistrito(rs.getString("distrito"));
                oClienteBE.setCategoriacliente(rs.getString("categoriacliente"));
                oClienteBE.setGironegocio(rs.getString("gironegocio"));
                oClienteBE.setCoordenada_y(rs.getDouble("coordenada_y"));
                oClienteBE.setCoordenada_x(rs.getDouble("coordenada_x"));

                list.add(oClienteBE);
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
            oClienteBE1 = null;
        }
        return list;
    }

    public List cargarCombosFiltroCliente(ClienteBE oClienteBE1) throws SQLException {
        List list = new LinkedList();
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sql = "";

        try {

            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);

            if (oClienteBE1.getIndOpSp() == 1) {
                sql = "select distinct(distrito) distrito , distrito from cliente";

            }
            if (oClienteBE1.getIndOpSp() == 2) {
                sql = "select distinct(categoriacliente) categoriacliente , categoriacliente from cliente";

            }
            if (oClienteBE1.getIndOpSp() == 3) {
                sql = "select distinct(gironegocio) gironegocio , gironegocio from cliente";

            }

            pst = cn.prepareStatement(sql);
            //pst.setString(1, oClienteBE1.getCodigocliente());
            rs = pst.executeQuery();
            System.out.println("sql: " + sql);

            while (rs.next()) {
                Object[] obj = {rs.getString(1), rs.getString(2)};
                list.add(obj);
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
            oClienteBE1 = null;
        }
        return list;
    }

    public int insertarRegistrosClienteBE(ArrayList<ClienteBE> oListaClienteBE) throws SQLException {
        int resultado = 0;
        Connection cn = null;
        CallableStatement cs = null;

        try {
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);

            cn.setAutoCommit(false);

            for (ClienteBE oClienteBE : oListaClienteBE) {
                cs = cn.prepareCall("{call uspInsertarCliente(?,?,?,?,?,?,?,?,?)}");
                cs.setString(1, oClienteBE.getNombrecliente());
                cs.setString(2, oClienteBE.getDireccion());
                cs.setString(3, oClienteBE.getDistrito());
                cs.setString(4, oClienteBE.getCategoriacliente());
                cs.setString(5, oClienteBE.getGironegocio());
                cs.setDouble(6, oClienteBE.getCoordenada_y());
                cs.setDouble(7, oClienteBE.getCoordenada_x());
                cs.setInt(8, oClienteBE.getVisual());
                cs.registerOutParameter(9, java.sql.Types.INTEGER);
                cs.execute();
                resultado = cs.getInt(9);
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

    public int insertarClienteBE(ClienteBE oClienteBE) throws SQLException {
        int resultado = 0;
        Connection cn = null;
        CallableStatement cs = null;

        try {
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);

            cn.setAutoCommit(false);

            cs = cn.prepareCall("{call uspInsertarCliente(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, oClienteBE.getNombrecliente());
            cs.setString(2, oClienteBE.getDireccion());
            cs.setString(3, oClienteBE.getDistrito());
            cs.setString(4, oClienteBE.getCategoriacliente());
            cs.setString(5, oClienteBE.getGironegocio());
            cs.setDouble(6, oClienteBE.getCoordenada_y());
            cs.setDouble(7, oClienteBE.getCoordenada_x());
            cs.setInt(8, oClienteBE.getVisual());
            cs.registerOutParameter(9, java.sql.Types.INTEGER);
            cs.execute();
            resultado = cs.getInt(9);
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

    public int actualizarClienteBE(ClienteBE oClienteBE) throws SQLException {
        int resultado = 0;
        Connection cn = null;
        CallableStatement cs = null;

        try {
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);

            cn.setAutoCommit(false);

            cs = cn.prepareCall("{call uspActualizarCliente(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, oClienteBE.getCodigocliente());
            cs.setString(2, oClienteBE.getNombrecliente());
            cs.setString(3, oClienteBE.getDireccion());
            cs.setString(4, oClienteBE.getDistrito());
            cs.setString(5, oClienteBE.getCategoriacliente());
            cs.setString(6, oClienteBE.getGironegocio());
            cs.setDouble(7, oClienteBE.getCoordenada_y());
            cs.setDouble(8, oClienteBE.getCoordenada_x());
            cs.setInt(9, oClienteBE.getVisual());
            cs.registerOutParameter(9, java.sql.Types.INTEGER);
            cs.executeUpdate();
            resultado = cs.getInt(9);
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
