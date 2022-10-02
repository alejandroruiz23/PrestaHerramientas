package controller;

import java.util.Map;

public interface IUsuarioController {
    
    public String login(String username, String contrasena);
    
    public String register(String username, String contrasena, 
            String nombre_usu, String apellidos, String email, double saldo, boolean frecuente);
    
    public String pedir(String username);
    
    public String modificar(String username, String nuevaContrasena,
            String nuevoNombre, String nuevosApellidos, String nuevoEmail,
            double nuevoSaldo, boolean nuevoFrecuente);
    
    public String vernDisponible(String username);

    public String devolverHerramientas(String username, Map<Integer, Integer> nDisponible);

    public String eliminar(String username);
    
    public String restarDinero(String username, double nuevoSaldo);
    
}
