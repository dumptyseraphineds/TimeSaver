package TSPrincipal;
import java.io.Serializable;

public class ConfiguracionUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean notificaciones;
    private String tema;
    boolean isNotificaciones;
    
    public ConfiguracionUsuario(){
        this.tema = "Claro";
        this.notificaciones = true;
    }
    
    public ConfiguracionUsuario(String temaVisual, boolean notificaciones){
        this.tema = temaVisual;
        this.notificaciones = notificaciones;
    }
    
    public String getTema(){
        return tema;
    }
    
    public void setTema(String tema){
        this.tema = tema;
    }
    
    public boolean getNotificaciones(){
        return notificaciones;
    }
    
    public void setNotificaciones(boolean notificaciones){
        this.notificaciones = notificaciones;
    }
    
    public void modificarPreferencias(String nuevoTema, boolean nuevasNotificaciones){
        this.tema = nuevoTema;
        this.notificaciones = nuevasNotificaciones;
    }
    
    @Override
    public String toString(){
        return "Configuracion del usuario: " + 
                "Tema: " + tema + ", Notificaciones: " + notificaciones; 
    }

    boolean isNotificaciones() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
