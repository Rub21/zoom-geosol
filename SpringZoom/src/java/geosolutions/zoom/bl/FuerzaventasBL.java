package geosolutions.zoom.bl;


//@autor Sergio Medina


import geosolutions.zoom.be.FuerzaventasBE;
import geosolutions.zoom.da.FuerzaventasDA;
import java.util.ArrayList;
public class FuerzaventasBL {
public FuerzaventasBL() {
}
 public FuerzaventasBE listarFuerzaventasBE(FuerzaventasBE oFuerzaventasBE1) {
	FuerzaventasBE oFuerzaventasBE=null;
	FuerzaventasDA oFuerzaventasDA=null;
try{
	oFuerzaventasDA=new FuerzaventasDA();
	oFuerzaventasBE=oFuerzaventasDA.listarFuerzaventasBE(oFuerzaventasBE1);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally{
	oFuerzaventasBE1=null;
	oFuerzaventasDA=null;
}
return oFuerzaventasBE;
}
public ArrayList<FuerzaventasBE> listarRegistrosFuerzaventasBE(FuerzaventasBE oFuerzaventasBE){
ArrayList<FuerzaventasBE> oListaFuerzaventasBE=null;
FuerzaventasDA oFuerzaventasDA=null;
try{
	oFuerzaventasDA=new FuerzaventasDA();
	oListaFuerzaventasBE=oFuerzaventasDA.listarRegistroFuerzaventasBE(oFuerzaventasBE);
}
catch(Exception ex){
	ex.printStackTrace();
}
finally {
	oFuerzaventasBE=null;
	oFuerzaventasDA=null;
}
return oListaFuerzaventasBE;
}

public int insertarFuerzaventasBE(FuerzaventasBE oFuerzaventasBE){
	int resultado=0;
	FuerzaventasDA oFuerzaventasDA = null;

try {
	oFuerzaventasDA=new FuerzaventasDA();
	resultado=oFuerzaventasDA.insertarFuerzaventasBE(oFuerzaventasBE);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally {
	oFuerzaventasBE=null;
	oFuerzaventasDA=null;
}
return resultado;
}


    public int insertarRegistrosFuerzaventasBE(ArrayList<FuerzaventasBE> oListaFuerzaventasBE){
       int resultado=0;
        FuerzaventasDA oFuerzaventasDA=null;

        try {
            oFuerzaventasDA=new FuerzaventasDA();
            resultado=oFuerzaventasDA.insertarRegistrosFuerzaventasBE(oListaFuerzaventasBE);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally
        {
            oListaFuerzaventasBE=null;
            oFuerzaventasDA=null;
        }
        return resultado;
    }


public int actualizarFuerzaventasBE(FuerzaventasBE oFuerzaventasBE1){
        int resultado=0;
        FuerzaventasDA oFuerzaventasDA=null;
        try {
            oFuerzaventasDA=new FuerzaventasDA();
            resultado=oFuerzaventasDA.actualizarFuerzaventasBE(oFuerzaventasBE1);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
       finally
        {
            oFuerzaventasBE1=null;
            oFuerzaventasDA=null;
        }

        return resultado;
    }


}
