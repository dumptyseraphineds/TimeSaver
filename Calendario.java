package TSPrincipal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.io.Serializable;

public class Calendario implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Tarea> tareas;
    
    public Calendario(){
        this.tareas = new ArrayList<>();
    }
    
    public List<Tarea> getTareas(){
        return tareas;
    }
    
    public void agregarTarea(Tarea tarea){
        tareas.add(tarea);
    }
    
    public boolean eliminarTarea(int id){
        return tareas.removeIf(tarea -> tarea.getId() == id);
    }
    
    public List<Tarea> obtenerTareasPorFecha(Date fecha){
        java.util.Calendar cal1 = java.util.Calendar.getInstance();
        java.util.Calendar cal2 = java.util.Calendar.getInstance();
        cal1.setTime(fecha);
        return tareas.stream().filter(tarea -> {
            cal2.setTime(tarea.getFechaLimite());
            return cal1.get(java.util.Calendar.YEAR) == cal2.get(java.util.Calendar.YEAR) &&
                   cal1.get(java.util.Calendar.DAY_OF_YEAR) == cal2.get(java.util.Calendar.DAY_OF_YEAR);
        }).collect(Collectors.toList());
    }
    
    public Tarea buscarTareaPorId(int id){
        return tareas.stream().filter(tarea -> tarea.getId() == id).findFirst().orElse(null);
    }
    
    public List<Tarea> obtenerTareasCompletadas(){
        return tareas.stream().filter(tarea -> tarea.getEstado().equals("Completada")).collect(Collectors.toList());
    }
    
    public List<Tarea> obtenerTareasPorPrioridad(){
        return tareas.stream().sorted((t1, t2) -> Integer.compare(t2.getPrioridad(), t1.getPrioridad())).collect(Collectors.toList());
    }
    
    public String obtenerTareas() {
    StringBuilder sb = new StringBuilder();
    for (Tarea tarea : tareas) {
        sb.append(tarea.toString()).append("\n");
    }
    return sb.toString();
    }
    public void limpiarTareas() {
    tareas.clear();
    }
    
    @Override
    public String toString(){
        return "Calendario{" + "Tareas: " + tareas + "}";
    }
      
}
