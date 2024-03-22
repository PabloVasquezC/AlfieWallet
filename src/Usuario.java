
public class Usuario {


    //atributos
    private String id;
    private String nombre;
    private String contrasena;
    private AlfieWallet wallet;


    //constructor vacio
//    public Usuario() {
//
//    }

    //constructor por parametros
    public Usuario(String id, String nombre, String contrasena, AlfieWallet wallet) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.wallet = new AlfieWallet();
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() { return contrasena; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public AlfieWallet getWallet() {
        return wallet;
    }

    public void setWallet(AlfieWallet wallet) {
        this.wallet = wallet;
    }

}
