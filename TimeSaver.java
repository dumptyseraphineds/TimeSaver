package TSPrincipal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import Visual.VentanaPrincipal;


public class TimeSaver {
    private static Scanner scanner = new Scanner(System.in);
    private static Sistema sistema;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        sistema = new Sistema("datos_timesaver.dat");
        sistema.cargarUsuarios("Usuarios.txt");
        VentanaPrincipal Ventana1 = new VentanaPrincipal();
        Ventana1.setVisible(true);
        

    }
}
