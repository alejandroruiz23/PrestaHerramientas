
package beans;

public class usuario {
    
    private String username;
    private String contrasena;
    private String nombre_usu;
    private String apellidos;
    private String email;
    private double saldo;
    private boolean marca;

    public usuario(String username, String contrasena, String nombre_usu, String apellidos, String email, double saldo, boolean marca) {
        this.username = username;
        this.contrasena = contrasena;
        this.nombre_usu = nombre_usu;
        this.apellidos = apellidos;
        this.email = email;
        this.saldo = saldo;
        this.marca = marca;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre_usu() {
        return nombre_usu;
    }

    public void setNombre_usu(String nombre_usu) {
        this.nombre_usu = nombre_usu;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isMarca() {
        return marca;
    }

    public void setMarca(boolean marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "usuario{" + "username=" + username + ", contrasena=" + contrasena + ", nombre_usu=" + nombre_usu + ", apellidos=" + apellidos + ", email=" + email + ", saldo=" + saldo + ", marca=" + marca + '}';
    }
    
}
