package geosolutions.zoom.da;

//@autor Sergio Medina
import geosolutions.zoom.be.ClienteBE;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestConection extends BaseDA {

    String cadenaConexion;
    String DriverConnection;
    String user;
    String password;

    public TestConection() {
        cadenaConexion = super.getConnectionString();
        DriverConnection = super.getDriverConnection();
        user = super.getUser();
        password = super.getPassword();
    }

    public Connection testConection() throws SQLException {
        Connection cn = null;

        try {
            Class.forName(DriverConnection);
            cn = DriverManager.getConnection(cadenaConexion, user, password);
        } catch (Exception ex) {
            cn = null;
            ex.printStackTrace();
        }
        return cn;
    }

}
