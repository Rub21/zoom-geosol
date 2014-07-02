package geosolutions.zoom.be;


import java.util.Date;
//@autor Sergio Medina


public class Cuotas_vendedorBE
 { 
private int IndOpSp;
 private String codigovendedorc;
 private String nombrevendedorc;
 private String fecha_cuota;
 private int anio_cuota;
 private String mes_cuota;
 private double cuota_lacteos;
 private double cuota_cafe;
 private double cuota_bebidas;
 private double cuota_culinarios;

public Cuotas_vendedorBE(){
this.IndOpSp = 0;
this.codigovendedorc = "";
this.nombrevendedorc = "";
this.fecha_cuota = "";
this.anio_cuota = 0;
this.mes_cuota = "";
this.cuota_lacteos = 0.0;
this.cuota_cafe = 0.0;
this.cuota_bebidas = 0.0;
this.cuota_culinarios = 0.0;

}
public Cuotas_vendedorBE(int pIndOpSp,String pcodigovendedorc,String pnombrevendedorc,String pfecha_cuota,int panio_cuota,String pmes_cuota,double pcuota_lacteos,double pcuota_cafe,double pcuota_bebidas,double pcuota_culinarios)

{
this.IndOpSp = pIndOpSp;
this.codigovendedorc = pcodigovendedorc;
this.nombrevendedorc = pnombrevendedorc;
this.fecha_cuota = pfecha_cuota;
this.anio_cuota = panio_cuota;
this.mes_cuota = pmes_cuota;
this.cuota_lacteos = pcuota_lacteos;
this.cuota_cafe = pcuota_cafe;
this.cuota_bebidas = pcuota_bebidas;
this.cuota_culinarios = pcuota_culinarios;
}
 public int getIndOpSp() {
    return IndOpSp;
    }

public String getCodigovendedorc() {
return codigovendedorc;

 }
public String getNombrevendedorc() {
return nombrevendedorc;

 }
public String getFecha_cuota() {
return fecha_cuota;

 }
public int getAnio_cuota() {
return anio_cuota;

 }
public String getMes_cuota() {
return mes_cuota;

 }
public double getCuota_lacteos() {
return cuota_lacteos;

 }
public double getCuota_cafe() {
return cuota_cafe;

 }
public double getCuota_bebidas() {
return cuota_bebidas;

 }
public double getCuota_culinarios() {
return cuota_culinarios;

 }
 public void setIndOpSp(int IndOpSp) {
     this.IndOpSp = IndOpSp;
    }

public void setCodigovendedorc(String codigovendedorc){

this.codigovendedorc = codigovendedorc;
}

public void setNombrevendedorc(String nombrevendedorc){

this.nombrevendedorc = nombrevendedorc;
}

public void setFecha_cuota(String fecha_cuota){

this.fecha_cuota = fecha_cuota;
}

public void setAnio_cuota(int anio_cuota){

this.anio_cuota = anio_cuota;
}

public void setMes_cuota(String mes_cuota){

this.mes_cuota = mes_cuota;
}

public void setCuota_lacteos(double  cuota_lacteos){

this.cuota_lacteos = cuota_lacteos;
}

public void setCuota_cafe(double  cuota_cafe){

this.cuota_cafe = cuota_cafe;
}

public void setCuota_bebidas(double  cuota_bebidas){

this.cuota_bebidas = cuota_bebidas;
}

public void setCuota_culinarios(double  cuota_culinarios){

this.cuota_culinarios = cuota_culinarios;
}

}
