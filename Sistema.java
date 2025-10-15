import java.util.Scanner;
import java.util.ArrayList;

public class Sistema {
    private Scanner sc;
    private ArrayList<Doctores> lista_doctores;
    private ArrayList<Pacientes> lista_pacientes;
    private ArrayList<Citas> lista_citas;
    private boolean sesionActiva;

    public Sistema () {
        this.sc = new Scanner(System.in);
        this.lista_doctores = new ArrayList<>();
        this.lista_pacientes = new ArrayList<>();
        this.lista_citas = new ArrayList<>();
        this.sesionActiva = true;
    }
    public char bienvenida() {
        System.out.println("Bienvenido al sistema de citas médicas");
        System.out.println("¿Deseas comenzar? (s/n)");
        return sc.next().charAt(0);
    }
    
    public void iniciar(){
        sc.nextLine();
        while(sesionActiva) {
            System.out.println("--------------------------------");
            System.out.println("---------MENU PRINCIPAL---------");
            System.out.println("Seleccione una opcion:");
            System.out.println("1)Registrar datos\n2)Sacar una cita\n3)Lista de citas\n4)Cambiar estado de cita\n5)Registro de Sistema de citas\n0)Cerrar");
            String seleccion = sc.nextLine();
            switch (seleccion) {
                case "1":
                    System.out.println("--------------------------------");
                    System.out.println("-------MENU AGREGAR DATOS-------");
                    System.out.println("Seleccione una opcion:");
                    System.out.println("1)Registrarse como doctor\n2)Registrarse como paciente\n3)Volver");
                    String seleccion_1 = sc.nextLine();
                    switch (seleccion_1) {
                        case "1":
                            lista_doctores.add(new Doctores(sc));
                            System.out.println("Se logró registrar como doctor con éxito!.");
                            break;
                        case "2":
                            lista_pacientes.add(new Pacientes(sc));
                            System.out.println("Se logró registrar como paciente con éxito!.");
                            break;
                        case "3":
                            break;
                        default:
                            System.out.println("Opción ingresada no válida.\nPor favor ingresar uno de los números de la lista.");
                            break;
                    }
                    break;
                case "2":
                    System.out.println("--------------------------------");
                    System.out.println("-------SACANDO CITA-------");

                    lista_citas.add(new Citas(sc, lista_pacientes, lista_doctores, lista_citas));
                    sc.nextLine();
                case "3":
                    System.out.println("--------------------------------");
                    System.out.println("-------MENU LISTA DE CITAS-------");
                    System.out.println("Seleccione una opcion:");
                    System.out.println("1)Mostrar todas las citas programadas\n2)Mostrar Lista de citas por doctor\n3)Lista de citas por paciente\n4)Volver");
                    String seleccion_2 = sc.nextLine();
                    switch (seleccion_2) {
                        case "1":
                            System.out.println("Lista de Citas Programadas:");
                            int atendidas = 0, canceladas = 0;
                            for (Citas c : lista_citas) {
                                System.out.println(c);
                                if (c.getEstado().equalsIgnoreCase("Atendida")) atendidas++;
                                if (c.getEstado().equalsIgnoreCase("Cancelada")) canceladas++;
                            }
                            System.out.println("--------------------------------");
                            System.out.println("Número de citas atendidas: "+ atendidas);
                            System.out.println("Número de citas canceladas: "+ canceladas);
                            break;
                        case "2":
                            System.out.print("Ingrese el código del doctor: ");
                            int codDoc = sc.nextInt();
                            sc.nextLine();
                            for (Citas c : lista_citas) {
                                if (c.getDoctor().getCodigo() == codDoc) {
                                    System.out.println(c);
                                }
                            }
                            break;
                        case "3":
                            System.out.print("Ingrese el código del paciente: ");
                            int codPac = sc.nextInt();
                            sc.nextLine();
                            for (Citas c : lista_citas) {
                                if (c.getPaciente().getCodigo() == codPac) {
                                    System.out.println(c);
                                }
                            }
                            break;
                        case "4":
                            break;
                        default:
                            System.out.println("Opción ingresada no válida.\nPor favor ingresar uno de los números de la lista.");
                            break;
                    }
                    break;
                case "4":
                    System.out.print("Ingrese el código de la cita: ");
                    int codCita = sc.nextInt();
                    sc.nextLine();

                    for (Citas c : lista_citas) {
                        if (c.getCodigo() == codCita) {
                            System.out.println("Estado actual: " + c.getEstado());
                            System.out.println("Ingrese nuevo estado (Pendiente / Atendida / Cancelada): ");
                            String nuevoEstado = sc.nextLine();
                            c.setEstado(nuevoEstado);
                            System.out.println("Estado actualizado correctamente.");
                        }
                    }
                    break;
                case "5":
                    System.out.println("-----------------------------------");
                    System.out.println("---REGISTROS DEL SITEMA DE CITAS---");
                    System.out.println("Seleccione una opcion:");
                    System.out.println("1)Mostrar datos de doctores\n2)Mostras datos de pacientes\n3)Volver");
                    String seleccion_4 = sc.nextLine();
                    switch (seleccion_4) {
                        case "1":
                            for (Doctores d : lista_doctores) {
                                System.out.println(d);
                            }
                            break;
                        case "2":
                            for (Pacientes p : lista_pacientes) {
                                System.out.println(p);
                            }
                            break;
                        case "3":
                            break;
                        default:
                            System.out.println("Opción ingresada no válida.\nPor favor ingresar uno de los números de la lista.");
                            break;
                    }
                    break;
                case "0":
                    sesionActiva = false;
                    despedida();
                    break;
                default:
                    System.out.println("Opción ingresada no válida.\nPor favor ingresar uno de los números de la lista.");
                    break;
            }
        }
    }

    public void despedida(){
        System.out.println("Gracias por usar el sistema de citas médicas.");
        sc.close();
    }
}
