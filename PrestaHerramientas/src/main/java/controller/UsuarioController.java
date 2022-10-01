package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import com.google.gson.Gson;

import beans.usuario;
import connection.DBConnection;
import java.util.HashMap;
import java.util.Map;

public class UsuarioController implements IUsuarioController {

    @Override
    public String login(String username, String contrasena) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();

        String sql = "Select * from usuario where username = '" + username
                + "' and contrasena = '" + contrasena + "'";
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String nombre_usu = rs.getString("nombre_usu");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                double saldo = rs.getDouble("saldo");
                boolean frecuente = rs.getBoolean("frecuente");

                usuario usuario
                        = new usuario(username, contrasena, nombre_usu, apellidos, email, saldo, frecuente);
                return gson.toJson(usuario);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }

    @Override
    public String register(String username, String contrasena, String nombre_usu, String apellidos, String email,
            double saldo, boolean frecuente) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "Insert into usuario values('" + username + "', '" + contrasena + "', '" + nombre_usu
                + "', '" + apellidos + "', '" + email + "', " + saldo + ", " + frecuente + ")";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            usuario usuario = new usuario(username, contrasena, nombre_usu, apellidos, email, saldo, frecuente);

            st.close();

            return gson.toJson(usuario);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        } finally {
            con.desconectar();
        }

        return "false";

    }


@Override
    public String pedir(String username) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "Select * from usuario where username = '" + username + "'";

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String contrasena = rs.getString("contrasena");
                String nombre = rs.getString("nombre_usu");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                double saldo = rs.getDouble("saldo");
                boolean frecuente = rs.getBoolean("frecuente");

                usuario usuario = new usuario(username, contrasena,
                        nombre, apellidos, email, saldo, frecuente);

                return gson.toJson(usuario);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }
    
    @Override
    public String modificar(String username, String nuevaContrasena,
            String nuevoNombre, String nuevosApellidos,
            String nuevoEmail, double nuevoSaldo, boolean nuevoFrecuente) {

        DBConnection con = new DBConnection();

        String sql = "Update usuario set contrasena = '" + nuevaContrasena
                + "', nombre_usu = '" + nuevoNombre + "', "
                + "apellidos = '" + nuevosApellidos + "', email = '"
                + nuevoEmail + "', saldo = " + nuevoSaldo + ", frecuente = ";

        if (nuevoFrecuente == true) {
            sql += " 1 ";
        } else {
            sql += " 0 ";
        }

        sql += " where username = '" + username + "'";

        try {

            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";

    }
    
    @Override
    public String vernDisponible(String username) {

        DBConnection con = new DBConnection();
        String sql = "Select id,count(*) as num_nDisponible from alquiler where username = '"
                + username + "' group by id;";

        Map<Integer, Integer> nDisponible = new HashMap<Integer, Integer>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                int num_nDisponible = rs.getInt("num_nDisponible");

                nDisponible.put(id, num_nDisponible);
            }

            devolverHerramientas(username, nDisponible);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";

    }

    @Override
    public String devolverHerramientas(String username, Map<Integer, Integer> nDisponible) {

        DBConnection con = new DBConnection();

        try {
            for (Map.Entry<Integer, Integer> herramienta : nDisponible.entrySet()) {
                int id = herramienta.getKey();
                int num_nDisponible = herramienta.getValue();

                String sql = "Update herramienta set nDisponible = (Select nDisponible + " + num_nDisponible
                        + " from herramienta where id = " + id + ") where id = " + id;

                Statement st = con.getConnection().createStatement();
                st.executeUpdate(sql);

            }

            this.eliminar(username);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return "false";
    }

    @Override
    public String eliminar(String username) {

        DBConnection con = new DBConnection();

        String sql1 = "Delete from alquiler where username = '" + username + "'";
        String sql2 = "Delete from usuario where username = '" + username + "'";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql1);
            st.executeUpdate(sql2);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }
    
    @Override
    public String restarDinero(String username, double nuevoSaldo) {

        DBConnection con = new DBConnection();
        String sql = "Update usuario set saldo = " + nuevoSaldo + " where username = '" + username + "'";

        try {

            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }
}