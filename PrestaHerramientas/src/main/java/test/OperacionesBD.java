//Actualizar datos y traer datos de la bse de datos
package test;
import beans.herramienta;
import connection.DBConnection;
import java.sql.ResultSet;
import java.sql.Statement;

public class OperacionesBD {
    public static void main(String[] args) {
        listarHerramienta();
        
    }
    
    public static void actualizarHerramienta(int id, String marca ){
        DBConnection con = new DBConnection();
        String sql = "UPDATE herramienta SET marca = '"+ marca +"'WHERE id ="+ id;
        
        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            con.desconectar();
        }
        
    }
    public static void listarHerramienta(){
        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM herramienta";
        
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre_herra = rs.getString("nombre_herra");
                String marca = rs.getString("marca");
                String tipoTrabajo =rs.getString("tipoTrabajo");
                int nDisponible = rs.getInt("nDisponible");
                boolean disponible = rs.getBoolean("disponible");
                
                herramienta herramienta = new herramienta(id, nombre_herra, marca,tipoTrabajo,nDisponible, disponible);
                
                System.out.println(herramienta.toString());
                
            }
            
            st.executeQuery(sql);
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally{
            con.desconectar();
        }
    }
}