package geosolutions.zoom.be;


import java.util.Date;
//@autor Sergio Medina


public class ClienteBE
 { 
private int IndOpSp;
 private String codigocliente;
 private String nombrecliente;
 private String direccion;
 private String distrito;
 private String categoriacliente;
 private String gironegocio;
 private double coordenada_y;
 private double coordenada_x;
 private int visual;

public ClienteBE(){
this.IndOpSp = 0;
this.codigocliente = "";
this.nombrecliente = "";
this.direccion = "";
this.distrito = "";
this.categoriacliente = "";
this.gironegocio = "";
this.coordenada_y = 0.0;
this.coordenada_x = 0.0;
this.visual = 0;

}
public ClienteBE(int pIndOpSp,String pcodigocliente,String pnombrecliente,String pdireccion,String pdistrito,String pcategoriacliente,String pgironegocio,double pcoordenada_y,double pcoordenada_x,int pvisual)

{
this.IndOpSp = pIndOpSp;
this.codigocliente = pcodigocliente;
this.nombrecliente = pnombrecliente;
this.direccion = pdireccion;
this.distrito = pdistrito;
this.categoriacliente = pcategoriacliente;
this.gironegocio = pgironegocio;
this.coordenada_y = pcoordenada_y;
this.coordenada_x = pcoordenada_x;
this.visual = pvisual;
}
 public int getIndOpSp() {
    return IndOpSp;
    }

public String getCodigocliente() {
return codigocliente;

 }
public String getNombrecliente() {
return nombrecliente;

 }
public String getDireccion() {
return direccion;

 }
public String getDistrito() {
return distrito;

 }
public String getCategoriacliente() {
return categoriacliente;

 }
public String getGironegocio() {
return gironegocio;

 }
public double getCoordenada_y() {
return coordenada_y;

 }
public double getCoordenada_x() {
return coordenada_x;

 }
public int getVisual() {
return visual;

 }
 public void setIndOpSp(int IndOpSp) {
     this.IndOpSp = IndOpSp;
    }

public void setCodigocliente(String codigocliente){

this.codigocliente = codigocliente;
}

public void setNombrecliente(String nombrecliente){

this.nombrecliente = nombrecliente;
}

public void setDireccion(String direccion){

this.direccion = direccion;
}

public void setDistrito(String distrito){

this.distrito = distrito;
}

public void setCategoriacliente(String categoriacliente){

this.categoriacliente = categoriacliente;
}

public void setGironegocio(String gironegocio){

this.gironegocio = gironegocio;
}

public void setCoordenada_y(double  coordenada_y){

this.coordenada_y = coordenada_y;
}

public void setCoordenada_x(double  coordenada_x){

this.coordenada_x = coordenada_x;
}

public void setVisual(int visual){

this.visual = visual;
}

}
