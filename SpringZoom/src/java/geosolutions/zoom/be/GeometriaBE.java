/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geosolutions.zoom.be;

/**
 *
 * @author ruben
 */
public class GeometriaBE {

    private int IndOpSp;
    private String idgeometria;
    private String nombre;
    private String tipo;
    private String valor;

    private String descripcion;
    private String color;
    int opacity;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getOpacity() {
        return opacity;
    }

    public void setOpacity(int opacity) {
        this.opacity = opacity;
    }

    public int getIndOpSp() {
        return IndOpSp;
    }

    public void setIndOpSp(int IndOpSp) {
        this.IndOpSp = IndOpSp;
    }

    public String getIdgeometria() {
        return idgeometria;
    }

    public void setIdgeometria(String idgeometria) {
        this.idgeometria = idgeometria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
