package TSPrincipal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.io.*;
public class Sistema {
    private static List<Usuario> usuarios;
    private static GestordeDatos gestorDatos;
    private static Usuario usuarioActual;
    
    public Sistema(String rutaArchivoDatos){
        this.usuarios = new ArrayList<>();
        this.gestorDatos = new GestordeDatos(rutaArchivoDatos);
        this.usuarioActual = null;
    }
    
    public List<Usuario> getUsuario(){
        return usuarios;
    }
    
    public GestordeDatos getGestordeDatos(){
        return gestorDatos;
    }
    
    public static Usuario getUsuarioActual(){
        return usuarioActual;
    }
    
    public static boolean iniciarSesion(int idUsuario){
        Optional<Usuario> usuarioEncontrado = usuarios.stream().filter(u -> u.getId() == idUsuario).findFirst();
        
        if(usuarioEncontrado.isPresent()){
            usuarioActual = usuarioEncontrado.get();
            List<Tarea> tareasCargadas = gestorDatos.cargarDatos();
            for(Tarea tarea : tareasCargadas){
                usuarioActual.agregarTarea(tarea);
            }
            
            ConfiguracionUsuario configCargada = gestorDatos.cargarConfiguracion(idUsuario);
            if(configCargada != null){
                usuarioActual.cambiarConfiguracion(configCargada);
            }
            
            System.out.println("Sesion iniciada: " + usuarioActual.getNombre());
            return true;
        }
        
        System.out.println("Usuario no encontrado con ID: " + idUsuario);
        return false;
    }
    
    public static void guardarProgreso(){
        if(usuarioActual != null){
            gestorDatos.guardarDatos(usuarioActual.getCalendario().getTareas());
            gestorDatos.guardarConfiguracion(usuarioActual);
            System.out.println("Progeso guardado para: " + usuarioActual.getNombre());
        }
        else{
            System.out.println("No hay usuario con sesion activa");
        }
    }
    
    public static void registrarUsuario(Usuario nuevoUsuario){
        boolean existe = usuarios.stream().anyMatch(u -> u.getId() == nuevoUsuario.getId());
        
        if(!existe){
            usuarios.add(nuevoUsuario);
            System.out.println("Usuario registrado: " + nuevoUsuario.getNombre());
        }
        else{
            System.out.println("Ya existe un usuario con el ID: " + nuevoUsuario.getNombre());
        }
    }
    
    public void cerrarSesion() {
        if (usuarioActual != null) {
            guardarProgreso();
            System.out.println("Sesion cerrada: " + usuarioActual.getNombre());
            usuarioActual = null;
        }
    }
    
public static void guardarUsuarios(String archivoUsuarios) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoUsuarios))) {
        for (Usuario usuario : usuarios) {
            writer.write(usuario.getId() + ";" + usuario.getNombre());
            writer.newLine();
        }
        System.out.println("Usuarios guardados en " + archivoUsuarios);
    } catch (IOException e) {
        System.out.println("Error al guardar usuarios: " + e.getMessage());
    }
}

public void cargarUsuarios(String archivoUsuarios) {
    usuarios.clear();
    try (BufferedReader reader = new BufferedReader(new FileReader(archivoUsuarios))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] partes = linea.split(";");
            if (partes.length == 2) {
                int id = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                usuarios.add(new Usuario(id, nombre));
            }
        }
        System.out.println("Usuarios cargados desde " + archivoUsuarios);
    } catch (IOException e) {
        System.out.println("Error al cargar usuarios: " + e.getMessage());
    }
}
    
    @Override
    public String toString() {
        return "Sistema{" +
                "usuarios=" + usuarios.size() + " registrados" +
                ", gestorDatos=" + gestorDatos.getRutaArchivo() +
                ", usuarioActual=" + (usuarioActual != null ? usuarioActual.getNombre() : "ninguno") +
                '}';
    }
}
