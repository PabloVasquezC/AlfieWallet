// Importaciones necesarias para la correcta ejecución del programa
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


// Clase Main
public class Main {


    // Función para crear el Id unico para cada usuario basado en la fecha de creación
    public static String generateUniqueID() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }


    // Método main
    public static void main(String[] args) {

        // ArrayList donde se iran almacenando los usuarios
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        // Creación del usuario "admin" con saldo inicial de $3,500,000
        Usuario admin = new Usuario(generateUniqueID(), "admin", "123abc", new AlfieWallet());

        // Depositando el saldo inicial
        admin.getWallet().depositar(3500000);

        // Añadiendo usuario al ArrayList usuarios
        usuarios.add(admin);

        // Inicialización del objeto scanner
        Scanner scanner = new Scanner(System.in);

        // Variable donde se guardara el indice del usuario
        int indiceUsuarioEjecutor = -1;


        // Mensaje de bienvenda
        System.out.println("");
        System.out.println("");
        System.out.println("Bienvenidoo(a) a su billetera digital :D");

        // variable flag del bucle general de la aplicación
        boolean flagBucleInicial = true;
        // Inicio del bucle
        while (flagBucleInicial) {

            // Mensaje de opciones
            System.out.println("A continuación ingresa la opción que deseas realizar");
            System.out.println("");
            System.out.println("1- Ingresar a billetera personal");
            System.out.println("2- Crear billetera personal");
            System.out.println("0- Salir");
            System.out.print("-> ");

            // Almacenando decision del usuario
            String opcionIngresarOCrear = scanner.nextLine();

            // Controlando el flujo de la aplicación con una sentencia switch
            switch (opcionIngresarOCrear) {
                // Controlando caso "1" (inicio de seción)
                case "1":
                    // Variable flag para controlar la existencia del usuario o la nueva yteración del subBluce
                    boolean flagSubBucleInicial = true;
                    // Inicio del sub bucle para ingresar a la aplicación
                    while(flagSubBucleInicial) {
                        System.out.print("Ingrese su nombre de usuario: ");
                        String nombreUsuario = scanner.nextLine();
                        System.out.print("Ingrese su contraseña: ");
                        String contrasena = scanner.nextLine();
                        boolean usuarioValido = false;

                        // Verificando la existencia del usuario en el Arraylist de usuarios
                        for (Usuario usuario : usuarios) {
                            if (usuario.getNombre().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)) {
                                usuarioValido = true;
                                indiceUsuarioEjecutor = usuarios.indexOf(usuario);
                                break;
                            }
                        }

                        // Acciones relacionadas con el correcto ingreso del usuario
                        if (usuarioValido) {
                            System.out.println(" ");
                            System.out.println("Usuario válido");
                            System.out.println(" ");
                            break;
                        }
                        // Acciones relacionas con el incorrecto ingreso del usuario
                        else {
                            System.out.println(" ");
                            System.out.println("Usuario o contraseña incorrectos");
                            System.out.println("Selecciona la acción que deseas realizar a continuación ");
                            System.out.println(" ");

                            // Preguntando al usuario si desea intentar ingresar otra vez o volver a atras
                            System.out.println("1- Volver a intentar");
                            System.out.println("2- Volver atras");
                            System.out.print(": ");
                            String continuidad = scanner.nextLine();

                            // control de flujo para determinar el flujo de la aplicación en base a su decisión
                            if (continuidad.equals("1")) {
                                flagSubBucleInicial = true;
                            } else {
                                break;
                            }
                        }
                    }
                    break;

                // Controlando caso "2" (creación de usuario)
                case "2":
                    System.out.print("Ingrese su nombre de usuario: ");
                    String nombreUsuario = scanner.nextLine();
                    System.out.print("Ingrese su contraseña: ");
                    String contrasena = scanner.nextLine();

                    // Creando nuevo usuario
                    Usuario nuevoUsuario = new Usuario(generateUniqueID(), nombreUsuario, contrasena, new AlfieWallet());
                    usuarios.add(nuevoUsuario);

                    System.out.println(" ");
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
                    // Menú de opciones
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
