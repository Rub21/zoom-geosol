package geosolutions.zoom.bl;


//@autor Sergio Medina


import geosolutions.zoom.be.AnuladoBE;
import geosolutions.zoom.da.AnuladoDA;
import java.util.ArrayList;
public class AnuladoBL {
public AnuladoBL() {
}
 public AnuladoBE listarAnuladoBE(AnuladoBE oAnuladoBE1) {
	AnuladoBE oAnuladoBE=null;
	AnuladoDA oAnuladoDA=null;
try{
	oAnuladoDA=new AnuladoDA();
	oAnuladoBE=oAnuladoDA.listarAnuladoBE(oAnuladoBE1);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally{
	oAnuladoBE1=null;
	oAnuladoDA=null;
}
return oAnuladoBE;
}
public ArrayList<AnuladoBE> listarRegistrosAnuladoBE(AnuladoBE oAnuladoBE){
ArrayList<AnuladoBE> oListaAnuladoBE=null;
AnuladoDA oAnuladoDA=null;
try{
	oAnuladoDA=new AnuladoDA();
	oListaAnuladoBE=oAnuladoDA.listarRegistroAnuladoBE(oAnuladoBE);
}
catch(Exception ex){
	ex.printStackTrace();
}
finally {
	oAnuladoBE=null;
	oAnuladoDA=null;
}
return oListaAnuladoBE;
}

public int insertarAnuladoBE(AnuladoBE oAnuladoBE){
	int resultado=0;
	AnuladoDA oAnuladoDA = null;

try {
	oAnuladoDA=new AnuladoDA();
	resultado=oAnuladoDA.insertarAnuladoBE(oAnuladoBE);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally {
	oAnuladoBE=null;
	oAnuladoDA=null;
}
return resultado;
}


    public int insertarRegistrosAnuladoBE(ArrayList<AnuladoBE> oListaAnuladoBE){
       int resultado=0;
        AnuladoDA oAnuladoDA=null;

        try {
            oAnuladoDA=new AnuladoDA();
            resultado=oAnuladoDA.insertarRegistrosAnuladoBE(oListaAnuladoBE);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally
        {
            oListaAnuladoBE=null;
            oAnuladoDA=null;
        }
        return resultado;
    }


public int actualizarAnuladoBE(AnuladoBE oAnuladoBE1){
        int resultado=0;
        AnuladoDA oAnuladoDA=null;
        try {
            oAnuladoDA=new AnuladoDA();
            resultado=oAnuladoDA.actualizarAnuladoBE(oAnuladoBE1);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
       finally
        {
            oAnuladoBE1=null;
            oAnuladoDA=null;
        }

        return resultado;
    }


}
