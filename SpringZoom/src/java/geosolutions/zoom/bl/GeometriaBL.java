/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geosolutions.zoom.bl;

import geosolutions.zoom.be.GeometriaBE;
import geosolutions.zoom.be.SquareBE;
import geosolutions.zoom.da.ClienteDA;
import geosolutions.zoom.da.GeometriaDA;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruben
 */
public class GeometriaBL {

    public GeometriaBL() {
    }

    public int insertarGeometriaBE(List<GeometriaBE> lista) {
        int resultado = 0;
        GeometriaDA oGeometriaDA = null;
        oGeometriaDA = new GeometriaDA();
        try {

            resultado = oGeometriaDA.insertarGeometriaBE(lista);

        } catch (SQLException ex) {
            Logger.getLogger(GeometriaBL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public ArrayList<GeometriaBE> listarGeometriaBE() {

        ArrayList<GeometriaBE> oListaGeometriaBE = null;
        GeometriaDA oGeometriaDA = new GeometriaDA();
        try {

            oListaGeometriaBE = oGeometriaDA.listarGeometriaBE();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            oGeometriaDA = null;
            oGeometriaDA = null;
        }
        return oListaGeometriaBE;
    }

    public List consultarClienteObjectGeometria(GeometriaBE oGeometriaBE) {
        List list = new LinkedList();
        GeometriaDA oGeometriaDA = null;
        try {
            oGeometriaDA = new GeometriaDA();
            list = oGeometriaDA.consultarClienteObjectGeometria(oGeometriaBE);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            oGeometriaDA = null;
        }
        return list;
    }

    public int eliminarGeometria(GeometriaBE oGeometriaBE) {

        int resultado = 0;
        try {

            GeometriaDA oGeometriaDA = null;
            oGeometriaDA = new GeometriaDA();
            resultado = oGeometriaDA.eliminarGeometria(oGeometriaBE);

        } catch (SQLException ex) {
            Logger.getLogger(GeometriaBL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public String actualizarGeometria(GeometriaBE oGeometriaBE) {
        String resultado = "0";
        try {

            GeometriaDA oGeometriaDA = null;
            oGeometriaDA = new GeometriaDA();
            resultado = oGeometriaDA.actualizarGeometria(oGeometriaBE);

        } catch (SQLException ex) {
            Logger.getLogger(GeometriaBL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;

    }

    public ArrayList<SquareBE> listarSquare(SquareBE osquareBE){

        ArrayList<SquareBE> oListSquare = null;
        GeometriaDA oGeometriaDA = new GeometriaDA();
        SquareBE oSquareBE = new SquareBE();
        try {

            oListSquare = oGeometriaDA.listarSquare(osquareBE);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            oGeometriaDA = null;
            oGeometriaDA = null;
        }
        return oListSquare;
    }

  
}
