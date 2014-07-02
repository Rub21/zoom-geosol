package geosolutions.zoom.bl;


//@autor Sergio Medina


import geosolutions.zoom.be.VehiculoBE;
import geosolutions.zoom.da.VehiculoDA;
import java.util.ArrayList;
public class VehiculoBL {
public VehiculoBL() {
}
 public VehiculoBE listarVehiculoBE(VehiculoBE oVehiculoBE1) {
	VehiculoBE oVehiculoBE=null;
	VehiculoDA oVehiculoDA=null;
try{
	oVehiculoDA=new VehiculoDA();
	oVehiculoBE=oVehiculoDA.listarVehiculoBE(oVehiculoBE1);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally{
	oVehiculoBE1=null;
	oVehiculoDA=null;
}
return oVehiculoBE;
}
public ArrayList<VehiculoBE> listarRegistrosVehiculoBE(VehiculoBE oVehiculoBE){
ArrayList<VehiculoBE> oListaVehiculoBE=null;
VehiculoDA oVehiculoDA=null;
try{
	oVehiculoDA=new VehiculoDA();
	oListaVehiculoBE=oVehiculoDA.listarRegistroVehiculoBE(oVehiculoBE);
}
catch(Exception ex){
	ex.printStackTrace();
}
finally {
	oVehiculoBE=null;
	oVehiculoDA=null;
}
return oListaVehiculoBE;
}

public int insertarVehiculoBE(VehiculoBE oVehiculoBE){
	int resultado=0;
	VehiculoDA oVehiculoDA = null;

try {
	oVehiculoDA=new VehiculoDA();
	resultado=oVehiculoDA.insertarVehiculoBE(oVehiculoBE);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally {
	oVehiculoBE=null;
	oVehiculoDA=null;
}
return resultado;
}


    public int insertarRegistrosVehiculoBE(ArrayList<VehiculoBE> oListaVehiculoBE){
       int resultado=0;
        VehiculoDA oVehiculoDA=null;

        try {
            oVehiculoDA=new VehiculoDA();
            resultado=oVehiculoDA.insertarRegistrosVehiculoBE(oListaVehiculoBE);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally
        {
            oListaVehiculoBE=null;
            oVehiculoDA=null;
        }
        return resultado;
    }


public int actualizarVehiculoBE(VehiculoBE oVehiculoBE1){
        int resultado=0;
        VehiculoDA oVehiculoDA=null;
        try {
            oVehiculoDA=new VehiculoDA();
            resultado=oVehiculoDA.actualizarVehiculoBE(oVehiculoBE1);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
       finally
        {
            oVehiculoBE1=null;
            oVehiculoDA=null;
        }

        return resultado;
    }


}
