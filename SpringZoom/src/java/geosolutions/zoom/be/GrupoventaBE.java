package geosolutions.zoom.be;


import java.util.Date;
//@autor Sergio Medina


public class GrupoventaBE
 { 
private int IndOpSp;
 private String codigogrupoventa;
 private String grupoventas;
 private String canal;

public GrupoventaBE(){
this.IndOpSp = 0;
this.codigogrupoventa = "";
this.grupoventas = "";
this.canal = "";

}
public GrupoventaBE(int pIndOpSp,String pcodigogrupoventa,String pgrupoventas,String pcanal)

{
this.IndOpSp = pIndOpSp;
this.codigogrupoventa = pcodigogrupoventa;
this.grupoventas = pgrupoventas;
this.canal = pcanal;
}
 public int getIndOpSp() {
    return IndOpSp;
    }

public String getCodigogrupoventa() {
return codigogrupoventa;

 }
public String getGrupoventas() {
return grupoventas;

 }
public String getCanal() {
return canal;

 }
 public void setIndOpSp(int IndOpSp) {
     this.IndOpSp = IndOpSp;
    }

public void setCodigogrupoventa(String codigogrupoventa){

this.codigogrupoventa = codigogrupoventa;
}

public void setGrupoventas(String grupoventas){

this.grupoventas = grupoventas;
}

public void setCanal(String canal){

this.canal = canal;
}

}
