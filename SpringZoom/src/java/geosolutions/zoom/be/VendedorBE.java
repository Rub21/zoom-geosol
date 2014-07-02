package geosolutions.zoom.be;


import java.util.Date;
//@autor Sergio Medina


public class VendedorBE
 { 
private int IndOpSp;
 private String codigovendedor;
 private String nombrevendedor;

public VendedorBE(){
this.IndOpSp = 0;
this.codigovendedor = "";
this.nombrevendedor = "";

}
public VendedorBE(int pIndOpSp,String pcodigovendedor,String pnombrevendedor)

{
this.IndOpSp = pIndOpSp;
this.codigovendedor = pcodigovendedor;
this.nombrevendedor = pnombrevendedor;
}
 public int getIndOpSp() {
    return IndOpSp;
    }

public String getCodigovendedor() {
return codigovendedor;

 }
public String getNombrevendedor() {
return nombrevendedor;

 }
 public void setIndOpSp(int IndOpSp) {
     this.IndOpSp = IndOpSp;
    }

public void setCodigovendedor(String codigovendedor){

this.codigovendedor = codigovendedor;
}

public void setNombrevendedor(String nombrevendedor){

this.nombrevendedor = nombrevendedor;
}

}
