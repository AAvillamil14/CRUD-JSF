/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author USUARIO
 */
public class Personas {
    private int id;
    private String nombre;
    private String n_cedula;
    private String telefono;
    private String correo;
    private String contraseña;
    private int t_documento;
    private int id_pais;
    private String n_pais;
    private String descripcion;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getN_cedula() {
        return n_cedula;
    }

    public void setN_cedula(String n_cedula) {
        this.n_cedula = n_cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getT_documento() {
        return t_documento;
    }

    public void setT_documento(int t_documento) {
        this.t_documento = t_documento;
    }

    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    public String getN_pais() {
        return n_pais;
    }

    public void setN_pais(String n_pais) {
        this.n_pais = n_pais;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    

    @Override
    public String toString() {
        return "Personas{" + "id=" + id + ", nombre=" + nombre + ", n_cedula=" + n_cedula + ", telefono=" + telefono + ", correo=" + correo + ", contrase\u00f1a=" + contraseña + ", t_documento=" + t_documento + ", id_pais=" + id_pais + '}';
    }

    public void getPaises2(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
