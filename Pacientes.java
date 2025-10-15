import java.util.Scanner;

public class Pacientes {
    private int codigo, edad, DNI;
    private String nombre;
    private static int contador_pacientes = 1;
    public Pacientes(Scanner sc) {
        System.out.println("Ingrese su nombre:");
        this.nombre=sc.nextLine();
        do {
            System.out.println("Ingrese su edad:");
            this.edad = sc.nextInt();
            if (edad <= 0 || edad>=120) {
                System.out.println("Edad inválida. Intente de nuevo.");
            }
        } while (edad <= 0 || edad>=120);

        System.out.println("Ingrese su DNI:");
        DNI = sc.nextInt();
        sc.nextLine();

        System.out.println("Su código será: " + contador_pacientes);
        codigo = contador_pacientes++;
    }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public int getDNI() { return DNI; }
    public void setDNI(int DNI) { this.DNI=DNI; }

    public String toString() {
        return "Paciente #" + codigo + 
                " | Nombre: " + nombre + 
                " | Edad: " + edad + 
                " | DNI: " + DNI;
    }
}
