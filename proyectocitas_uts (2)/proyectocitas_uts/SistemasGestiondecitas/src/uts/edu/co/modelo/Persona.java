package uts.edu.co.modelo;

public class Persona {
    private int idUsuario;
    private String cedula;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String rol;
    private int estado;

    public Persona() {}

    public Persona(int idUsuario, String cedula, String nombre, String apellido, String email, String password, String rol, int estado) {
        this.idUsuario = idUsuario;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.estado = estado;
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
}