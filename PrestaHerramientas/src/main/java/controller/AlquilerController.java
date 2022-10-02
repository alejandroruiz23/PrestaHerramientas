package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.google.gson.Gson;

import beans.alquiler;
import connection.DBConnection;

public class AlquilerController implements IAlquilerController {

    @Override
    public String listarAlquileres(String username) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();

        String sql = "Select l.id, l.nombre_herra, l.marca, l.disponible, a.fecha from herramienta l "
                + "inner join alquiler a on l.id = a.id inner join usuario u on a.username = u.username "
                + "where a.username = '" + username + "'";

        List<String> alquileres = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre_herra = rs.getString("nombre_herra");
                String marca = rs.getString("marca");
                boolean disponible = rs.getBoolean("disponible");
                Date fecha = rs.getDate("fecha");

                alquiler alquiler = new alquiler(id, nombre_herra, fecha, disponible, marca);

                alquileres.add(gson.toJson(alquiler));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return gson.toJson(alquileres);
    }
}
