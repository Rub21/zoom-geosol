package geosolutions.zoom.be;


import java.util.Date;
//@autor Sergio Medina


public class AnuladoBE
 { 
private int IndOpSp;
 private String codigoanulado;
 private String motivoanulacion;

public AnuladoBE(){
this.IndOpSp = 0;
this.codigoanulado = "";
this.motivoanulacion = "";

}
public AnuladoBE(int pIndOpSp,String pcodigoanulado,String pmotivoanulacion)

{
this.IndOpSp = pIndOpSp;
this.codigoanulado = pcodigoanulado;
this.motivoanulacion = pmotivoanulacion;
}
 public int getIndOpSp() {
    return IndOpSp;
    }

public String getCodigoanulado() {
return codigoanulado;

 }
public String getMotivoanulacion() {
return motivoanulacion;

 }
 public void setIndOpSp(int IndOpSp) {
     this.IndOpSp = IndOpSp;
    }

public void setCodigoanulado(String codigoanulado){

this.codigoanulado = codigoanulado;
}

public void setMotivoanulacion(String motivoanulacion){

this.motivoanulacion = motivoanulacion;
}

}
