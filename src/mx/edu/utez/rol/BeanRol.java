package mx.edu.utez.rol;

public class BeanRol {

    private int idRol;
    private String tipo;

    public BeanRol() {
    }

    public BeanRol(int idRol, String tipo) {
        this.idRol = idRol;
        this.tipo = tipo;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
