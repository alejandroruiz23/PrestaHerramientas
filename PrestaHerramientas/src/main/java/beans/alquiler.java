
package beans;

import java.sql.Date;

public class alquiler {
    
    private int id;
    private String username;
    private Date fecha;
    private boolean disponible;
    private String marca;

    public alquiler(int id, String username, Date fecha, boolean disponible, String marca) {
        this.id = id;
        this.username = username;
        this.fecha = fecha;
        this.disponible = disponible;
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "alquiler{" + "id=" + id + ", username=" + username + ", fecha=" + fecha + ", disponible=" + disponible + ", marca=" + marca + '}';
    }

    

    
    
    
    
}
