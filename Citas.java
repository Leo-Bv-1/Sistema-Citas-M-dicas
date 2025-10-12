import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Citas {
    private int codigo;
    private Pacientes paciente;
    private Doctores doctor;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;

    private static int contador_citas = 1;
    public Citas(Scanner sc, ArrayList<Pacientes> listaPacientes, ArrayList<Doctores> listaDoctores, ArrayList<Citas> listaCitas) {
        System.out.println("Ingrese su código de paciente:");
        int codPaciente = sc.nextInt();
        sc.nextLine();

        paciente = null;
        for (Pacientes p : listaPacientes) {
            if (p.getCodigo() == codPaciente) {
                paciente = p;
                break;
            }
        }

        if (paciente == null) {
            System.out.println("No se encontró el paciente con ese código.");
            return;
        }

        System.out.println(paciente.getNombre() + ", ¿en qué especialidad desea ser atendido?");
        String especialidad = sc.nextLine();

        // --- Buscar doctores de esa especialidad ---
        ArrayList<Doctores> doctoresEncontrados = new ArrayList<>();
        for (Doctores d : listaDoctores) {
            if (d.getEspecialidad().equalsIgnoreCase(especialidad)) {
                doctoresEncontrados.add(d);
            }
        }

        if (doctoresEncontrados.isEmpty()) {
            System.out.println("No hay doctores disponibles en esa especialidad.");
            return;
        }

        System.out.println("Doctores disponibles:");
        for (Doctores d : doctoresEncontrados) {
            System.out.println("Código: " + d.getCodigo() + " | " + d.getNombre() +
                               " | Horario: " + d.getHorarioAtencion());
        }

        System.out.println("Ingrese el código del doctor con el que desea la cita:");
        int codDoctor = sc.nextInt();
        sc.nextLine();

        doctor = null;
        for (Doctores d : doctoresEncontrados) {
            if (d.getCodigo() == codDoctor) {
                doctor = d;
                break;
            }
        }

        if (doctor == null) {
            System.out.println("Doctor no válido.");
            return;
        }

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

        boolean valido = false;
        while (!valido) {
            try {
                System.out.println("Ingrese la fecha de la cita (formato dd/MM/yyyy):");
                fecha = LocalDate.parse(sc.nextLine(), formatoFecha);

                System.out.println("Ingrese la hora de la cita (formato HH:mm):");
                hora = LocalTime.parse(sc.nextLine(), formatoHora);

                // Validar si la hora está dentro del horario del doctor
                if (hora.isBefore(doctor.getHoraInicio()) || hora.isAfter(doctor.getHoraFin())) {
                    System.out.println("La hora no está dentro del horario de atención del doctor.");
                    continue;
                }

                //  Validar que el doctor no tenga otra cita a la misma hora y fecha
                boolean ocupado = false;
                for (Citas c : listaCitas) {
                    if (c.getDoctor().getCodigo() == doctor.getCodigo() &&
                        c.getFecha().equals(fecha) &&
                        c.getHora().equals(hora)) {
                        ocupado = true;
                        break;
                    }
                }

                if (ocupado) {
                    System.out.println("El doctor ya tiene una cita en esa fecha y hora. Elija otra hora.");
                } else {
                    valido = true; // todo correcto
                }

            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Intente de nuevo.");
            }
        }

        // --- Asignar código y estado ---
        this.codigo = contador_citas;
        System.out.println("El código de su cita será: "+ this.codigo);
        contador_citas++;
        this.estado = "Pendiente";

        System.out.println("Cita registrada correctamente.");
        System.out.println(this);
    }

    public int getCodigo() { return codigo; }
    public Pacientes getPaciente() { return paciente; }
    public Doctores getDoctor() { return doctor; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String toString() {
        return "Cita #" + codigo + " | Paciente: " + paciente.getNombre() +
               " | Doctor: " + doctor.getNombre() +
               " | Fecha: " + fecha +
               " | Hora: " + hora +
               " | Estado: " + estado;
    }
}
