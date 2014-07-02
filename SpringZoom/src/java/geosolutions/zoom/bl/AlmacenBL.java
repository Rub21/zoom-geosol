package geosolutions.zoom.bl;


//@autor Sergio Medina


import geosolutions.zoom.be.AlmacenBE;
import geosolutions.zoom.da.AlmacenDA;
import java.util.ArrayList;
public class AlmacenBL {
public AlmacenBL() {
}
 public AlmacenBE listarAlmacenBE(AlmacenBE oAlmacenBE1) {
	AlmacenBE oAlmacenBE=null;
	AlmacenDA oAlmacenDA=null;
try{
	oAlmacenDA=new AlmacenDA();
	oAlmacenBE=oAlmacenDA.listarAlmacenBE(oAlmacenBE1);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally{
	oAlmacenBE1=null;
	oAlmacenDA=null;
}
return oAlmacenBE;
}
public ArrayList<AlmacenBE> listarRegistrosAlmacenBE(AlmacenBE oAlmacenBE){
ArrayList<AlmacenBE> oListaAlmacenBE=null;
AlmacenDA oAlmacenDA=null;
try{
	oAlmacenDA=new AlmacenDA();
	oListaAlmacenBE=oAlmacenDA.listarRegistroAlmacenBE(oAlmacenBE);
}
catch(Exception ex){
	ex.printStackTrace();
}
finally {
	oAlmacenBE=null;
	oAlmacenDA=null;
}
return oListaAlmacenBE;
}

public int insertarAlmacenBE(AlmacenBE oAlmacenBE){
	int resultado=0;
	AlmacenDA oAlmacenDA = null;

try {
	oAlmacenDA=new AlmacenDA();
	resultado=oAlmacenDA.insertarAlmacenBE(oAlmacenBE);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally {
	oAlmacenBE=null;
	oAlmacenDA=null;
}
return resultado;
}


    public int insertarRegistrosAlmacenBE(ArrayList<AlmacenBE> oListaAlmacenBE){
       int resultado=0;
        AlmacenDA oAlmacenDA=null;

        try {
            oAlmacenDA=new AlmacenDA();
            resultado=oAlmacenDA.insertarRegistrosAlmacenBE(oListaAlmacenBE);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally
        {
            oListaAlmacenBE=null;
            oAlmacenDA=null;
        }
        return resultado;
    }


public int actualizarAlmacenBE(AlmacenBE oAlmacenBE1){
        int resultado=0;
        AlmacenDA oAlmacenDA=null;
        try {
            oAlmacenDA=new AlmacenDA();
            resultado=oAlmacenDA.actualizarAlmacenBE(oAlmacenBE1);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
       finally
        {
            oAlmacenBE1=null;
            oAlmacenDA=null;
        }

        return resultado;
    }


}
