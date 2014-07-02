package geosolutions.zoom.bl;


//@autor Sergio Medina


import geosolutions.zoom.be.GrupoventaBE;
import geosolutions.zoom.da.GrupoventaDA;
import java.util.ArrayList;
public class GrupoventaBL {
public GrupoventaBL() {
}
 public GrupoventaBE listarGrupoventaBE(GrupoventaBE oGrupoventaBE1) {
	GrupoventaBE oGrupoventaBE=null;
	GrupoventaDA oGrupoventaDA=null;
try{
	oGrupoventaDA=new GrupoventaDA();
	oGrupoventaBE=oGrupoventaDA.listarGrupoventaBE(oGrupoventaBE1);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally{
	oGrupoventaBE1=null;
	oGrupoventaDA=null;
}
return oGrupoventaBE;
}
public ArrayList<GrupoventaBE> listarRegistrosGrupoventaBE(GrupoventaBE oGrupoventaBE){
ArrayList<GrupoventaBE> oListaGrupoventaBE=null;
GrupoventaDA oGrupoventaDA=null;
try{
	oGrupoventaDA=new GrupoventaDA();
	oListaGrupoventaBE=oGrupoventaDA.listarRegistroGrupoventaBE(oGrupoventaBE);
}
catch(Exception ex){
	ex.printStackTrace();
}
finally {
	oGrupoventaBE=null;
	oGrupoventaDA=null;
}
return oListaGrupoventaBE;
}

public int insertarGrupoventaBE(GrupoventaBE oGrupoventaBE){
	int resultado=0;
	GrupoventaDA oGrupoventaDA = null;

try {
	oGrupoventaDA=new GrupoventaDA();
	resultado=oGrupoventaDA.insertarGrupoventaBE(oGrupoventaBE);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally {
	oGrupoventaBE=null;
	oGrupoventaDA=null;
}
return resultado;
}


    public int insertarRegistrosGrupoventaBE(ArrayList<GrupoventaBE> oListaGrupoventaBE){
       int resultado=0;
        GrupoventaDA oGrupoventaDA=null;

        try {
            oGrupoventaDA=new GrupoventaDA();
            resultado=oGrupoventaDA.insertarRegistrosGrupoventaBE(oListaGrupoventaBE);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally
        {
            oListaGrupoventaBE=null;
            oGrupoventaDA=null;
        }
        return resultado;
    }


public int actualizarGrupoventaBE(GrupoventaBE oGrupoventaBE1){
        int resultado=0;
        GrupoventaDA oGrupoventaDA=null;
        try {
            oGrupoventaDA=new GrupoventaDA();
            resultado=oGrupoventaDA.actualizarGrupoventaBE(oGrupoventaBE1);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
       finally
        {
            oGrupoventaBE1=null;
            oGrupoventaDA=null;
        }

        return resultado;
    }


}
