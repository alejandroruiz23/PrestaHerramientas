package controller;

import java.util.Map;

public interface IUsuarioController {
    
    public String login(String username, String contrasena);
    
    public String register(String username, String contrasena, 
            String nombre_usu, String apellidos, String email, double saldo, boolean frecuente);
    
    public String pedir(String username);
    
}
