package TSPrincipal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Usuario implements Serializable{
    private int id;
    private String nombre;
    private ConfiguracionUsuario configuracion;
    private Calendario calendario;
    
    public Usuario(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
        this.configuracion = new ConfiguracionUsuario();
        this.calendario = new Calendario();
    }
    
    public int getId(){
        return id;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setId(int ID){
        this.id = ID;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public ConfiguracionUsuario getConfiguracion(){
        return configuracion;
    }
    
    public Calendario getCalendario(){
        return calendario;
    }
    
    public void cambiarConfiguracion(ConfiguracionUsuario nuevaConfig){
        this.configuracion = nuevaConfig;
    }

    public void verTareas(){
        for(Tarea tarea: calendario.getTareas()){
            System.out.println(tarea);
        }
    }
  
    public String obtenerTareas() {
    return calendario.obtenerTareas();
    }
    
    public void agregarTarea(Tarea tarea){
        calendario.agregarTarea(tarea);
    }
    
    public void eliminarTarea(int idTarea){
        calendario.eliminarTarea(idTarea);
    }
    
    
    public void guardarTareasEnArchivo() {
    try {
        FileWriter writer = new FileWriter("tareas_usuario" + id + ".txt");
        for (Tarea tarea : calendario.getTareas()) {
            writer.write(tarea.getId() + "," + tarea.getTitulo() + "," + tarea.getDescripcion() + "," +
                         new SimpleDateFormat("dd/MM/yyyy").format(tarea.getFechaLimite()) + "," +
                         tarea.getPrioridad() + "," + tarea.getEstado() + "\n");
        }
        writer.close();
    } catch (IOException e) {
        System.out.println("Error al guardar tareas: " + e.getMessage());
    }
}
    public void cargarTareasDesdeArchivo() {
    try {
        File archivo = new File("tareas_usuario" + id + ".txt");
        if (!archivo.exists()) return;

        BufferedReader reader = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] partes = linea.split(",");
            if (partes.length >= 6) {
                int idTarea = Integer.parseInt(partes[0]);
                String titulo = partes[1];
                String descripcion = partes[2];
                Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(partes[3]);
                int prioridad = Integer.parseInt(partes[4]);
                String estado = partes[5];

                Tarea tarea = new Tarea(idTarea, titulo, descripcion, fecha, prioridad);
                tarea.setEstado(estado);
                calendario.agregarTarea(tarea);
            }
        }
        reader.close();
    } catch (Exception e) {
        System.out.println("Error al cargar tareas: " + e.getMessage());
    }
}
    
    @Override
    public String toString(){
        return "Usuario: {" + "ID: " + id + ", Nombre: " + nombre + 
                ", Configuracion: " + configuracion + "}";
    }
}
