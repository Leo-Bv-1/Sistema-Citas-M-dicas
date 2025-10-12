public class SistemaMedico {
    public static void main(String[] args) {
        Sistema sistema_de_citas = new Sistema();
        char continuar = sistema_de_citas.bienvenida();
        if (continuar == 's' || continuar == 'S') sistema_de_citas.iniciar();
        else sistema_de_citas.despedida();
    }
}
