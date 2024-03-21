public interface Wallet {

    double obtenerSaldo();

    void depositar(double cantidad);

    void retirar(double cantidad);

    void convertirMoneda(double cantidad, String aMoneda);

}
