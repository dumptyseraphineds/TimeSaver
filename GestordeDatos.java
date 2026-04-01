package TSPrincipal;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestordeDatos {
    private String rutaArchivo;
    
    public GestordeDatos(String rutaArchivo){
        this.rutaArchivo = rutaArchivo;   
    }
    
    public String getRutaArchivo(){
        return rutaArchivo;
    }
    
    public void setRutaArchivo(String rutaArchivo){
        this.rutaArchivo = rutaArchivo;
    }
    
    public void guardarDatos(List<Tarea> datos){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))){
            oos.writeObject(new ArrayList<>(datos));
            System.out.println("Datos guardados correctamente en: " + rutaArchivo);
        }
        catch(IOException e){
            System.err.println("Error al guardar los datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<Tarea> cargarDatos(){
        List<Tarea> tareasRecuperadas = new ArrayList<>();
        
        File archivo = new File(rutaArchivo);
        if(!archivo.exists()){
            System.out.println("No existe archivo de datos previo. Se comenzara con una lista vacia.");
            return tareasRecuperadas;
        }
        
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))){
            tareasRecuperadas = (List<Tarea>) ois.readObject();
        }
        catch(IOException | ClassNotFoundException e){
            System.err.println("Error al cargar los datos: " + e.getMessage());
            e.printStackTrace();
        }
        
        return tareasRecuperadas;
    }
    
    public void guardarConfiguracion(Usuario usuario){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo + "_config_" + usuario.getId()))){
            oos.writeObject(usuario.getConfiguracion());
            System.out.println("Configuracion guardada para el usuario: " + usuario.getNombre());
        }
        catch(IOException e){
            System.err.println("Error al guardar la configuracion: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public ConfiguracionUsuario cargarConfiguracion(int idUsuario){
        ConfiguracionUsuario config = null;
        
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo + "_config_" + idUsuario))){
            config = (ConfiguracionUsuario)ois.readObject();
            System.out.println("Configuracion cargada para el usuario con ID: " + idUsuario);
        }
        catch(IOException | ClassNotFoundException e){
            System.out.println("No se encontro configuracion previa o hubo un error: " + e.getMessage());
            config = new ConfiguracionUsuario();
        }
        
        return config;
    }
}
