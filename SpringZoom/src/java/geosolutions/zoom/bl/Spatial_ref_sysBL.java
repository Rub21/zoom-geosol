package geosolutions.zoom.bl;


//@autor Sergio Medina


import geosolutions.zoom.be.Spatial_ref_sysBE;
import geosolutions.zoom.da.Spatial_ref_sysDA;
import java.util.ArrayList;
public class Spatial_ref_sysBL {
public Spatial_ref_sysBL() {
}
 public Spatial_ref_sysBE listarSpatial_ref_sysBE(Spatial_ref_sysBE oSpatial_ref_sysBE1) {
	Spatial_ref_sysBE oSpatial_ref_sysBE=null;
	Spatial_ref_sysDA oSpatial_ref_sysDA=null;
try{
	oSpatial_ref_sysDA=new Spatial_ref_sysDA();
	oSpatial_ref_sysBE=oSpatial_ref_sysDA.listarSpatial_ref_sysBE(oSpatial_ref_sysBE1);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally{
	oSpatial_ref_sysBE1=null;
	oSpatial_ref_sysDA=null;
}
return oSpatial_ref_sysBE;
}
public ArrayList<Spatial_ref_sysBE> listarRegistrosSpatial_ref_sysBE(Spatial_ref_sysBE oSpatial_ref_sysBE){
ArrayList<Spatial_ref_sysBE> oListaSpatial_ref_sysBE=null;
Spatial_ref_sysDA oSpatial_ref_sysDA=null;
try{
	oSpatial_ref_sysDA=new Spatial_ref_sysDA();
	oListaSpatial_ref_sysBE=oSpatial_ref_sysDA.listarRegistroSpatial_ref_sysBE(oSpatial_ref_sysBE);
}
catch(Exception ex){
	ex.printStackTrace();
}
finally {
	oSpatial_ref_sysBE=null;
	oSpatial_ref_sysDA=null;
}
return oListaSpatial_ref_sysBE;
}

public int insertarSpatial_ref_sysBE(Spatial_ref_sysBE oSpatial_ref_sysBE){
	int resultado=0;
	Spatial_ref_sysDA oSpatial_ref_sysDA = null;

try {
	oSpatial_ref_sysDA=new Spatial_ref_sysDA();
	resultado=oSpatial_ref_sysDA.insertarSpatial_ref_sysBE(oSpatial_ref_sysBE);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally {
	oSpatial_ref_sysBE=null;
	oSpatial_ref_sysDA=null;
}
return resultado;
}


    public int insertarRegistrosSpatial_ref_sysBE(ArrayList<Spatial_ref_sysBE> oListaSpatial_ref_sysBE){
       int resultado=0;
        Spatial_ref_sysDA oSpatial_ref_sysDA=null;

        try {
            oSpatial_ref_sysDA=new Spatial_ref_sysDA();
            resultado=oSpatial_ref_sysDA.insertarRegistrosSpatial_ref_sysBE(oListaSpatial_ref_sysBE);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally
        {
            oListaSpatial_ref_sysBE=null;
            oSpatial_ref_sysDA=null;
        }
        return resultado;
    }


public int actualizarSpatial_ref_sysBE(Spatial_ref_sysBE oSpatial_ref_sysBE1){
        int resultado=0;
        Spatial_ref_sysDA oSpatial_ref_sysDA=null;
        try {
            oSpatial_ref_sysDA=new Spatial_ref_sysDA();
            resultado=oSpatial_ref_sysDA.actualizarSpatial_ref_sysBE(oSpatial_ref_sysBE1);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
       finally
        {
            oSpatial_ref_sysBE1=null;
            oSpatial_ref_sysDA=null;
        }

        return resultado;
    }


}
