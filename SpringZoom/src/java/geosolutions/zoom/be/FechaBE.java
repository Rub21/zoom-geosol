package geosolutions.zoom.be;


import java.util.Date;
//@autor Sergio Medina


public class FechaBE
 { 
private int IndOpSp;
 private int dias_facturados_anio;
 private int dias_facturados_mes;
 private String fechafacturacion;
 private int anio;
 private String semestre;
 private String trimestre;
 private String bimestre;
 private int mes_num;
 private String mes_desc;
 private String mes_anio;
 private int semana;
 private String semana_anio;
 private String dia_nombre;
 private int dia_semana;
 private String mes_anio_balance;
 private String mes_anio_gasto;

public FechaBE(){
this.IndOpSp = 0;
this.dias_facturados_anio = 0;
this.dias_facturados_mes = 0;
this.fechafacturacion = "";
this.anio = 0;
this.semestre = "";
this.trimestre = "";
this.bimestre = "";
this.mes_num = 0;
this.mes_desc = "";
this.mes_anio = "";
this.semana = 0;
this.semana_anio = "";
this.dia_nombre = "";
this.dia_semana = 0;
this.mes_anio_balance = "";
this.mes_anio_gasto = "";

}
public FechaBE(int pIndOpSp,int pdias_facturados_anio,int pdias_facturados_mes,String pfechafacturacion,int panio,String psemestre,String ptrimestre,String pbimestre,int pmes_num,String pmes_desc,String pmes_anio,int psemana,String psemana_anio,String pdia_nombre,int pdia_semana,String pmes_anio_balance,String pmes_anio_gasto)

{
this.IndOpSp = pIndOpSp;
this.dias_facturados_anio = pdias_facturados_anio;
this.dias_facturados_mes = pdias_facturados_mes;
this.fechafacturacion = pfechafacturacion;
this.anio = panio;
this.semestre = psemestre;
this.trimestre = ptrimestre;
this.bimestre = pbimestre;
this.mes_num = pmes_num;
this.mes_desc = pmes_desc;
this.mes_anio = pmes_anio;
this.semana = psemana;
this.semana_anio = psemana_anio;
this.dia_nombre = pdia_nombre;
this.dia_semana = pdia_semana;
this.mes_anio_balance = pmes_anio_balance;
this.mes_anio_gasto = pmes_anio_gasto;
}
 public int getIndOpSp() {
    return IndOpSp;
    }

public int getDias_facturados_anio() {
return dias_facturados_anio;

 }
public int getDias_facturados_mes() {
return dias_facturados_mes;

 }
public String getFechafacturacion() {
return fechafacturacion;

 }
public int getAnio() {
return anio;

 }
public String getSemestre() {
return semestre;

 }
public String getTrimestre() {
return trimestre;

 }
public String getBimestre() {
return bimestre;

 }
public int getMes_num() {
return mes_num;

 }
public String getMes_desc() {
return mes_desc;

 }
public String getMes_anio() {
return mes_anio;

 }
public int getSemana() {
return semana;

 }
public String getSemana_anio() {
return semana_anio;

 }
public String getDia_nombre() {
return dia_nombre;

 }
public int getDia_semana() {
return dia_semana;

 }
public String getMes_anio_balance() {
return mes_anio_balance;

 }
public String getMes_anio_gasto() {
return mes_anio_gasto;

 }
 public void setIndOpSp(int IndOpSp) {
     this.IndOpSp = IndOpSp;
    }

public void setDias_facturados_anio(int dias_facturados_anio){

this.dias_facturados_anio = dias_facturados_anio;
}

public void setDias_facturados_mes(int dias_facturados_mes){

this.dias_facturados_mes = dias_facturados_mes;
}

public void setFechafacturacion(String fechafacturacion){

this.fechafacturacion = fechafacturacion;
}

public void setAnio(int anio){

this.anio = anio;
}

public void setSemestre(String semestre){

this.semestre = semestre;
}

public void setTrimestre(String trimestre){

this.trimestre = trimestre;
}

public void setBimestre(String bimestre){

this.bimestre = bimestre;
}

public void setMes_num(int mes_num){

this.mes_num = mes_num;
}

public void setMes_desc(String mes_desc){

this.mes_desc = mes_desc;
}

public void setMes_anio(String mes_anio){

this.mes_anio = mes_anio;
}

public void setSemana(int semana){

this.semana = semana;
}

public void setSemana_anio(String semana_anio){

this.semana_anio = semana_anio;
}

public void setDia_nombre(String dia_nombre){

this.dia_nombre = dia_nombre;
}

public void setDia_semana(int dia_semana){

this.dia_semana = dia_semana;
}

public void setMes_anio_balance(String mes_anio_balance){

this.mes_anio_balance = mes_anio_balance;
}

public void setMes_anio_gasto(String mes_anio_gasto){

this.mes_anio_gasto = mes_anio_gasto;
}

}
