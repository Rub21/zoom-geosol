package geosolutions.zoom.be;


import java.util.Date;
//@autor Sergio Medina


public class UsersBE
 { 
private int IndOpSp;
 private int id;
 private String firstname;
 private String lastname;

public UsersBE(){
this.IndOpSp = 0;
this.id = 0;
this.firstname = "";
this.lastname = "";

}
public UsersBE(int pIndOpSp,int pid,String pfirstname,String plastname)

{
this.IndOpSp = pIndOpSp;
this.id = pid;
this.firstname = pfirstname;
this.lastname = plastname;
}
 public int getIndOpSp() {
    return IndOpSp;
    }

public int getId() {
return id;

 }
public String getFirstname() {
return firstname;

 }
public String getLastname() {
return lastname;

 }
 public void setIndOpSp(int IndOpSp) {
     this.IndOpSp = IndOpSp;
    }

public void setId(int id){

this.id = id;
}

public void setFirstname(String firstname){

this.firstname = firstname;
}

public void setLastname(String lastname){

this.lastname = lastname;
}

}
