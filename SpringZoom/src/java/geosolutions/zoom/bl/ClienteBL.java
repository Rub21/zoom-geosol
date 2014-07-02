package geosolutions.zoom.bl;

//@autor Sergio Medina
import geosolutions.zoom.be.ClienteBE;
import geosolutions.zoom.da.ClienteDA;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ClienteBL {

    public ClienteBL() {
    }

    public ClienteBE listarClienteBE(ClienteBE oClienteBE1) {
        ClienteBE oClienteBE = null;
        ClienteDA oClienteDA = null;
        try {
            oClienteDA = new ClienteDA();
            oClienteBE = oClienteDA.listarClienteBE(oClienteBE1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            oClienteBE1 = null;
            oClienteDA = null;
        }
        return oClienteBE;
    }

    public ArrayList<ClienteBE> listarRegistrosClienteBE(ClienteBE oClienteBE) {
        ArrayList<ClienteBE> oListaClienteBE = null;
        ClienteDA oClienteDA = null;
        try {
            oClienteDA = new ClienteDA();
            oListaClienteBE = oClienteDA.listarRegistroClienteBE(oClienteBE);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            oClienteBE = null;
            oClienteDA = null;
        }
        return oListaClienteBE;
    }

    public List cargarCombosFiltroCliente(ClienteBE oClienteBE1) {
        List list = new LinkedList();
        ClienteBE oClienteBE = null;
        ClienteDA oClienteDA = null;
        try {
            oClienteDA = new ClienteDA();
            list = oClienteDA.cargarCombosFiltroCliente(oClienteBE1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            oClienteBE1 = null;
            oClienteDA = null;
        }
        return list;
    }

    public List consultarCliente(ClienteBE oClienteBE1) {
        List list = new LinkedList();
        ClienteBE oClienteBE = null;
        ClienteDA oClienteDA = null;
        try {
            oClienteDA = new ClienteDA();
            list = oClienteDA.consultarCliente(oClienteBE1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            oClienteBE1 = null;
            oClienteDA = null;
        }
        return list;
    }
    
    
      public List listaLayers(ClienteBE oClienteBE1) {
        List list = new LinkedList();
        ClienteBE oClienteBE = null;
        ClienteDA oClienteDA = null;
        try {
            oClienteDA = new ClienteDA();
            list = oClienteDA.listaLayers(oClienteBE1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            oClienteBE1 = null;
            oClienteDA = null;
        }
        return list;
    }
    
    

    public List consultarClienteObject(ClienteBE oClienteBE1) {
        List list = new LinkedList();
        ClienteBE oClienteBE = null;
        ClienteDA oClienteDA = null;
        try {
            oClienteDA = new ClienteDA();
            list = oClienteDA.consultarClienteObject(oClienteBE1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            oClienteBE1 = null;
            oClienteDA = null;
        }
        return list;
    }

    public int insertarClienteBE(ClienteBE oClienteBE) {
        int resultado = 0;
        ClienteDA oClienteDA = null;

        try {
            oClienteDA = new ClienteDA();
            resultado = oClienteDA.insertarClienteBE(oClienteBE);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            oClienteBE = null;
            oClienteDA = null;
        }
        return resultado;
    }

    public int insertarRegistrosClienteBE(ArrayList<ClienteBE> oListaClienteBE) {
        int resultado = 0;
        ClienteDA oClienteDA = null;

        try {
            oClienteDA = new ClienteDA();
            resultado = oClienteDA.insertarRegistrosClienteBE(oListaClienteBE);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            oListaClienteBE = null;
            oClienteDA = null;
        }
        return resultado;
    }

    public int actualizarClienteBE(ClienteBE oClienteBE1) {
        int resultado = 0;
        ClienteDA oClienteDA = null;
        try {
            oClienteDA = new ClienteDA();
            resultado = oClienteDA.actualizarClienteBE(oClienteBE1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            oClienteBE1 = null;
            oClienteDA = null;
        }

        return resultado;
    }

}
