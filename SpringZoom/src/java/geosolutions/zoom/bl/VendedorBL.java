package geosolutions.zoom.bl;


//@autor Sergio Medina


import geosolutions.zoom.be.VendedorBE;
import geosolutions.zoom.da.VendedorDA;
import java.util.ArrayList;
public class VendedorBL {
public VendedorBL() {
}
 public VendedorBE listarVendedorBE(VendedorBE oVendedorBE1) {
	VendedorBE oVendedorBE=null;
	VendedorDA oVendedorDA=null;
try{
	oVendedorDA=new VendedorDA();
	oVendedorBE=oVendedorDA.listarVendedorBE(oVendedorBE1);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally{
	oVendedorBE1=null;
	oVendedorDA=null;
}
return oVendedorBE;
}
public ArrayList<VendedorBE> listarRegistrosVendedorBE(VendedorBE oVendedorBE){
ArrayList<VendedorBE> oListaVendedorBE=null;
VendedorDA oVendedorDA=null;
try{
	oVendedorDA=new VendedorDA();
	oListaVendedorBE=oVendedorDA.listarRegistroVendedorBE(oVendedorBE);
}
catch(Exception ex){
	ex.printStackTrace();
}
finally {
	oVendedorBE=null;
	oVendedorDA=null;
}
return oListaVendedorBE;
}

public int insertarVendedorBE(VendedorBE oVendedorBE){
	int resultado=0;
	VendedorDA oVendedorDA = null;

try {
	oVendedorDA=new VendedorDA();
	resultado=oVendedorDA.insertarVendedorBE(oVendedorBE);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally {
	oVendedorBE=null;
	oVendedorDA=null;
}
return resultado;
}


    public int insertarRegistrosVendedorBE(ArrayList<VendedorBE> oListaVendedorBE){
       int resultado=0;
        VendedorDA oVendedorDA=null;

        try {
            oVendedorDA=new VendedorDA();
            resultado=oVendedorDA.insertarRegistrosVendedorBE(oListaVendedorBE);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally
        {
            oListaVendedorBE=null;
            oVendedorDA=null;
        }
        return resultado;
    }


public int actualizarVendedorBE(VendedorBE oVendedorBE1){
        int resultado=0;
        VendedorDA oVendedorDA=null;
        try {
            oVendedorDA=new VendedorDA();
            resultado=oVendedorDA.actualizarVendedorBE(oVendedorBE1);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
       finally
        {
            oVendedorBE1=null;
            oVendedorDA=null;
        }

        return resultado;
    }


}
