package geosolutions.zoom.bl;


//@autor Sergio Medina


import geosolutions.zoom.be.ProductoBE;
import geosolutions.zoom.da.ProductoDA;
import java.util.ArrayList;
public class ProductoBL {
public ProductoBL() {
}
 public ProductoBE listarProductoBE(ProductoBE oProductoBE1) {
	ProductoBE oProductoBE=null;
	ProductoDA oProductoDA=null;
try{
	oProductoDA=new ProductoDA();
	oProductoBE=oProductoDA.listarProductoBE(oProductoBE1);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally{
	oProductoBE1=null;
	oProductoDA=null;
}
return oProductoBE;
}
public ArrayList<ProductoBE> listarRegistrosProductoBE(ProductoBE oProductoBE){
ArrayList<ProductoBE> oListaProductoBE=null;
ProductoDA oProductoDA=null;
try{
	oProductoDA=new ProductoDA();
	oListaProductoBE=oProductoDA.listarRegistroProductoBE(oProductoBE);
}
catch(Exception ex){
	ex.printStackTrace();
}
finally {
	oProductoBE=null;
	oProductoDA=null;
}
return oListaProductoBE;
}

public int insertarProductoBE(ProductoBE oProductoBE){
	int resultado=0;
	ProductoDA oProductoDA = null;

try {
	oProductoDA=new ProductoDA();
	resultado=oProductoDA.insertarProductoBE(oProductoBE);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally {
	oProductoBE=null;
	oProductoDA=null;
}
return resultado;
}


    public int insertarRegistrosProductoBE(ArrayList<ProductoBE> oListaProductoBE){
       int resultado=0;
        ProductoDA oProductoDA=null;

        try {
            oProductoDA=new ProductoDA();
            resultado=oProductoDA.insertarRegistrosProductoBE(oListaProductoBE);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally
        {
            oListaProductoBE=null;
            oProductoDA=null;
        }
        return resultado;
    }


public int actualizarProductoBE(ProductoBE oProductoBE1){
        int resultado=0;
        ProductoDA oProductoDA=null;
        try {
            oProductoDA=new ProductoDA();
            resultado=oProductoDA.actualizarProductoBE(oProductoBE1);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
       finally
        {
            oProductoBE1=null;
            oProductoDA=null;
        }

        return resultado;
    }


}
