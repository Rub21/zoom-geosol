package geosolutions.zoom.be;


import java.util.Date;
//@autor Sergio Medina


public class Spatial_ref_sysBE
 { 
private int IndOpSp;
 private int srid;
 private String auth_name;
 private int auth_srid;
 private String srtext;
 private String proj4text;

public Spatial_ref_sysBE(){
this.IndOpSp = 0;
this.srid = 0;
this.auth_name = "";
this.auth_srid = 0;
this.srtext = "";
this.proj4text = "";

}
public Spatial_ref_sysBE(int pIndOpSp,int psrid,String pauth_name,int pauth_srid,String psrtext,String pproj4text)

{
this.IndOpSp = pIndOpSp;
this.srid = psrid;
this.auth_name = pauth_name;
this.auth_srid = pauth_srid;
this.srtext = psrtext;
this.proj4text = pproj4text;
}
 public int getIndOpSp() {
    return IndOpSp;
    }

public int getSrid() {
return srid;

 }
public String getAuth_name() {
return auth_name;

 }
public int getAuth_srid() {
return auth_srid;

 }
public String getSrtext() {
return srtext;

 }
public String getProj4text() {
return proj4text;

 }
 public void setIndOpSp(int IndOpSp) {
     this.IndOpSp = IndOpSp;
    }

public void setSrid(int srid){

this.srid = srid;
}

public void setAuth_name(String auth_name){

this.auth_name = auth_name;
}

public void setAuth_srid(int auth_srid){

this.auth_srid = auth_srid;
}

public void setSrtext(String srtext){

this.srtext = srtext;
}

public void setProj4text(String proj4text){

this.proj4text = proj4text;
}

}
