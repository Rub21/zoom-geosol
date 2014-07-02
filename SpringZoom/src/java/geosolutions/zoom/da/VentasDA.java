package geosolutions.zoom.da;

//@autor Sergio Medina
import geosolutions.zoom.be.ClienteBE;
import geosolutions.zoom.be.VentasBE;
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

public class VentasDA extends BaseDA {

    String cadenaConexion;
    String DriverConnection;
    String user;
    String password;

    public VentasDA() {
        cadenaConexion = super.getConnectionString();
        DriverConnection = super.getDriverConnection();
        user = super.getUser();
        password = super.getPassword();
    }

    public VentasBE listarVentasBE(VentasBE oVentasBE1) throws SQLException {

        VentasBE oVentasBE = null;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sql = "";

        try {
            oVentasBE = new VentasBE();
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);

            if (oVentasBE1.getIndOpSp() == 1) {

                sql = "select b.codigodia,sum(cantidadunitaria) unidadesVendidas, sum (itemcostoventa) costoVenta, sum (itemvalorventa), sum (totalventa)  from ventas a\n"
                        + "inner join cliente b on a.codigocliente=b.codigocliente\n"
                        + "where anulado='FALSE' AND tipotransaccion='Venta'\n"
                        + "and date_part('year', to_timestamp(fecha_time))= case when '" + oVentasBE1.getAnio() + "'='null' then date_part('year', to_timestamp(fecha_time)) else " + oVentasBE1.getAnio() + " end \n"
                        + "and date_part('month', to_timestamp(fecha_time))=case when '" + oVentasBE1.getMes() + "'='null' then date_part('month', to_timestamp(fecha_time)) else " + oVentasBE1.getMes() + " end \n"
                        + "and date_part('day', to_timestamp(fecha_time))=case when '" + oVentasBE1.getDia() + "'='null' then date_part('day', to_timestamp(fecha_time)) else " + oVentasBE1.getDia() + " end \n"
                        + "group by b.codigodia";

            }
            if (oVentasBE1.getIndOpSp() == 2) {

                sql = "select b.codigomesa,sum(cantidadunitaria), sum (itemcostoventa), sum (itemvalorventa), sum (totalventa)  from ventas a\n"
                        + "inner join cliente b on a.codigocliente=b.codigocliente\n"
                        + "where anulado='FALSE' AND tipotransaccion='Venta'\n"
                        + "and date_part('year', to_timestamp(fecha_time))= case when '" + oVentasBE1.getAnio() + "'='null' then date_part('year', to_timestamp(fecha_time)) else " + oVentasBE1.getAnio() + " end \n"
                        + "and date_part('month', to_timestamp(fecha_time))=case when '" + oVentasBE1.getMes() + "'='null' then date_part('month', to_timestamp(fecha_time)) else " + oVentasBE1.getMes() + " end \n"
                        + "and date_part('day', to_timestamp(fecha_time))=case when '" + oVentasBE1.getDia() + "'='null' then date_part('day', to_timestamp(fecha_time)) else " + oVentasBE1.getDia() + " end \n"
                        + "group by b.codigomesa";

            }

            if (oVentasBE1.getIndOpSp() == 3) {

                sql = "select b.codigoruta,sum(cantidadunitaria), sum (itemcostoventa), sum (itemvalorventa), sum (totalventa) from ventas a\n"
                        + "inner join cliente b on a.codigocliente=b.codigocliente\n"
                        + "where anulado='FALSE' AND tipotransaccion='Venta'\n"
                        + "and date_part('year', to_timestamp(fecha_time))= case when '" + oVentasBE1.getAnio() + "'='null' then date_part('year', to_timestamp(fecha_time)) else " + oVentasBE1.getAnio() + " end \n"
                        + "and date_part('month', to_timestamp(fecha_time))=case when '" + oVentasBE1.getMes() + "'='null' then date_part('month', to_timestamp(fecha_time)) else " + oVentasBE1.getMes() + " end \n"
                        + "and date_part('day', to_timestamp(fecha_time))=case when '" + oVentasBE1.getDia() + "'='null' then date_part('day', to_timestamp(fecha_time)) else " + oVentasBE1.getDia() + " end \n"
                        + "group by b.codigoruta";

            }

            if (oVentasBE1.getIndOpSp() == 4) {

                sql = "select b.codigodia,sum(cantidadunitaria), sum (itemcostoventa), sum (itemvalorventa), sum (totalventa)  from ventas a\n"
                        + "inner join cliente b on a.codigocliente=b.codigocliente\n"
                        + "where anulado='FALSE' AND tipotransaccion='Venta'\n"
                        + "and a.fecha_time >= case when '" + oVentasBE1.getFechaInicio() + "' ilike '%null%' then extract(epoch FROM '1970-01-01'::date) else extract(epoch FROM '" + oVentasBE1.getFechaInicio() + "'::date)  end\n"
                        + "and a.fecha_time <= case when '" + oVentasBE1.getFechaFin() + "' ilike '%null%' then extract(epoch FROM '2100-01-01'::date) else extract(epoch FROM '" + oVentasBE1.getFechaFin() + "'::date) end\n"
                        + "group by b.codigodia";

            }
            if (oVentasBE1.getIndOpSp() == 5) {

                sql = "select b.codigomesa,sum(cantidadunitaria), sum (itemcostoventa), sum (itemvalorventa), sum (totalventa)  from ventas a\n"
                        + "inner join cliente b on a.codigocliente=b.codigocliente\n"
                        + "where anulado='FALSE' AND tipotransaccion='Venta'\n"
                        + "and a.fecha_time >= case when '" + oVentasBE1.getFechaInicio() + "' ilike '%null%' then extract(epoch FROM '1970-01-01'::date) else extract(epoch FROM '" + oVentasBE1.getFechaInicio() + "'::date)  end\n"
                        + "and a.fecha_time <= case when '" + oVentasBE1.getFechaFin() + "' ilike '%null%' then extract(epoch FROM '2100-01-01'::date) else extract(epoch FROM '" + oVentasBE1.getFechaFin() + "'::date) end\n"
                        + "group by b.codigomesa";

            }
            if (oVentasBE1.getIndOpSp() == 6) {

                sql = "select b.codigoruta,sum(cantidadunitaria), sum (itemcostoventa), sum (itemvalorventa), sum (totalventa) from ventas a\n"
                        + "inner join cliente b on a.codigocliente=b.codigocliente\n"
                        + "where anulado='FALSE' AND tipotransaccion='Venta'\n"
                        + "and a.fecha_time >= case when '" + oVentasBE1.getFechaInicio() + "' ilike '%null%' then extract(epoch FROM '1970-01-01'::date) else extract(epoch FROM '" + oVentasBE1.getFechaInicio() + "'::date)  end\n"
                        + "and a.fecha_time <= case when '" + oVentasBE1.getFechaFin() + "' ilike '%null%' then extract(epoch FROM '2100-01-01'::date) else extract(epoch FROM '" + oVentasBE1.getFechaFin() + "'::date) end\n"
                        + "group by b.codigoruta";

            }

            pst = cn.prepareStatement(sql);
            //pst.setString(1, oVentasBE1.getCodigovendedor());
            System.out.println("consulta :" + sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                oVentasBE.setCodigovendedor(rs.getString("codigovendedor"));
                oVentasBE.setFuerzaventas(rs.getString("fuerzaventas"));
                oVentasBE.setGrupoventas(rs.getString("grupoventas"));
                oVentasBE.setCodigocliente(rs.getString("codigocliente"));
                oVentasBE.setCodigozona(rs.getString("codigozona"));
                oVentasBE.setCodigomesa(rs.getString("codigomesa"));
                oVentasBE.setCodigodia(rs.getString("codigodia"));
                oVentasBE.setFechafacturacion(rs.getString("fechafacturacion"));
                oVentasBE.setTipodocumento(rs.getString("tipodocumento"));
                oVentasBE.setNumerodocumento(rs.getString("numerodocumento"));
                oVentasBE.setCodigoproducto(rs.getString("codigoproducto"));
                oVentasBE.setCodigobonificacion(rs.getString("codigobonificacion"));
                oVentasBE.setBonificacion(rs.getString("bonificacion"));
                oVentasBE.setCodigoalmacen(rs.getString("codigoalmacen"));
                oVentasBE.setFormaventa(rs.getString("formaventa"));
                oVentasBE.setAnulado(rs.getString("anulado"));
                oVentasBE.setMotivoanulacion(rs.getString("motivoanulacion"));
                oVentasBE.setTipotransaccion(rs.getString("tipotransaccion"));
                oVentasBE.setCantidadunitaria(rs.getInt("cantidadunitaria"));
                oVentasBE.setCantidadformato(rs.getDouble("cantidadformato"));
                oVentasBE.setValorunitario(rs.getDouble("valorunitario"));
                oVentasBE.setItemcostoventa(rs.getDouble("itemcostoventa"));
                oVentasBE.setItemvalorventa(rs.getDouble("itemvalorventa"));
                oVentasBE.setTotalventa(rs.getDouble("totalventa"));
                oVentasBE.setCodigovehiculo(rs.getString("codigovehiculo"));
                oVentasBE.setFecha_time(rs.getInt("fecha_time"));
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
        return oVentasBE;
    }

    public List cargarCombosFiltroVentas(VentasBE oVentasBE) throws SQLException {

        List list = new LinkedList();
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sql = "";

        try {

            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);

            if (oVentasBE.getIndOpSp() == 1) { //opcion para mostrar el vendedor
                sql = "select codigovendedor,nombrevendedor from vendedor";

            }
            if (oVentasBE.getIndOpSp() == 2) { //fuerza ventas 
                sql = "select distinct(fuerzaventas) codFuerza,fuerzaventas from ventas;";

            }
            if (oVentasBE.getIndOpSp() == 3) {//grupo ventas
                sql = "select distinct(grupoventas) codgrupoventas,grupoventas from ventas;";

            }
            if (oVentasBE.getIndOpSp() == 4) {
                sql = "select distinct(codigozona) codcodigozona,codigozona from ventas;";

            }
            if (oVentasBE.getIndOpSp() == 5) {
                sql = "select distinct(tipodocumento) codtipodocumento,tipodocumento from ventas;";

            }
            if (oVentasBE.getIndOpSp() == 6) {
                sql = "select distinct(codigoproducto) , codigobonificacion from ventas where codigobonificacion is not null;";

            }
            if (oVentasBE.getIndOpSp() == 7) {
                sql = "select distinct(tipotransaccion) codtipotransaccion , tipotransaccion from ventas;";

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
            oVentasBE = null;
        }
        return list;
    }

    public List FiltroVentas(VentasBE oVentasBE) throws SQLException {

        List list = new LinkedList();
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sql = "";

        try {

            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);

            sql = " select * from lst_ventas_vendedor('" + oVentasBE.getCodigovendedor() + "',' " + oVentasBE.getFuerzaventas()
                    + "', '" + oVentasBE.getGrupoventas() + "',' " + oVentasBE.getCodigozona() + "', '" + oVentasBE.getTipodocumento()
                    + "', '" + oVentasBE.getCodigoproducto() + "', '" + oVentasBE.getTipotransaccion()
                    + "','" + oVentasBE.getFechaInicio() + "',' " + oVentasBE.getFechaFin() + "') ;";

            System.out.println("sql :" + sql);
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] obj = {rs.getDouble("y"), rs.getDouble("x"), rs.getString("codigo"), rs.getString("nombre")};
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
            oVentasBE = null;
        }
        return list;
    }

    public List listarRegistroVentasBE(VentasBE oVentasBE1) throws SQLException {
        List list = new LinkedList();
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        try {

            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);
            String sql = "";
            if (oVentasBE1.getIndOpSp() == 1) {

                sql = "select b.codigodia,sum(cantidadunitaria) unidadesVendidas, sum (itemcostoventa) costoVenta, sum (itemvalorventa), sum (totalventa)  from ventas a\n"
                        + "inner join cliente b on a.codigocliente=b.codigocliente\n"
                        + "where anulado='FALSE' AND tipotransaccion='Venta'\n"
                        + "and date_part('year', to_timestamp(fecha_time))= case when '" + oVentasBE1.getAnio() + "'='null' then date_part('year', to_timestamp(fecha_time)) else " + oVentasBE1.getAnio() + " end \n"
                        + "and date_part('month', to_timestamp(fecha_time))=case when '" + oVentasBE1.getMes() + "'='null' then date_part('month', to_timestamp(fecha_time)) else " + oVentasBE1.getMes() + " end \n"
                        + "and date_part('day', to_timestamp(fecha_time))=case when '" + oVentasBE1.getDia() + "'='null' then date_part('day', to_timestamp(fecha_time)) else " + oVentasBE1.getDia() + " end \n"
                        + "and b.codigodia is not null\n"
                        + "group by b.codigodia";

            }
            if (oVentasBE1.getIndOpSp() == 2) {

                sql = "select b.codigomesa,sum(cantidadunitaria), sum (itemcostoventa), sum (itemvalorventa), sum (totalventa)  from ventas a\n"
                        + "inner join cliente b on a.codigocliente=b.codigocliente\n"
                        + "where anulado='FALSE' AND tipotransaccion='Venta'\n"
                        + "and date_part('year', to_timestamp(fecha_time))= case when '" + oVentasBE1.getAnio() + "'='null' then date_part('year', to_timestamp(fecha_time)) else " + oVentasBE1.getAnio() + " end \n"
                        + "and date_part('month', to_timestamp(fecha_time))=case when '" + oVentasBE1.getMes() + "'='null' then date_part('month', to_timestamp(fecha_time)) else " + oVentasBE1.getMes() + " end \n"
                        + "and date_part('day', to_timestamp(fecha_time))=case when '" + oVentasBE1.getDia() + "'='null' then date_part('day', to_timestamp(fecha_time)) else " + oVentasBE1.getDia() + " end \n"
                        + "and b.codigomesa is not null\n"
                        + "group by b.codigomesa";

            }
            if (oVentasBE1.getIndOpSp() == 3) {

                sql = "select b.codigoruta,sum(cantidadunitaria), sum (itemcostoventa), sum (itemvalorventa), sum (totalventa) from ventas a\n"
                        + "inner join cliente b on a.codigocliente=b.codigocliente\n"
                        + "where anulado='FALSE' AND tipotransaccion='Venta'\n"
                        + "and date_part('year', to_timestamp(fecha_time))= case when '" + oVentasBE1.getAnio() + "'='null' then date_part('year', to_timestamp(fecha_time)) else " + oVentasBE1.getAnio() + " end \n"
                        + "and date_part('month', to_timestamp(fecha_time))=case when '" + oVentasBE1.getMes() + "'='null' then date_part('month', to_timestamp(fecha_time)) else " + oVentasBE1.getMes() + " end \n"
                        + "and date_part('day', to_timestamp(fecha_time))=case when '" + oVentasBE1.getDia() + "'='null' then date_part('day', to_timestamp(fecha_time)) else " + oVentasBE1.getDia() + " end \n"
                        + "and b.codigoruta is not null\n"
                        + "group by b.codigoruta";

            }
            if (oVentasBE1.getIndOpSp() == 4) {

                sql = "select b.codigodia,sum(cantidadunitaria), sum (itemcostoventa), sum (itemvalorventa), sum (totalventa)  from ventas a\n"
                        + "inner join cliente b on a.codigocliente=b.codigocliente\n"
                        + "where anulado='FALSE' AND tipotransaccion='Venta'\n"
                        + "and a.fecha_time >= case when '" + oVentasBE1.getFechaInicio() + "' ilike '%null%' then extract(epoch FROM '1970-01-01'::date) else extract(epoch FROM '" + oVentasBE1.getFechaInicio() + "'::date)  end\n"
                        + "and a.fecha_time <= case when '" + oVentasBE1.getFechaFin() + "' ilike '%null%' then extract(epoch FROM '2100-01-01'::date) else extract(epoch FROM '" + oVentasBE1.getFechaFin() + "'::date) end\n"
                        + "and b.codigodia is not null\n"
                        + "group by b.codigodia";

            }
            if (oVentasBE1.getIndOpSp() == 5) {

                sql = "select b.codigomesa,sum(cantidadunitaria), sum (itemcostoventa), sum (itemvalorventa), sum (totalventa)  from ventas a\n"
                        + "inner join cliente b on a.codigocliente=b.codigocliente\n"
                        + "where anulado='FALSE' AND tipotransaccion='Venta'\n"
                        + "and a.fecha_time >= case when '" + oVentasBE1.getFechaInicio() + "' ilike '%null%' then extract(epoch FROM '1970-01-01'::date) else extract(epoch FROM '" + oVentasBE1.getFechaInicio() + "'::date)  end\n"
                        + "and a.fecha_time <= case when '" + oVentasBE1.getFechaFin() + "' ilike '%null%' then extract(epoch FROM '2100-01-01'::date) else extract(epoch FROM '" + oVentasBE1.getFechaFin() + "'::date) end\n"
                        + "and b.codigomesa is not null\n"
                        + "group by b.codigomesa";

            }
            if (oVentasBE1.getIndOpSp() == 6) {

                sql = "select b.codigoruta,sum(cantidadunitaria), sum (itemcostoventa), sum (itemvalorventa), sum (totalventa) from ventas a\n"
                        + "inner join cliente b on a.codigocliente=b.codigocliente\n"
                        + "where anulado='FALSE' AND tipotransaccion='Venta'\n"
                        + "and a.fecha_time >= case when '" + oVentasBE1.getFechaInicio() + "' ilike '%null%' then extract(epoch FROM '1970-01-01'::date) else extract(epoch FROM '" + oVentasBE1.getFechaInicio() + "'::date)  end\n"
                        + "and a.fecha_time <= case when '" + oVentasBE1.getFechaFin() + "' ilike '%null%' then extract(epoch FROM '2100-01-01'::date) else extract(epoch FROM '" + oVentasBE1.getFechaFin() + "'::date) end\n"
                        + "and b.codigoruta is not null \n"
                        + "group by b.codigoruta";

            }
            pst = cn.prepareStatement(sql);
            System.out.println("sql:" + sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Object[] obj = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
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
            oVentasBE1 = null;
        }
        return list;
    }

    public int insertarRegistrosVentasBE(ArrayList<VentasBE> oListaVentasBE) throws SQLException {
        int resultado = 0;
        Connection cn = null;
        CallableStatement cs = null;

        try {
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);

            cn.setAutoCommit(false);

            for (VentasBE oVentasBE : oListaVentasBE) {
                cs = cn.prepareCall("{call uspInsertarVentas(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cs.setString(1, oVentasBE.getFuerzaventas());
                cs.setString(2, oVentasBE.getGrupoventas());
                cs.setString(3, oVentasBE.getCodigocliente());
                cs.setString(4, oVentasBE.getCodigozona());
                cs.setString(5, oVentasBE.getCodigomesa());
                cs.setString(6, oVentasBE.getCodigodia());
                cs.setString(7, oVentasBE.getFechafacturacion());
                cs.setString(8, oVentasBE.getTipodocumento());
                cs.setString(9, oVentasBE.getNumerodocumento());
                cs.setString(10, oVentasBE.getCodigoproducto());
                cs.setString(11, oVentasBE.getCodigobonificacion());
                cs.setString(12, oVentasBE.getBonificacion());
                cs.setString(13, oVentasBE.getCodigoalmacen());
                cs.setString(14, oVentasBE.getFormaventa());
                cs.setString(15, oVentasBE.getAnulado());
                cs.setString(16, oVentasBE.getMotivoanulacion());
                cs.setString(17, oVentasBE.getTipotransaccion());
                cs.setInt(18, oVentasBE.getCantidadunitaria());
                cs.setDouble(19, oVentasBE.getCantidadformato());
                cs.setDouble(20, oVentasBE.getValorunitario());
                cs.setDouble(21, oVentasBE.getItemcostoventa());
                cs.setDouble(22, oVentasBE.getItemvalorventa());
                cs.setDouble(23, oVentasBE.getTotalventa());
                cs.setString(24, oVentasBE.getCodigovehiculo());
                cs.setInt(25, oVentasBE.getFecha_time());
                cs.registerOutParameter(26, java.sql.Types.INTEGER);
                cs.execute();
                resultado = cs.getInt(26);
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

    public int insertarVentasBE(VentasBE oVentasBE) throws SQLException {
        int resultado = 0;
        Connection cn = null;
        CallableStatement cs = null;

        try {
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);

            cn.setAutoCommit(false);

            cs = cn.prepareCall("{call uspInsertarVentas(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, oVentasBE.getFuerzaventas());
            cs.setString(2, oVentasBE.getGrupoventas());
            cs.setString(3, oVentasBE.getCodigocliente());
            cs.setString(4, oVentasBE.getCodigozona());
            cs.setString(5, oVentasBE.getCodigomesa());
            cs.setString(6, oVentasBE.getCodigodia());
            cs.setString(7, oVentasBE.getFechafacturacion());
            cs.setString(8, oVentasBE.getTipodocumento());
            cs.setString(9, oVentasBE.getNumerodocumento());
            cs.setString(10, oVentasBE.getCodigoproducto());
            cs.setString(11, oVentasBE.getCodigobonificacion());
            cs.setString(12, oVentasBE.getBonificacion());
            cs.setString(13, oVentasBE.getCodigoalmacen());
            cs.setString(14, oVentasBE.getFormaventa());
            cs.setString(15, oVentasBE.getAnulado());
            cs.setString(16, oVentasBE.getMotivoanulacion());
            cs.setString(17, oVentasBE.getTipotransaccion());
            cs.setInt(18, oVentasBE.getCantidadunitaria());
            cs.setDouble(19, oVentasBE.getCantidadformato());
            cs.setDouble(20, oVentasBE.getValorunitario());
            cs.setDouble(21, oVentasBE.getItemcostoventa());
            cs.setDouble(22, oVentasBE.getItemvalorventa());
            cs.setDouble(23, oVentasBE.getTotalventa());
            cs.setString(24, oVentasBE.getCodigovehiculo());
            cs.setInt(25, oVentasBE.getFecha_time());
            cs.registerOutParameter(26, java.sql.Types.INTEGER);
            cs.execute();
            resultado = cs.getInt(26);
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

    public int actualizarVentasBE(VentasBE oVentasBE) throws SQLException {
        int resultado = 0;
        Connection cn = null;
        CallableStatement cs = null;

        try {
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);

            cn.setAutoCommit(false);

            cs = cn.prepareCall("{call uspActualizarVentas(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, oVentasBE.getCodigovendedor());
            cs.setString(2, oVentasBE.getFuerzaventas());
            cs.setString(3, oVentasBE.getGrupoventas());
            cs.setString(4, oVentasBE.getCodigocliente());
            cs.setString(5, oVentasBE.getCodigozona());
            cs.setString(6, oVentasBE.getCodigomesa());
            cs.setString(7, oVentasBE.getCodigodia());
            cs.setString(8, oVentasBE.getFechafacturacion());
            cs.setString(9, oVentasBE.getTipodocumento());
            cs.setString(10, oVentasBE.getNumerodocumento());
            cs.setString(11, oVentasBE.getCodigoproducto());
            cs.setString(12, oVentasBE.getCodigobonificacion());
            cs.setString(13, oVentasBE.getBonificacion());
            cs.setString(14, oVentasBE.getCodigoalmacen());
            cs.setString(15, oVentasBE.getFormaventa());
            cs.setString(16, oVentasBE.getAnulado());
            cs.setString(17, oVentasBE.getMotivoanulacion());
            cs.setString(18, oVentasBE.getTipotransaccion());
            cs.setInt(19, oVentasBE.getCantidadunitaria());
            cs.setDouble(20, oVentasBE.getCantidadformato());
            cs.setDouble(21, oVentasBE.getValorunitario());
            cs.setDouble(22, oVentasBE.getItemcostoventa());
            cs.setDouble(23, oVentasBE.getItemvalorventa());
            cs.setDouble(24, oVentasBE.getTotalventa());
            cs.setString(25, oVentasBE.getCodigovehiculo());
            cs.setInt(26, oVentasBE.getFecha_time());
            cs.registerOutParameter(26, java.sql.Types.INTEGER);
            cs.executeUpdate();
            resultado = cs.getInt(26);
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
