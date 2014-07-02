package geosolutions.zoom.bl;


//@autor Sergio Medina


import geosolutions.zoom.be.UsersBE;
import geosolutions.zoom.da.UsersDA;
import java.util.ArrayList;
public class UsersBL {
public UsersBL() {
}
 public UsersBE listarUsersBE(UsersBE oUsersBE1) {
	UsersBE oUsersBE=null;
	UsersDA oUsersDA=null;
try{
	oUsersDA=new UsersDA();
	oUsersBE=oUsersDA.listarUsersBE(oUsersBE1);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally{
	oUsersBE1=null;
	oUsersDA=null;
}
return oUsersBE;
}
public ArrayList<UsersBE> listarRegistrosUsersBE(UsersBE oUsersBE){
ArrayList<UsersBE> oListaUsersBE=null;
UsersDA oUsersDA=null;
try{
	oUsersDA=new UsersDA();
	oListaUsersBE=oUsersDA.listarRegistroUsersBE(oUsersBE);
}
catch(Exception ex){
	ex.printStackTrace();
}
finally {
	oUsersBE=null;
	oUsersDA=null;
}
return oListaUsersBE;
}

public int insertarUsersBE(UsersBE oUsersBE){
	int resultado=0;
	UsersDA oUsersDA = null;

try {
	oUsersDA=new UsersDA();
	resultado=oUsersDA.insertarUsersBE(oUsersBE);
}
catch (Exception ex){
	ex.printStackTrace();
}
finally {
	oUsersBE=null;
	oUsersDA=null;
}
return resultado;
}


    public int insertarRegistrosUsersBE(ArrayList<UsersBE> oListaUsersBE){
       int resultado=0;
        UsersDA oUsersDA=null;

        try {
            oUsersDA=new UsersDA();
            resultado=oUsersDA.insertarRegistrosUsersBE(oListaUsersBE);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally
        {
            oListaUsersBE=null;
            oUsersDA=null;
        }
        return resultado;
    }


public int actualizarUsersBE(UsersBE oUsersBE1){
        int resultado=0;
        UsersDA oUsersDA=null;
        try {
            oUsersDA=new UsersDA();
            resultado=oUsersDA.actualizarUsersBE(oUsersBE1);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
       finally
        {
            oUsersBE1=null;
            oUsersDA=null;
        }

        return resultado;
    }


}
