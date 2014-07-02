/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geosolutions.zoom.system;

/**
 *
 * @author ERIK-PC
 */
public class ParametrosSystem {
    

    private static String driverPostgres = "";
    private static String urlPostgres = "";
    private static String ipServidor = "";
    private static String nombrePcServidor = "";
    private static String ipCliente = "";
    private static String nombrePcCliente = "";
    private static String puertoPostgres = "";
    private static String baseDatos = "";
    private static String usuarioPostgres = "";
    private static String passwordPostgres = "";
    private static String pathPgDumpPostgres = "";
    private static String pathReport = "";
    private static String pathBackup = "";//ruta de la copia
    private static String nickSistema = "";
    //otros
    private static String esServidor = "";
    private static String interrupcion = "";//se va la luz o se reinicia la pc
    private static String esAutorizado = "";//para copias de seguridad
    //conexion con otra base de datos
    private static String otroDB = "";//principalmente base de datos kardex
    private static String otroIpServidor = "";
    private static String otroPuertoPostgres = "";
    private static String horaCierrePlanilla = "";

    public static String getBaseDatos() {
        return baseDatos;
    }

    public static void setBaseDatos(String baseDatos) {
        ParametrosSystem.baseDatos = baseDatos;
    }

    public static String getDriverPostgres() {
        return driverPostgres;
    }

    public static void setDriverPostgres(String driverPostgres) {
        ParametrosSystem.driverPostgres = driverPostgres;
    }

    public static String getEsServidor() {
        return esServidor;
    }

    public static void setEsServidor(String esServidor) {
        ParametrosSystem.esServidor = esServidor;
    }

    public static String getHoraCierrePlanilla() {
        return horaCierrePlanilla;
    }

    public static void setHoraCierrePlanilla(String horaCierrePlanilla) {
        ParametrosSystem.horaCierrePlanilla = horaCierrePlanilla;
    }

    public static String getInterrupcion() {
        return interrupcion;
    }

    public static void setInterrupcion(String interrupcion) {
        ParametrosSystem.interrupcion = interrupcion;
    }

    public static String getIpServidor() {
        return ipServidor;
    }

    public static void setIpServidor(String ipServidor) {
        ParametrosSystem.ipServidor = ipServidor;
    }

    public static String getNickSistema() {
        return nickSistema;
    }

    public static void setNickSistema(String nickSistema) {
        ParametrosSystem.nickSistema = nickSistema;
    }

    public static String getNombrePcCliente() {
        return nombrePcCliente;
    }

    public static void setNombrePcCliente(String nombrePcCliente) {
        ParametrosSystem.nombrePcCliente = nombrePcCliente;
    }

    public static String getOtroDB() {
        return otroDB;
    }

    public static void setOtroDB(String otroDB) {
        ParametrosSystem.otroDB = otroDB;
    }

    public static String getOtroIpServidor() {
        return otroIpServidor;
    }

    public static void setOtroIpServidor(String otroIpServidor) {
        ParametrosSystem.otroIpServidor = otroIpServidor;
    }

    public static String getOtroPuertoPostgres() {
        return otroPuertoPostgres;
    }

    public static void setOtroPuertoPostgres(String otroPuertoPostgres) {
        ParametrosSystem.otroPuertoPostgres = otroPuertoPostgres;
    }

    public static String getPasswordPostgres() {
        return passwordPostgres;
    }

    public static void setPasswordPostgres(String passwordPostgres) {
        ParametrosSystem.passwordPostgres = passwordPostgres;
    }

    public static String getPathBackup() {
        return pathBackup;
    }

    public static void setPathBackup(String pathBackup) {
        ParametrosSystem.pathBackup = pathBackup;
    }

    public static String getPathPgDumpPostgres() {
        return pathPgDumpPostgres;
    }

    public static void setPathPgDumpPostgres(String pathPgDumpPostgres) {
        ParametrosSystem.pathPgDumpPostgres = pathPgDumpPostgres;
    }

    public static String getPathReport() {
        return pathReport;
    }

    public static void setPathReport(String pathReport) {
        ParametrosSystem.pathReport = pathReport;
    }

    public static String getPuertoPostgres() {
        return puertoPostgres;
    }

    public static void setPuertoPostgres(String puertoPostgres) {
        ParametrosSystem.puertoPostgres = puertoPostgres;
    }

    public static String getUrlPostgres() {
        return urlPostgres;
    }

    public static void setUrlPostgres(String urlPostgres) {
        ParametrosSystem.urlPostgres = urlPostgres;
    }

    public static String getUsuarioPostgres() {
        return usuarioPostgres;
    }

    public static void setUsuarioPostgres(String usuarioPostgres) {
        ParametrosSystem.usuarioPostgres = usuarioPostgres;
    }

    public static String getIpCliente() {
        return ipCliente;
    }

    public static void setIpCliente(String ipCliente) {
        ParametrosSystem.ipCliente = ipCliente;
    }

    public static String getNombrePcServidor() {
        return nombrePcServidor;
    }

    public static void setNombrePcServidor(String nombrePcServidor) {
        ParametrosSystem.nombrePcServidor = nombrePcServidor;
    }

    public static String getEsAutorizado() {
        return esAutorizado;
    }

    public static void setEsAutorizado(String esAutorizado) {
        ParametrosSystem.esAutorizado = esAutorizado;
    }


    
}
