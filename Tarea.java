package TSPrincipal;
import java.io.Serializable;

public class Tarea implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String titulo;
    private String descripcion;
    private java.util.Date fechaLimite;
    private int prioridad;
    private String estado;
    
    public Tarea(int id, String titulo, String descripcion, java.util.Date fechaLimite, int prioridad){
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.prioridad = prioridad;
        this.estado = "Pendiente";
    }
    
    public int getId(){
        return id;
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public java.util.Date getFechaLimite(){
        return fechaLimite;
    }
    
    public void setFechaLimite(java.util.Date fechaLimite){
        this.fechaLimite = fechaLimite;
    }
    
    public int getPrioridad(){
        return prioridad;
    }
    
    public void setPrioridad(int prioridad){
        this.prioridad = prioridad;
    }
    
    public String getEstado(){
        return estado;
    }
    
    public void setEstado(String estado){
        this.estado = estado;
    }
    
    public void marcarComoCompletada(){
        this.estado = "Completada";
    }
    
    public void editarTarea(String nuevoTitulo, String nuevaDescripcion, java.util.Date nuevaFecha, int nuevaPrioridad){
        this.titulo = nuevoTitulo;
        this.descripcion = nuevaDescripcion;
        this.fechaLimite = nuevaFecha;
        this.prioridad = nuevaPrioridad;
    }
    
    public void eliminarTarea(){
        this.estado = "Eliminada";
    }
    
    @Override
    public String toString(){
        return "Tarea{" + "Nombre: " + titulo + ", ID: " + id + ", Descripcion: " + descripcion + 
                ", Fecha limite: " + fechaLimite + ", Prioridad: " + prioridad + 
                ", Estado: " + estado + "}";
    }
    
    
}
