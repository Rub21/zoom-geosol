package geosolutions.zoom.be;


import java.util.Date;
//@autor Sergio Medina


public class ProductoBE
 { 
private int IndOpSp;
 private String codigoproducto;
 private String grupo;
 private String familia;
 private String linea;
 private String nombremarca;
 private String productobase;
 private String codigonestle;
 private String nombreproducto;
 private double pesoneto;

public ProductoBE(){
this.IndOpSp = 0;
this.codigoproducto = "";
this.grupo = "";
this.familia = "";
this.linea = "";
this.nombremarca = "";
this.productobase = "";
this.codigonestle = "";
this.nombreproducto = "";
this.pesoneto = 0.0;

}
public ProductoBE(int pIndOpSp,String pcodigoproducto,String pgrupo,String pfamilia,String plinea,String pnombremarca,String pproductobase,String pcodigonestle,String pnombreproducto,double ppesoneto)

{
this.IndOpSp = pIndOpSp;
this.codigoproducto = pcodigoproducto;
this.grupo = pgrupo;
this.familia = pfamilia;
this.linea = plinea;
this.nombremarca = pnombremarca;
this.productobase = pproductobase;
this.codigonestle = pcodigonestle;
this.nombreproducto = pnombreproducto;
this.pesoneto = ppesoneto;
}
 public int getIndOpSp() {
    return IndOpSp;
    }

public String getCodigoproducto() {
return codigoproducto;

 }
public String getGrupo() {
return grupo;

 }
public String getFamilia() {
return familia;

 }
public String getLinea() {
return linea;

 }
public String getNombremarca() {
return nombremarca;

 }
public String getProductobase() {
return productobase;

 }
public String getCodigonestle() {
return codigonestle;

 }
public String getNombreproducto() {
return nombreproducto;

 }
public double getPesoneto() {
return pesoneto;

 }
 public void setIndOpSp(int IndOpSp) {
     this.IndOpSp = IndOpSp;
    }

public void setCodigoproducto(String codigoproducto){

this.codigoproducto = codigoproducto;
}

public void setGrupo(String grupo){

this.grupo = grupo;
}

public void setFamilia(String familia){

this.familia = familia;
}

public void setLinea(String linea){

this.linea = linea;
}

public void setNombremarca(String nombremarca){

this.nombremarca = nombremarca;
}

public void setProductobase(String productobase){

this.productobase = productobase;
}

public void setCodigonestle(String codigonestle){

this.codigonestle = codigonestle;
}

public void setNombreproducto(String nombreproducto){

this.nombreproducto = nombreproducto;
}

public void setPesoneto(double  pesoneto){

this.pesoneto = pesoneto;
}

}
