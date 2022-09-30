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
    }
    
   