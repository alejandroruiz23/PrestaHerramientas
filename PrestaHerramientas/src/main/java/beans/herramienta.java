
package beans;

    
public class herramienta {
    private int id;
    private String nombre_herra;
    private String marca;
    private String tipoTrabajo;
    private int nDisponible;
    private boolean disponible;

    public herramienta(int id, String nombre_herra, String marca, String tipoTrabajo, int nDisponible, boolean disponible) {
        this.id = id;
        this.nombre_herra = nombre_herra;
        this.marca = marca;
        this.tipoTrabajo = tipoTrabajo;
        this.nDisponible = nDisponible;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_herra() {
        return nombre_herra;
    }

    public void setNombre_herra(String nombre_herra) {
        this.nombre_herra = nombre_herra;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipoTrabajo() {
        return tipoTrabajo;
    }

    public void setTipoTrabajo(String tipoTrabajo) {
        this.tipoTrabajo = tipoTrabajo;
    }

    public int getnDisponible() {
        return nDisponible;
    }

    public void setnDisponible(int nDisponible) {
        this.nDisponible = nDisponible;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "herramienta{" + "id=" + id + ", nombre_herra=" + nombre_herra + ", marca=" + marca + ", tipoTrabajo=" + tipoTrabajo + ", nDisponible=" + nDisponible + ", disponible=" + disponible + '}';
    }
    
    
    
}


