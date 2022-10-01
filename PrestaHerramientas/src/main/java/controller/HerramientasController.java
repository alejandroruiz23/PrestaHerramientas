package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import beans.herramienta;
import connection.DBConnection;

public class HerramientasController implements IHerramientasController {

    @Override
    public String listar(boolean ordenar, String orden) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "Select * from herramienta";

        if (ordenar == true) {
            sql += " order by marca " + orden;
        }

        List<String> herramientas = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                String nombre_herra = rs.getString("nombre_herra");
                String marca = rs.getString("marca");
                String tipoTrabajo = rs.getString("tipoTrabajo");
                int nDisponible = rs.getInt("nDisponible");
                boolean disponible = rs.getBoolean("disponible");

                herramienta herramienta = new herramienta(id, nombre_herra, marca, tipoTrabajo, nDisponible, disponible);

                herramientas.add(gson.toJson(herramienta));

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return gson.toJson(herramientas);

    }
    
    @Override
    public String devolver(int id, String username) {

        DBConnection con = new DBConnection();
        String sql = "Delete from alquiler where id= " + id + " and username = '" 
                + username + "' limit 1";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeQuery(sql);

            this.sumarCantidad(id);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";
    }
    @Override
    public String sumarCantidad(int id) {

        DBConnection con = new DBConnection();

        String sql = "Update herramienta set nDisponible = (Select nDisponible from herramienta where id = " 
                + id + ") + 1 where id = " + id;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";

    }
    
    @Override
    public String alquilar(int id, String username) {

        Timestamp fecha = new Timestamp(new Date().getTime());
        DBConnection con = new DBConnection();
        String sql = "Insert into alquiler values ('" + id + "', '" + username + "', '" + fecha + "')";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            String modificar = modificar(id);

            if (modificar.equals("true")) {
                return "true";
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }
        return "false";
    }

     @Override
    public String modificar(int id) {

        DBConnection con = new DBConnection();
        String sql = "Update herramienta set nDisponible = (nDisponible - 1) where id = " + id;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";

    }
    
    }
    
   