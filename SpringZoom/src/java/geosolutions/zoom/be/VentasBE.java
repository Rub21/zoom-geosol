package geosolutions.zoom.be;

import java.util.Date;
//@autor Sergio Medina

public class VentasBE {

    private int IndOpSp;
    private String codigovendedor;
    private String fuerzaventas;
    private String grupoventas;
    private String codigocliente;
    private String codigozona;
    private String codigomesa;
    private String codigodia;
    private String fechafacturacion;
    private String tipodocumento;
    private String numerodocumento;
    private String codigoproducto;
    private String codigobonificacion;
    private String bonificacion;
    private String codigoalmacen;
    private String formaventa;
    private String anulado;
    private String motivoanulacion;
    private String tipotransaccion;
    private int cantidadunitaria;
    private double cantidadformato;
    private double valorunitario;
    private double itemcostoventa;
    private double itemvalorventa;
    private double totalventa;
    private String codigovehiculo;
    private int fecha_time;
    private String fechaInicio;
    private String fechaFin;
    private String anio;
    private String mes;
    private String dia;

    public VentasBE() {
        this.IndOpSp = 0;
        this.codigovendedor = "";
        this.fuerzaventas = "";
        this.grupoventas = "";
        this.codigocliente = "";
        this.codigozona = "";
        this.codigomesa = "";
        this.codigodia = "";
        this.fechafacturacion = "";
        this.tipodocumento = "";
        this.numerodocumento = "";
        this.codigoproducto = "";
        this.codigobonificacion = "";
        this.bonificacion = "";
        this.codigoalmacen = "";
        this.formaventa = "";
        this.anulado = "";
        this.motivoanulacion = "";
        this.tipotransaccion = "";
        this.cantidadunitaria = 0;
        this.cantidadformato = 0.0;
        this.valorunitario = 0.0;
        this.itemcostoventa = 0.0;
        this.itemvalorventa = 0.0;
        this.totalventa = 0.0;
        this.codigovehiculo = "";
        this.fecha_time = 0;

    }

    public VentasBE(int pIndOpSp, String pcodigovendedor, String pfuerzaventas, String pgrupoventas, String pcodigocliente, String pcodigozona, String pcodigomesa, String pcodigodia, String pfechafacturacion, String ptipodocumento, String pnumerodocumento, String pcodigoproducto, String pcodigobonificacion, String pbonificacion, String pcodigoalmacen, String pformaventa, String panulado, String pmotivoanulacion, String ptipotransaccion, int pcantidadunitaria, double pcantidadformato, double pvalorunitario, double pitemcostoventa, double pitemvalorventa, double ptotalventa, String pcodigovehiculo, int pfecha_time) {
        this.IndOpSp = pIndOpSp;
        this.codigovendedor = pcodigovendedor;
        this.fuerzaventas = pfuerzaventas;
        this.grupoventas = pgrupoventas;
        this.codigocliente = pcodigocliente;
        this.codigozona = pcodigozona;
        this.codigomesa = pcodigomesa;
        this.codigodia = pcodigodia;
        this.fechafacturacion = pfechafacturacion;
        this.tipodocumento = ptipodocumento;
        this.numerodocumento = pnumerodocumento;
        this.codigoproducto = pcodigoproducto;
        this.codigobonificacion = pcodigobonificacion;
        this.bonificacion = pbonificacion;
        this.codigoalmacen = pcodigoalmacen;
        this.formaventa = pformaventa;
        this.anulado = panulado;
        this.motivoanulacion = pmotivoanulacion;
        this.tipotransaccion = ptipotransaccion;
        this.cantidadunitaria = pcantidadunitaria;
        this.cantidadformato = pcantidadformato;
        this.valorunitario = pvalorunitario;
        this.itemcostoventa = pitemcostoventa;
        this.itemvalorventa = pitemvalorventa;
        this.totalventa = ptotalventa;
        this.codigovehiculo = pcodigovehiculo;
        this.fecha_time = pfecha_time;
    }

   
    public int getIndOpSp() {
        return IndOpSp;
    }

    public String getCodigovendedor() {
        return codigovendedor;

    }

    public String getFuerzaventas() {
        return fuerzaventas;

    }

    public String getGrupoventas() {
        return grupoventas;

    }

    public String getCodigocliente() {
        return codigocliente;

    }

    public String getCodigozona() {
        return codigozona;

    }

    public String getCodigomesa() {
        return codigomesa;

    }

    public String getCodigodia() {
        return codigodia;

    }

    public String getFechafacturacion() {
        return fechafacturacion;

    }

    public String getTipodocumento() {
        return tipodocumento;

    }

    public String getNumerodocumento() {
        return numerodocumento;

    }

    public String getCodigoproducto() {
        return codigoproducto;

    }

    public String getCodigobonificacion() {
        return codigobonificacion;

    }

    public String getBonificacion() {
        return bonificacion;

    }

    public String getCodigoalmacen() {
        return codigoalmacen;

    }

    public String getFormaventa() {
        return formaventa;

    }

    public String getAnulado() {
        return anulado;

    }

    public String getMotivoanulacion() {
        return motivoanulacion;

    }

    public String getTipotransaccion() {
        return tipotransaccion;

    }

    public int getCantidadunitaria() {
        return cantidadunitaria;

    }

    public double getCantidadformato() {
        return cantidadformato;

    }

    public double getValorunitario() {
        return valorunitario;

    }

    public double getItemcostoventa() {
        return itemcostoventa;

    }

    public double getItemvalorventa() {
        return itemvalorventa;

    }

    public double getTotalventa() {
        return totalventa;

    }

    public String getCodigovehiculo() {
        return codigovehiculo;

    }

    public int getFecha_time() {
        return fecha_time;

    }

    public void setIndOpSp(int IndOpSp) {
        this.IndOpSp = IndOpSp;
    }

    public void setCodigovendedor(String codigovendedor) {

        this.codigovendedor = codigovendedor;
    }

    public void setFuerzaventas(String fuerzaventas) {

        this.fuerzaventas = fuerzaventas;
    }

    public void setGrupoventas(String grupoventas) {

        this.grupoventas = grupoventas;
    }

    public void setCodigocliente(String codigocliente) {

        this.codigocliente = codigocliente;
    }

    public void setCodigozona(String codigozona) {

        this.codigozona = codigozona;
    }

    public void setCodigomesa(String codigomesa) {

        this.codigomesa = codigomesa;
    }

    public void setCodigodia(String codigodia) {

        this.codigodia = codigodia;
    }

    public void setFechafacturacion(String fechafacturacion) {

        this.fechafacturacion = fechafacturacion;
    }

    public void setTipodocumento(String tipodocumento) {

        this.tipodocumento = tipodocumento;
    }

    public void setNumerodocumento(String numerodocumento) {

        this.numerodocumento = numerodocumento;
    }

    public void setCodigoproducto(String codigoproducto) {

        this.codigoproducto = codigoproducto;
    }

    public void setCodigobonificacion(String codigobonificacion) {

        this.codigobonificacion = codigobonificacion;
    }

    public void setBonificacion(String bonificacion) {

        this.bonificacion = bonificacion;
    }

    public void setCodigoalmacen(String codigoalmacen) {

        this.codigoalmacen = codigoalmacen;
    }

    public void setFormaventa(String formaventa) {

        this.formaventa = formaventa;
    }

    public void setAnulado(String anulado) {

        this.anulado = anulado;
    }

    public void setMotivoanulacion(String motivoanulacion) {

        this.motivoanulacion = motivoanulacion;
    }

    public void setTipotransaccion(String tipotransaccion) {

        this.tipotransaccion = tipotransaccion;
    }

    public void setCantidadunitaria(int cantidadunitaria) {

        this.cantidadunitaria = cantidadunitaria;
    }

    public void setCantidadformato(double cantidadformato) {

        this.cantidadformato = cantidadformato;
    }

    public void setValorunitario(double valorunitario) {

        this.valorunitario = valorunitario;
    }

    public void setItemcostoventa(double itemcostoventa) {

        this.itemcostoventa = itemcostoventa;
    }

    public void setItemvalorventa(double itemvalorventa) {

        this.itemvalorventa = itemvalorventa;
    }

    public void setTotalventa(double totalventa) {

        this.totalventa = totalventa;
    }

    public void setCodigovehiculo(String codigovehiculo) {

        this.codigovehiculo = codigovehiculo;
    }

    public void setFecha_time(int fecha_time) {

        this.fecha_time = fecha_time;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
    
    

}
