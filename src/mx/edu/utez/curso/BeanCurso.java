package mx.edu.utez.curso;

public class BeanCurso {

    private int idCurso;
    private String nombre;
    private String fecha;
    private String descripcion;
    private String tipoCurso;

    public BeanCurso() {
    }

    public BeanCurso(int idCurso, String nombre, String fecha, String descripcion, String tipoCurso) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tipoCurso = tipoCurso;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }
}
