package geosolutions.zoom.bl;


//@autor Sergio Medina


import geosolutions.zoom.be.FechaBE;
import geosolutions.zoom.da.FechaDA;
import java.util.ArrayList;
public class FechaBL {
public FechaBL() {
}
 public FechaBE listarFechaBE(FechaBE oFechaBE1) {
	FechaBE oFechaBE=null;
	FechaDA oFechaDA=null;
try{
	oFechaDA=new FechaDA();
	oFechaBE=oFechaDA.listarFechaBE(oFechaBE1);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally{
	oFechaBE1=null;
	oFechaDA=null;
}
return oFechaBE;
}
public ArrayList<FechaBE> listarRegistrosFechaBE(FechaBE oFechaBE){
ArrayList<FechaBE> oListaFechaBE=null;
FechaDA oFechaDA=null;
try{
	oFechaDA=new FechaDA();
	oListaFechaBE=oFechaDA.listarRegistroFechaBE(oFechaBE);
}
catch(Exception ex){
	ex.printStackTrace();
}
finally {
	oFechaBE=null;
	oFechaDA=null;
}
return oListaFechaBE;
}

public int insertarFechaBE(FechaBE oFechaBE){
	int resultado=0;
	FechaDA oFechaDA = null;

try {
	oFechaDA=new FechaDA();
	resultado=oFechaDA.insertarFechaBE(oFechaBE);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally {
	oFechaBE=null;
	oFechaDA=null;
}
return resultado;
}


    public int insertarRegistrosFechaBE(ArrayList<FechaBE> oListaFechaBE){
       int resultado=0;
        FechaDA oFechaDA=null;

        try {
            oFechaDA=new FechaDA();
            resultado=oFechaDA.insertarRegistrosFechaBE(oListaFechaBE);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally
        {
            oListaFechaBE=null;
            oFechaDA=null;
        }
        return resultado;
    }


public int actualizarFechaBE(FechaBE oFechaBE1){
        int resultado=0;
        FechaDA oFechaDA=null;
        try {
            oFechaDA=new FechaDA();
            resultado=oFechaDA.actualizarFechaBE(oFechaBE1);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
       finally
        {
            oFechaBE1=null;
            oFechaDA=null;
        }

        return resultado;
    }


}
