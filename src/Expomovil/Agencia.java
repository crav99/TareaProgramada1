/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Expomovil;

/**
 *
 * @author allanvz
 */
public class Agencia {
    
    private String cedJuridica;
    private String nombre;
    private int cantidad;
    
    public Agencia() {
        this.cantidad = 0;
        this.cedJuridica = "Unknown";
        this.nombre = "Unknown";
    }
    
    public Agencia(String cedJuridica, String nombre) {
        this.cantidad = 0;
        this.cedJuridica = cedJuridica;
        this.nombre = nombre;
    }

    /**
     * @return the cedJuridica
     */
    public String getCedJuridica() {
        return cedJuridica;
    }

    /**
     * @param cedJuridica the cedJuridica to set
     */
    public void setCedJuridica(String cedJuridica) {
        this.cedJuridica = cedJuridica;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    /**
     * @param cantidad 
     */

    public void addCantidad(int cantidad) {
        this.cantidad += cantidad;
    }
    
}
