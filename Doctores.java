import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Doctores {
    private int codigo;
    private String nombre, especialidad;
    private LocalTime horaInicio, horaFin;
    private static int contador_doctores = 1;
    public Doctores(Scanner sc){
        System.out.println("Ingrese su nombre:");
        this.nombre=sc.nextLine();
        System.out.println("Ingrese su especialidad:");
        this.especialidad=sc.nextLine();
        System.out.println("Horarios de Ateción:");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        boolean valido = false;
        while (!valido) {
            try {
                System.out.println("Ingrese la hora de inicio (formato 08:00):");
                this.horaInicio = LocalTime.parse(sc.nextLine(), formatoHora);

                System.out.println("Ingrese la hora de fin (formato 14:00):");
                this.horaFin = LocalTime.parse(sc.nextLine(), formatoHora);

                if (horaFin.isAfter(horaInicio)) {
                    valido = true;
                } else {
                    System.out.println("La hora de fin debe ser posterior a la de inicio. Intente de nuevo.");
                }

            } catch (DateTimeParseException e) {
                System.out.println("Formato de hora inválido. Use el formato HH:mm (por ejemplo, 09:30).");
            }
        }
        System.out.println("Su código será: "+ contador_doctores);
        codigo=contador_doctores;
        contador_doctores++;
    }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    public String getHorarioAtencion() {
        return horaInicio + "-" + horaFin;
    }

    public String toString() {
        return "Doctor " + nombre + " | Código: " + codigo + " | Especialidad: " + especialidad +" | Horario: " + getHorarioAtencion();
    }
}
