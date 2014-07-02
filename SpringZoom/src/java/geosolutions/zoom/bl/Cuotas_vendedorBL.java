package geosolutions.zoom.bl;


//@autor Sergio Medina


import geosolutions.zoom.be.Cuotas_vendedorBE;
import geosolutions.zoom.da.Cuotas_vendedorDA;
import java.util.ArrayList;
public class Cuotas_vendedorBL {
public Cuotas_vendedorBL() {
}
 public Cuotas_vendedorBE listarCuotas_vendedorBE(Cuotas_vendedorBE oCuotas_vendedorBE1) {
	Cuotas_vendedorBE oCuotas_vendedorBE=null;
	Cuotas_vendedorDA oCuotas_vendedorDA=null;
try{
	oCuotas_vendedorDA=new Cuotas_vendedorDA();
	oCuotas_vendedorBE=oCuotas_vendedorDA.listarCuotas_vendedorBE(oCuotas_vendedorBE1);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally{
	oCuotas_vendedorBE1=null;
	oCuotas_vendedorDA=null;
}
return oCuotas_vendedorBE;
}
public ArrayList<Cuotas_vendedorBE> listarRegistrosCuotas_vendedorBE(Cuotas_vendedorBE oCuotas_vendedorBE){
ArrayList<Cuotas_vendedorBE> oListaCuotas_vendedorBE=null;
Cuotas_vendedorDA oCuotas_vendedorDA=null;
try{
	oCuotas_vendedorDA=new Cuotas_vendedorDA();
	oListaCuotas_vendedorBE=oCuotas_vendedorDA.listarRegistroCuotas_vendedorBE(oCuotas_vendedorBE);
}
catch(Exception ex){
	ex.printStackTrace();
}
finally {
	oCuotas_vendedorBE=null;
	oCuotas_vendedorDA=null;
}
return oListaCuotas_vendedorBE;
}

public int insertarCuotas_vendedorBE(Cuotas_vendedorBE oCuotas_vendedorBE){
	int resultado=0;
	Cuotas_vendedorDA oCuotas_vendedorDA = null;

try {
	oCuotas_vendedorDA=new Cuotas_vendedorDA();
	resultado=oCuotas_vendedorDA.insertarCuotas_vendedorBE(oCuotas_vendedorBE);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally {
	oCuotas_vendedorBE=null;
	oCuotas_vendedorDA=null;
}
return resultado;
}


    public int insertarRegistrosCuotas_vendedorBE(ArrayList<Cuotas_vendedorBE> oListaCuotas_vendedorBE){
       int resultado=0;
        Cuotas_vendedorDA oCuotas_vendedorDA=null;

        try {
            oCuotas_vendedorDA=new Cuotas_vendedorDA();
            resultado=oCuotas_vendedorDA.insertarRegistrosCuotas_vendedorBE(oListaCuotas_vendedorBE);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally
        {
            oListaCuotas_vendedorBE=null;
            oCuotas_vendedorDA=null;
        }
        return resultado;
    }


public int actualizarCuotas_vendedorBE(Cuotas_vendedorBE oCuotas_vendedorBE1){
        int resultado=0;
        Cuotas_vendedorDA oCuotas_vendedorDA=null;
        try {
            oCuotas_vendedorDA=new Cuotas_vendedorDA();
            resultado=oCuotas_vendedorDA.actualizarCuotas_vendedorBE(oCuotas_vendedorBE1);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
       finally
        {
            oCuotas_vendedorBE1=null;
            oCuotas_vendedorDA=null;
        }

        return resultado;
    }


}
