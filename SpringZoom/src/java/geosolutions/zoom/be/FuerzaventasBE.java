package geosolutions.zoom.be;


import java.util.Date;
//@autor Sergio Medina


public class FuerzaventasBE
 { 
private int IndOpSp;
 private String codigofuerzaventas;
 private String fuerzaventas;

public FuerzaventasBE(){
this.IndOpSp = 0;
this.codigofuerzaventas = "";
this.fuerzaventas = "";

}
public FuerzaventasBE(int pIndOpSp,String pcodigofuerzaventas,String pfuerzaventas)

{
this.IndOpSp = pIndOpSp;
this.codigofuerzaventas = pcodigofuerzaventas;
this.fuerzaventas = pfuerzaventas;
}
 public int getIndOpSp() {
    return IndOpSp;
    }

public String getCodigofuerzaventas() {
return codigofuerzaventas;

 }
public String getFuerzaventas() {
return fuerzaventas;

 }
 public void setIndOpSp(int IndOpSp) {
     this.IndOpSp = IndOpSp;
    }

public void setCodigofuerzaventas(String codigofuerzaventas){

this.codigofuerzaventas = codigofuerzaventas;
}

public void setFuerzaventas(String fuerzaventas){

this.fuerzaventas = fuerzaventas;
}

}
