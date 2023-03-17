/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Conexion;
import Modelo.Paises;
import Modelo.Personas;
import Modelo.Tipo_documento;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

/**
 *
 * @author USUARIO
 */
@Named(value = "personasBean")
@RequestScoped
public class PersonasBean implements Serializable {

    Conexion conexion = new Conexion();
    Connection con = conexion.conexion();
    private Personas persona = new Personas();
    private List<Paises> paises = new ArrayList<>();
    private List<Tipo_documento> documento = new ArrayList<>();
    private Paises paises2 = new Paises();
    private Tipo_documento t_documento = new Tipo_documento();

    /**
     * Creates a new instance of PersonasBean
     */
    public PersonasBean() {
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    
    
    public List<Paises> getPaises() {
        Connection con = conexion.conexion();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from paises");
            while (rs.next()) {
                Paises pais = new Paises();
                pais.setId_pais(rs.getInt("id"));
                pais.setN_pais(rs.getString("nombre_pais"));
                paises.add(pais);
            }
        } catch (Exception e) {
        }
        return paises;
    }

    public void setPaises(List<Paises> paises) {
        this.paises = paises;
    }

    //metodo para traeer paises
    public List<Paises> consulta_paises() {
        return null;
    }

    public List<Tipo_documento> getDocumento() {
        Connection con = conexion.conexion();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from documento");
            while (rs.next()) {
                Tipo_documento documentos = new Tipo_documento();
                documentos.setT_documento(rs.getInt("t_documento"));
                documentos.setDescripcion(rs.getString("descripcion"));
                documento.add(documentos);
            }

        } catch (SQLException e) {
        }
        return documento;
    }

    public void setDocumento(List<Tipo_documento> documento) {
        this.documento = documento;
    }

    //METODOS PARA LOS USUARIOS 
    //metodo para insertar datos 
    public void insertarUsuarios() throws Exception {
        System.out.println("*****************entro al metodo*****************");

        try {
            PreparedStatement ps;
            if (consulta_personalizada().isEmpty() || consulta_personalizada() == null) {
                ps = con.prepareStatement("Insert into personas values(?,?,?,?,?,?,?,?)");
                ps.setInt(1, this.persona.getId());
                ps.setString(2, this.persona.getNombre());
                ps.setString(3, this.persona.getN_cedula());
                ps.setString(4, this.persona.getTelefono());
                ps.setString(5, this.persona.getCorreo());
                ps.setString(6, this.persona.getContraseña());
                ps.setInt(7, this.persona.getT_documento());
                ps.setInt(8, this.persona.getId_pais());
                ps.executeUpdate();//realiza la insercion de datos
            } else {
                System.out.println("Ya existe");
                throw new Exception("El usuario ya existe");
            }
        } catch (SQLException e) {
            System.out.println("error" + e);
        }
    }

    //metodo para actualizar un usuario 
    public String actualizarUsuario() {
        try {
            System.out.println("************Entre al metodo ****************");
//            if (consulta_personalizada().isEmpty() || consulta_personalizada() == null) {
            PreparedStatement prepare;
            prepare = con.prepareStatement("Update personas set nombre=?, n_cedula=?, telefono=?, correo=?, contraseña=? where n_cedula = ? ");
            prepare.setString(1, this.persona.getNombre());
            prepare.setString(2, this.persona.getN_cedula());
            prepare.setString(3, this.persona.getTelefono());
            prepare.setString(4, this.persona.getCorreo());
            prepare.setString(5, this.persona.getContraseña());
            prepare.setString(6, this.persona.getN_cedula());
            prepare.executeUpdate();
            System.out.println("Registrado");
//            } else {
            // System.out.println("No existe el usuario");
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Registros_personas";
    }

    //metodo de consulta personalizada
    public List<Personas> consulta_personalizada() {
        Personas personasObj = new Personas();
        List<Personas> p = new ArrayList<>();
        try {
//            Connection con = conexion.conexion();

            PreparedStatement ps;
            ps = con.prepareStatement("Select * from personas where n_cedula = ?");
            ps.setString(1, this.persona.getN_cedula());
            Statement st = con.createStatement();//genera la consulta
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                this.persona.setId(rs.getInt("id"));
                this.persona.setNombre(rs.getString("nombre"));
                this.persona.setN_cedula(rs.getString("n_cedula"));
                this.persona.setTelefono(rs.getString("telefono"));
                this.persona.setCorreo(rs.getString("correo"));
//                personasObj.setT_documento(rs.getInt("descripcion"));
//                personasObj.setId_pais(rs.getInt("nombre_pais"));
                System.out.println(this.persona.toString());
                p.add(this.persona);
            }
        } catch (SQLException e) {
        }
        return p;
    }
//metodos para actualizar

    public String consulta_actualizar(String cedula) {

        try {
//            Connection con = conexion.conexion();
            PreparedStatement ps;
            ps = con.prepareStatement("Select * from personas where n_cedula = ?");
            ps.setString(1, cedula);

            Statement st = con.createStatement();//genera la consulta
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//                this.persona=new Personas();
                this.persona.setId(rs.getInt("id"));
                this.persona.setNombre(rs.getString("nombre"));
                this.persona.setN_cedula(rs.getString("n_cedula"));
                this.persona.setTelefono(rs.getString("telefono"));
                this.persona.setCorreo(rs.getString("correo"));
//                personasObj.setT_documento(rs.getInt("descripcion"));
//                personasObj.setId_pais(rs.getInt("nombre_pais"));
                System.out.println(this.persona.toString());

            }
        } catch (SQLException e) {
        }
        return "Actualizar_registros";
    }

    //metodo de acceso a datos 
    public List<Personas> registrosPersonas() {
        System.out.println("*****************entro al metodo*****************");
        List<Personas> lista_P = new ArrayList<>();
//        Conexion conexion = new Conexion();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from personas as p inner join paises as pa on p.id_pais = pa.id inner join documento as d on d.t_documento=p.t_documento");
            while (rs.next()) {
                this.persona = new Personas();
                this.persona.setId(rs.getInt("id"));
                this.persona.setNombre(rs.getString("nombre"));
                this.persona.setN_cedula(rs.getString("n_cedula"));
                this.persona.setTelefono(rs.getString("telefono"));
                this.persona.setCorreo(rs.getString("correo"));
                this.persona.setId_pais(rs.getInt("id_pais"));
                this.persona.setT_documento(rs.getInt("t_documento"));
                this.persona.setN_pais(rs.getString("nombre_pais"));
                this.persona.setDescripcion(rs.getString("descripcion"));
//                personasObj.setT_documento(rs.getInt("descripcion"));
//                personasObj.setId_pais(rs.getInt("nombre_pais"));
                lista_P.add(this.persona);
                System.out.println(this.persona.toString());
            }

        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
        return lista_P;
    }

    public void eliminar_usuarios(String cedula) {
        try {
            System.out.println("*******************entro al metodo*****************");
//            if (consulta_personalizada().isEmpty() || consulta_personalizada() == null) {
            PreparedStatement prepare;
            prepare = con.prepareStatement("Delete from personas where n_cedula = ? ");
            prepare.setString(1, cedula);
            System.out.println(this.persona.getN_cedula());
//            if (this.persona.getN_cedula() != null) {
            prepare.executeUpdate();
            System.out.println("eliminado");
//            } else {
            System.out.println("adios");
//            }
//            } else {
//                System.out.println("No existe el usuario");
//            }
        } catch (SQLException e) {
            System.out.println("no existe" + e);
        }

    }

    public void validacionesnombre(FacesContext context, UIComponent comp, Object value) throws Exception {
        System.out.println("*******ENTRE AL METODO**********");
        String nombre = (String) value;
        if (value != null) {
            System.out.println("dsada");
            String regex = "[a-zA-Z\\s]";//solo letras con espacios
            Pattern pattern = Pattern.compile(regex);
            for (int i = 0; i < nombre.length(); i++) {
                char charAt = nombre.charAt(i);
                String caracteres = String.valueOf(charAt);
                Matcher matcher = pattern.matcher(caracteres);
                boolean variable = matcher.find();
                System.out.println(caracteres);
                if (variable) {
                    System.out.println("si se registra");
                } else {
                    System.out.println("No sirve");
                    throw new Exception("El nombre no es correcto");
                }
            }
        } else {
            System.out.println("sdad");

        }
    }

    public int ValidartipoDocumento(FacesContext context, UIComponent comp, Object value) {
        int valor=(int)value;
        persona.setT_documento(valor);
        return persona.getT_documento();
    }

    public void validarNumerodocumento(FacesContext context, UIComponent comp, Object value) throws Exception {
        System.out.println("*******ENTRE AL METODO**********");
//        System.out.println(tipo);
        String documento =(String) value;
        if (value != null) {
            if (persona.getT_documento() == 2) {
                String regex = "^[A-Za-z0-9]{6,10}$";
                Pattern pattern = Pattern.compile(regex);
//                for (int i = 0; i < documento.length(); i++) {
//                    char charAt = documento.charAt(i);
//                    String caracteres = String.valueOf(charAt);
                    Matcher matcher = pattern.matcher(documento);
                    boolean variable2 = matcher.find();
                    System.out.println(documento);
                    if (variable2) {
                        System.out.println("si se registra");
                    } else {
                        System.out.println("No sirve");
                        throw new Exception("El numero de documento no corresponde al tipo indicado");
                    }
                
            } else {
                String regex2 = "^[0-9]+$";
                Pattern pattern = Pattern.compile(regex2);
//            for (int i = 0; i < documento.length(); i++) {
//                char charAt = documento.charAt(i);
//                String caracteres = String.valueOf(charAt);
                Matcher matcher = pattern.matcher(documento);
                boolean variable = matcher.find();
                System.out.println(documento);
                if (variable) {
                    System.out.println("si se registra");
                } else {
                    System.out.println("No sirve");
                    throw new Exception("El numero de documento no corresponde al tipo indicado");
                }
            }
        } else {
            System.out.println("No existe");

        }

    }

    public void validarTelefono(FacesContext context, UIComponent comp, Object value) throws Exception {
        System.out.println("*******ENTRE AL METODO**********");
        String telefono = (String) value;
        if (value != null) {
            String regex = "^(\\+\\d{1,5}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
            Pattern pattern = Pattern.compile(regex);
//            for (int i = 0; i < documento.length(); i++) {
//                char charAt = documento.charAt(i);
//                String caracteres = String.valueOf(telefono);
            Matcher matcher = pattern.matcher(telefono);
            boolean variable = matcher.find();
            System.out.println(telefono);
            if (variable) {
                System.out.println("si se registra");
            } else {
                System.out.println("No sirve");
                throw new Exception("El numero telefonico es incorrecto");
            }
        }
//        } else {
//            System.out.println("sdad");
//
////        }

    }

    public void validarCorreo(FacesContext context, UIComponent comp, Object value) throws Exception {
        System.out.println("*******ENTRE AL METODO**********");
        String correo = (String) value;
        if (value != null) {
            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);
//            for (int i = 0; i < documento.length(); i++) {
//                char charAt = documento.charAt(i);
//                String caracteres = String.valueOf(charAt);
            Matcher matcher = pattern.matcher(correo);
            boolean variable = matcher.find();
            System.out.println(correo);
            if (variable) {
                System.out.println("si se registra");
            } else {
                System.out.println("No sirve");
                throw new Exception("El correo electronico no es valido");
            }
        }
//        } else {
//            System.out.println("sdad");
//
//        }

    }
}
