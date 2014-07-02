package geosolutions.zoom.bl;

//@autor Sergio Medina
import geosolutions.zoom.be.VentasBE;
import geosolutions.zoom.da.VentasDA;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class VentasBL {

    public VentasBL() {
    }

    public VentasBE listarVentasBE(VentasBE oVentasBE1) {
        VentasBE oVentasBE = null;
        VentasDA oVentasDA = null;
        try {
            oVentasDA = new VentasDA();
            oVentasBE = oVentasDA.listarVentasBE(oVentasBE1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            oVentasBE1 = null;
            oVentasDA = null;
        }
        return oVentasBE;
    }

    public List cargarCombosFiltroVentas(VentasBE oVentasBE1) {
        List list = new LinkedList();
        VentasDA oVentasDA = null;
        try {
            oVentasDA = new VentasDA();
            list = oVentasDA.cargarCombosFiltroVentas(oVentasBE1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            oVentasBE1 = null;
            oVentasDA = null;
        }
        return list;
    }
    
     public List FiltroVentas(VentasBE oVentasBE1) {
        List list = new LinkedList();
        VentasDA oVentasDA = null;
        try {
            oVentasDA = new VentasDA();
            list = oVentasDA.FiltroVentas(oVentasBE1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            oVentasBE1 = null;
            oVentasDA = null;
        }
        return list;
    }

    public List listarRegistrosVentasBE(VentasBE oVentasBE) {
        List oListaVentasBE = null;
        VentasDA oVentasDA = null;
        try {
            oVentasDA = new VentasDA();
            oListaVentasBE = oVentasDA.listarRegistroVentasBE(oVentasBE);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            oVentasBE = null;
            oVentasDA = null;
        }
        return oListaVentasBE;
    }

    public int insertarVentasBE(VentasBE oVentasBE) {
        int resultado = 0;
        VentasDA oVentasDA = null;

        try {
            oVentasDA = new VentasDA();
            resultado = oVentasDA.insertarVentasBE(oVentasBE);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            oVentasBE = null;
            oVentasDA = null;
        }
        return resultado;
    }

    public int insertarRegistrosVentasBE(ArrayList<VentasBE> oListaVentasBE) {
        int resultado = 0;
        VentasDA oVentasDA = null;

        try {
            oVentasDA = new VentasDA();
            resultado = oVentasDA.insertarRegistrosVentasBE(oListaVentasBE);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            oListaVentasBE = null;
            oVentasDA = null;
        }
        return resultado;
    }

    public int actualizarVentasBE(VentasBE oVentasBE1) {
        int resultado = 0;
        VentasDA oVentasDA = null;
        try {
            oVentasDA = new VentasDA();
            resultado = oVentasDA.actualizarVentasBE(oVentasBE1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            oVentasBE1 = null;
            oVentasDA = null;
        }

        return resultado;
    }

}
