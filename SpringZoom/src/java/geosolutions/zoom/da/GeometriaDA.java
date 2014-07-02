/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geosolutions.zoom.da;

import geosolutions.zoom.be.AlmacenBE;
import geosolutions.zoom.be.ClienteBE;
import geosolutions.zoom.be.GeometriaBE;
import geosolutions.zoom.be.SquareBE;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ruben
 */
public class GeometriaDA extends BaseDA {

    String cadenaConexion;
    String DriverConnection;
    String user;
    String password;

    public GeometriaDA() {
        cadenaConexion = super.getConnectionString();
        DriverConnection = super.getDriverConnection();
        user = super.getUser();
        password = super.getPassword();
    }

    public int insertarGeometriaBE(List<GeometriaBE> lista) throws SQLException {
        //VendedorBE oVendedorBE = null;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        try {
            //oVendedorBE = new VendedorBE();
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);

            String sql = "";

            for (int i = 0; i < lista.size(); i++) {
                String geom = "";
                if (lista.get(i).getTipo().equals("rectangle")) {
                    geom = "'POLYGON((" + lista.get(i).getValor() + "))'";
                } else if (lista.get(i).getTipo().equals("circle")) {
                    geom = lista.get(i).getValor();
                    int indice = geom.indexOf("/");
                    String cordenadas = geom.substring(0, indice);
                    String radio = geom.substring(indice + 1, geom.length());
                    geom = "ST_Buffer(ST_GeomFromText('POINT(" + cordenadas + ")'), " + radio + ")";

                } else if (lista.get(i).getTipo().equals("polygon")) {
                    geom = "'POLYGON((" + lista.get(i).getValor() + "))'";
                } else if (lista.get(i).getTipo().equals("polyline")) {

                    geom = "'LINESTRING(" + lista.get(i).getValor() + ")'";
                }
                //   sql = sql + "INSERT INTO geometries VALUES ('" + lista.get(i).getIdgeometria() + "','" + lista.get(i).getNombre() + "','" + lista.get(i).getTipo() + "', " + geom + ");";

                sql = sql + "INSERT INTO geometria (idgeometria, nombre,tipo,valor,descripcion,color,opacity) select case  when max(idgeometria) is null then 1 else max(idgeometria::integer)+1 end ,'" + lista.get(i).getNombre() + "','" + lista.get(i).getTipo() + "'," + geom + ", '" + lista.get(i).getDescripcion() + "', '" + lista.get(i).getColor() + "', " + lista.get(i).getOpacity() + " from geometria;";
//DELETE FROM geometria;
            }

            pst = cn.prepareStatement(sql);

            pst.execute();
            System.out.println("consulta :" + sql);

            cn.commit();
            cn.setAutoCommit(true);
        } catch (Exception ex) {
            cn.rollback();
            ex.printStackTrace();
        } finally {

            cn.close();
            cn = null;

        }

        return 1;
    }

    public ArrayList<GeometriaBE> listarGeometriaBE() throws SQLException {
        ArrayList<GeometriaBE> listaGeometriaBE = null;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        try {
            listaGeometriaBE = new ArrayList<GeometriaBE>();
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);

            String sql = "select idgeometria, nombre, tipo , st_asgeojson(valor,5) as valor, descripcion, color, opacity from geometria;";
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                ClienteBE oClienteBE = new ClienteBE();
                GeometriaBE oGeometriaBE = new GeometriaBE();
                oGeometriaBE.setIdgeometria(rs.getString("idgeometria"));
                oGeometriaBE.setNombre(rs.getString("nombre"));
                oGeometriaBE.setTipo(rs.getString("tipo"));
                oGeometriaBE.setValor(rs.getString("valor"));
                oGeometriaBE.setDescripcion(rs.getString("descripcion"));
                oGeometriaBE.setColor(rs.getString("color"));
                oGeometriaBE.setOpacity(rs.getInt("opacity"));
                listaGeometriaBE.add(oGeometriaBE);
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
        return listaGeometriaBE;
    }

    public List consultarClienteObjectGeometria(GeometriaBE oGeometriaBE) throws SQLException {
        List list = new LinkedList();
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        System.out.println(oGeometriaBE);
        try {

            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);
            String sql = "";
            if (oGeometriaBE.getIndOpSp() == 2) {//consulatr geometria
                String geom = "";
                if (oGeometriaBE.getTipo().equals("rectangle")) {
                    geom = "'POLYGON((" + oGeometriaBE.getValor() + "))'";
                } else if (oGeometriaBE.getTipo().equals("circle")) {
                    geom = oGeometriaBE.getValor();
                    int indice = geom.indexOf("/");
                    String cordenadas = geom.substring(0, indice);
                    String radio = geom.substring(indice + 1, geom.length());
                    geom = "ST_Buffer(ST_GeomFromText('POINT(" + cordenadas + ")'), " + radio + ")";

                } else if (oGeometriaBE.getTipo().equals("polygon")) {
                    geom = "'POLYGON((" + oGeometriaBE.getValor() + "))'";
                } else if (oGeometriaBE.getTipo().equals("polyline")) {

                    geom = "'LINESTRING(" + oGeometriaBE.getValor() + ")'";
                }

                sql = " select  codigocliente, nombrecliente,\n"
                        + "		 direccion,distrito, \n"
                        + "		 categoriacliente,gironegocio, \n"
                        + "		 coordenada_y, coordenada_x,visual,\n"
                        + "		  codigomesa,codigodia, codigoruta from consultar_geometriacliente_geom(" + geom + ");";
                System.out.println("sql: " + sql);
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();

            } else if (oGeometriaBE.getIndOpSp() == 1) {

                sql = " select  codigocliente, nombrecliente,\n"
                        + "		 direccion,distrito, \n"
                        + "		 categoriacliente,gironegocio, \n"
                        + "		 coordenada_y, coordenada_x,visual,\n"
                        + "		  codigomesa,codigodia, codigoruta from consultar_geometriacliente_id('" + oGeometriaBE.getIdgeometria() + "');";
                System.out.println("sql: " + sql);
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
            }

            while (rs.next()) {
                ClienteBE oBE = new ClienteBE();
                oBE.setCodigocliente(rs.getString("codigocliente"));
                oBE.setNombrecliente(rs.getString("nombrecliente"));
                oBE.setDireccion(rs.getString("direccion"));
                oBE.setDistrito(rs.getString("distrito"));
                oBE.setCategoriacliente(rs.getString("categoriacliente"));
                oBE.setGironegocio(rs.getString("gironegocio"));
                oBE.setCoordenada_x(rs.getDouble("coordenada_x"));
                oBE.setCoordenada_y(rs.getDouble("coordenada_y"));
                oBE.setVisual(rs.getInt("visual"));

                list.add(oBE);
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
        return list;
    }

    public int eliminarGeometria(GeometriaBE oGeometriaBE) throws SQLException {

        //VendedorBE oVendedorBE = null;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        try {
            //oVendedorBE = new VendedorBE();
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);

            String sql = "DELETE FROM geometria WHERE idgeometria='" + oGeometriaBE.getIdgeometria() + "';";

            pst = cn.prepareStatement(sql);

            pst.execute();
            System.out.println("consulta :" + sql);

            cn.commit();
            cn.setAutoCommit(true);

        } catch (Exception ex) {
            cn.rollback();
            ex.printStackTrace();
        } finally {

            cn.close();
            cn = null;

        }

        return 1;
    }

    public String actualizarGeometria(GeometriaBE oGeometriaBE) throws SQLException {

        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        try {
            //oVendedorBE = new VendedorBE();
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);

            String geom = "";
            if (oGeometriaBE.getTipo().equals("rectangle")) {
                geom = "'POLYGON((" + oGeometriaBE.getValor() + "))'";
            } else if (oGeometriaBE.getTipo().equals("circle")) {
                geom = oGeometriaBE.getValor();
                int indice = geom.indexOf("/");
                String cordenadas = geom.substring(0, indice);
                String radio = geom.substring(indice + 1, geom.length());
                geom = "ST_Buffer(ST_GeomFromText('POINT(" + cordenadas + ")'), " + radio + ")";

            } else if (oGeometriaBE.getTipo().equals("polygon")) {
                geom = "'POLYGON((" + oGeometriaBE.getValor() + "))'";
            } else if (oGeometriaBE.getTipo().equals("polyline")) {

                geom = "'LINESTRING(" + oGeometriaBE.getValor() + ")'";
            }

            String sql = "UPDATE geometria SET  valor= " + geom + " WHERE idgeometria='" + oGeometriaBE.getIdgeometria() + "';";
            System.out.println("consulta :" + sql);
            pst = cn.prepareStatement(sql);

            pst.execute();

            cn.commit();
            cn.setAutoCommit(true);

        } catch (Exception ex) {
            cn.rollback();
            ex.printStackTrace();
        } finally {

            cn.close();
            cn = null;

        }

        return "1";
    }

    public ArrayList<SquareBE> listarSquare(SquareBE osquareBE) throws SQLException {

        ArrayList<SquareBE> listaSquareBE = null;
        Connection cn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        try {
            listaSquareBE = new ArrayList<SquareBE>();
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
            cn.setAutoCommit(false);
            String sql = "";
            if (osquareBE.getIndOpSp() == 5) {
                sql = "SELECT gid, get_num_clientes(geom) AS num_cliente FROM square0005  ORDER BY gid asc;";
            }
            if (osquareBE.getIndOpSp() == 25) {
                sql = "SELECT gid, get_num_clientes(geom) AS num_cliente FROM square00025  ORDER BY gid asc;";
            }
            if (osquareBE.getIndOpSp() == 45) {
                sql = "SELECT gid, get_num_clientes(geom) AS num_cliente FROM hexagono00025  ORDER BY gid asc;";
            }
            if (osquareBE.getIndOpSp() == 50) {
                sql = "SELECT gid, get_num_clientes(geom) AS num_cliente FROM hexagono0005  ORDER BY gid asc;";
            }
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                SquareBE oSquareBE = new SquareBE();
                oSquareBE.setGid(rs.getInt("gid"));
                oSquareBE.setNum_clientes(rs.getInt("num_cliente"));

                listaSquareBE.add(oSquareBE);
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
        return listaSquareBE;

    }

}
