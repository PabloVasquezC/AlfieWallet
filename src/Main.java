import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static String generateUniqueID() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }

    public static void main(String[] args) {

        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        // Crear el usuario "admin" con saldo inicial de $3,500,000
        Usuario admin = new Usuario(generateUniqueID(), "admin", "123abc", new AlfieWallet());
        admin.getWallet().depositar(3500000); // Depositar el saldo inicial
        usuarios.add(admin);

        Scanner scanner = new Scanner(System.in);
        int indiceUsuarioEjecutor = -1; // Cambiado para que si no se ingresa el usuario, no cause problemas

        boolean flagBucleInicial = true;
        while (flagBucleInicial) {
            System.out.println("Bienvenidoo(a) a su billetera digital :D");
            System.out.println("Acontinuación ingrese la opción que desea realizar");
            System.out.println("");
            System.out.println("1- Ingresar a billetera personal");
            System.out.println("2- Crear billetera personal");
            System.out.println("0- Salir");
            System.out.print("-> ");
            String opcionIngresarOCrear = scanner.nextLine();

            switch (opcionIngresarOCrear) {
                case "1":
                    boolean flagSubBucleInicial = true;
                    while(flagSubBucleInicial) {
                        System.out.print("Ingrese su nombre de usuario: ");
                        String nombreUsuario = scanner.nextLine();
                        System.out.print("Ingrese su contraseña: ");
                        String contrasena = scanner.nextLine();
                        boolean usuarioValido = false;

                        for (Usuario usuario : usuarios) {
                            if (usuario.getNombre().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)) {
                                usuarioValido = true;
                                indiceUsuarioEjecutor = usuarios.indexOf(usuario);
                                break;
                            }
                        }

                        if (usuarioValido) {
                            System.out.println(" ");
                            System.out.println("Usuario válido");
                            System.out.println(" ");
                            break;
                        } else {
                            System.out.println(" ");
                            System.out.println("Usuario o contraseña incorrectos");
                            System.out.println("Selecciona la acción que deseas realizar a continuación ");
                            System.out.println(" ");
                            System.out.println("1- Volver a intentar");
                            System.out.println("2- Volver atras");
                            System.out.print(": ");
                            String continuidad = scanner.nextLine();

                            if (continuidad.equals("1")) {
                                flagSubBucleInicial = true;
                            } else {
                                break;
                            }
                        }
                    }
                    break;

                case "2":
                    System.out.print("Ingrese su nombre de usuario: ");
                    String nombreUsuario = scanner.nextLine();
                    System.out.print("Ingrese su contraseña: ");
                    String contrasena = scanner.nextLine();

                    Usuario nuevoUsuario = new Usuario(generateUniqueID(), nombreUsuario, contrasena, new AlfieWallet());
                    usuarios.add(nuevoUsuario);

                    System.out.println("Nuevo usuario creado");
                    break;

                case "0":
                    flagBucleInicial = false;
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }

            if (indiceUsuarioEjecutor != -1) { // Solo si se ha seleccionado un usuario válido
                System.out.println("Bienvenido(a) " + usuarios.get(indiceUsuarioEjecutor).getNombre());

                int opcion;
                do {
                    System.out.println(" ____________________________________");
                    System.out.println("| Ingrese una opcion                 |");
                    System.out.println("| 0: para salir                      |");
                    System.out.println("| 1: para consultar saldo            |");
                    System.out.println("| 2: para depositar                  |");
                    System.out.println("| 3: para retirar                    |");
                    System.out.println("| 4: para convertir moneda (EUR/USD) |");
                    System.out.println("| 5: para obtener transacciones      |");
                    System.out.println(" ------------------------------------");
                    System.out.print("-> ");

                    opcion = Integer.parseInt(scanner.nextLine());

                    switch (opcion) {
                        case 0:
                            System.out.println("Cerrando Menu");
                            break;
                        case 1:
                            System.out.println(usuarios.get(indiceUsuarioEjecutor).getWallet().obtenerSaldo());
                            break;
                        case 2:
                            System.out.print("Ingrese el monto a depositar: $");
                            int monto = Integer.parseInt(scanner.nextLine());
                            System.out.println("Monto depositado: $" + monto);
                            usuarios.get(indiceUsuarioEjecutor).getWallet().depositar(monto);
                            break;
                        case 3:
                            System.out.print("Ingrese el monto a retirar");
                            int montoRetiro = Integer.parseInt(scanner.nextLine());
                            System.out.println("Monto retirado es : " + montoRetiro);
                            usuarios.get(indiceUsuarioEjecutor).getWallet().retirar(montoRetiro);
                            break;
                        case 4:
                            System.out.println("Ingrese al tipo de moneda al cual quiere convertir.");
                            System.out.println("1- Dolar");
                            System.out.println("2- Euro");
                            System.out.print(": ");
                            String aMoneda = scanner.nextLine();
                            usuarios.get(indiceUsuarioEjecutor).getWallet().convertirMoneda(usuarios.get(indiceUsuarioEjecutor).getWallet().obtenerSaldo(), aMoneda);
                            break;
                        case 5:
                            System.out.println(usuarios.get(indiceUsuarioEjecutor).getWallet().transacciones);
                    }
                } while (opcion != 0);
            }
        }
        scanner.close();
    }
}
