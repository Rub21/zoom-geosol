package geosolutions.zoom.be;

import java.util.Date;
//@autor Sergio Medina

public class AlmacenBE {

    private int IndOpSp;
    private String codigoalmacen;
    private String nombrealmacen;

    public AlmacenBE() {
        this.IndOpSp = 0;
        this.codigoalmacen = "";
        this.nombrealmacen = "";

    }

    public AlmacenBE(int pIndOpSp, String pcodigoalmacen, String pnombrealmacen) {
        this.IndOpSp = pIndOpSp;
        this.codigoalmacen = pcodigoalmacen;
        this.nombrealmacen = pnombrealmacen;
    }

    public int getIndOpSp() {
        return IndOpSp;
    }

    public String getCodigoalmacen() {
        return codigoalmacen;

    }

    public String getNombrealmacen() {
        return nombrealmacen;

    }

    public void setIndOpSp(int IndOpSp) {
        this.IndOpSp = IndOpSp;
    }

    public void setCodigoalmacen(String codigoalmacen) {

        this.codigoalmacen = codigoalmacen;
    }

    public void setNombrealmacen(String nombrealmacen) {

        this.nombrealmacen = nombrealmacen;
    }

}
