//Importación de utilidades necesarias para la correcta ejecución del programa
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//Declaración de clase AlfieWallet
public class AlfieWallet implements Wallet {



    //Propiedades de la clase
    private double saldo;
    private List<String> transacciones = new ArrayList<>();


    //Instanciación de la clase Scanner
    Scanner scanner = new Scanner(System.in);


    //Sobreescritura e implementación de los metodos previamente definidos en la intefaz de Wallet
    @Override
    public double obtenerSaldo() {
        return saldo;
    }

    @Override
    public void retirar(double cantidad) {
        System.out.println("cantidad en AlfiWallet " + cantidad);
        if (cantidad > 0) { //&& saldo >= cantidad
            saldo -= cantidad;
            System.out.println("Saldo..." + obtenerSaldo());

            System.out.println("restando......" + saldo);

        } else {
            System.out.println("Ingrese un número mayor a 0 y menor o igual al saldo actual" + saldo);

        }
    }

    @Override
    public void convertirMoneda(double cantidad,  String aMoneda) {

        double cantidadConvertida;
        double tasaDeCambio;

        if(aMoneda.equals("1")) {
            tasaDeCambio =  0.0010204081632653;
            cantidadConvertida = cantidad * tasaDeCambio;
            System.out.println("Su saldo actual convertido a Dólares es aproximadamente: "+Math.round(cantidadConvertida)+"USD.");
        }

        if(aMoneda.equals("2")) {
            tasaDeCambio =  0.00094;
            cantidadConvertida = cantidad * tasaDeCambio;
            System.out.println("Su saldo actual convertido a Euros es aproximadamente: "+Math.round(cantidadConvertida)+"EUR.");
        }

    }

    @Override
    public void depositar(double cantidad) {

        saldo += cantidad; // 5000
        transacciones.add("Deposito: +" + saldo);


    }
}