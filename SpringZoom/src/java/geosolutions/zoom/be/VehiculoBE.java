package geosolutions.zoom.be;


import java.util.Date;
//@autor Sergio Medina


public class VehiculoBE
 { 
private int IndOpSp;
 private String codigovehiculo;
 private String placa;
 private String nombrevehiculo;
 private String nombreconductor;
 private String nombretransportista;

public VehiculoBE(){
this.IndOpSp = 0;
this.codigovehiculo = "";
this.placa = "";
this.nombrevehiculo = "";
this.nombreconductor = "";
this.nombretransportista = "";

}
public VehiculoBE(int pIndOpSp,String pcodigovehiculo,String pplaca,String pnombrevehiculo,String pnombreconductor,String pnombretransportista)

{
this.IndOpSp = pIndOpSp;
this.codigovehiculo = pcodigovehiculo;
this.placa = pplaca;
this.nombrevehiculo = pnombrevehiculo;
this.nombreconductor = pnombreconductor;
this.nombretransportista = pnombretransportista;
}
 public int getIndOpSp() {
    return IndOpSp;
    }

public String getCodigovehiculo() {
return codigovehiculo;

 }
public String getPlaca() {
return placa;

 }
public String getNombrevehiculo() {
return nombrevehiculo;

 }
public String getNombreconductor() {
return nombreconductor;

 }
public String getNombretransportista() {
return nombretransportista;

 }
 public void setIndOpSp(int IndOpSp) {
     this.IndOpSp = IndOpSp;
    }

public void setCodigovehiculo(String codigovehiculo){

this.codigovehiculo = codigovehiculo;
}

public void setPlaca(String placa){

this.placa = placa;
}

public void setNombrevehiculo(String nombrevehiculo){

this.nombrevehiculo = nombrevehiculo;
}

public void setNombreconductor(String nombreconductor){

this.nombreconductor = nombreconductor;
}

public void setNombretransportista(String nombretransportista){

this.nombretransportista = nombretransportista;
}

}
